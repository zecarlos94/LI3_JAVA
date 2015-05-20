
/**
 * Write a description of class ComparatorSalario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;

public class ComparatorNome implements Comparator<String>, Serializable {
  
   public int compare(String s1, String s2) {
     return s1.compareTo(s2);
     
   }
    
}
