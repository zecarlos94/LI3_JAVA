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
    private String compras_nome;
    private String clientes_nome;
    private String produtos_nome;
    private HashSet<String> catalogo_clientes;
    private HashSet<String> catalogo_produtos;
    private TreeMap<Integer, Compra> compras; 
    private HashSet<String> produtos_comprados;
    private HashSet<String> clientes_compradores;
    private ArrayList<String> invalidComp;
    private ArrayList<Double> factMes;
    private double faturacao_total;
    private int compras_gratis;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.compras_nome="N/A";
        this.clientes_nome="N/A";
        this.produtos_nome="N/A";
        this.catalogo_clientes=new HashSet<String>();
        this.catalogo_produtos=new HashSet<String>();
        this.compras=new TreeMap<Integer, Compra>(new IntCompare()); 
        this.produtos_comprados=new HashSet<String>();
        this.clientes_compradores=new HashSet<String>();
        this.invalidComp=new ArrayList<String>();
        this.factMes=new ArrayList<Double>();
        this.faturacao_total=0;
        this.compras_gratis=0;
    }
    
   public Hipermercado(Hipermercado h) {
        this.compras_nome=h.getComprasNome();
        this.clientes_nome=h.getClientesNome();
        this.produtos_nome=h.getProdutosNome();
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.compras=h.getCompras(); 
        this.produtos_comprados=h.getProdutosComprados();
        this.clientes_compradores=h.getClientesCompradores();
        this.invalidComp=h.getInvalidComp();
        this.factMes=h.getFactMes();
        this.faturacao_total=h.getFaturacaoTotal();
        this.compras_gratis=h.getComprasGratis();
   }
   
   /**
    * Getters
    */
   public String getComprasNome() {
       return this.compras_nome;
   }
   
   public String getClientesNome() {
       return this.clientes_nome;
   }
   
   public String getProdutosNome() {
       return this.produtos_nome;
   }
   
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
   
   public HashSet<String> getProdutosComprados() {
       return this.produtos_comprados;
    }
    
   public HashSet<String> getClientesCompradores() {
       return this.clientes_compradores;
   }
   
   public ArrayList<String> getInvalidComp() {
       return this.invalidComp;
   }
   
   public ArrayList<Double> getFactMes() {
       return this.factMes;
   }
   
   public double getFaturacaoTotal() {
       return this.faturacao_total;
   }
   
   public int getComprasGratis() {
       return this.compras_gratis;
   }
   
   /**
    * Setters
    */
   public void setComprasNome(String compras_nome) {
       this.compras_nome=compras_nome;
   }
   
   public void setClientesNome(String clientes_nome) {
       this.clientes_nome=clientes_nome;
   }
   
   public void setProdutosNome(String produtos_nome) {
       this.produtos_nome=produtos_nome;
   }
   
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
   
   public void setProdutosComprados(HashSet<String> produtos_comprados) {
       this.produtos_comprados=produtos_comprados;
   }
   
   public void setClientesCompradores(HashSet<String> clientes_compradores) {
       this.clientes_compradores=clientes_compradores;
   }
   
   public void setInvalidComp(ArrayList<String> invalidComp) {
       this.invalidComp=invalidComp;
   }
   
   public void setFactMes(ArrayList<Double> factMes) {
       this.factMes=factMes;
   }
   
   public void setFaturacaoTotal(double faturacao_total) {
       this.faturacao_total=faturacao_total;
   }
   
   public void setComprasGratis(int compras_gratis) {
       this.compras_gratis=compras_gratis;
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
        if(type.equals("clientes")) {
            this.clientes_nome=ficheiro;
            this.catalogo_clientes=catalogo;
        }
        else {
            this.produtos_nome=ficheiro;
            this.catalogo_produtos=catalogo;
        }
   }
   
   /**
    * Faz a leitura do ficheiro de compras
    */
   public void readCompras(String ficheiro) {
        BufferedReader br=null;
        double faturacao;
        int numero_compras;
        this.compras_nome=ficheiro;
        ArrayList<Double> aux=new ArrayList<Double>(12);
        for(int i=0; i<12; i++) aux.add(i, 0.0);
        this.factMes=aux;
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
                    this.faturacao_total+=preco*quantidade_comprada;
                    if(preco==0) this.compras_gratis++;
                    if(!this.produtos_comprados.contains(codigo_produto)) this.produtos_comprados.add(codigo_produto);
                    if(!this.clientes_compradores.contains(codigo_cliente)) this.clientes_compradores.add(codigo_cliente);
                    double fact=this.factMes.get(mes-1);
                    this.factMes.set(mes-1, fact+(preco*quantidade_comprada));
                    Compra c=new Compra(codigo_produto, preco, quantidade_comprada, modo, codigo_cliente, mes);
                    this.compras.put(mes, c.clone());
                }
                else this.invalidComp.add(line);
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
    * Calcula o total de produtos não comprados
    */
   public int totalProdutosNaoComprados() {
       return this.catalogo_produtos.size()-this.produtos_comprados.size();
   }
   
   /**
    * Devolve o total de clientes não compradores
    */
   public int totalClientesNaoCompradores() {
       return this.catalogo_clientes.size()-this.clientes_compradores.size();
   }
   
   /**
    * Devolve o número de compras efetuadas num dado mês
    */
   public int comprasMes(int mes) {
       return this.compras.subMap(mes, mes+1).size();
   }
   
   /**
    * Calcula o número total de clientes que efetuaram compras num dado mes
    */
   public int clientesMes(int mes) {
       HashSet<String> aux=new HashSet<String>();
       SortedMap<Integer, Compra> s=this.compras.subMap(mes, mes+1);
       Iterator<Compra> it=s.values().iterator();
       while(it.hasNext()) {
           Compra c=it.next();
           if(!aux.contains(c.getCodigoCliente())) aux.add(c.getCodigoCliente());
       }
       return aux.size();
   }
}
