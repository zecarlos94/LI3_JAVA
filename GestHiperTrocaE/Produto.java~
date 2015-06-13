import java.util.*;

public class Produto
{
    /**
     * Variáveis de instância
     */
    private int numero_compras;
    private double faturacao;
    private int comprasN;
    private int comprasP;
    private TreeMap<String, Integer> clientes_compradores;
    
    /**
     * Construtores
     */
    public Produto() {
        this.numero_compras=0;
        this.faturacao=0;
        this.comprasN=0;
        this.comprasP=0;
        this.clientes_compradores=new TreeMap<String, Integer>();
    }
    
    public Produto(int numero_compras, double faturacao, int comprasN, int comprasP, TreeMap <String, Integer> clientes_compradores) {
        this.numero_compras=numero_compras;
        this.faturacao=faturacao;
        this.comprasN=comprasN;
        this.comprasP=comprasP;
        this.clientes_compradores=clientes_compradores;
    }
    
    public Produto(Produto p) {
        this.numero_compras=p.getNumeroCompras();
        this.faturacao=p.getFaturacao();
        this.comprasN=p.getComprasN();
        this.comprasP=p.getComprasP();
        this.clientes_compradores=p.getClientesCompradores();
    }
    
    /**
     * Getters e Setters
     */
    public int getNumeroCompras() {
        return this.numero_compras;
    }
    
    public double getFaturacao() {
        return this.faturacao;
    }
    
    public int getComprasN() {
        return this.comprasN;
    }
    
    public int getComprasP() {
        return this.comprasP;
    }
    
    public TreeMap<String, Integer> getClientesCompradores() {
        return this.clientes_compradores;
    }
    
    public void setNumeroCompras(int numero_compras) {
        this.numero_compras=numero_compras;
    }
    
    public void setFaturacao(double faturacao) {
        this.faturacao=faturacao;
    }
    
    public void setComprasN(int comprasN) {
        this.comprasN=comprasN;
    }
    
    public void setComprasP(int comprasP) {
        this.comprasP=comprasP;
    }
    
    public void setClientesCompradores(TreeMap<String, Integer> clientes_compradores) {
        this.clientes_compradores=clientes_compradores;
    }
    
    /**
     * Métodos auxiliares
     */
    public boolean equals(Produto p) {
        if(this.numero_compras==p.getNumeroCompras() && this.faturacao==p.getFaturacao() && this.comprasN==p.getComprasN() && this.comprasP==p.getComprasP()
           && this.clientes_compradores.equals(p.getClientesCompradores())) return true;
        else return false;
    }
    
    public String toString() {
        return new String("Número de compras=" +this.numero_compras+ ", Faturação=" +this.faturacao+ ", Número de compras em modo N=" +this.comprasN+
                                     ", Número de compras em modo P=" +this.comprasP+ ", Clientes compradores: " +this.clientes_compradores.toString());
    }
    
    public Produto clone() {
        return new Produto(this);
    }
}   