public class EmailException extends Exception{

    public EmailException()  {
        System.out.println("Email já se encontra no sistema");
    }
    
    public EmailException(String s)  {
        System.out.println(s);
    }
    
}
