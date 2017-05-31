public class ViaturaException extends Exception{

    public ViaturaException()  {
        System.out.println("Viatura já registada");
    }
    
    public ViaturaException(String s)  {
        System.out.println(s);
    }
    
}


