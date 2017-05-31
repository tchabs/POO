public class Carro extends Viatura
{   
    public Carro (){
      super ("null",0.0,0.0,0.0);
    }
    
    public Carro(String matricula, double velMedia, double custo, double fiabilidade){
        super(matricula,velMedia,custo,fiabilidade);
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
