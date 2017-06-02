import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Collections;

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
    
    public boolean contem(Utilizador utilizador) {
        return this.catalog.contains(utilizador);
    }
    
    public Utilizador findU(String email) throws EmailException {
        
         List<Utilizador> s = this.catalog
            .stream()
            .filter(x -> x.getEmail() == email)
            .collect(Collectors.toList());
            
         if(s.isEmpty()) throw new EmailException("Email nao registado no sistema");
         else return s.stream().findFirst().get();
            
    }
    
    public double totalFaturado() {
        return this.catalog.stream().mapToDouble(x -> x.totalFaturado()).sum();
    }
    
    public List<Cliente> top5() {
        //return this.catalog.stream().filter(x -> x instanceof Cliente).sorted(new ComparadorTop10()).limit(10).collect(Collectors.toList());
        ArrayList<Motorista> motorista = new ArrayList<Motorista>();
        int i = 0;
        for(Utilizador u : this.catalog){
            if(u instanceof Motorista){
                Motorista c = (Motorista) u;
                if(i<this.catalog.size()){
                    motorista.add(c);
                    i++;
                }
            }
        }
        
        Collections.sort(motorista, new ComparadorTop10());
        List<Motorista> nova = new ArrayList<>(motorista.subList(0,4));
        return nova;
    }
    
    public List<Cliente> top10() {
        //return this.catalog.stream().filter(x -> x instanceof Cliente).sorted(new ComparadorTop10()).limit(10).collect(Collectors.toList());
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        int i = 0;
        for(Utilizador u : this.catalog){
            if(u instanceof Cliente){
                Cliente c = (Cliente) u;
                if(i<this.catalog.size()){
                    clientes.add(c);
                    i++;
                }
            }
        }
        
        Collections.sort(clientes, new ComparadorTop10());
        List<Cliente> nova = new ArrayList<>(clientes.subList(0,9));
        return nova;
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
