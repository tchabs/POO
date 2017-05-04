public class Ligeiros extends Carro
{   
    public Ligeiros (){
      super (0,0.0,0.0,0.0);
    }
    
    public Ligeiros(int id, double velMedia, double custo, double fiabilidade){
        super(id,velMedia,custo,fiabilidade);
    }
    
    public Ligeiros (Ligeiros c){
      super (c);
    }
    
    public Ligeiros clone (){
      return new Ligeiros (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      return false;  
    }
}
