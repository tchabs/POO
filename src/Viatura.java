import java.io.Serializable;

public class Viatura implements Serializable
{
    private String matricula;
    private String tipo;
    private double velMedia;
    private double custo;
    private double fiabilidade;
    private boolean disp;
    private Localizacao local;
    
   public Viatura(){
        this.matricula = "null";
        this.tipo = "null";
        this.velMedia = 0.0;
        this.custo = 0.0;
        this.fiabilidade = 0.0;
        this.disp = false;
        this.local = null;
   }
   
   public Viatura(String matricula,String tipo, double velMedia, double custo, double fiabilidade){
       this.matricula = matricula;
       this.matricula = "tipo";
       this.velMedia = velMedia;
       this.custo = custo;
       this.fiabilidade = fiabilidade;
       this.disp = true;
       this.local = null;
    }
    
   public Viatura(Viatura c){
       this.matricula = c.getMatricula();
       this.tipo = c.getTipo();
       this.velMedia = c.getVelMedia();
       this.custo = c.getCusto();
       this.fiabilidade = c.getFiabilidade();
       this.disp = true;
       this.local = c.getLocal();
   }    
    
   public String getMatricula(){
       return this.matricula;
    }
     
   public String getTipo(){
       return this.tipo;
    }
    
   public double getVelMedia(){
       return this.velMedia;
    }
    
   public double getCusto(){
       return this.custo;
    }
    
   public double getFiabilidade(){
       return this.fiabilidade;
    }
   
   public boolean getDisp(){
       return this.disp;
    }
   
   public Localizacao getLocal() throws NullPointerException{
       if(this.local == null)
       throw new NullPointerException("No location is defined");
       return this.local;
   }
   
   public void setMatricula(String matricula){
       this.matricula=matricula;
    }
   
   public void setTipo(String tipo){
       this.tipo=tipo;
    } 
    
   public void setVelMedia(double velMedia){
       this.velMedia = velMedia;
    }
    
   public void setFiabilidade(double fiabilidade){
       this.fiabilidade = fiabilidade;
    }
    
   public void setDisp(boolean disp){
       this.disp = disp;
    }
    
   public void setLocal (Localizacao local){
       this.local = local;
   }
   
   public Viatura clone() {
       return new Viatura(this);
    }
    
   
    public boolean equals(Object obj){
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Viatura v = (Viatura) obj;
    return v.getMatricula().equals(matricula) 
                                      && v.getTipo().equals(tipo)
                                      && this.velMedia == v.getVelMedia()
                                      && this.custo == v.getCusto() 
                                      && this.fiabilidade == v.getFiabilidade()
                                      && this.disp == v.getDisp()
                                      && v.getLocal().equals(local);
   }
  
   public String toString (){
        StringBuilder s = new StringBuilder();
        s.append("A velocidade média é: " + velMedia +";\n");
        s.append("O custo é: " + custo + ";\n"); 
        s.append("Fiabilidade: " + fiabilidade + ";\n"); 
        s.append("A matricula do taxi é " + matricula + ";\n"); 
        s.append("O tipo do taxi é " + tipo + ";\n"); 
        s.append("A localização é: " + local + ";\n");
        s.append("A disponibilidade: " + disp + ".");
        return s.toString();
   }
}
  
