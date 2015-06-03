import java.util.*;

/**
 * Informação mensal de um produto
 */
public class InfoProdutoMes
{
    /**
     *  Variáveis de classe
     */
    private static String normal="N";
    private static String promocao="P";
    
    public static String getNormal() {return normal;}
    public static String getPromocao() {return promocao;}
    
    /**
     *  Variáveis de instancia
     */
    private TreeSet<String> clientes; // Lista de clientes que compraram o produto
    private int comprasN; // Quantidade de compras normais
    private int comprasP; // Quantidade de compras em promoção
    private double factN; // Faturação em modo normal
    private double factP; // Faturação em modo de promoção
    
    public InfoProdutoMes() {
        this.clientes=new TreeSet<String>(new StringCompare());
        this.comprasN=0;
        this.comprasP=0;
        this.factN=0;
        this.factP=0;
    }
    
    public InfoProdutoMes(InfoProdutoMes p) {
        this.clientes=p.getClientes();
        this.comprasN=p.getComprasN();
        this.comprasP=p.getComprasP();
        this.factN=p.getFactN();
        this.factP=p.getFactP();
    }

    public void add(String codigo_produto, double preco, int quantidade_comprada, String modo, String codigo_cliente) {
        double fact=preco*quantidade_comprada;
        if(modo.equals(normal)) {
            this.comprasN++;
            this.factN+=fact;
        }
        else {
            this.comprasP++;
            this.factP+=fact;
        }
        this.clientes.add(codigo_cliente);
    }
    
    public int numeroClientesDistintos(){
           return clientes.size();
    }
    public int getComprasN(){   return comprasN;    }
    public int getComprasP(){   return comprasP;    }
    public double getFactN(){   return comprasN;    }
    public double getFactP(){   return factP;   }
    public TreeSet<String> getClientes(){   return clientes; }
    
    public InfoProdutoMes clone() {
        return new InfoProdutoMes(this);
    }
 
}
