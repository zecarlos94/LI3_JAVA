import java.util.*;
import java.io.*;

/**
 * Classe que contém todas as informações mensais dos produtos
 */
public class ProdutosMes implements Serializable
{
    /**
     * Variáveis de instância
     */
    private HashMap<String, Integer> compras_produto;
    private HashMap<String, Double> factProduto;
    private TreeMap<String, String> produto_cliente;
    
    /**
     * Construtores
     */
    public ProdutosMes() {
        this.compras_produto=new HashMap<String, Integer>();
        this.factProduto=new HashMap<String, Double>();
        this.produto_cliente=new TreeMap<String, String>(new StringCompare());
    }
    
    public ProdutosMes(ProdutosMes cm) {
        this.compras_produto=cm.getComprasProduto();
        this.factProduto=cm.getFactProduto();
        this.produto_cliente=cm.getProdutoCliente();
    }
    
    /**
     * Getters
     */
    public HashMap<String, Integer> getComprasProduto() {
        return this.compras_produto;
    }
    
    public HashMap<String, Double> getFactProduto() {
        return this.factProduto;
    }
    
    public TreeMap<String, String> getProdutoCliente() {
        return this.produto_cliente;
    }
    
    /**
     * Setters
     */
    public void setComprasProduto(HashMap<String, Integer> compras_produto) {
        this.compras_produto=compras_produto;
    }
    
    public void setFactProduto(HashMap<String, Double> factProduto) {
        this.factProduto=factProduto;
    }
    
    public void setProdutoCliente(TreeMap<String, String> produto_cliente) {
        this.produto_cliente=produto_cliente;
    }
    
    /**
     * Clone
     */
    public ProdutosMes clone() {
        return new ProdutosMes(this);
    }
    
    /**
    * Devolve o número de clientes que compraram o produto num dado mês
    */
    public int produtosClntMes(String produto) {
       int total=0;
       HashSet<String> clnt=new HashSet<String>();
       Iterator<Map.Entry<String, String>> it=this.produto_cliente.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<String, String> elem=it.next();
           if(elem.getKey().equals(produto) && !clnt.contains(elem.getValue())) {
               total++;
               clnt.add(elem.getValue());
           }
       }
       return total;
    }
    
    /**
     * Faz a leitura dos ficheiros e atualiza a classe classe
     */
    public void leitura(String codigo_cliente, String codigo_produto, double preco, int quantidade_comprada) {
         if(!this.compras_produto.containsKey(codigo_produto)) {
             this.compras_produto.put(codigo_produto, 1);
             this.factProduto.put(codigo_produto, quantidade_comprada*preco);
         }
         else {
             int numCompras=this.compras_produto.get(codigo_produto)+1;
             double factProd=this.factProduto.get(codigo_produto)+(quantidade_comprada*preco);
             this.compras_produto.remove(codigo_produto); this.compras_produto.put(codigo_produto, numCompras);
             this.factProduto.remove(codigo_produto); this.factProduto.put(codigo_produto, factProd);        
         }
         this.produto_cliente.put(codigo_produto, codigo_cliente); 
    }
}
