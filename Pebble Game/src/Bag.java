import java.util.ArrayList; 

public class Bag {
	ArrayList<Integer> content = new ArrayList<Integer>();
	Bag pairedBag;
	String name;
	
	public void putInPebble(Integer i) {
		content.add(i);
	}
	public String getName() {
		return name;
	}
}
