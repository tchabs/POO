import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;

public  class ComparadorUtilizadores implements Comparator<Utilizador>, Serializable{

    public int compare(Utilizador a, Utilizador b) {
        if(a==null || b==null) return -2;
        
		String emailA = a.getEmail();
		String emailB = b.getEmail();

		return emailB.compareTo(emailA);

  }
}

    

