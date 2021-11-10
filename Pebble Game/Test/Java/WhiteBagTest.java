import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class WhiteBagTest {
    private WhiteBag testWhiteBag;
    @Before
    public void setUp() {
        Integer[] pebbles = {9};
        BlackBag testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        testWhiteBag = testBlackBag.getPairedBag();
    }

    @After
    public void tearDown()  {
        testWhiteBag = null;
    }

    @Test
    public void testPutInPebble() {
        testWhiteBag.putInPebble(4);
        assertEquals(1, testWhiteBag.getContent().size());
        assertEquals(4, (int) testWhiteBag.getContent().get(0));
    }

    @Test
    public void empty() {
        testWhiteBag.putInPebble(4);
        LinkedList<Integer> empty = testWhiteBag.empty();
        assertEquals(empty.size(), 1);
        assertEquals(4, (int) empty.get(0));
        assertEquals(0, testWhiteBag.getContent().size());
    }
}