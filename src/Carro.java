import java.util.ArrayList;
import java.util.stream.Collectors;

public class Carro extends Viatura
{   
    private ArrayList<Cliente> waitList;
    
    public Carro (){
      super ("null",0.0,0.0,0.0);
      waitList = new ArrayList<Cliente>();
    }
    
    public Carro(String matricula, double velMedia, double custo, double fiabilidade){
        super(matricula,velMedia,custo,fiabilidade);
        waitList = new ArrayList<>();
    }
    
    public Carro (Carro c){
      super (c);
      waitList = c.getWaitList();
    }
    
    public ArrayList<Cliente> getWaitList(){
      return this.waitList.stream().collect(Collectors.toCollection(ArrayList::new));
    }


    public void setWaitList(ArrayList<Cliente> waitList){
      this.waitList = waitList.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public void addCliente(Cliente c){
        this.waitList.add(c);
    }

    public void rmCliente(){
      waitList.remove(this);
    }
    
    public Carro clone (){
      return new Carro (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      if (obj == null || obj.getClass() != this.getClass()) return false;
      
      Carro c = (Carro) obj;
      return c.getWaitList().equals(this.waitList);
    }
}
