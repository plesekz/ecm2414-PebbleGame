import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ PlayerTest.class, PebbleGameTest.class, BlackBagTest.class, WhiteBagTest.class })
public class TestSuite {
}
