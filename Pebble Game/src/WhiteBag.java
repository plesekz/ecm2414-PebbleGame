import java.util.ArrayList;

public class WhiteBag extends Bag{

	WhiteBag(Bag buddy, String name){
		content = new ArrayList<Integer>();
		pairedBag = buddy;
		this.name = name;
	}
	public ArrayList<Integer> empty() {
		ArrayList<Integer> returnValue = content;
		content = new ArrayList<Integer>();
		return returnValue;
	}
}
