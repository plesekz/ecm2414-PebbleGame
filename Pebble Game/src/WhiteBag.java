import java.util.ArrayList;

public class WhiteBag extends Bag{

	WhiteBag(Bag buddy){
		content = new ArrayList<Integer>();
		pairedBag = buddy;
	}
	public ArrayList<Integer> empty() {
		ArrayList<Integer> returnValue = content;
		content = new ArrayList<Integer>();
		return returnValue;
	}
}
