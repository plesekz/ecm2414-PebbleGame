import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Testing class for the BlackBag class of the Pebble suite
 * Provides a suite of unit tests meant to insure that the BlackBag methods work as expected
 * @author [REDACTED] and [REDACTED]
 */
public class BlackBagTest {

    /**
     * pointer to the BlackBag that the tests operate on during testing
     */
    private BlackBag testBlackBag;
    /**
     * pointer to the paired WhiteBag of the testing BlackBag
     */
    private WhiteBag testWhiteBag;


    /**
     * Function that runs prior to each test creating the necessary objects (testBlackBag and testWhiteBag) for the suite tests to run
     */
    @Before
    public void setUp() {
        Integer[] pebbles = {9};
        testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        testWhiteBag = testBlackBag.getPairedBag();
    }

    /**
     * Function to destroy all objects held in the class (testWhiteBag, testBlackBag) after each test runs so that each test is independent of any prior tests
     */
    @After
    public void tearDown() {
        testBlackBag = null;
        testWhiteBag = null;
    }

    /**
     * Function to test the GetPebble method of the BlackBag class for the situation where the BlackBag is empty
     * This should throw an EmptyBagException
     */
    @Test
    public void testGetPebbleEmpty() {

        // Create empty BlackBag
        Integer[] pebbles = new Integer[0];
        testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");


        // Check if empty bag exception is thrown
        try{
            testBlackBag.getPebble();
            fail("Empty bag exception should have been thrown");
        }
        catch (EmptyBagException e){
        }

    }

    /**
     * Function to test the getPebble method of the BlackBag class for the case where only one pebble is contained in the bag
     * Expected output is the pebble inside being returned by the function
     */
    @Test
    public void testGetPebbleOnePebble() throws EmptyBagException {
        assertEquals(9, (int) testBlackBag.getPebble());
    }

    /**
     * Function to check if the GetPebble method successfully empties itself upon reaching one pebble and then returns its contents upon the next getPebble
     * Expected output is that getPebble returns one of the two pebbles placed into the BlackBag
     */
    @Test
    public void testGetPebbleMultiplePebbles() throws EmptyBagException {


        testBlackBag.putInPebble(4);
        testBlackBag.putInPebble(5);

        // Empty 9 from BlackBag and insert 4, 5 from WhiteBag into BlackBag
        testBlackBag.getPebble();

        Integer pebble = testBlackBag.getPebble();
        assertTrue(pebble == 4 || pebble == 5);
    }

    /**
     * Function to test the randomness of the getPebble method of the BlackBag Class by using a BlackBag with three pebbles
     * Expected result is an equal distribution of all three pebbles across the 10000 pebbles we store from the BlackBag
     * This is the case as we expect uniform randomness
     */
    @Test
    public void testGetPebbleRandomness() throws EmptyBagException {


        Integer currentPebble;


        // Initialize hit counters
        int hit0 = 0;
        int hit1 = 0;
        int hit2 = 0;


        for(int i = 0; i < 10000; i++){

            // Fill the pairedWhiteBag of the BlackBag with the three testing pebbles
            testBlackBag.putInPebble(0);
            testBlackBag.putInPebble(1);
            testBlackBag.putInPebble(2);

            /*  As the pebble count is 1 at this point in the loop the getPebble method should return that pebble and
            more importantly fill itself with all the testing pebbles in its paired white bag and then shuffle the pebbles it just got
            Additionally, empties the paired white bag
             */
            testBlackBag.getPebble();

            // Because of the shuffling that occurred prior the pebble we get here should be a random selection of the three testing pebbles each time
            currentPebble = testBlackBag.getPebble();


            // Increment the hit counter depending on what testing pebble was returned
            if(currentPebble == 0){
                hit0++;
            }
            else if(currentPebble == 1){
                hit1++;
            }
            else if(currentPebble == 2){
                hit2++;
            }

            // Fail if the pebble returned isn't a testing pebble
            else  {
                fail("Pebble returned that was not in the original selection");
            }

            // Run getPebble so that the Pebbles contained inside decreases to one as is required in the start of the loop to trigger the shuffle of the BlackBag method
            testBlackBag.getPebble();
        }

        // Check if the method was uniformly randomly by seeing if the hit counters all appeared a
        assertEquals(3333, hit0, 100);
        assertEquals(3333, hit1, 100);
        assertEquals(3333, hit2, 100);
    }

    /**
     * Function to test the putInPebble method of the BlackBag Class
     * Tests the method by calling the method twice with one pebble as the argument each time,
     * then the corresponding whiteBag's are emptied and checked for the two pebbles
     * Expected output is the array of the two pebble integers inserted matching the contents emptied
     */
    @Test
    public void putInPebble() {

        testBlackBag.putInPebble(4);
        testBlackBag.putInPebble(5);
        LinkedList<Integer> whiteBagContents = testWhiteBag.empty();
        int[] comparedArray = {4, 5};
        for (int i=-0; i<comparedArray.length; i++) {
            assertEquals(comparedArray[i], (int) whiteBagContents.get(i));

        }
    }
}