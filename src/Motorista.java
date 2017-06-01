import java.util.ArrayList;
import java.util.stream.Collectors;

public class Motorista extends Utilizador
{
    private int pontualidade;
    private int classificacao;
    private long kms;
    private boolean disponivel;
    private ArrayList<Integer> aval;
    
    public Motorista()
    {
        super("null","null","null","null","null");
        pontualidade = 0;
        classificacao = 0;
        kms = 0;
        disponivel = false;
        aval = new ArrayList<>();
    }    
        
        
        
    public Motorista(String email, String nome, String password, String morada, String dataNascimento,int pontualidade,int classificacao,long kms,boolean disponivel)
    {
        super(email,nome,password,morada,dataNascimento);
        this.pontualidade = pontualidade;
        this.classificacao = classificacao;
        this.kms = kms;
        this.disponivel = disponivel;
        this.aval = new ArrayList<>();
    }
    
    public Motorista(Motorista m){
        super(m);
        this.pontualidade = m.getPontualidade();
        this.classificacao = m.getClassificacao();
        this.kms = m.getKms();
        this.disponivel = m.getDisponivel();
        this.aval = m.getAval();
    }
    
    public int getPontualidade() {
        return this.pontualidade;
    }
    
    public int getClassificacao() {
        return this.classificacao;
    }
              
    public long getKms() {
        return this.kms;
    }
    
    public boolean getDisponivel() {
        return this.disponivel;
    }
    
    public ArrayList<Integer> getAval() {
        return this.aval
            .stream()
            .collect(Collectors.toCollection(ArrayList::new));
    }
           
    public void setPontualidade(int pontualidade) {
        this.pontualidade = pontualidade;
    }
    
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
    
    public void setKms(long kms) {
        this.kms = kms;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public void setAval(ArrayList<Integer> aval){
      this.aval = aval.stream().collect(Collectors.toCollection(ArrayList::new));
    }
    
    public void addAval(int aval) {
        this.aval.add(aval);
    }
    
    public Motorista clone (){
        return new Motorista(this);
    }

    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
          return false;
        Motorista m = (Motorista) obj;
        return m.getPontualidade() == pontualidade
        && m.getClassificacao() == classificacao
        && m.getKms() == kms 
        && m.getDisponivel() == disponivel;
      }

    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Pontualidade(0-100):" +pontualidade +"\n");
        sb.append("Classificacao(0-100):" +classificacao +"\n");
        sb.append("Kms percorridos:" +kms +"\n");
        sb.append("Encontra-se disponivel?" +disponivel +"\n");
        return sb.toString();
      }

    } 
    
                    