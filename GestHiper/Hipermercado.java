import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

/**
 * Classe que contém toda a informação do Hipermercado
 */
public class Hipermercado implements Serializable
{
    /**
     * Declaração de variáveis
     */
    private HashSet<String> catalogo_clientes;
    private HashSet<String> catalogo_produtos;
    private TreeMap<Integer, Compra> compras; 
    private TreeSet<String> produtos_inativos;
    private TreeSet<String> clientes_inativos;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.catalogo_clientes=new HashSet<String>();
        this.catalogo_produtos=new HashSet<String>();
        this.compras=new TreeMap<Integer, Compra>(new IntCompare()); 
        this.produtos_inativos=new TreeSet<String>(new StringCompare());
        this.clientes_inativos=new TreeSet<String>(new StringCompare());
    }
    
   public Hipermercado(Hipermercado h) {
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.compras=h.getCompras(); 
        this.produtos_inativos=h.getProdutosInativos();
        this.clientes_inativos=h.getClientesInativos();
   }
   
   /**
    * Getters
    */
   public HashSet<String> getCatalogoClientes() {
       return this.catalogo_clientes;
   }
   
   public HashSet<String> getCatalogoProdutos() {
       return this.catalogo_produtos;
   }
   
   public TreeMap<Integer, Compra> getCompras() {
       TreeMap<Integer, Compra> aux=new TreeMap<Integer, Compra>(new IntCompare()); 
       Iterator<Map.Entry<Integer, Compra>> it=this.compras.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<Integer, Compra> elem=it.next();
           aux.put(elem.getKey(), elem.getValue().clone());
       }
       return aux;
   }
   
   public TreeSet<String> getProdutosInativos() {
       return this.produtos_inativos;
    }
    
   public TreeSet<String> getClientesInativos() {
       return this.clientes_inativos;
   }
   
   /**
    * Setters
    */
   public void setCatalogoClientes(HashSet<String> catalogo_clientes) {
       this.catalogo_clientes=catalogo_clientes;
   }
   
   public void setCatalogoProdutos(HashSet<String> catalogo_produtos) {
       this.catalogo_produtos=catalogo_produtos;
   }
   
   public void setCompras(TreeMap<Integer, Compra> compras) {
       TreeMap<Integer, Compra> aux=new TreeMap<Integer, Compra>(new IntCompare()); 
       Iterator<Map.Entry<Integer, Compra>> it=compras.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<Integer, Compra> elem=it.next();
           aux.put(elem.getKey(), elem.getValue().clone());
       }
       this.compras=aux;
   }
   
   public void setProdutosInativos(TreeSet<String> produtos_inativos) {
       this.produtos_inativos=produtos_inativos;
   }
   
   public void setClientesInativos(TreeSet<String> clientes_inativos) {
       this.clientes_inativos=clientes_inativos;
   }
   
   /**
    * Clone
    */
   public Hipermercado clone() {
       return new Hipermercado(this);
   }
   
   /**
    * Efetua a leitura dos ficheiros de clientes e de produtos
    */
   public void readCatalogos(String ficheiro, String type) {
        HashSet<String> catalogo=new HashSet<String>();
        BufferedReader br=null;
        try {
            String line;
            br=new BufferedReader(new FileReader(ficheiro));
            while((line = br.readLine())!=null) {
                catalogo.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null) br.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        if(type.equals("clientes")) this.catalogo_clientes=catalogo;
        else this.catalogo_produtos=catalogo;
   }
   
   /**
    * Faz a leitura do ficheiro de compras
    */
   public void readCompras(String ficheiro) {
        BufferedReader br=null;
        double faturacao;
        int numero_compras;
        try {
            String line;
            br=new BufferedReader(new FileReader(ficheiro));
            while((line = br.readLine())!=null) {
                StringTokenizer st=new StringTokenizer(line, " ");
                String codigo_produto=st.nextElement().toString(); 
                Double preco=Double.parseDouble(st.nextElement().toString());
                Integer quantidade_comprada=Integer.parseInt(st.nextElement().toString());
                String modo=st.nextElement().toString();
                String codigo_cliente=st.nextElement().toString();
                Integer mes=Integer.parseInt(st.nextElement().toString());
                    
                if(existe(codigo_produto, "produtos") && existe(codigo_cliente, "clientes")) {
                    Compra c=new Compra(codigo_produto, preco, quantidade_comprada, modo, codigo_cliente, mes);
                    addCompra(mes, c.clone());
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null) br.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
   }
   
   /**
    * Verifica se um produto/cliente com um dado código existe no catálogo respetivo
    */
   public boolean existe(String code, String type) {
       if(type.equals("produtos")) return this.catalogo_produtos.contains(code);
       else return this.catalogo_clientes.contains(code);
   }
   
   /**
    * Adiciona uma compra à estrutura
    */
   public void addCompra(int mes, Compra c) {
       this.compras.put(mes, c.clone());
   }
}
