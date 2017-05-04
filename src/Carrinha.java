public class Carrinha extends Viatura
{
    public Carrinha (){
      super (0,0.0,0.0,0.0);
    }
    
    
    public Carrinha(int id, double velMedia, double custo, double fiabilidade){
        super(id,velMedia,custo,fiabilidade);
    }
    
    public Carrinha (Carrinha c){
      super (c);
    }
    
    public Carrinha clone (){
      return new Carrinha (this);
    }

}
