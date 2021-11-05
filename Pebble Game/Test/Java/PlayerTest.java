import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    private PebbleGame testGame;
    private Class gameCls ;
    private Class playerCls;
    private Object playerObject;

    @Before
    public void setUp() throws Exception {


        PebbleGame Game = new PebbleGame();
        testGame = Game;
        gameCls = testGame.getClass();


        //TODO: FIX THIS
        Class Player = Class.forName("PebbleGame$Player");
        Constructor playerConstructor = Player.getDeclaredConstructor(gameCls, ArrayList.class, String.class);
        playerConstructor.setAccessible(true);
        Object playerObject = playerConstructor.newInstance(gameCls);
        playerCls = Player;
    }

    @After
    public void tearDown() throws Exception {
        testGame = null;
        gameCls = null;
        playerCls = null;
    }

    @Test
    public void testDiscardPebble() {
        // Check if it returns a given pebble assuming the hand has one thing in it
        // Maybe this should be done with mock objects?
        // Finish this tommorow
        try {
            Method discardPebbleMethod = playerCls.getDeclaredMethod("discardPebble");
            discardPebbleMethod.setAccessible(true);
            Integer i = (Integer) discardPebbleMethod.invoke(playerObject);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            fail();
        }
    }

    @Test
    public void testDiscardPebbleRandomness() {
        // Check if the shuffling actually randomises the results
        try {
            Method discardPebbleMethod = playerCls.getDeclaredMethod("discardPebble");
            discardPebbleMethod.setAccessible(true);
            Integer i = (Integer) discardPebbleMethod.invoke(playerObject);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            fail();
        }
    }



    @Test
    public void testLogDiscarded() {

    }

    @Test
    public void testLogAdded(){

    }

    @Test
    public void testCheckVictory(){
        // Check if it actually stops when it gets to 100

    }
}
