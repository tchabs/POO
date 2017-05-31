public class Carrinha extends Viatura
{
    public Carrinha (){
      super ("null",0.0,0.0,0.0);
    }
    
    
    public Carrinha(String matricula, double velMedia, double custo, double fiabilidade){
        super(matricula,velMedia,custo,fiabilidade);
    }
    
    public Carrinha (Carrinha c){
      super (c);
    }
    
    public Carrinha clone (){
      return new Carrinha (this);
    }

}
