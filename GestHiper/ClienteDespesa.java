
/**
 * Write a description of class ClienteDespesa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClienteDespesa
{
    // Nome do cliente
    private String cliente;
    // Despesa no produto
    private double despesa;
    // Quantidade comprada
    private int quantidade;

    /**
     * Constructor for objects of class ClienteDespesa
     */
    public ClienteDespesa(Compra compra)
    {
        cliente = compra.getCodigoCliente();
        despesa = compra.getPreco()*compra.getQuantidadeComprada();
        quantidade = compra.getQuantidadeComprada();
    }

    public void add(Compra compra)
    {
        this.despesa+= compra.getQuantidadeComprada() * compra.getPreco();
        this.quantidade+= compra.getQuantidadeComprada();
    }
    
    public int getQuantidade(){ return quantidade;  }
    public String getCliente(){ return cliente; }
    public double getDespesa(){ return despesa; }
}
