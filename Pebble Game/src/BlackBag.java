import java.util.Collections;
import java.util.LinkedList;

/**
 * Instances of this class represent the BlackBag, the Bag the players interact with.
 * Extends the Bag class.
 * @author [REDACTED] and [REDACTED]
 *
 */
public class BlackBag extends Bag{
	/**
	 * The paired Bag.
	 */
	WhiteBag pairedBag;
	
	/**
	 * Constructor for the Bag class.
	 * @param pebbles
	 * Integer[] representing the weights of pebbles that are to be contained within.
	 * @param name
	 * The Bag's name.
	 * @param pairedBagName
	 * The name for the paired Bag.
	 */
	BlackBag(Integer[] pebbles, String name, String pairedBagName){
		this.name = name;
		content = new LinkedList<Integer>();
		for(int i = 0; i<pebbles.length; i++) {
			content.add(pebbles[i]);
		}
		Collections.shuffle(content);
		pairedBag = new WhiteBag(this, pairedBagName);
	}
	
	/**
	 * A synchronised (within an instance) getter method.
	 * @return
	 * An Integer representing the weight of the pebble retrieved from the Bag.
	 * @throws EmptyBagException
	 * If a draw is attempted from the bag whilst it is empty.
	 */
	public Integer getPebble() throws EmptyBagException{
		//this form of synchronisation, rather than synchronising the method, is used to allow other bags be accessed.
		synchronized(this) {
			if(content.size()==0) {
				throw new EmptyBagException();
			}
			if(content.size()>1) {
				return content.remove(0);
			} else {
				Integer hold = content.remove(0);
				content = pairedBag.empty();
				Collections.shuffle(content);
				return hold;
			}
		}
	}
	
	/**
	 * A method for entering a pebble into the paired bags.
	 * Method redirects at the paired bag.
	 * @param pebble
	 * An Integer represting the weight of the pebble entered.
	 */
	@Override
	public void putInPebble(Integer pebble) {
		synchronized(this) {
			pairedBag.putInPebble(pebble);
		}
	}

	/**
	 * Getter.
	 * @return
	 * A pointer to the WhiteBag which is the paired bag.
	 */
	@Override
	public WhiteBag getPairedBag(){
		return this.pairedBag;
	}
	
	/**
	 * Getter.
	 * @return
	 * A String representing the name of the PairedBag.
	 */
	@Override
	public String getPairName() {
		return pairedBag.getName();
	}


}
