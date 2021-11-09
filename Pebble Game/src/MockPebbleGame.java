import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MockPebbleGame {
    ArrayList<Thread> threads;
    Scanner sc;
    String failureCondition;

    private class Player implements Runnable{
        MockPebbleGame g;
        ArrayList<Integer> hand;
        ArrayList<BlackBag> availableBags;
        Boolean done;
        BufferedWriter output;
        BlackBag lastBagChosen;


        public Player(ArrayList<BlackBag> bagsFromFile, String Name, MockPebbleGame g) throws IOException {
            availableBags = bagsFromFile;
            Thread.currentThread().setName(Name);
            File file = new File(Name + "_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            output = new BufferedWriter(fw);
            this.g = g;
            done = false;
            hand = new ArrayList<Integer>();
            Integer drawnPebble = null;
            for(int i = 0; i < 10; i++){
                lastBagChosen = availableBags.get(0);
                try {
                    drawnPebble = lastBagChosen.getPebble();
                    hand.add(drawnPebble);
                    break;
                } catch (EmptyBagException e) {
                    Collections.shuffle(availableBags);
                }
            }
            checkVictory();
        }
        public void run(){
            while(!done){
                //TODO - make this up to flag1 atomic
                //Handling discarding
                Integer discardedPebble = discardPebble();
                logDiscarded(discardedPebble);

                //Handling drawing
                Integer drawnPebble;
                //Integer drawnPebble = getPebble();
                Collections.shuffle(availableBags);
                while(true) {
                    lastBagChosen = availableBags.get(0);
                    try {
                        drawnPebble = lastBagChosen.getPebble();
                        break;
                    } catch (EmptyBagException e) {
                        Collections.shuffle(availableBags);
                    }
                }
                //flag1

                //handling adding to the hand
                hand.add(drawnPebble);
                logAdded(drawnPebble);

                //checking for victory
                checkVictory();
            }
        }

        /* Dunno, this is here as a curio, Haskell, I guess
        private Integer getPebble() {
        	Integer pebble = null;
        	lastBagChosen = availableBags.get(0);
        	try {
                lastBagChosen.getPebble();
            } catch (EmptyBagException e) {
                BlackBag hold = availableBags.remove(0);
                pebble = getPebble();
                availableBags.add(hold);
            }
        	return pebble;
        }
        */

        private Integer discardPebble(){
            Collections.shuffle(hand);
            return hand.remove(0);
        }

        private void logDiscarded(Integer discardedPebble){
            String Name = Thread.currentThread().getName();
            StringBuilder Message = new StringBuilder();
            Message.append(Name);
            Message.append(" has discarded a ");
            Message.append(discardedPebble);
            Message.append(" to ");
            Message.append(lastBagChosen.getPairName());
            Message.append(" ");
            Message.append(Name);
            Message.append(" hand is ");
            for(Integer I: hand){
                Message.append(I);
                Message.append(", ");
            }
            try{
                output.write(Message.toString());
                output.flush();
            }
            catch(IOException e){
                System.err.println(e);
            }
        }

        private void logAdded(Integer drawnPebble){
            String Name = Thread.currentThread().getName();
            StringBuilder Message = new StringBuilder();
            Message.append(Name);
            Message.append(" has drawn a ");
            Message.append(drawnPebble);
            Message.append(" from ");
            Message.append(lastBagChosen.getName());
            Message.append("/r/n");
            Message.append(Name);
            Message.append(" hand is ");
            for(Integer I: hand){
                Message.append(I);
                Message.append(", ");
            }
            try{
                output.write(Message.toString());
                output.flush();
            }
            catch(IOException e){
                System.err.println(e);
            }
        }

        private void checkVictory(){
            Integer currentTotal = 0;
            for(Integer i : hand){
                currentTotal += i;
            }
            if(currentTotal == 100){
                String victoryMessage = Thread.currentThread().getName() + " has won the game!";
                try{
                    output.write(victoryMessage);
                }
                catch(IOException e){
                    System.err.println(e);
                }
                System.out.println(victoryMessage);
                // Kill the other threads here
                g.endOfGame();
            }
        }
    }


    public static void main(String[] args) {
        PebbleGame g = new PebbleGame();
        g.game();
    }

    public void game(ArrayList<String> Strings, int y) {
        System.out.println(""
                + "Welcome to the PebbleGame!!\n"
                + "You will be asked to enter the umber of players.\n"
                + "and then for the location of three files in turn containing comma seperated integer values for the pebble weights.\n"
                + "The integer values must be strictly positive.\n"
                + "The game will then be simulated, and output written to files in this directory.");

        int numberOfPlayers = getNumberOfPlayers(y);
        ArrayList<BlackBag> bags = setUpBags(numberOfPlayers,  Strings);
        threads = new ArrayList<Thread>();
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        for(int i = 0; i<numberOfPlayers; i++) {
            int tmp = i+1;
            try {
                MockPebbleGame.Player p = new MockPebbleGame.Player(bags, "Player " +tmp, this);
                runnables.add(p);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        for(Runnable r: runnables) {
            Thread t = new Thread(r);
            t.start();
            threads.add(t);
        }
    }
    private ArrayList<BlackBag> setUpBags(int numberOfPlayers, ArrayList<String> Strings) {
        ArrayList<BlackBag> bgs = new ArrayList<BlackBag>();
        for(int i = 0; i<3; i++) {
            bgs.add(setUpABag(i, numberOfPlayers, Strings.get(i)));
        }
        return bgs;
    }

    private BlackBag setUpABag(int n, int numberOfPlayers, String s) {
        failureCondition = null;
        BlackBag b = null;
        Integer[] values = null;

        while(true) {
            System.out.println("Please enter the location of bag number "+n+" to load:");
            try {
                BufferedReader r = new BufferedReader(new FileReader(s));
                s = r.readLine();
                // store the content of the file in s
                r.close();
                break;
            } catch (IOException e) {
                failureCondition = "INVALID INPUT!!! Invalid File!!";
                return b;
            }
        }
        try {
            values = new Integer[s.split(",").length];
            if(values.length<11*numberOfPlayers) {
                throw new NotEnoughPebblesException();
            }
            for(int i = 0; i<s.split(",").length;i++) {
                values[i] = Integer.parseInt(s.split(",")[i]);
                if(values[i]<0) throw new NotPositivePebbleWeightException();
            }
        } catch (NumberFormatException e) {
            failureCondition = "INVALID INPUT!!! Not a number!!!";
            return b;
        } catch (NotEnoughPebblesException e) {
            failureCondition = "INVALID INPUT!!! Not a number!!! Not enough pebbles exception!!!";
            return b;
        } catch (NotPositivePebbleWeightException e) {
            failureCondition = "INVALID INPUT!!! Not Positive Pebble Weight exception!!!";
            return b;
        }
        char black = (char) ('Z'-2+n);
        char white = (char) ('A'+ n);
        b = new BlackBag(values, "bag "+ black, "bag "+white);
        return b;
    }

    public String getFailureCondition(){
        return failureCondition;
    }
    private int getNumberOfPlayers(int p) {
        while(true) {
            System.out.println("Please enter the number of players:\n");
            if(p>0) break;
        }
        return p;
    }

    public void endOfGame() {
        for(Thread t: threads) {
            t.interrupt();
            System.exit(0);
        }
    }
}
