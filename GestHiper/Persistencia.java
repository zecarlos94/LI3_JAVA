import java.util.*;
import java.io.*;

/**
 * Efetua a leitura e escrita de objetos binários
 */
public class Persistencia
{
    public static Hipermercado leBinario(String filename) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream(filename));
        Hipermercado e = (Hipermercado) ois.readObject();
        ois.close();
        return e;
    }
}
