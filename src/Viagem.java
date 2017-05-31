import java.io.Serializable;
import java.util.Calendar;
public class Viagem implements Serializable {
    
    private Localizacao inicioL;
    private Localizacao fimL;
    private int classificacao;
    private double tempo;
    private double preco;
    private Calendar inicioT;
    private Calendar fimT;
    
    
    public Viagem() {
        inicioL = null;
        fimL = null;
        classificacao = 0;
        tempo = 0.0;
        preco = 0.0;
        inicioT = null;
        fimT = null;
    }

    public Viagem(Localizacao inicioL,Localizacao fimL,Viatura viatura,int classificacao,double tempo,double preco,Calendar inicioT,Calendar fimT){
        this.inicioL = inicioL;
        this.fimL = fimL;
        this.classificacao = classificacao;
        this.tempo = tempo;
        this.preco = preco;
        this.inicioT = inicioT;
        this.fimT = fimT;
    }

    public Viagem(Viagem viagem) {
        inicioL = viagem.inicioL;
        fimL = viagem.fimL;
        classificacao = viagem.classificacao;
        tempo = viagem.tempo;
        preco = viagem.preco;
        inicioT = viagem.inicioT;
        fimT = viagem.fimT;
    }

    public Localizacao getInicioL() {
        return this.inicioL;
    }
        
    public Localizacao getFimL() {
        return this.fimL;
    }

    public int getClassificacao() {
        return this.classificacao;
    }

    public double getTempo(){
        return this.tempo;
    } 

    public double getPreco(){
        return this.preco;
    }
    
    public Calendar getInicioT() {
		return this.inicioT;
	}
        
	public Calendar getFimT() {
		return this.fimT;
	}

    public void setInicioL(Localizacao l){
        this.inicioL = l;
    }

    public void setFimL(Localizacao l){
        this.fimL = l;
    }

    public void setClassificacao(int x){
        this.classificacao = x;
    }
   

    public void setTempo(double x){
        this.tempo = x;
    }

    public void setPreco(double x){
        this.preco = x;
    }
    
    public void setInicioT(Calendar c){
        this.inicioT = c;
    }

    public void setFimL(Calendar c){
        this.fimT = c;
    }
    
    public Viagem clone(){
    return new Viagem(this);
    }

    public boolean equals(Object obj){
       if (obj == this)
           return true;
       if (obj == null || obj.getClass() != this.getClass())
         return false;
       Viagem m = (Viagem) obj;
       return m.getInicioL() == this.inicioL
       && m.getClassificacao() == this.classificacao
       && m.getFimL() == this.fimL 
       && m.getTempo() == this.tempo
       && m.getPreco() == this.preco;
     }

    public String toString (){
       StringBuilder sb = new StringBuilder();
       sb.append("O inicioL foi em " + this.inicioL +"\n");
       sb.append("O fimL foi em :" +this.fimL +"\n");
       sb.append("O tempo da viagem foi " +this.tempo +"\n");
       sb.append("O preço foi " +this.preco +"\n");
       return sb.toString();
     }
}