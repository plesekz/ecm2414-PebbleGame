import java.util.ArrayList;
import java.util.Collections;

public class BlackBag extends Bag{
	WhiteBag pairedBag;
	
	BlackBag(Integer[] pebbles){
		content = new ArrayList<Integer>();
		for(int i = 0; i<pebbles.length; i++) {
			content.add(pebbles[i]);
		}
		Collections.shuffle(content);
		pairedBag = new WhiteBag(this);
	}
	synchronized public Integer swapPebble(Integer pebble) {
		pairedBag.putInPebble(pebble);
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
