import java.util.TreeSet;
import java.util.List;
import java.util.HashMap;
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
    public TreeSet<Viatura> getV(){
        return this.catalog;
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
        List<Viatura> s =null;
         s = this.catalog
            .stream()
            .filter(x -> x.getMatricula().equals(matricula))
            .collect(Collectors.toList());
            
         if(s.equals(null)) throw new ViaturaException("Viatura nao registada no sistema");
         if(s.isEmpty()) throw new ViaturaException("Viatura nao registada no sistema");
         else return s.stream().findFirst().get();
            
    }
    
    public Viatura getNearBy(Localizacao local,String tipo) throws ViaturaException{
        ComparadorDistancia comp = new ComparadorDistancia();
        String matricula=null;
        HashMap<String,Double> catD = null;

        if(tipo=="any"){
            
            catD = (HashMap<String,Double>) this.catalog
                .stream()
                .collect(Collectors.toMap(v->v.getMatricula(),v->v.getLocal().distCalc(local)));
            
            matricula = catD.entrySet()
                               .stream()
                               .min(comp)
                               .get()
                               .getKey();
        }
                               
                               
        else {
            if(tipo=="carro") {
                    
                    catD = (HashMap<String,Double>) this.catalog
                        .stream()
                        .filter(x -> x instanceof Carro)
                        .collect(Collectors
                            .toMap(v->v.getMatricula(),
                                   v->v.getLocal().distCalc(local)));
                    if(catD.isEmpty()) matricula = "Nenhum taxi do tipo escolhido disponivel"          ;     
                    else{
                    matricula = catD.entrySet()
                               .stream()
                               .min(comp)
                               .get()
                               .getKey();
                            }
                        }

            if(tipo=="moto") {

                
                    catD = (HashMap<String,Double>) this.catalog
                        .stream()
                        .filter(x -> x instanceof Moto)
                        .collect(Collectors
                            .toMap(v->v.getMatricula(),
                                   v->v.getLocal().distCalc(local)));
                    if(catD.isEmpty()) matricula = "Nenhum taxi do tipo escolhido disponivel";               
                    else{
                    matricula = catD.entrySet()
                               .stream()
                               .min(comp)
                               .get()
                               .getKey();
                            }
                        }

            if(tipo=="carrinha") {
                    catD = (HashMap<String,Double>) this.catalog
                        .stream()
                        .filter(x -> x instanceof Carrinha)
                        .collect(Collectors
                            .toMap(v->v.getMatricula(),
                                   v->v.getLocal().distCalc(local)));
                                   
                    if(catD.isEmpty()) matricula = "Nenhum taxi do tipo escolhido disponivel";              
                    else{
                    matricula = catD.entrySet()
                               .stream()
                               .min(comp)
                               .get()
                               .getKey();
                            }
                        }
        }

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