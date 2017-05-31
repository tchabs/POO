import java.util.Map;
import java.util.Comparator;

public class ComparadorDistancia implements Comparator<Map.Entry<String,Double>>{
	
	public int compare(Map.Entry<String,Double> a, Map.Entry<String,Double> b){
		double r = a.getValue() - b.getValue();

		if(r > 0) return 1;
		else if(r < 0) return -1;
		return 0;
	}
}
