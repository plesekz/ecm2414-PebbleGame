import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Testing class for the WhiteBag Class
 * Provides a suite of unit tests meant to ensure that the WhiteBag Class methods work as expected
 * @author [REDACTED] and [REDACTED]
 */
public class WhiteBagTest {
    private WhiteBag testWhiteBag;

    /**
     * Function that runs prior to each test creating the necessary testWhiteBag object for the suite tests to run
     */
    @Before
    public void setUp() {
        Integer[] pebbles = {9};
        BlackBag testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        testWhiteBag = testBlackBag.getPairedBag();
    }

    /**
     * Function that wipes the testWhiteBag object after each test runs
     */
    @After
    public void tearDown()  {
        testWhiteBag = null;
    }

    /**
     * Function to test the putInPebble method of the Bag Class
     * Tests the method by calling the method on an empty WhiteBag and checking to see if the corresponding pebble has been added and the amount of the pebbles is 1.
     */
    @Test
    public void testPutInPebble() {
        testWhiteBag.putInPebble(4);
        assertEquals(1, testWhiteBag.getContent().size());
        assertEquals(4, (int) testWhiteBag.getContent().get(0));
    }

    /**
     * Function to test the empty method of the WhiteBag Class
     * Tests the method by calling the method on a WhiteBag with one pebble in it and then emptying it into an array
     * Expected outcome is that the emptied contents contain only the one pebble and that the WhiteBag itself is now empty
     */
    @Test
    public void testEmpty() {
        testWhiteBag.putInPebble(4);
        LinkedList<Integer> empty = testWhiteBag.empty();
        assertEquals(empty.size(), 1);
        assertEquals(4, (int) empty.get(0));
        assertEquals(0, testWhiteBag.getContent().size());
    }
}