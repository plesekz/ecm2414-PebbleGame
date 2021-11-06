import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        Class Player = gameCls.getDeclaredClasses()[0];
        Constructor playerConstructor = Player.getDeclaredConstructors()[0];
        playerConstructor.setAccessible(true);
        Integer[] defaultPebbles = new Integer[1];
        defaultPebbles[0] = 0;
        ArrayList<BlackBag> bagArrayList = new ArrayList<>();
        BlackBag defaultBag = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag);
        BlackBag defaultBag1 = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag1);
        BlackBag defaultBag2 = new BlackBag(defaultPebbles, "defaultBag", "defaultPairedBag");
        bagArrayList.add(defaultBag2);
        playerObject = playerConstructor.newInstance(Game, bagArrayList, "defaultPlayer", Game);

        playerCls = Player;
    }

    @After
    public void tearDown() throws Exception {
        testGame = null;
        gameCls = null;
        playerCls = null;
        playerObject = null;
    }

    @Test
    public void testDiscardPebbleOnePebble() {
        // Check if it returns a given pebble assuming the hand has one thing in it
        // Maybe this should be done with mock objects?
        // Finish this tommorow
        // TODO: RESEARCH FIELD SET METHOD TO SET HAND TO ONE INTEGER ARRAYLIST
        Field handField = null;
        Integer i = null;
        try {
            handField = playerObject.getClass().getDeclaredField("hand");
        } catch (NoSuchFieldException e) {
            fail("Hand Field Set Incorrectly");
        }
        handField.setAccessible(true);
        ArrayList<Integer> defaultHand = new ArrayList<>();
        defaultHand.add(1);
        try {
            handField.set(playerObject, defaultHand);
        } catch (IllegalAccessException e) {
            fail("Hand Field Access not set properly");
        }
        try {
            Method discardPebbleMethod = playerObject.getClass().getDeclaredMethod("discardPebble");
            discardPebbleMethod.setAccessible(true);
            i = (Integer) discardPebbleMethod.invoke(playerObject);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            fail("Method Invoked incorrectly in the test");
        }
        assertTrue(i == 1);
    }

    @Test
    public void testDiscardPebbleRandomness() {
        // Check if the shuffling actually randomises the results
        // TODO: Clean this code up
        Field handField = null;
        Integer i = null;
        int hit1 = 0;
        int hit2 = 0;
        int hit3 = 0;
        try {
            handField = playerObject.getClass().getDeclaredField("hand");
        } catch (NoSuchFieldException e) {
            fail("Hand Field Set Incorrectly");
        }
        handField.setAccessible(true);
        ArrayList<Integer> defaultHand = new ArrayList<>();
        defaultHand.add(1);
        defaultHand.add(2);
        defaultHand.add(3);
        ArrayList<Integer> tempHand = new ArrayList<>();
        tempHand = defaultHand;
        try {
            handField.set(playerObject, tempHand);
        } catch (IllegalAccessException e) {
            fail("Hand Field Access not set properly");
        }
        int counter = 0;
        try {
            Method discardPebbleMethod = playerObject.getClass().getDeclaredMethod("discardPebble");
            discardPebbleMethod.setAccessible(true);
            for(int o = 0; o<10000; o++){
                i = (Integer) discardPebbleMethod.invoke(playerObject);
                if( i == 1){
                    hit1++;
                }
                else if(i == 2){
                    hit2++;
                }
                else if(i == 3){
                    hit3++;
                }
                else {
                    fail("pebble not in hand accessed");
                }
                try {
                    defaultHand = new ArrayList<>();
                    defaultHand.add(1);
                    defaultHand.add(2);
                    defaultHand.add(3);
                    handField.set(playerObject, defaultHand);
                } catch (IllegalAccessException e) {
                    fail("Hand Field Access not set properly");
                }
                System.out.println(counter++);
            }
        }
        catch(NoSuchMethodException e){
            fail("Method does not exist");
        }
        catch(IllegalAccessException e){
            fail("Illegal Access ");
        }
        catch(InvocationTargetException e){
            e.printStackTrace();
            fail();
        }
        System.out.println(hit1);

        System.out.println(hit2);

        System.out.println(hit3);
        assertEquals(3333, hit1, 100);
        assertEquals(3333, hit2, 100);
        assertEquals(3333, hit3, 100);
    }



    @Test
    public void testLogDiscarded() throws IOException {
        // TODO: CHECK EXPECTED OUTPUT
        Field handField = null;
        Integer i = null;
        Integer o = 2;
        try {
            handField = playerObject.getClass().getDeclaredField("hand");
        } catch (NoSuchFieldException e) {
            fail("Hand Field Set Incorrectly");
        }
        handField.setAccessible(true);
        ArrayList<Integer> defaultHand = new ArrayList<>();
        defaultHand.add(1);
        try {
            handField.set(playerObject, defaultHand);
        } catch (IllegalAccessException e) {
            fail("Hand Field Access not set properly");
        }
        try {
            Method logDiscardMethod = playerObject.getClass().getDeclaredMethod("logDiscarded", Integer.class);
            logDiscardMethod.setAccessible(true);
            logDiscardMethod.invoke(playerObject,  o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }
        String strCurrentLine ;
        ArrayList<String> fileStrings = new ArrayList<>();
        BufferedReader Reader = new BufferedReader(new FileReader("defaultPlayer_output.txt"));
        while ((strCurrentLine = Reader.readLine()) != null) {
            System.out.println(strCurrentLine);
            fileStrings.add(strCurrentLine);
        }
        assertTrue("defaultPlayer has discarded a 2 to defaultPairedBag".equals(fileStrings.get(0)));
        assertTrue("defaultPlayer hand is 1, ".equals(fileStrings.get(1)));


    }

    @Test
    public void testLogAdded() throws IOException {
        //TODO: CHECK IF GENERATED ADDED FILE MATCHES THE EXPECTED OUTPUT FOR THE GIVEN HAND
        Field handField = null;
        Integer i = null;
        Integer o = 2;
        try {
            handField = playerObject.getClass().getDeclaredField("hand");
        } catch (NoSuchFieldException e) {
            fail("Hand Field Set Incorrectly");
        }
        handField.setAccessible(true);
        ArrayList<Integer> defaultHand = new ArrayList<>();
        defaultHand.add(1);
        try {
            handField.set(playerObject, defaultHand);
        } catch (IllegalAccessException e) {
            fail("Hand Field Access not set properly");
        }
        try {
            Method logDiscardMethod = playerObject.getClass().getDeclaredMethod("logAdded", Integer.class);
            logDiscardMethod.setAccessible(true);
            logDiscardMethod.invoke(playerObject,  o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }
        String strCurrentLine ;
        ArrayList<String> fileStrings = new ArrayList<>();
        BufferedReader Reader = new BufferedReader(new FileReader("defaultPlayer_output.txt"));
        while ((strCurrentLine = Reader.readLine()) != null) {
            System.out.println(strCurrentLine);
            fileStrings.add(strCurrentLine);
            assertTrue(strCurrentLine.equals("defaultPlayer has drawn a 2 from defaultBag defaultPlayer hand is 1, "));
        }
        assertTrue(fileStrings.get(0).equals("defaultPlayer has drawn a 2 from defaultBag"));
        assertTrue(fileStrings.get(1).equals("defaultPlayer hand is 1, "));
    }

    @Test
    public void testCheckVictory(){
        // TODO: Check if it actually stops when it gets to 100
        // TODO: IS THIS EVEN A GOOD IDEA TO TEST HERE AS THE METHOD IS IMPLICITLY THREAD DEPENDANT (MAYBE BLACBOK TEST THROUGH PEBBLEGAME?)
        fail("Thing dosent exist yet");
    }
}
