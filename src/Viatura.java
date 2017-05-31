import java.io.Serializable;
import java.util.TreeSet;
public class Viatura implements Serializable
{
    private String matricula;
    private Motorista motorista;
    private double velMedia;
    private double custo;
    private double fiabilidade;
    private boolean disp;
    private Localizacao local;
    protected TreeSet<Viagem> catViagens;

   public Viatura(){
        this.matricula = "null";
        this.motorista = null;
        this.velMedia = 0.0;
        this.custo = 0.0;
        this.fiabilidade = 0.0;
        this.disp = false;
        this.local = null;
        this.catViagens = new TreeSet<Viagem>(new ComparadorViagem());
   }

   public Viatura(String matricula,double velMedia, double custo, double fiabilidade){
       this.matricula = matricula;
       this.motorista =null;
       this.velMedia = velMedia;
       this.custo = custo;
       this.fiabilidade = fiabilidade;
       this.disp = true;
       this.local = null;
       this.catViagens = new TreeSet<Viagem>(new ComparadorViagem());
    }

   public Viatura(Viatura c){
       this.matricula = c.getMatricula();
       this.motorista = c.getMotorista();
       this.velMedia = c.getVelMedia();
       this.custo = c.getCusto();
       this.fiabilidade = c.getFiabilidade();
       this.disp = true;
       this.local = c.getLocal();
       this.catViagens = c.getCatViagens();
   }

   public String getMatricula(){
       return this.matricula;
    }

   public Motorista getMotorista(){
       return this.motorista.clone();
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
   
   public TreeSet<Viagem> getCatViagens() throws NullPointerException{
       if(this.catViagens == null) throw new NullPointerException("No catalog");
       return this.catViagens;
   }

   
   public void setMatricula(String matricula){
       this.matricula=matricula;
    }
    
   public void setMotorista(Motorista motorista){
       this.motorista=motorista;
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

   public void setCatViagens(TreeSet<Viagem> catViagens) {
       this.catViagens = catViagens;
   }
   
   public void insertViagem(Viagem viagem) {
        catViagens.add(viagem);
    }
   
   public int checkTipoV() {
       if(this instanceof Carro) return 1;
       else if(this instanceof Moto) return 2;
       else if(this instanceof Carrinha) return 3;
       else return 0;
   }
   
   
    public double totalFaturado() {
        return catViagens.stream()
                            .mapToDouble(x -> x.getPreco())
                            .sum();
                        }
   
   
   public Viatura clone() {
       return new Viatura(this);
    }


    public boolean equals(Object obj){
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Viatura v = (Viatura) obj;
    return v.getMatricula().equals(matricula)
                                      && v.getMotorista().equals(motorista)
                                      && this.velMedia == v.getVelMedia()
                                      && this.custo == v.getCusto()
                                      && this.fiabilidade == v.getFiabilidade()
                                      && this.disp == v.getDisp()
                                      && v.getLocal().equals(local)
                                      && v.getCatViagens().equals(catViagens);
   }

   public String toString (){
        StringBuilder s = new StringBuilder();
        s.append("A velocidade média é: " + velMedia +";\n");
        s.append("O custo é: " + custo + ";\n");
        s.append("Fiabilidade: " + fiabilidade + ";\n");
        s.append("A matricula do taxi é " + matricula + ";\n");
        s.append("A localização é: " + local + ";\n");
        s.append("A disponibilidade: " + disp + ".");
        return s.toString();
   }
}
