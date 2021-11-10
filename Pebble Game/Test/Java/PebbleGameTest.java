import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Testing class for the PebbeGame class
 * Provides a suite of unit tests meant to ensure that the PebbleGame methods work as expected
 * @author [REDACTED] and [REDACTED]
 */
public class PebbleGameTest {
    PebbleGame Game;

    /**
     * Function that runs prior to each test creating the necessary MockPebbleGame object for the suite tests to run
     */
    @Before
    public void setUp() {
        Game = new PebbleGame();
    }

    /**
     * Function that wipes the MockPebbleGame object after each test runs
     */
    @After
    public void tearDown() {
        Game = null;
    }


    /**
     * Function that tests if BagZ is properly set up
     * Expected output is a BlackBag named Bag Z with a paired WhiteBag named Bag C and containing a hand of the integers 1-34
     */
    @Test
    public void testSetUpABagZ() throws  NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Store and set bagFactory method to be accessible outside the MockPebbleGame
        Method bagFactory = Game.getClass().getDeclaredMethod("bagFactory", Integer[].class, int.class);
        bagFactory.setAccessible(true);

        // Create an array of the pebbles to be inserted into the bag constructor and invoke the method with it as an argument
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        BlackBag testBlackBag = (BlackBag) bagFactory.invoke(Game, testContent, 2);


        //Check that the BlackBag name is bagZ and its pair is bag C
        assertEquals(testBlackBag.getName(), "bag Z", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag C", testBlackBag.getPairName());

        LinkedList<Integer> actualContent = testBlackBag.getContent();
        // Reset test content to make sure it remains independent of the actual content of the bag
        testContent = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};

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

        // Store and set bagFactory method to be accessible outside the MockPebbleGame
        Method bagFactory = Game.getClass().getDeclaredMethod("bagFactory", Integer[].class, int.class);
        bagFactory.setAccessible(true);

        // Create an array of the pebbles to be inserted into the bag constructor and invoke the method with it as an argument
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        BlackBag testBlackBag = (BlackBag) bagFactory.invoke(Game, testContent, 1);

        //Check that the BlackBag name is bag Y and its pair is bag B
        assertEquals(testBlackBag.getName(), "bag Y", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag B", testBlackBag.getPairName());

        LinkedList<Integer> actualContent = testBlackBag.getContent();

        // Reset test content to make sure it remains independent of the actual content of the bag
        testContent = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};

        // Because the contents of the bag will be randomized upon being inserted into the BlackBag we need to sort the content so that it is in ascending order again
        Collections.sort(actualContent);

        String both;

        // Iterate over the content in the bag and check if it matches the test content
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

        // Store and set bagFactory method to be accessible outside the MockPebbleGame
        Method bagFactory = Game.getClass().getDeclaredMethod("bagFactory", Integer[].class, int.class);
        bagFactory.setAccessible(true);

        // Create an array of the pebbles to be inserted into the bag constructor and invoke the method with it as an argument
        Integer[] testContent = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        BlackBag testBlackBag = (BlackBag) bagFactory.invoke(Game,  testContent, 0);


        //Check that the BlackBag name is bag X and its pair is bag A
        assertEquals(testBlackBag.getName(), "bag X", testBlackBag.getName());
        assertEquals(testBlackBag.getPairName(), "bag A", testBlackBag.getPairName());

        LinkedList<Integer> actualContent = testBlackBag.getContent();

        // Because the contents will be randomized upon being inserted into the BlackBag we need to sort the content so that it is in ascending order again
        Collections.sort(actualContent);
        String both;

        // Reset test content to make sure it remains independent of the actual content of the bag
        testContent = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};

        // Iterate over the content in the bag and check if it matches the expected content
        for (int i=0; i<actualContent.size(); i++) {
            both = "currentActualPebble is " + actualContent.get(i) + "currentExpectedPebble is " + testContent[i];
            assertSame(both, actualContent.get(i), testContent[i]);
        }
    }


    /**
     * Function that tests if the NotPositivePebblesException is properly thrown and caught by the setUpABag method's logic
     * This is for the case where the negative pebble is in the middle of the array
     * Expected output is the CannotParseException being thrown
     */
    @Test
    public void testParseContentNegativePebbleMiddleOfArray() throws NoSuchMethodException {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "1,2,-3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception = assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }

    /**
     * Function that tests if the NotPositivePebblesException is properly thrown and caught by the setUpABag method's logic
     * This is for the case where the negative pebble is at the start of the array
     * Expected output is the CannotParseException being thrown
     */
    @Test
    public void testParseContentNegativePebbleStartOfArray() throws NoSuchMethodException {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "-1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception =  assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }

    /**
     * Function that tests if the NotPositivePebblesException is properly thrown and caught by the setUpABag method's logic
     * This is for the case where the negative pebble is at the end of the array
     * Expected output is the CannotParseException being thrown
     */
    @Test
    public void testParseContentNegativePebbleEndOfArray() throws NoSuchMethodException {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,-40";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception =assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }


    /**
     * Function that tests if the NotEnoughPebblesException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the CannotParseException being thrown
     */
    @Test
    public void testSetUpABagNotEnoughPebbles() throws NoSuchMethodException  {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "1,2, 3, 4";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception =  assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }

    /**
     * Function that tests if the NumberFormatException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the CannotParseException being thrown
     * This is for the case where the non-number is in the middle of the array
     */
    @Test
        public void testSetUpABagBadFormattingMiddleOfArray() throws NoSuchMethodException  {
            Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
            parseContent.setAccessible(true);
        String s = "1,2,3,4,5,6,7,8,9,10,11,twenty bajillion,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception = assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
        }

    /**
     * Function that tests if the NumberFormatException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the CannotParseException being thrown
     * This is for the case where the non-number is at the start of the array
     */
    @Test
    public void testSetUpABagBadFormattingStartOfArray() throws NoSuchMethodException {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "twenty trillion,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception = assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }

    /**
     * Function that tests if the NumberFormatException is properly thrown and caught by the setUpABag method's logic
     * Expected output is the CannotParseException being thrown
     */
    @Test
    public void testSetUpABagBadFormattingEndOfArray() throws NoSuchMethodException  {
        Method parseContent = Game.getClass().getDeclaredMethod("parseContent", String.class, int.class);
        parseContent.setAccessible(true);
        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40, twenty bajillion";

        // Check to see if the InvocationTargetException is wrapping a CannotParseException
        // This would mean that the CannotParseException has been thrown as expected
        Exception exception =  assertThrows(InvocationTargetException.class, () -> parseContent.invoke(Game, s, 3));
        assertEquals(CannotParseException.class, exception.getCause().getClass());
    }


}