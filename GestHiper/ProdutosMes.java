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
    private TreeMap<String, ArrayList<String>> produto_cliente;
    private HashMap<String, Integer> comprasN;  
    private HashMap<String, Integer> comprasP;
    private HashMap<String, Double> factN;  
    private HashMap<String, Double> factP;   
    
    /**
     * Construtores
     */
    public ProdutosMes() {
        this.compras_produto=new HashMap<String, Integer>();
        this.factProduto=new HashMap<String, Double>();
        this.produto_cliente=new TreeMap<String, ArrayList<String>>(new StringCompare());
        this.comprasN=new HashMap<String, Integer>();
        this.comprasP=new HashMap<String, Integer>();
        this.factN=new HashMap<String, Double>();
        this.factP=new HashMap<String, Double>();
    }
    
    public ProdutosMes(ProdutosMes cm) {
        this.compras_produto=cm.getComprasProduto();
        this.factProduto=cm.getFactProduto();
        this.produto_cliente=cm.getProdutoCliente();
        this.comprasN=cm.getComprasN();
        this.comprasP=cm.getComprasP();
        this.factN=cm.getFactN();
        this.factP=cm.getFactP();
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
    
    public TreeMap<String, ArrayList<String>> getProdutoCliente() {
        return this.produto_cliente;
    }
    
    public HashMap<String, Integer> getComprasN() {
        return this.comprasN;
    }
    
    public HashMap<String, Integer> getComprasP() {
        return this.comprasP;
    }
    
    public HashMap<String, Double> getFactN() {
        return this.factN;
    }
    
    public HashMap<String, Double> getFactP() {
        return this.factP;
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
    
    public void setProdutoCliente(TreeMap<String, ArrayList<String>> produto_cliente) {
        this.produto_cliente=produto_cliente;
    }
    
    public void setComprasN(HashMap<String, Integer> comprasN) {
        this.comprasN=comprasN;
    }
    
    public void setComprasP(HashMap<String, Integer> comprasP) {
        this.comprasP=comprasP;
    }
    
    public void setFactN(HashMap<String, Double> factN) {
        this.factN=factN;
    }
    
    public void setFactP(HashMap<String, Double> factP) {
        this.factP=factP;
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
        ArrayList<String> prod=new ArrayList<String>();
        Iterator<Map.Entry<String, ArrayList<String>>> it=this.produto_cliente.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, ArrayList<String>> elem=it.next();
            if(elem.getKey().equals(produto)) {
                ArrayList<String> list=elem.getValue();
                for(String s: list) {
                    if(!prod.contains(s)) prod.add(s);
                }
            }
        }
        return prod.size();
    }
    
    /**
     * Devolve o número de compras do produto em modo N
     */
    public int comprasModoN(String produto) {
        if(this.comprasN.containsKey(produto)) return this.comprasN.get(produto);
        else return 0;
    }
    
    /**
     * Devolve a faturação em modo N
     */
    public double factModoN(String produto) {
        if(this.factN.containsKey(produto)) return this.factN.get(produto);
        else return 0.0;
    }
    
    /**
     * Devolve o número de compras do produto em modo P
     */
    public int comprasModoP(String produto) {
        if(this.comprasP.containsKey(produto)) return this.comprasP.get(produto);
        else return 0;
    }
    
    /**
     * Devolve a faturação em modo P
     */
    public double factModoP(String produto) {
        if(this.factP.containsKey(produto)) return this.factP.get(produto);
        else return 0.0;
    }
    
    /**
     * Faz a leitura dos ficheiros e atualiza a classe
     */
    public void leitura(String codigo_cliente, String codigo_produto, double preco, int quantidade_comprada, String modo) {
         if(!this.produto_cliente.containsKey(codigo_produto)) {
             ArrayList<String> list=new ArrayList<String>();
             list.add(codigo_cliente);
             this.produto_cliente.put(codigo_produto, list);
         }
         else {
             ArrayList<String> lista=this.produto_cliente.get(codigo_produto);
             lista.add(codigo_cliente);
             this.produto_cliente.remove(codigo_produto);
             this.produto_cliente.put(codigo_produto, lista);
         }
         if(!this.compras_produto.containsKey(codigo_produto)) this.compras_produto.put(codigo_produto, 1);
         else {
             int numCompras=this.compras_produto.get(codigo_produto)+1;
             this.compras_produto.remove(codigo_produto); 
             this.compras_produto.put(codigo_produto, numCompras);
         }
         if(!this.factProduto.containsKey(codigo_produto)) this.factProduto.put(codigo_produto, quantidade_comprada*preco);
         else {
             double factProd=this.factProduto.get(codigo_produto)+(quantidade_comprada*preco);
             this.factProduto.remove(codigo_produto); 
             this.factProduto.put(codigo_produto, factProd);  
         }
         if(modo.equals("N")) {
             if(!this.comprasN.containsKey(codigo_produto)) this.comprasN.put(codigo_produto, 1);
             else {
                 int compN=this.comprasN.get(codigo_produto)+1;
                 this.comprasN.remove(codigo_produto);
                 this.comprasN.put(codigo_produto, compN);
             }
             if(!this.factN.containsKey(codigo_produto)) this.factN.put(codigo_produto, quantidade_comprada*preco);
             else {
                 double faturaçãoN=this.factN.get(codigo_produto)+(quantidade_comprada*preco);
                 this.factN.remove(codigo_produto);
                 this.factN.put(codigo_produto, faturaçãoN);
             }
         }
         else {
             if(!this.comprasP.containsKey(codigo_produto)) this.comprasP.put(codigo_produto, 1);
             else {
                 int compP=this.comprasP.get(codigo_produto)+1;
                 this.comprasP.remove(codigo_produto);
                 this.comprasP.put(codigo_produto, compP);
             }
             if(!this.factP.containsKey(codigo_produto)) this.factP.put(codigo_produto, quantidade_comprada*preco);
             else {
                 double faturaçãoP=this.factP.get(codigo_produto)+(quantidade_comprada*preco);
                 this.factP.remove(codigo_produto);
                 this.factP.put(codigo_produto, faturaçãoP);
             }
         }
    }
}
