public class Cliente extends Utilizador
{
    public Cliente (){
      super ("null","null","null","null","null");
    }
    
    public Cliente(String email, String nome, String password, String morada, String dataNascimento)
    {
        super(email,nome,password,morada,dataNascimento);
    }
    
    public Cliente (Cliente c){
      super (c);
    }
    
    public Cliente clone (){
      return new Cliente (this);
    }

    public boolean equals(Object obj){
      if (obj == this) return true;
      return false;  
    }
}
