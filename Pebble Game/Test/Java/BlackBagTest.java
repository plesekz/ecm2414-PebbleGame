import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BlackBagTest {
    private BlackBag testBlackBag;
    private WhiteBag testWhiteBag;
    @Before
    public void setUp() throws Exception {
        Integer[] pebbles = new Integer[0];
        BlackBag bag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        testBlackBag = bag;
        testWhiteBag = (WhiteBag) testBlackBag.getPairedBag();
    }

    @After
    public void tearDown() throws Exception {
        testBlackBag = null;
    }

    @Test
    public void testGetPebbleEmpty() {
        try{
            testBlackBag.getPebble();
            fail("Empty bag exception should have been thrown");
        }
        catch (EmptyBagException e){
        }

    }
    @Test
    public void testGetPebbleOnePebble() throws EmptyBagException {

        Integer[] pebbles = new Integer[1];
        pebbles[0] = 4;
        testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        assertTrue(testBlackBag.getPebble() == 4);

    }


    @Test
    public void testGetPebbleMultiplePebbles() throws EmptyBagException {

        Integer[] pebbles = new Integer[1];
        pebbles[0] = 9;
        testBlackBag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");

        testBlackBag.putInPebble(4);
        testBlackBag.putInPebble(5);

        // Empty 9 from BlackBag and insert 4, 5 from WhiteBag into BlackBag
        testBlackBag.getPebble();

        Integer pebble = testBlackBag.getPebble();
        assertTrue(pebble == 4 || pebble == 5);
    }

    @Test
    public void testGetPebbleRandomness() throws EmptyBagException {
        //TODO: FIX THIS
        Integer previousPebble = 0;
        Integer currentPebble = null;
        Boolean flag = false;
        Integer[] pebbles = {1};
        BlackBag bag = new BlackBag(pebbles, "TestBag", "TestWhiteBag");
        testBlackBag = bag;
        int hit0 = 0;
        int hit1 = 0;
        int hit2 = 0;
        for(int i = 0; i < 10000; i++){
            testBlackBag.putInPebble(0);
            testBlackBag.putInPebble(1);
            testBlackBag.putInPebble(2);
            testBlackBag.getPebble();
            currentPebble = testBlackBag.getPebble();
            if(currentPebble == 0){
                hit0++;
            }
            else if(currentPebble == 1){
                hit1++;
            }
            else if(currentPebble == 2){
                hit2++;
            }
            testBlackBag.getPebble();
        }
        assertEquals(3333, hit0, 100);
        assertEquals(3333, hit1, 100);
        assertEquals(3333, hit2, 100);
    }

    @Test
    public void putInPebble() {
        testBlackBag.putInPebble(4);
        testBlackBag.putInPebble(5);
        boolean check = true;
        // TODO: CHECK IF THE WHITE BAG HAS THE STUFF INSIDE OF IT
        ArrayList<Integer> whiteBagContents = testWhiteBag.empty();
        ArrayList<Integer> comparedArray = new ArrayList<>();
        comparedArray.add(4);
        comparedArray.add(5);
        for (int i : testWhiteBag.empty()) {
            if(comparedArray.get(i) != whiteBagContents.get(i)){
                check = false;
                break;
            }

        }
        assertTrue(check);
    }
}