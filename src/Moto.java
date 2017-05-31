public class Moto extends Viatura
{   
    public Moto (){
      super ("null",0.0,0.0,0.0);
    }
    
    public Moto(String matricula, double velMedia, double custo, double fiabilidade){
        super(matricula,velMedia,custo,fiabilidade);
    }
    
    
    public Moto (Moto c){
      super (c);
    }
    
    public Moto clone (){
      return new Moto (this);
    }

}