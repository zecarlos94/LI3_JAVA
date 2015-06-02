import java.util.Comparator;
import java.io.Serializable;

/**
 * Comparador de Strings
 */
public class StringCompare implements Comparator<String>, Serializable
{
   public int compare(String s1, String s2) {
       return s1.compareTo(s2);
   }
}
