import java.util.*;
import java.io.*;

/**
 * Classe que contém todas as informações mensais das compras
 */
public class ClientesMes implements Serializable
{
    /**
     * Variáveis de instância
     */
    private HashMap<String, Integer> compras_cliente;
    private HashMap<String, Double> gastos_cliente;
    private TreeMap<String, String> cliente_produto;
    
    /**
     * Construtores
     */
    public ClientesMes() {
        this.compras_cliente=new HashMap<String, Integer>();
        this.gastos_cliente=new HashMap<String, Double>();
        this.cliente_produto=new TreeMap<String, String>(new StringCompare());
    }
    
    public ClientesMes(ClientesMes cm) {
        this.compras_cliente=cm.getComprasCliente();
        this.gastos_cliente=cm.getGastosCliente();
        this.cliente_produto=cm.getClienteProduto();
    }
    
    /**
     * Getters
     */
    public HashMap<String, Integer> getComprasCliente() {
        return this.compras_cliente;
    }
    
    public HashMap<String, Double> getGastosCliente() {
        return this.gastos_cliente;
    }
    
    public TreeMap<String, String> getClienteProduto() {
        return this.cliente_produto;
    }
    
    /**
     * Setters
     */
    public void setComprasCliente(HashMap<String, Integer> compras_cliente) {
        this.compras_cliente=compras_cliente;
    }
    
    public void setGastosCliente(HashMap<String, Double> gastos_cliente) {
        this.gastos_cliente=gastos_cliente;
    }
    
    public void setClienteProduto(TreeMap<String, String> cliente_produto) {
        this.cliente_produto=cliente_produto;
    }
    
    /**
     * Clone
     */
    public ClientesMes clone() {
        return new ClientesMes(this);
    }
    
    /**
    * Devolve o número de produtos distintos comprados por um cliente num dado mês
    */
    public int clientesProdMes(String cliente) {
       int total=0;
       HashSet<String> prod=new HashSet<String>();
       Iterator<Map.Entry<String, String>> it=this.cliente_produto.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<String, String> elem=it.next();
           if(elem.getKey().equals(cliente) && !prod.contains(elem.getValue())) {
               total++;
               prod.add(elem.getValue());
           }
       }
       return total;
    }
    
    /**
     * Faz a leitura dos ficheiros e atualiza a classe classe
     */
    public void leitura(String codigo_cliente, String codigo_produto, double preco, int quantidade_comprada) {
         if(!this.compras_cliente.containsKey(codigo_cliente)) {
             this.compras_cliente.put(codigo_cliente, 1);
             this.gastos_cliente.put(codigo_cliente, quantidade_comprada*preco);
         }
         else {
             int numCompras=this.compras_cliente.get(codigo_cliente)+1;
             double factClnt=this.gastos_cliente.get(codigo_cliente)+(quantidade_comprada*preco);
             this.compras_cliente.remove(codigo_cliente); this.compras_cliente.put(codigo_cliente, numCompras);
             this.gastos_cliente.remove(codigo_cliente); this.gastos_cliente.put(codigo_cliente, factClnt);        
         }
         this.cliente_produto.put(codigo_cliente, codigo_produto); 
    }
}
