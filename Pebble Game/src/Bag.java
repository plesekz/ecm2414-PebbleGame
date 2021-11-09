import java.util.LinkedList; 

/**
 * An over-arching class for the Bags.
 * Defines core functionality and supports polymorphy.
 * @author [REDACTED] and [REDACTED]
 *
 */
public class Bag {
	/**
	 * The weights of the pebbles inside.
	 */
	protected LinkedList<Integer> content = new LinkedList<Integer>();
	/**
	 * Pointer to a paired bag.
	 */
	protected Bag pairedBag;
	/**
	 * The name of the bag.
	 */
	protected String name;
	
	/**
	 * A method fore depositing a pebble into the bag.
	 * @param i
	 * Integer representing the weight of the pebble inserted.
	 */
	public void putInPebble(Integer i) {
		content.add(i);
	}
	
	/**
	 * Name getter.
	 * @return
	 * Returns a String representing the Bag's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Name getter for the paired bag.
	 * @return
	 * Returns a String representing the paired Bag's name.
	 */
	public String getPairName() {
		return pairedBag.getName();
	}

	/**
	 * Getter, returns the paired bag.
	 * @return
	 * Returns the paired Bag.
	 */
	public Bag getPairedBag() {
		return pairedBag;
	}

	/**
	 * Getter, returns the LinkedList holding the content.
	 * @return
	 * LinkedList holding the content.
	 */
	public LinkedList<Integer> getContent() {
		return content;
	}

}
