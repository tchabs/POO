import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;

public  class ComparadorMotoristas implements Comparator<Motorista>, Serializable{

    public int compare(Motorista a, Motorista b) {
        if(a==null || b==null) return -2;
        
		String emailA = a.getEmail();
		String emailB = b.getEmail();

		return emailB.compareTo(emailA);

  }
}

    

