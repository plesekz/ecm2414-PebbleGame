import java.util.ArrayList;
import java.util.Collections;

public class BlackBag extends Bag{
	WhiteBag pairedBag;
	
	BlackBag(Integer[] pebbles, String name, String pairedBagName){
		this.name = name;
		content = new ArrayList<Integer>();
		for(int i = 0; i<pebbles.length; i++) {
			content.add(pebbles[i]);
		}
		Collections.shuffle(content);
		pairedBag = new WhiteBag(this, pairedBagName);
	}
	synchronized public Integer getPebble() throws EmptyBagException{
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
	@Override
	synchronized public void putInPebble(Integer pebble) {
		pairedBag.putInPebble(pebble);
	}

	@Override
	public WhiteBag getPairedBag(){
		return this.pairedBag;
	}

}
