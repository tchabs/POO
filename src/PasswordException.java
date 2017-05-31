public class PasswordException extends Exception{

    public PasswordException()  {
        System.out.println("Password incorrecta");
    }
    
    public PasswordException(String s)  {
        System.out.println(s);
    }
    
}

