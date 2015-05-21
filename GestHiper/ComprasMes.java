import java.util.*;

/**
 * Compras efetuadas em cada mês
 */
public class ComprasMes
{
    /**
     * Variáveis de Instância
     */
    private double faturacao;
    private int numero_compras;
    private HashMap<String, Cliente> clientes_mes;
    private HashMap<String, Produto> produtos_mes;
    
    /**
     * Construtores
     */
    public ComprasMes() {
        this.faturacao=0;
        this.numero_compras=0;
        this.clientes_mes=new HashMap<String, Cliente>();
        this.produtos_mes=new HashMap<String, Produto>();
    }
    
    public ComprasMes(double faturacao, int numero_compras, HashMap<String, Cliente> clientes_mes, HashMap<String, Produto> produtos_mes) {
        this.faturacao=faturacao;
        this.numero_compras=numero_compras;
        setClientesMes(clientes_mes);
        setProdutosMes(produtos_mes);
    }
    
    public ComprasMes(ComprasMes cm) {
        this.faturacao=cm.getFaturacao();
        this.numero_compras=cm.getNumeroCompras();
        this.clientes_mes=cm.getClientesMes();
        this.produtos_mes=cm.getProdutosMes();
    }
    
    /**
     * Getters e Setters
     */
    public double getFaturacao() {
        return this.faturacao;
    }
    
    public int getNumeroCompras() {
        return this.numero_compras;
    }
    
    public HashMap<String, Cliente> getClientesMes() {
        HashMap<String, Cliente> aux=new HashMap<String, Cliente>();
        Set<Map.Entry<String, Cliente>> kset=this.clientes_mes.entrySet();
        Iterator<Map.Entry<String, Cliente>> it=kset.iterator();
        while(it.hasNext()) {
            Map.Entry<String, Cliente> elem=it.next();
            aux.put(elem.getKey(), elem.getValue().clone());  
        }
        return aux;
    }
    
    public HashMap<String, Produto> getProdutosMes() {
        HashMap<String, Produto> aux=new HashMap<String, Produto>();
        Set<Map.Entry<String, Produto>> kset=this.produtos_mes.entrySet();
        Iterator<Map.Entry<String, Produto>> it=kset.iterator();
        while(it.hasNext()) {
            Map.Entry<String, Produto> elem=it.next();
            aux.put(elem.getKey(), elem.getValue().clone());  
        }
        return aux;
    }
    
    public void setFaturacao(double faturacao) {
        this.faturacao=faturacao;
    }
    
    public void setNumeroCompras(int numero_compras) {
        this.numero_compras=numero_compras;
    }
    
    public void setClientesMes(HashMap<String, Cliente> clientes_mes) {
        Set<Map.Entry<String, Cliente>> kset=clientes_mes.entrySet();
        Iterator<Map.Entry<String, Cliente>> it=kset.iterator();
        while(it.hasNext()) {
            Map.Entry<String, Cliente> elem=it.next();
            this.clientes_mes.put(elem.getKey(), elem.getValue().clone());  
        }
    }
    
    public void setProdutosMes(HashMap<String, Produto> produtos_mes) {
        Set<Map.Entry<String, Produto>> kset=produtos_mes.entrySet();
        Iterator<Map.Entry<String, Produto>> it=kset.iterator();
        while(it.hasNext()) {
            Map.Entry<String, Produto> elem=it.next();
            this.produtos_mes.put(elem.getKey(), elem.getValue().clone());
        }
    }
    
    /**
     * Outros métodos auxiliares
     */
    public boolean equals(ComprasMes cm) {
        if(this.faturacao==cm.getFaturacao() && this.numero_compras==cm.getNumeroCompras() && this.clientes_mes.equals(cm.getClientesMes()) 
           && this.produtos_mes.equals(cm.getProdutosMes())) return true;
        else return false;
    }
    
    public String toString() {
        return new String("Faturação=" +this.faturacao+ ", Número de Compras=" +this.numero_compras+ ", Clientes neste mês: " +this.clientes_mes.toString()+
                                     ", Produtos vendidos este mês: " +this.produtos_mes.toString());
    }
    
    public ComprasMes clone() {
        return new ComprasMes(this);
    }
}
