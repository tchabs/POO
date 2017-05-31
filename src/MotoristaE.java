import java.io.Serializable;

public class MotoristaE extends Motorista implements Serializable {
    
    private Empresa empresa;
    private boolean disp;
    
    public MotoristaE() {
        this.empresa = null;
        this.disp = false;
    }
    
    public MotoristaE(MotoristaE m) {
        this.empresa = m.getEmpresa().clone();
        this.disp = m.getDisp();
    }
    
    public Empresa getEmpresa(){
        return this.empresa;
    }
    
    public boolean getDisp() {
        return this.disp;
    }
    
    public void setEmpresa(Empresa e) {
        this.empresa = e.clone();
    }
    
    public void setDisp(boolean disp) {
        this.disp = disp;
    }
    
    public MotoristaE clone(){
        return new MotoristaE(this);
    }

    public boolean equals(Object obj){
        
        if (obj == this)
          return true;
        
        if (obj == null || obj.getClass() != this.getClass())
          return false;
        
        MotoristaE m = (MotoristaE) obj;
    
        return m.getEmpresa().equals(empresa);
}
}
