/**
 * Despesas do cliente
 */
public class ClienteDespesa
{
    private String cliente; // Nome do cliente
    private double despesa; // Despesa no produto
    private int quantidade; // Quantidade comprada

    public ClienteDespesa() {
        this.cliente="N/A";
        this.despesa=0.00;
        this.quantidade=0;
    }
    
    public ClienteDespesa(String cliente, double despesa, int quantidade) {
        this.cliente=cliente;
        this.despesa=despesa;
        this.quantidade=quantidade;
    }
    
    public ClienteDespesa(ClienteDespesa c) {
        this.cliente=c.getCliente();
        this.despesa=c.getDespesa();
        this.quantidade=c.getQuantidade();
    }
    
    public int getQuantidade(){ return quantidade;  }
    public String getCliente(){ return cliente; }
    public double getDespesa(){ return despesa; }
    
    public void add(double preco, int quantidade) {
        this.despesa+=preco*quantidade;
        this.quantidade+=quantidade;
    }
    
    public ClienteDespesa clone() {
        return new ClienteDespesa(this);
    }
}
