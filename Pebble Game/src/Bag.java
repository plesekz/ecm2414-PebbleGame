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
	public String getPairName() {
		return pairedBag.getName();
	}

	public Bag getPairedBag() {
		return pairedBag;
	}

	public ArrayList<Integer> getContent() {
		return content;
	}
}
