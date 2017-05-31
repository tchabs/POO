import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;

public  class ComparadorTop10 implements Comparator<Utilizador>, Serializable{

    public int compare(Utilizador a, Utilizador b) {
        if(a==null || b==null) return -2;
        
        double gastoA = a.totalFaturado();
        double gastoB = b.totalFaturado();

        double r =  gastoA - gastoB;
        if(r>0) return -1;
        else if(r<0) return -1;
        return 0;
  }
}

    
