import java.util.TreeSet;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;


public class CatUtilizadores implements Serializable
{
    private TreeSet<Utilizador>  catalog;

    public CatUtilizadores(){
        this.catalog = new TreeSet<Utilizador>(new ComparadorUtilizadores());
    }

    public CatUtilizadores(Collection<Utilizador> collection) {
        this.catalog = new TreeSet<Utilizador>(new ComparadorUtilizadores());

        for(Utilizador utilizador: collection) {
            this.catalog.add(utilizador);
        }
    }

    public CatUtilizadores(CatUtilizadores catUtilizadores){
        this.catalog = new TreeSet<Utilizador>(new ComparadorUtilizadores());

        for(Utilizador utilizador: catUtilizadores.catalog){
            this.catalog.add(utilizador);
        }
    }

    public int sizeCatalog() {
        return this.catalog.size();
    }

    public void insertUtilizador(Utilizador utilizador){
        this.catalog.add(utilizador);
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
        CatUtilizadores i = (CatUtilizadores) obj;

        return this.catalog.equals(i.catalog);
    }

    public CatUtilizadores clone(){
        return new CatUtilizadores(this);
    }
}
