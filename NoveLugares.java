public class NoveLugares extends Carro
{
    public NoveLugares (){
      super (0,0.0,0.0,0.0);
    }
    
    
    public NoveLugares(int id, double velMedia, double custo, double fiabilidade){
        super(id,velMedia,custo,fiabilidade);
    }
    
    public NoveLugares (NoveLugares c){
      super (c);
    }
    
    public NoveLugares clone (){
      return new NoveLugares (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      return false;  
    }
}
