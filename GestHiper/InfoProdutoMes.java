import java.util.*;
/**
 * Write a description of class InfoProdutoMes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoProdutoMes
{
    /**
     *  Variáveis de classe
     */
    private String normal = "N";
    private String promocao = "P";
    
    /**
     *  Variáveis de instancia
     */
    // Lista de clientes que compraram o produto
    private TreeSet<String> clientes;
    //Quantidade de compras sem Promoçaoo
    private int comprasN;
    // Com promoçao
    private int comprasP;
    // Facturaçao em compras sem Promoçao
    private double factN;
    // Com promoçao
    private double factP;
    
    public InfoProdutoMes()
    {
        clientes = new TreeSet<String>(new StringCompare());
        comprasN = 0;
        comprasP = 0;
        factN = 0;
        factP = 0;
    }

    public void add(Compra c)
    {
        double fact = c.getPreco() * c.getQuantidadeComprada();
        if(c.getModo().equals(normal))
        {
            this.comprasN++;
            this.factN+=fact;
        }
        else
        {
            this.comprasP++;
            this.factP+=fact;
        }
        clientes.add(c.getCodigoCliente());
    }
    
    public int numeroClientesDistintos(){
           return clientes.size();
    }
    public int getComprasN(){   return comprasN;    }
    public int getComprasP(){   return comprasP;    }
    public double getFactN(){   return comprasN;    }
    public double getFactP(){   return factP;   }
    public TreeSet<String> getClientes(){   return clientes; }
 
}
