package CollectionsAndGenerics;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		
		v.add(90);
		v.add(80);
		v.add(70);
		v.add(60);
		v.add(50);
		
		System.out.println(v);
		System.out.println();
		
		for (Integer val:v) {
			System.out.println(val);
		}
		
		System.out.println();
		
		Iterator<Integer> itr = v.iterator();
		
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		System.out.println("\n" + v.get(4) * 5);
		System.out.println(v.size());
		
	}
}
