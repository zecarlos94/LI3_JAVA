import java.util.Comparator;
import java.io.Serializable;

/**
 * Comparador de inteiros
 */
public class NumCompare implements Comparator<Integer>, Serializable
{
    /**
     * Compara dois inteiros
     */
    public int compare(Integer x, Integer y) {
        if(x>y) return 1;
        else if(x==y) return 0;
        else return -1;
    }
}