import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> content = new ArrayList<Integer>();
		content.add(1);
		content.add(2);
		ArrayList<Integer> hold = content;
		content = new ArrayList<Integer>();
		for(Integer i:content) {
			System.out.print("Content:" + i);
		}
		for(Integer i:hold) {
			System.out.print("Hold:" + i);
		}
		
		char black = (char) ('Z'-2+1);
		char white = (char) ('A'+ 1);
		
		System.out.print(black);
		System.out.print(white);

	}

}
