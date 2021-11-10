import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * Testing class for the Player nested class of the PebbeGame class
 * Provides a suite of unit tests meant to ensure that the Player Class methods work as expected
 * @author [REDACTED] and [REDACTED]
 */
public class PlayerTest {

    /**
     * Pointer to the parent PebbleGame of the nested Player Object that the test methods operate on
     */
    private PebbleGame testGame;
    /**
     * Pointer to the Player Object that the test methods operate on
     */
    private Object playerObject;


    /**
     * Function that runs prior to each test generating the necessary objects (testGame and playerObject) for the suite tests to run
     */
    @Before
    public void setUp() throws Exception {

        //Create the testGame PebbleGame
        PebbleGame Game = new PebbleGame();
        testGame = Game;



        // Store the constructor for the nested player class and make it accessible outside of PebbleGame (it would normally not be as a private class)
        Class Player = testGame.getClass().getDeclaredClasses()[0];
        Constructor playerConstructor = Player.getDeclaredConstructors()[0];
        playerConstructor.setAccessible(true);

        // Make a set of identical Blackbags to later insert into PlayerObject
        Integer[] defaultPebbles = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};
        ArrayList<BlackBag> bagArrayList = new ArrayList<>();
        BlackBag defaultBag = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag);
        BlackBag defaultBag1 = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag1);
        BlackBag defaultBag2 = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag2);

        // Generate playerObject using the constructor method and the array of bags
        playerObject = playerConstructor.newInstance(Game, bagArrayList, "defaultPlayer", Game);
    }

    /**
     * Function to destroy all objects held in the class (testGame, playerObject) after each test runs so that each test is independent of any prior tests
     */
    @After
    public void tearDown() {
        testGame = null;
        playerObject = null;
    }

    /**
     * Function to destroy all objects held in the class (testWhiteBag, testBlackBag) after each test runs so that each test is independent of any prior tests
     */
    @Test
    public void testDiscardPebbleOnePebble() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Field handField = playerObject.getClass().getDeclaredField("hand");
        handField.setAccessible(true);
        LinkedList<Integer> defaultHand = new LinkedList<>();
        defaultHand.add(1);
        handField.set(playerObject, defaultHand);
        Method discardPebbleMethod = playerObject.getClass().getDeclaredMethod("discardPebble");
        discardPebbleMethod.setAccessible(true);
        Integer i = (Integer) discardPebbleMethod.invoke(playerObject);
        assertEquals(1, (int) i);
        assertEquals(0, defaultHand.size());
    }

    /**
     * Function to check if pebbles are discarded uniformly at random when invoking the discardPebble method
     * Expected outcome is all three pebbles being returned roughly the same amount of times after 10000 iteratioms
     */
    @Test
    public void testDiscardPebbleRandomness() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        // Initialize the Integer I and the hit counters
        Integer i;
        int hit1 = 0;
        int hit2 = 0;
        int hit3 = 0;

        // Store and set the hand field of PlayerObject to be accessible outside PebbleGame
        Field handField = playerObject.getClass().getDeclaredField("hand");
        handField.setAccessible(true);


        // Create a default hand consisting of 3 pebbles and then set the player hand to be that linked list
        LinkedList<Integer> defaultHand = new LinkedList<Integer>();
        defaultHand.add(1);
        defaultHand.add(2);
        defaultHand.add(3);
        handField.set(playerObject, defaultHand);


        // Store and set the discardPebble method of the playerObject to be accessible outside of the private playerObject
        Method discardPebbleMethod = playerObject.getClass().getDeclaredMethod("discardPebble");
        discardPebbleMethod.setAccessible(true);

        for(int o = 0; o<10000; o++){
            // Invoke the discard Pebble method and store the result in i
            i = (Integer) discardPebbleMethod.invoke(playerObject);

            // Increase the corresponding hit counter depending on what number is stored in i
            if( i == 1){
                hit1++;
            }
            else if(i == 2){
                hit2++;
            }
            else if(i == 3){
                hit3++;
            }

            // if a number that should not be in the hand is returned by the hand method the test should fail
            else {
                fail("pebble not in hand accessed");
            }


            // Reset the PlayerObject hand by inputting a new default hand
            defaultHand = new LinkedList<Integer>();
            defaultHand.add(1);
            defaultHand.add(2);
            defaultHand.add(3);
            handField.set(playerObject, defaultHand);
            }

        // Check if each pebble has been discarded roughly the same amount of times
        assertEquals(3333, hit1, 100);
        assertEquals(3333, hit2, 100);
        assertEquals(3333, hit3, 100);
    }

    /**
     * Function to check if the correct lines are written when the logDiscarded method is invoked
     * Expected outcome is:
     * Line 1: main has discarded a 2 to defaultPairedBag
     * Line 2: main hand is [ 1,]
     */
    @Test
    public void testLogDiscarded() throws IOException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Integer o = 2;


        //Create a default hand, add one to it
        LinkedList<Integer> defaultHand = new LinkedList<>();
        defaultHand.add(1);

        // Store and set the logDiscarded method to be accessible outside the class and then invoke it with 2 as the pebble that has been discarded from playerObject
        // Also invoke it with defaultHand as the old hand
        Method logDiscardMethod = playerObject.getClass().getDeclaredMethod("logDiscarded", Integer.class, LinkedList.class);
        logDiscardMethod.setAccessible(true);
        logDiscardMethod.invoke(playerObject,  o, defaultHand);

        String strCurrentLine ;
        ArrayList<String> fileStrings = new ArrayList<>();

        // Iterate over the file that logDiscarded wrote on and store all the lines in an array of strings
        BufferedReader Reader = new BufferedReader(new FileReader("defaultPlayer_output.txt"));
        while ((strCurrentLine = Reader.readLine()) != null) {
            fileStrings.add(strCurrentLine);
        }

        int size = fileStrings.size();
        assertEquals("main has discarded a 2 to defaultPairedBag.", fileStrings.get(size - 2));
        assertEquals("main hand is [ 1,]", fileStrings.get(size - 1));


    }

    /**
     * Function to check if the correct lines are written when the logAdded method is invoked
     * Expected outcome is:
     * Line 1: main has drawn a 2 from defaultBag
     * Line 2: main hand is [ 1,]
     */
    @Test
    public void testLogAdded() throws IOException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Integer o = 2;

        // Store and set the hand field of the playerObject to be accessible out of the private Player class
        Field handField = playerObject.getClass().getDeclaredField("hand");
        handField.setAccessible(true);

        // Create a default hand, add one to it and set the handField to be it
        LinkedList<Integer> defaultHand = new LinkedList<>();
        defaultHand.add(1);
        handField.set(playerObject, defaultHand);

        // Store and set the logAdded method to be accessible outside the class and then invoke it with 2 as the pebble that has been added to the hand
        Method logAddedMethod = playerObject.getClass().getDeclaredMethod("logAdded", Integer.class);
        logAddedMethod.setAccessible(true);
        logAddedMethod.invoke(playerObject,  o);

        String strCurrentLine ;
        ArrayList<String> fileStrings = new ArrayList<>();

        // Iterate over the file that logAdded wrote on and store all the lines in an array of strings
        BufferedReader Reader = new BufferedReader(new FileReader("defaultPlayer_output.txt"));
        while ((strCurrentLine = Reader.readLine()) != null) {
            fileStrings.add(strCurrentLine);
        }

        int size = fileStrings.size();
        assertEquals("main has drawn a 2 from defaultBag.", fileStrings.get(size - 2));
        assertEquals("main hand is [ 1,]", fileStrings.get(size -1));
    }


}
