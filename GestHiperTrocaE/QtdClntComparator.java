import java.util.Comparator;
import java.io.Serializable;

public class QtdClntComparator implements Comparator<InfoCliente>, Serializable
{
    public int compare(InfoCliente p1, InfoCliente p2) {
        if(p1.getQuantidade()>p2.getQuantidade()) return -1;
        else if(p1.getQuantidade()<p2.getQuantidade()) return 1;
        else {
            if(p1.getCodigo().compareTo(p2.getCodigo())<0) return -1;
            else if(p1.getCodigo().compareTo(p2.getCodigo())>0) return 1;
            else return 0;
        }
    }
}