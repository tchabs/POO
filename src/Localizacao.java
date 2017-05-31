import java.io.Serializable;

public class Localizacao implements Serializable
{
    private int x;
    private int y;
    
    public Localizacao(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public double distCalc(Localizacao l){
        double x = Math.pow(this.x - l.getX(), 2); 
        double y = Math.pow(this.y - l.getY(), 2);

        return Math.sqrt(x+y);
    }
    
    public boolean equals(Object obj){
       if (obj == this) return true;
       if (obj == null || obj.getClass() != this.getClass()) return false;
       Localizacao v = (Localizacao) obj;
       return (this.x == v.getX() && this.y == v.getY());
    }
    
    public String toString (){
        StringBuilder s = new StringBuilder();
        s.append("A posição em X é: " + x +";\n");
        s.append("A posição em Y é: " + y +";\n");
        return s.toString();
    }
}
