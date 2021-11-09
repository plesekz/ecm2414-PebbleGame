import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.ArrayList;


public class MockPebbleGameTest {
    MockPebbleGame Game;

    @Before
    public void setUp() throws Exception {
        Game = new MockPebbleGame();

    }

    @After
    public void tearDown() throws Exception {
        Game = null;
    }

    @Test
    public void main() {
    }

    @Test
    public void game() {
        fail("Not Implemented Yet");
    }

    @Test
    public void endOfGame() {
        fail("Not Implemented Yet");
    }


    @Test
    public void testSetUpABagX() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 2, 3, "Pebble Game/example_file_4.csv");
        assertTrue(Game.getFailureCondition(), testBlackBag != null);
        assertTrue(testBlackBag.getName().equals("bag X"));
        assertTrue(testBlackBag.getPairName().equals("bag A"));
        Integer[] testContent = {1, 2, 3, 4};
        for (Integer i: testBlackBag.getContent()) {
            assertTrue(i == testContent[i]);
        }

    }

    @Test
    public void testSetUpABagY() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 1, 3, "Pebble Game/example_file_4.csv");
        assertTrue(Game.getFailureCondition(), testBlackBag != null);
        assertTrue(testBlackBag.getName().equals("bag Y"));
        assertTrue(testBlackBag.getPairName().equals("bag B"));
        Integer[] testContent = {1, 2, 3, 4};
        for (Integer i: testBlackBag.getContent()) {
            assertTrue(i == testContent[i]);
        }
    }

    @Test
    public void testSetUpABagZ() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "Pebble Game/example_file_4.csv");
        assertTrue(Game.getFailureCondition(), testBlackBag != null);
        assertTrue(testBlackBag.getName().equals("bag Z"));
        assertTrue(testBlackBag.getPairName().equals("bag C"));
        Integer[] testContent = {1, 2, 3, 4};
        for (Integer i: testBlackBag.getContent()) {
            assertTrue(i == testContent[i]);
        }
    }

    @Test
    public void testSetUpABagNegativePebble() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "negative_pebbles_1.csv");
        assertTrue(Game.getFailureCondition(), Game.getFailureCondition().equals("INVALID INPUT!!! Not Positive Pebble Weight exception!!!"));
    }

    @Test
    public void testSetUpABagNotEnoughPebbles() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "not_enough_pebbles.csv");
        assertTrue(Game.getFailureCondition(), Game.getFailureCondition().equals("INVALID INPUT!!! Not a number!!! Not enough pebbles exception!!!"));
    }

    @Test
    public void testSetUpABagBadFormatting() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpABag = Game.getClass().getDeclaredMethod("setUpABag", int.class, int.class, String.class);
        setUpABag.setAccessible(true);
        BlackBag testBlackBag = (BlackBag) setUpABag.invoke(Game, 0, 3, "badly_format.csv");
        assertTrue(Game.getFailureCondition(), Game.getFailureCondition().equals("INVALID INPUT!!! Not a number!!!"));
    }

    @Test
    public void testSetUpBags() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setUpBags = Game.getClass().getDeclaredMethod("setUpBags", int.class, ArrayList.class);
        setUpBags.setAccessible(true);
        ArrayList<String> s = new ArrayList<>();
        s.add("Pebble Game/example_file_4.csv");
        s.add("Pebble Game/example_file_4.csv");
        s.add("Pebble Game/example_file_4.csv");
        ArrayList<BlackBag> testBlackBags = (ArrayList<BlackBag>) setUpBags.invoke(Game, 3, s );
        assertTrue(Game.getFailureCondition(), testBlackBags.get(2) != null);
        assertTrue(Game.getFailureCondition(), testBlackBags.get(1) != null);
        assertTrue(Game.getFailureCondition(), testBlackBags.get(0) != null);
        assertTrue(testBlackBags.get(2).getName().equals("bag X"));
        assertTrue(testBlackBags.get(2).getPairName().equals("bag A"));
        assertTrue(testBlackBags.get(1).getName().equals("bag Y"));
        assertTrue(testBlackBags.get(1).getPairName().equals("bag B"));
        assertTrue(testBlackBags.get(0).getName().equals("bag Z"));
        assertTrue(testBlackBags.get(0).getPairName().equals("bag C"));
        Integer[] testContent = {1, 2, 3, 4};
        for (BlackBag b: testBlackBags) {
            for (Integer i: b.getContent()) {
                assertTrue(i == testContent[i]);
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