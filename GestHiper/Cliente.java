import java.util.*;

public class Cliente
{
    /**
     * Variáveis de Instância
     */
    private int numero_compras;
    private double gastos;
    private TreeMap<String, Integer> produtos_comprados;
    
    /**
     * Construtores
     */
    public Cliente() {
        this.numero_compras=0;
        this.gastos=0;
        this.produtos_comprados=new TreeMap<String, Integer>();
    }
    
    public Cliente(int numero_compras, double gastos, TreeMap<String, Integer> produtos_comprados) {
        this.numero_compras=numero_compras;
        this.gastos=gastos;
        this.produtos_comprados=produtos_comprados;
    }
    
    public Cliente(Cliente c) {
        this.numero_compras=c.getNumeroCompras();
        this.gastos=c.getGastos();
        this.produtos_comprados=c.getProdutosComprados();
    }
    
    /**
     * Getters e Setters
     */
    public int getNumeroCompras() {
        return this.numero_compras;
    }
    
    public double getGastos() {
        return this.gastos;
    }
    
    public TreeMap<String, Integer> getProdutosComprados() {
        return this.produtos_comprados;
    }
    
    public void setNumeroCompras(int numero_compras) {
        this.numero_compras=numero_compras;
    }
    
    public void setGastos(double gastos) {
        this.gastos=gastos;
    }
    
    public void setProdutosComprados(TreeMap<String, Integer> produtos_comprados) {
        this.produtos_comprados=produtos_comprados;
    }
    
    /**
     * Métodos Auxiliares
     */
    public boolean equals(Cliente c) {
        if(this.numero_compras==c.getNumeroCompras() && this.gastos==c.getGastos() && this.produtos_comprados.equals(c.getProdutosComprados())) return true;
        else return false;
    }
    
    public String toString() {
        return new String("Número de compras do cliente=" +this.numero_compras+ ", Gastos=" +this.gastos+ ", Produtos comprados e respetiva quantidade: " 
                                    +this.produtos_comprados.toString());
    }
    
    public Cliente clone() {
        return new Cliente(this);
    }
}
