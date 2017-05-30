import java.io.Serializable;

public abstract class Utilizador implements Serializable
{
    protected String email;
    protected String nome;
    protected String password;
    protected String morada;
    protected String dataNascimento;
    protected Localizacao local;
    
    
    public Utilizador()
    {
        email = "null";
        nome = "null";
        password = "null";
        morada = "null";
        dataNascimento = "null";
        local = null;
    }
    
    public Utilizador(String email, String nome, String password, String morada, String dataNascimento)
    {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.local = null;
    }    
    
    public Utilizador (Utilizador a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();
        this.dataNascimento = a.getDataNascimento();
        this.local = a.getLocal();
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getMorada() {
        return this.morada;
    }
    
    public String getDataNascimento() {
        return this.dataNascimento;
    }
    
    public Localizacao getLocal() throws NullPointerException{
        if(this.local == null) throw new NullPointerException("No location defined");
        return this.local;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setMorada(String morada) {
        this.morada = morada;
    }
    
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public void setLocal(Localizacao local) {
        this.local = local;
    }
    
    public abstract Utilizador clone();
    
    public boolean equals(Object obj){
      if (obj == this)
        return true;
        
      if (obj == null || obj.getClass() != this.getClass())
        return false;
        
      Utilizador u = (Utilizador) obj;
      
      return u.getEmail().equals(email)
      && u.getNome().equals(nome)
      && u.getPassword().equals(password)
      && u.getMorada().equals(morada)
      && u.getDataNascimento().equals(dataNascimento)
      && u.getLocal().equals(local);
    }
    
    public String toString (){
      StringBuilder sb = new StringBuilder ();
      sb.append("Email:").append(email).append("\n");
      sb.append("Nome:").append(nome).append("\n");
      sb.append("Password:").append(password).append("\n");
      sb.append("Morada:").append(morada).append("\n");
      sb.append("Data de nascimento:").append(dataNascimento).append("\n");
      if (local != null) sb.append("Localizacao:").append(local).append("\n");
      return sb.toString();
    }
    
}
