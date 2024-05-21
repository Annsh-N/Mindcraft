package CollectionsAndGenerics;
import java.util.*;

public class UtilityReport {
	private Map<String, Double> m;
		
	public void showReport() {
		for (String key : m.keySet()) {
			System.out.println(key + "-->" + m.get(key));
		}
	}
}
