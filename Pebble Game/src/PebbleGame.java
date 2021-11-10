import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A program to simulate PebbleGame™.
 * Create an instance and call its game() method to run.
 * The application utilises console for IO handling.
 * @author [REDACTED] and [REDACTED]
 *
 */
public class PebbleGame {
	/**
	 * An ArrayList holding pointers to the Thread objects representing individual players.
	 */
    private ArrayList<Thread> threads;
    
    /**
     * A Scanner instance which is used to read the console input.
     */
    Scanner sc;
    
    /**
     * An inner private class which instances represent individual players
     * in the multi-threaded PebbleGame™.
     * @author [REDACTED] and [REDACTED]
     *
     */
    private class Player implements Runnable{
    	
    	/**
    	 * Holds a pointer to the game running.
    	 */
    	private PebbleGame g;
    	
    	/**
    	 * The players hand.
    	 * It is a LinkedList because of the frequent removal and addition.
    	 * It would be possible to implement more effective data structure,
    	 * but the effort investment is not worth the increase in efficiency.
    	 */
        private LinkedList<Integer> hand;
        
        /**
         * Holds the pointers to the (paired) Bags available for drawing and depositing.
         * Is an arraylist because the size never changes, but the objects are accessed frequently.
         */
        private ArrayList<BlackBag> availableBags;
        
        /**
         * Holds the output Buffered Writer.
         * Given the frequent flushing, to ensure that the output is written even if the
         * game finishes, buffering present only a little benefit.
         */
        private BufferedWriter output;
        
        /**
         * Holds a pointer to the last bag drawn from.
         * Given the ubiquitous use, it is useful to be held as an object property.
         */
        private BlackBag lastBagChosen;
        
        /**
         * Thread's (and Player's) name.
         */
        private String name;

