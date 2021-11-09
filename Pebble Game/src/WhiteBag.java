import java.util.LinkedList;

/**
 * An instance of this class represents a single WhiteBag, a repository for pebbles returned to the bags.
 * @author [REDACTED] and [REDACTED]
 *
 */
public class WhiteBag extends Bag{

	/**
	 * A constructor.
	 * @param buddy
	 * A pointer to a Bag that is the linked Bag.s
	 * @param name
	 * A String representing the name of this Bag.
	 */
	WhiteBag(Bag buddy, String name){
		content = new LinkedList<Integer>();
		pairedBag = buddy;
		this.name = name;
	}
	
	/**
	 * Method which empties the bag.
	 * @return
	 * A LinkedList which holds all the pebbles originally within this bag.
	 */
	public LinkedList<Integer> empty() {
		LinkedList<Integer> returnValue = content;
		content = new LinkedList<Integer>();
		return returnValue;
	}
}
