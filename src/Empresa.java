import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.io.Serializable;


public class Empresa implements Serializable{

    private long id;
    private CatUtilizadores motoristas;
    private CatViaturas viaturas;

    public Empresa() {
        id = 0;
        motoristas = new CatUtilizadores();
        viaturas = new CatViaturas();
    }
    
    public Empresa(Empresa e) {
        this.id = e.getId();
        this.motoristas = e.getMotoristas().clone();
        this.viaturas = e.getViaturas().clone();
    }
    
    public long getId() {
        return this.id;
    }

    public CatUtilizadores getMotoristas() {
        return this.motoristas;
    }
    
    public CatViaturas getViaturas() {
        return this.viaturas;
                   
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public void setMotoristas(CatUtilizadores m) {
        this.motoristas = m.clone();
    }

    public void setViaturas(CatViaturas v) {
        this.viaturas = v.clone();
    }
    
    public void insertViaturaE(Viatura v) {
        viaturas.insertViatura(v);
    }
    
    public void insertMotoristaE(Motorista m) {
        motoristas.insertUtilizador(m);
    }
    
    public double totalFaturado() {
        return this.motoristas.totalFaturado();
    }
        
    
    public Empresa clone(){
        return new Empresa(this);
    }
       
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Empresa e = (Empresa) obj;

        return e.getViaturas().equals(viaturas) && 
               e.getMotoristas().equals(motoristas) && 
               e.getId()==this.id;
    }
    
    public String toString (){
      StringBuilder sb = new StringBuilder ();
      sb.append("Id:").append(id).append("\n");
      
      return sb.toString();
    }
}