        /**
         * Constructor for a player instance.
         * Use polymorphism to treat as a Runnable, and wrap in a Thread to start.
         * @param bagsFromFile
         * An ArraList holding the pointers to all (paired) bags that the player is to interact with.
         * @param name
         * String representing the player's (and thread's) name.
         * Also governs the output file's name. Which has the "[name]_output.txt" form.
         * @param g
         * A pointer to the PebbleGame instance this player belongs to.
         * @throws IOException
         * In case the output files cannot be created.
         */
        public Player(ArrayList<BlackBag> bagsFromFile, String name, PebbleGame g) throws IOException {
            //establish variables
        	availableBags = bagsFromFile;
            this.name = name;
            this.g = g;
            
            //create file writer and the output file, if needed
            Boolean newGame = true;
            File file = new File(name + "_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            } else {
            	newGame = false;
            }
            FileWriter fw = new FileWriter(file);
            output = new BufferedWriter(fw);
            
            //First write
            if(!newGame) {
            	output.append("\n\n");
            }
            output.append("Game begins!\n\n");
            
            //Establish initial the hand
            hand = new LinkedList<Integer>();
            Integer drawnPebble = null;
            for(int i = 0; i < 10; i++){
            	while(true) {
            		Collections.shuffle(availableBags);
                	lastBagChosen = availableBags.get(0);
                	try {
                    	drawnPebble = lastBagChosen.getPebble();
                    	hand.add(drawnPebble);
                    	break;
                	} catch (EmptyBagException e) {
                    	
                	}
            	}
            }
            //check whether the initial hand is a victorious one
            checkVictory();
        }
        /**
         * The main body of the thread.
         * Will keep executing until interrupted.
         */
        public void run(){
        	//rename the thread so that the name matches the player's name.
        	Thread.currentThread().setName(name);
        	
        	//establish variables and their scope
        	Integer drawnPebble = null;
        	Integer discardedPebble = null;
        	//the main loop
            while(true){
            	//the act of drawing and discarding is made into an atomic action through synchronisation.
            	synchronized (Player.class){
            		//Handling discarding
            		discardedPebble = discardPebble();
            		lastBagChosen.putInPebble(discardedPebble);
            		
            		//Handling drawing
            		Collections.shuffle(availableBags);
            		//will be left only if the drawing from a bag doesn't trigger an exception
            		while(true) {                	
            			lastBagChosen = availableBags.get(0);
                		try {
                			drawnPebble = lastBagChosen.getPebble();
                			break;
                		} catch (EmptyBagException e) {
                			//Will enter this block if pebble drawing failed because the bag was empty
                			Collections.shuffle(availableBags);
                		}
                	}
            	}
                //handling adding to the hand
                hand.add(drawnPebble);
                
                //handling logging
                logDiscarded(discardedPebble);
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

        /**
         * Function that handles discarding a pebble from the hand.
         * It will remove a random Integer from the hand, and return it.
         * @return Integer, the removed pebble.
         */
        private Integer discardPebble(){
            Collections.shuffle(hand);
            return hand.remove(0);
        }

        /**
         * A function to automate logging of discarded pebbles.
         * @param discardedPebble
         * Integer representing the value of the discarded pebble.
         */
        private void logDiscarded(Integer discardedPebble){
            String Name = Thread.currentThread().getName();
            StringBuilder Message = new StringBuilder();
            Message.append(Name);
            Message.append(" has discarded a ");
            Message.append(discardedPebble);
            Message.append(" to ");
            Message.append(lastBagChosen.getPairName());
            Message.append(".\n");
            try{
                output.write(Message.toString());
                output.flush();
            }
            catch(IOException e){
                System.err.println(e);
            }
        }

        /**
         * A function to automate logging of the pebble drawn.
         * @param drawnPebble
         * An integer representing the value of the drawn pebble.
         */
        private void logAdded(Integer drawnPebble){
            String Name = Thread.currentThread().getName();
            StringBuilder Message = new StringBuilder();
            Message.append(Name);
            Message.append(" has drawn a ");
            Message.append(drawnPebble);
            Message.append(" from ");
            Message.append(lastBagChosen.getName());
            Message.append("\n");
            Message.append(Name);
            Message.append(" hand is ");
            Message.append("[");
            for(Integer I: hand){
            	Message.append(" ");
                Message.append(I);
                Message.append(",");
            }
            Message.append("]\n");
            try{
                output.write(Message.toString());
                output.flush();
            }
            catch(IOException e){
                System.err.println(e);
            }
        }

        /**
         * A function that checks whether the current hand is a winning hand.
         * If the check passes, the function will end the game by calling
         * endOfGame method on the assigned instance of PebbleGame.
         */
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

    /**
     * Main method.
     * @param args
     * passed from the console.
     */
    public static void main(String[] args) {
    	PebbleGame g = new PebbleGame();
    	g.game();
    }
    /**
     * The main body of the PebbleGame.
     */
    public void game() {
    	sc = new Scanner(System.in);
        threads = new ArrayList<Thread>();
        
    	//welcome message, compiler will optimise formatting
    	System.out.println(""
    			+ "Welcome to the PebbleGame!!\n"
    			+ "You will be asked to enter the umber of players.\n"
    			+ "and then for the location of three files in turn containing comma seperated integer values for the pebble weights.\n"
    			+ "The integer values must be strictly positive.\n"
    			+ "The game will then be simulated, and output written to files in this directory.");
    	
    	//gets number of players and list of bags
    	int numberOfPlayers = getNumberOfPlayers();
    	ArrayList<BlackBag> bags = setUpBags(numberOfPlayers, 3);    	

    	//creates the individual threads
    	for(int i = 0; i<numberOfPlayers; i++) {
    		int tmp = i+1;
    		try {
    			Runnable r = new Player(bags, "Player " +tmp, this);
    			Thread t = new Thread(r);
    			threads.add(t);
    		} catch (IOException e) {
    			System.err.println(e);
    		}
    	}
    	
    	//starts the individual threads, the for is duplicated so that the players have as equal start as possible
    	for(Thread t: threads) {
    		t.start();    
    	}
    	System.out.println("The game is running.");
    	
    	//Allows to end the program by typing 'E' or 'e' into the console.
    	String s = null;
    	while(true) {
    		s = sc.nextLine();
    		if(s.equals("E")||s.equals("e")) endOfGame();
    	}
    }
    /**
     * A function to automate setting up (paired) bags for the game.
     * @param numberOfPlayers
     * int representing the number of players in the game, important for checking the bag size
     * @param numberOfBags
     * int representing the number of (paired) bags to be set up.
     * @return
     * ArrayList<BlackBag> holding all the (black) bags to be used in the game.
     * The associated white bags are held within the black ones.
     */
    private ArrayList<BlackBag> setUpBags(int numberOfPlayers, int numberOfBags) {
		ArrayList<BlackBag> bgs = new ArrayList<BlackBag>();
        //sc.nextLine(); //used to move the reader to the eof of the console
		for(int i = 0; i<numberOfBags; i++) {
			bgs.add(setUpABag(i, numberOfPlayers));
		}
		return bgs;
	}
    
    /**
     * Method to get the contents of a file corresponding to a bag N.
     * @param numberOfTheBag
     * An int represnting the number of the bag.
     * @return
     * A String representing the content o the file.
     */
    private String getContentsOfAFile(int numberOfTheBag) {
    	String s = null;    	
    	while(true) {
			System.out.println("Please enter the location of bag number "+numberOfTheBag+" to load:");
			try {
				s = sc.nextLine();
				if(s.equals("E")||s.equals("e")) kill();
                BufferedReader r = new BufferedReader(new FileReader(s));
                s = r.readLine();
				// store the content of the file in s
                r.close();
				break;
			} catch (IOException e) {
                System.out.println("INVALID INPUT!!!");
                System.out.println("Invalid File!!");				
			}
		}
    	return s;
    }
    
    /**
     * A method to parse an input String into an Integer[]
     * @param s
     * The String to be parsed.
     * @param numberOfPlayers
     * An int representing the number of players in the game.
     * @return
     * An Integer[] array holding the weights of all pebbles in the file.
     * @throws CannotParseException
     * If the content is for some reason invalid.
     * Consult the IO for details.
     */
    private Integer[] parseContent(String s, int numberOfPlayers) throws CannotParseException {
    	Integer[] values;
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
			System.out.println("INVALID INPUT!!!");
			System.out.println("Not a number!!!");
			throw new CannotParseException();
		} catch (NotEnoughPebblesException e) {
			System.out.println("INVALID INPUT!!!");
			System.out.println("Not enough pebbles exception!!!");
			throw new CannotParseException();
		} catch (NotPositivePebbleWeightException e) {
			System.out.println("INVALID INPUT!!!");
			System.out.println("Not Positive Pebble Weight exception!!!");
			throw new CannotParseException();
		}
    	return values;
    }
    
    /**
     * Internal function to set up a single BlackBag (and accompanied WhiteBag held within.)
     * Function calls itself recursively if setting up of the BlackBag fails because of
     * an improper file.
     * @param n
     * int representing the number of the bag.
     * @param numberOfPlayers
     * int representing the number of players in game, important for checking the integrity of the input.
     * @return
     * The set up BlackBag.
     */
	private BlackBag setUpABag(int n, int numberOfPlayers) {
		BlackBag b = null;
		String s;
		Integer[] values = null;

		//will keep trying to read a file
		s = getContentsOfAFile(n);
		
		//tries to parse the contents of the file read previously
		try {
			values = parseContent(s, numberOfPlayers);
		}catch (CannotParseException e) {
			b = setUpABag(n, numberOfPlayers);
		}

		return bagFactory(values, n);
	}

	private BlackBag bagFactory(Integer[] values, int n){
		char black = (char) ('Z'-2+n);
		char white = (char) ('A'+ n);

		//creates the (paired) bag(s)
		BlackBag b = new BlackBag(values, "bag "+ black, "bag "+white);

		return b;
	}

	/**
	 * Function to automate entering the number of players in game.
	 * @return
	 * An int representing the number of players in game.
	 */
	private int getNumberOfPlayers() {
		int p = 0;
		while(true) {
			System.out.println("Please enter the number of players:\n");
			try {
				String s = sc.nextLine();
				if(s.equals("E")||s.equals("e")) kill();
				p = Integer.parseInt(s);
			} catch (Exception e) {
				System.out.println("Not a number.");
			}
			if(p>0) break;
		}
		return p;
	}
	/**
	 * Private method that is (usually) called from the nested inner class Player
	 * when victory condition is met.
	 */
	private void endOfGame() {
    	for(Thread t: threads) {
            t.interrupt();
            kill();
    	}
    }
	/**
	 * A method to separate the System.exit() call for testing purposes.
	 */
	private void kill() {
		System.exit(0);
	}
}
