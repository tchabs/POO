import java.util.TreeSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

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
    
    public boolean contem(Viatura viatura) {
        return this.catalog.contains(viatura);
    }
    
    public Viatura findV(String matricula) throws ViaturaException {
        
         List<Viatura> s = this.catalog
            .stream()
            .filter(x -> x.getMatricula().equals(matricula))
            .collect(Collectors.toList());
            
         if(s.isEmpty()) throw new ViaturaException("Viatura nao registada no sistema");
         else return s.stream().findFirst().get();
            
    }
    
    public Viatura getNearBy(Localizacao local) throws ViaturaException{
        ComparadorDistancia comp = new ComparadorDistancia();
        
        TreeMap<String,Double> catD = (TreeMap<String,Double>) this.catalog
                                                                    .stream()
                                                                    .collect(Collectors
                                                                        .toMap(v->v.getMatricula(),
                                                                               v->v.getLocal().distCalc(local)));
        String matricula = catD.entrySet()
                               .stream()
                               .min(comp)
                               .get()
                               .getKey();
                               
        return findV(matricula);                       
                   
    }
    
    public double totalFaturado() {
        return this.catalog.stream().mapToDouble(x -> x.totalFaturado()).sum();
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