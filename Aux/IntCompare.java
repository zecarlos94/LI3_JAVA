import java.util.Comparator;
import java.io.Serializable;

/**
 * Comparador de inteiros
 */
public class IntCompare implements Comparator<Integer>, Serializable
{
    public int compare(Integer x, Integer y) {
        if(x>y) return 1;
        else if(x==y) return -1;
        else return -1;
    }
}