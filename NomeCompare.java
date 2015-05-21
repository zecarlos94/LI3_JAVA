import java.util.*;
import java.util.Comparator;
import java.io.Serializable;

/**
 * Escreva a descrição da classe NomeCompare aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class NomeCompare implements Comparator<String>,Serializable
{
    public int compare(String a, String b){
       return a.compareTo(b);
    }
}
