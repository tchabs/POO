import java.io.Serializable;
import java.util.TreeSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.Object;

public class UMER implements Serializable
{   
    private Utilizador uConectado;
    private CatUtilizadores catU;
    private CatViaturas catV;
    private ArrayList<Empresa> catE;
    public UMER() {
        catU = new CatUtilizadores();
        catV = new CatViaturas();
    }

    public UMER(UMER u) {
        catU = new CatUtilizadores(u.catU);
        catV = new CatViaturas(u.catV);
    }
    
    public ArrayList<Empresa> getCatE(){
      return this.catE;
    }
    
    public Utilizador getUtilizadorC(){
        return this.uConectado;
    }
    
    public CatUtilizadores getCatU() {
        return this.catU.clone();
    }

    public CatViaturas getCatV() {
        return this.catV.clone();
    }

    public void setCatU(CatUtilizadores catU) {
        this.catU = catU;
    }

    public void setCatV(CatViaturas catV) {
        this.catV = catV;
    }

    public static UMER init() throws IOException,ClassNotFoundException,FileNotFoundException{
        ObjectInputStream save = new ObjectInputStream(new FileInputStream ("snap.data"));
        UMER u =  (UMER) save.readObject();
        save.close();
        return u;
    }

    public void save() throws IOException,ClassNotFoundException,FileNotFoundException{
        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream ("snap.data"));
        save.writeObject(this);
        save.flush();
        save.close();
    }
    
    public void signUp(Utilizador u) throws EmailException {
        if (catU.contem(u)) throw new EmailException();
        catU.insertUtilizador(u);
    }
    
    public void signIn(String email, String password) throws EmailException,PasswordException {
        Utilizador u=null;
        u = this.catU.findU(email);
        if(u==null) throw new EmailException();
        if (u.getPassword()!=password) throw new PasswordException();
        else uConectado = u;
    
    }
     
    public void sigOut() {
        this.uConectado = null;
    }
    
    public void addViatura(Viatura v) throws PermissionException,ViaturaException{
        if(uConectado.checkTipoU()!=2) throw new PermissionException();
        if(catV.contem(v)) throw new ViaturaException();
        catV.insertViatura(v);
    }
    
    public void addViaturaE(Viatura v, Empresa e) throws PermissionException{
        if(uConectado.checkTipoU()!=3 ) throw new PermissionException();
        e.insertViaturaE(v);
    }
    
    public void setDriver(Motorista m, Viatura v) throws PermissionException{
        if(uConectado.checkTipoU() !=2) throw new PermissionException();
        v.setMotorista(m);
    }
    
    public void setEDriver(Motorista m, Empresa e) throws PermissionException{
        if(uConectado.checkTipoU()!=3 ) throw new PermissionException();
        e.insertMotoristaE(m);
    }
    
    public void callACab(Localizacao fim,String matricula,Empresa empresa) throws ViaturaException{
        
        Viagem viagem = new Viagem();
        Viatura v = null;
        viagem.setInicioL(uConectado.getLocal());
        viagem.setFimL(fim);
        viagem.setInicioT(new GregorianCalendar());
            
        if(matricula=="near by") v = getNearBy(viagem.getInicioL(),empresa);
        else if (empresa==null) v = catV.findV(matricula);
        else v = empresa.getViaturas().findV(matricula);
        
        v.insertViagem(viagem);
        uConectado.insertViagem(viagem);
        v.getMotorista().insertViagem(viagem);
    }
    
    public Viatura getNearBy(Localizacao local,Empresa empresa) throws ViaturaException{
        
        if (empresa==null) return catV.getNearBy(local);
        return empresa.getViaturas().getNearBy(local);
    }
    
    public void setFeedback(int aval, Motorista m) throws PermissionException{
        if(uConectado.checkTipoU() != 1) throw new PermissionException();
        m.addAval(aval);
    }
    
    public List<Viagem> viagensCliente(Cliente c, GregorianCalendar i, GregorianCalendar f){
        return c.getCatViagens()
                .stream()
                .filter(v -> (v.getInicioT().after(i) && v.getInicioT().before(f))  
                             || v.getInicioL().equals(i) || v.getInicioL().equals(f))
                .collect(Collectors.toList());
    }
    
    public List<Viagem> viagensMotorista(Motorista m, GregorianCalendar i, GregorianCalendar f){
        return m.getCatViagens()
            .stream()
            .filter(v -> (v.getInicioT().after(i) && v.getInicioT().before(f))  
                             || v.getInicioL().equals(i) || v.getInicioL().equals(f))
            .collect(Collectors.toList());
    }
    
    public double totalFaturado(Viatura v, Empresa e){
      
        if(v==null) {
            return e.totalFaturado();
        }
        
        return catV.totalFaturado();


    }
    
    public static UMER leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));

        UMER t = (UMER) ois.readObject();

        ois.close();
        return t;
    }
}


