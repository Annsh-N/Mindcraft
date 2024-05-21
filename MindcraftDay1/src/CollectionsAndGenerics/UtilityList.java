package CollectionsAndGenerics;
import java.util.*;

public class UtilityList {
	private List<Student> list;
	
	public void createList(Student s) {
		list.add(s);
	}
	
	public void printList() {
		for (Student s : list) {
			System.out.println(s);
		}
	}
}
