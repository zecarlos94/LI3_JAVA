import java.util.Comparator;
import java.io.Serializable;

/**
 * Comparador de inteiros, ordem decrescente
 */
public class IntDecrescenteCompare implements Comparator<Integer>, Serializable
{
    public int compare(Integer x, Integer y) {
        if(x>y) return -1;
        else if(x==y) return 0;
        else return 1;
    }
}