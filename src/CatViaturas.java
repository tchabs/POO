import java.util.TreeSet;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;

public class CatViaturas implements Serializable
{
    private TreeSet<Viatura>  catalog;
    
    public CatViaturas(){
        this.catalog = new TreeSet<Viatura>(new ComparadorViaturas());
    }
    
    public CatViaturas(Collection<Viatura> collection) {
        this.catalog = new TreeSet<Viatura>(new ComparadorViaturas());
        
        for(Viatura viatura: collection) {
            this.catalog.add(viatura);
        }
    }
    
    public CatViaturas(CatViaturas catViaturas){
        this.catalog = new TreeSet<Viatura>(new ComparadorViaturas());
        
        for(Viatura viatura: catViaturas.catalog){
            this.catalog.add(viatura);
        }
    }
    
    public int sizeCatalog() {
        return this.catalog.size();
    }

    public void insertViatura(Viatura viatura){
		this.catalog.add(viatura);
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
        CatViaturas i = (CatViaturas) obj;

        return this.catalog.equals(i.catalog);
    }

    public CatViaturas clone(){
        return new CatViaturas(this);
    }
}