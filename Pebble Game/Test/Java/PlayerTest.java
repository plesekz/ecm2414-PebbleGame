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

    @Before
    public void setUp() throws Exception {
        PebbleGame Game = new PebbleGame();
        testGame = Game;
        gameCls = testGame.getClass();
        Class Player = Class.forName("PebbleGame$Player");
        Constructor playerConstructor = Player.getDeclaredConstructor(gameCls, ArrayList.class, String.class);
        playerConstructor.setAccessible(true);

    }

    @After
    public void tearDown() throws Exception {
        testGame = null;
        gameCls = null;
        playerCls = null;
    }

    @Test
    public void testDiscardPebble() {

    }



    @Test
    public void testLogDiscarded() {

    }

    @Test
    public void testLogAdded(){

    }

    @Test
    public void testCheckVictory(){

    }
}
