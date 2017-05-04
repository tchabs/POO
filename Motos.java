public class Motos extends Carro
{   
    public Motos (){
      super (0,0.0,0.0,0.0);
    }
    
    public Motos(int id, double velMedia, double custo, double fiabilidade){
        super(id,velMedia,custo,fiabilidade);
    }
    
    
    public Motos (Motos c){
      super (c);
    }
    
    public Motos clone (){
      return new Motos (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      return false;  
    }
}