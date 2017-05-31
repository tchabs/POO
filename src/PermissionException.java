public class PermissionException extends Exception{

    public PermissionException()  {
        System.out.println("Permission denied");
    }
    
    public PermissionException(String s)  {
        System.out.println(s);
    }
    
}


