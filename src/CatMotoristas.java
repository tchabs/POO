import java.util.TreeSet;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;

public class CatMotoristas implements Serializable
{
    private TreeSet<Motorista>  catalog;
    
    public CatMotoristas(){
        this.catalog = new TreeSet<Motorista>(new ComparadorMotoristas());
    }
    
    public CatMotoristas(Collection<Motorista> collection) {
        this.catalog = new TreeSet<Motorista>(new ComparadorMotoristas());
        
        for(Motorista motorista: collection) {
            this.catalog.add(motorista);
        }
    }
    
    public CatMotoristas(CatMotoristas catMotoristas){
        this.catalog = new TreeSet<Motorista>(new ComparadorMotoristas());
        
        for(Motorista motorista: catMotoristas.catalog){
            this.catalog.add(motorista);
        }
    }
    
    public int sizeCatalog() {
        return this.catalog.size();
    }

    public void insertMotorista(Motorista motorista){
		this.catalog.add(motorista);
    }

	public void clearC() {
		this.catalog.clear();
	}

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CatMotoristas i = (CatMotoristas) obj;

        return this.catalog.equals(i.catalog);
    }

    public CatMotoristas clone(){
        return new CatMotoristas(this);
    }
}
