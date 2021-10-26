import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PebbleGame {


    private class Player implements Runnable{
        ArrayList<Integer> hand;
        ArrayList<BlackBag> availableBags;
        Boolean done;
        BufferedWriter output;
        BlackBag lastBagChosen;


        public Player(ArrayList<BlackBag> bagsFromFile, String Name) throws IOException {
            availableBags = bagsFromFile;
            Thread.currentThread().setName(Name);
            File file = new File("/" + Name + "_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            output = new BufferedWriter(fw);
        }
        public void run(){
            while(!done){
                Integer discardedPebble = discardPebble();
                logDiscarded(discardedPebble);
                Collections.shuffle(availableBags);
                lastBagChosen = availableBags.get(0);
                Integer drawnPebble = null;
                try {
                    drawnPebble = lastBagChosen.getPebble();
                } catch (EmptyBagException e) {
                    e.printStackTrace();
                }
                hand.add(drawnPebble);
                logAdded(drawnPebble);
                logHand();
                checkVictory();
            }
        }

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
                Message.append(i);
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

        private void logHand(){

        }

        private void checkVictory(){

        }
    }


    public static void main(String[] args) {



    }
}
