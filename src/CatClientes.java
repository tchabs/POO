import java.util.TreeSet;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;


public class CatClientes implements Serializable
{
    private TreeSet<Cliente>  catalog;
    
    public CatClientes(){
        this.catalog = new TreeSet<Cliente>(new ComparadorClientes());
    }
    
    public CatClientes(Collection<Cliente> collection) {
        this.catalog = new TreeSet<Cliente>(new ComparadorClientes());
        
        for(Cliente cliente: collection) {
            this.catalog.add(cliente);
        }
    }
    
    public CatClientes(CatClientes catClientes){
        this.catalog = new TreeSet<Cliente>(new ComparadorClientes());
        
        for(Cliente cliente: catClientes.catalog){
            this.catalog.add(cliente);
        }
    }
    
    public int sizeCatalog() {
        return this.catalog.size();
    }

    public void insertCliente(Cliente cliente){
		this.catalog.add(cliente);
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
        CatClientes i = (CatClientes) obj;

        return this.catalog.equals(i.catalog);
    }

    public CatClientes clone(){
        return new CatClientes(this);
    }
}
    
    