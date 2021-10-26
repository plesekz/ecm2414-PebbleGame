import java.util.ArrayList; 

public class Bag {
	ArrayList<Integer> content = new ArrayList<Integer>();
	Bag pairedBag;
	
	public void putInPebble(Integer i) {
		content.add(i);
	}
}
