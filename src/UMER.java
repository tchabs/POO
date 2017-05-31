import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class UMER implements Serializable
{
    private CatUtilizadores catU;
    private CatViaturas catV;

    public UMER() {
        catU = new CatUtilizadores();
        catV = new CatViaturas();
    }

    public UMER(UMER u) {
        catU = new CatUtilizadores(u.catU);
        catV = new CatViaturas(u.catV);
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

	public UMER iniciar() throws IOException,ClassNotFoundException,FileNotFoundException{
		ObjectInputStream save = new ObjectInputStream(new FileInputStream ("snap.data"));
		UMER u =  (UMER) save.readObject();
		save.close();
		return u;
	}

	public void gravar() throws IOException,ClassNotFoundException,FileNotFoundException{
		ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream ("snap.data"));
		save.writeObject(this);
		save.flush();
		save.close();
	}



}
