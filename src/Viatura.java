import java.io.Serializable;

public abstract class Viatura implements Serializable
{
    protected int id;
    protected double velMedia;
    protected double custo;
    protected double fiabilidade;
    protected boolean disp;
    protected Localizacao local;
    
   public Viatura(){
        this.id = 0;
        this.velMedia = 0.0;
        this.custo = 0.0;
        this.fiabilidade = 0.0;
        this.disp = false;
        this.local = null;
   }
   
   public Viatura(int id, double velMedia, double custo, double fiabilidade){
       this.id = id;
       this.velMedia = velMedia;
       this.custo = custo;
       this.fiabilidade = fiabilidade;
       this.disp = true;
       this.local = null;
    }
    
   public Viatura(Viatura c){
       this.id = c.getId();
       this.velMedia = c.getVelMedia();
       this.custo = c.getCusto();
       this.fiabilidade = c.getFiabilidade();
       this.disp = true;
       this.local = c.getLocal();
   }    
    
   public int getId(){
       return this.id;
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
   
   public void setId(int id){
       this.id = id;
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
   
   public abstract Viatura clone();
    
   
    public boolean equals(Object obj){
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Viatura v = (Viatura) obj;
    return this.id == v.getId() && this.velMedia == v.getVelMedia()
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
        s.append("O id do taxi é " + id + ";\n"); 
        s.append("A localização é: " + local + ";\n");
        s.append("A disponibilidade: " + disp + ".");
        return s.toString();
   }
}
  
