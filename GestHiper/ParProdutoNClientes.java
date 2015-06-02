
/**
 * Par de auxilio para a query 8
 */
public class ParProdutoNClientes
{
    private String produto;
    private int clientes;

    /**
     * Constructor for objects of class ParProdutoNClientes
     */
    public ParProdutoNClientes(String produto,int clientes)
    {
        produto = produto;
        clientes = clientes;
    }

    public String getProduto() {    return this.produto;}
    public int getNClientes(){  return this.clientes;}
}
