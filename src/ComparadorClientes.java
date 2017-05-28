import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;

public  class ComparadorClientes implements Comparator<Cliente>, Serializable{

    public int compare(Cliente a, Cliente b) {
        if(a==null || b==null) return -2;
        
		String emailA = a.getEmail();
		String emailB = b.getEmail();

		return emailB.compareTo(emailA);

  }
}

    

