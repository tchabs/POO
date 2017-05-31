import java.util.Comparator;
import java.util.TreeSet;
import java.io.Serializable;
import java.util.Calendar;

public  class ComparadorViagem implements Comparator<Viagem>, Serializable{

    public int compare(Viagem a, Viagem b) {
        if(a==null || b==null) return -2;
        
		Calendar dataA = a.getInicioT();
		Calendar dataB = b.getInicioT();

		return dataB.compareTo(dataA);

  }
}

    

