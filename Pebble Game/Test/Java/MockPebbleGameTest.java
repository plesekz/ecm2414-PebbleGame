import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class MockPebbleGameTest {
    MockPebbleGame Game;

    /**
     * Function that runs prior to each test creating the necessary MockPebbleGame object for the suite tests to run
     */
    @Before
    public void setUp() {
        Game = new MockPebbleGame();

    }

    /**
     * Function that wipes the MockPebbleGame object after each test runs
     */
    @After
    public void tearDown() {
        Game = null;
    }


    @Test
    public void game() {
        ArrayList<String> s = new ArrayList<>();
        s.add("example_file_2.txt");
        s.add("example_file_2.txt");
        s.add("example_file_2.txt");
        Game.game(s, 3);
        fail("Not Implemented Yet");

    }

    /**
     * Function that wipes the MockPebbleGame object after each test runs
     */
    @Test
    public void endOfGame() {
        // TODO: REPEAT GAME MULTIPLE THOUSAND TIMES AND CHECK IF THERES ONLY ONE UNIQUE WINNER'
        ArrayList<String> s = new ArrayList<>();
        s.add("example_file_2.txt");
        s.add("example_file_2.txt");
        s.add("example_file_2.txt");

        for(int i=0; i<20; i++){

            Game.game(s, 3);
        }
        fail("Not Implemented Yet");
    }


    /**
     * Function that tests if BagZ is properly set up
     * Expected output is a BlackBag named Bag Z with a paired WhiteBag named Bag C and containing a hand of the integers 1-34
     */
    @Test
    public void testSetUpABagZ() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Store and set setUpABag method to be accessible outside the MockPebbleGame
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);

        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 2, 3, "example_file_4.csv");

        //Check that the blackBag is not null (Would occur if an exception was thrown in MockPebbleGame)
        assertNotNull(Game.getFailureCondition(), testBlackBag);

        //Check that the BlackBag name is bagZ and its pair is bag C
        assertEquals(testBlackBag.getName(), "bag Z", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag C", testBlackBag.getPairName());

        // Create lists of the contents of the pebbles inside example_file_4 and of the actual contents of the bag
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        LinkedList<Integer> actualContent = testBlackBag.getContent();
        // Because the contents will be randomized upon being inserted into the BlackBag we need to sort the content so that it is in ascending order again
        Collections.sort(actualContent);
        String both;

        // Iterate over the content in the bag and check if it matches the expected content
        for (int i=0; i<actualContent.size(); i++) {
            both = "currentActualPebble is " + actualContent.get(i) + "currentExpectedPebble is " + testContent[i];
            assertSame(both, actualContent.get(i), testContent[i]);
        }

    }

    /**
     * Function that tests if BagY is properly set up
     * Expected output is a BlackBag named Bag Y with a paired WhiteBag named Bag B and containing a hand of the integers 1-34
     */
    @Test
    public void testSetUpABagY() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Store and set setUpABag method to be accessible outside the MockPebbleGame
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);

        //Check that the blackBag is not null (Would occur if an exception was thrown in MockPebbleGame)
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 1, 3, "example_file_4.csv");

        //Check that the BlackBag name is bag Y and its pair is bag B
        assertNotNull(Game.getFailureCondition(), testBlackBag);
        assertEquals(testBlackBag.getName(), "bag Y", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag B", testBlackBag.getPairName());

        // Create lists of the contents of the pebbles inside example_file_4 and of the actual contents of the bag
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        LinkedList<Integer> actualContent = testBlackBag.getContent();
        // Because the contents will be randomized upon being inserted into the BlackBag we need to sort the content so that it is in ascending order again
        Collections.sort(actualContent);

        String both;

        // Iterate over the content in the bag and check if it matches the expected content
        for (int i=0; i<actualContent.size(); i++) {
            both = "currentActualPebble is " + actualContent.get(i) + "currentExpectedPebble is " + testContent[i];
            assertSame(both, actualContent.get(i), testContent[i]);
        }
    }

    /**
     * Function that tests if BagX is properly set up
     * Expected output is a BlackBag named Bag X with a paired WhiteBag named Bag A and containing a hand of the integers 1-34
     */
    @Test
    public void testSetUpABagX() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Store and set setUpABag method to be accessible outside the MockPebbleGame
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);

        //Check that the blackBag is not null (Would occur if an exception was thrown in MockPebbleGame)
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "example_file_4.csv");

        //Check that the BlackBag name is bag X and its pair is bag A
        assertNotNull(Game.getFailureCondition(), testBlackBag);
        assertEquals(testBlackBag.getName(), "bag X", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag A", testBlackBag.getPairName());

        // Create lists of the contents of the pebbles inside example_file_4 and of the actual contents of the bag
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        LinkedList<Integer> actualContent = testBlackBag.getContent();
        // Because the contents will be randomized upon being inserted into the BlackBag we need to sort the content so that it is in ascending order again
        Collections.sort(actualContent);
        String both;


        // Iterate over the content in the bag and check if it matches the expected content
        for (int i=0; i<actualContent.size(); i++) {
            both = "currentActualPebble is " + actualContent.get(i) + "currentExpectedPebble is " + testContent[i];
            assertSame(both, actualContent.get(i), testContent[i]);
        }
    }

    /**
     * Function that tests if the NotPositivePebblesException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the failure condition of INVALID INPUT!!! Not Positive Pebble Weight exception!!! being generated w
     */
    @Test
    public void testSetUpABagNegativePebble() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "negative_pebbles_1.csv");
        assertEquals(Game.getFailureCondition(), "INVALID INPUT!!! Not Positive Pebble Weight exception!!!", Game.getFailureCondition());
    }

    /**
     * Function that tests if the NotEnoughPebblesException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the failure condition of INVALID INPUT!!! Not a number!!! Not enough pebbles exception!!! being generated w
     */
    @Test
    public void testSetUpABagNotEnoughPebbles() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "not_enough_pebbles.csv");
        assertEquals(Game.getFailureCondition(), "INVALID INPUT!!! Not a number!!! Not enough pebbles exception!!!", Game.getFailureCondition());
    }

    /**
     * Function that tests if the NumberFormatException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the failure condition of INVALID INPUT!!! Not a number!!! being generated
     */
    @Test
    public void testSetUpABagBadFormatting() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "badly_formatted.csv");
        assertEquals(Game.getFailureCondition(), "INVALID INPUT!!! Not a number!!!", Game.getFailureCondition());
    }

    /**
     * Function that tests if the IOException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the failure condition of INVALID INPUT!!! Invalid File!! being generated
     */
    @Test
    public void tesSetUpABagInvalidFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "badly_fd.csv");
        assertEquals(Game.getFailureCondition(), "INVALID INPUT!!! Invalid File!!", Game.getFailureCondition());
    }

    @Test
    public void testSetUpBags() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        Method setUpBags = Game.getClass().getDeclaredMethod("setUpBags", int.class, ArrayList.class);
        setUpBags.setAccessible(true);

        ArrayList<String> s = new ArrayList<>();
        s.add("example_file_4.csv");
        s.add("example_file_4.csv");
        s.add("example_file_4.csv");
        ArrayList<BlackBag> testBlackBags = (ArrayList<BlackBag>) setUpBags.invoke(Game, 3, s );
        assertNotNull(Game.getFailureCondition(), testBlackBags.get(2));
        assertNotNull(Game.getFailureCondition(), testBlackBags.get(1));
        assertNotNull(Game.getFailureCondition(), testBlackBags.get(0));
        assertEquals("bag Z", testBlackBags.get(2).getName());
        assertEquals("bag C", testBlackBags.get(2).getPairName());
        assertEquals("bag Y", testBlackBags.get(1).getName());
        assertEquals("bag B", testBlackBags.get(1).getPairName());
        assertEquals("bag X", testBlackBags.get(0).getName());
        assertEquals("bag A", testBlackBags.get(0).getPairName());
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        String both;
        for (BlackBag b: testBlackBags) {
            LinkedList<Integer> actualContent = b.getContent();
            Collections.sort(actualContent);
            for (int i=0; i<actualContent.size(); i++) {
                both = "currentActualPebble is " + actualContent.get(i) + "currentExpectedPebble is " + testContent[i];
                assertSame(both, actualContent.get(i), testContent[i]);
            }
        }
    }

    @Test
    public void testGetNumberOfPlayers() throws NoSuchMethodException {
        Method getNumberOfPlayers = Game.getClass().getDeclaredMethod("getNumberOfPlayers", int.class);
        getNumberOfPlayers.setAccessible(true);
         fail("Not Implemented Yet");

    }

    @Test
    public void testGetNumberOfPlayersZero() throws NoSuchMethodException {
        Method getNumberOfPlayers = Game.getClass().getDeclaredMethod("getNumberOfPlayers", int.class);
        getNumberOfPlayers.setAccessible(true);
        fail("Not Implemented Yet");

    }

}