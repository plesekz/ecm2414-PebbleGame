import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PebbleGame {
    ArrayList<Thread> threads;

    private class Player implements Runnable{
    	PebbleGame g;
        ArrayList<Integer> hand;
        ArrayList<BlackBag> availableBags;
        Boolean done;
        BufferedWriter output;
        BlackBag lastBagChosen;


        public Player(ArrayList<BlackBag> bagsFromFile, String Name, PebbleGame g) throws IOException {
            availableBags = bagsFromFile;
            Thread.currentThread().setName(Name);
            File file = new File("/" + Name + "_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            output = new BufferedWriter(fw);
            this.g = g;
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
            Message.append(Name);
            Message.append(" hand is ");
            for(Integer I: hand){
                Message.append(I);
                Message.append(", ");
            }
            try{
                output.write(Message.toString());
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
                String victoryMessage = Thread.currentThread().getName() + "has won the game!";
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
    
    public void game() {
    	ArrayList<BlackBag> bags = setUpBags();
    	int numberOfPlayers = getNumberOfPlayers();
        threads = new ArrayList<Thread>;
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();
    	
    	for(int i = 0; i<numberOfPlayers; i++) {
    		int tmp = i+1;
    		Player p = new Player(bags, "Player " +tmp, this);
    		runnables.add(p);
    	}
    	for(Runnable r: runnables) {
            Thread t = new Thread(r);
    		t.start();
            threads.add(t);
    	}
    }
    public void endOfGame() {
    	for(Thread t: threads) {
            t.interrupt();
    	}
    }
}
