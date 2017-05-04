public class Carro extends Viatura
{   
    public Carro (){
      super (0,0.0,0.0,0.0);
    }
    
    public Carro(int id, double velMedia, double custo, double fiabilidade){
        super(id,velMedia,custo,fiabilidade);
    }
    
    public Carro (Carro c){
      super (c);
    }
    
    public Carro clone (){
      return new Carro (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      return false;  
    }
}
