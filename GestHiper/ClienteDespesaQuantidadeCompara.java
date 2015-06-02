
import java.util.Comparator;
import java.io.Serializable;

/**
 * Comparador de 
 */
public class ClienteDespesaQuantidadeCompara implements Comparator<ClienteDespesa>, Serializable
{
    public int compare(ClienteDespesa c1, ClienteDespesa c2) {
        int n1 = c1.getQuantidade();
        int n2 = c2.getQuantidade();
        
        if(n1 > n2) return 1;
        else if(n1==n2) return -1;
        else return -1;
    }
}
