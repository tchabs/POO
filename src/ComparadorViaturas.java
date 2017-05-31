import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;

public  class ComparadorViaturas implements Comparator<Viatura>, Serializable{

    public int compare(Viatura a, Viatura b) {
        if(a==null || b==null) return -2;
        
		String matriculaA = a.getMatricula();
		String matriculaB = b.getMatricula();

		return matriculaB.compareTo(matriculaA);

  }
}

    

