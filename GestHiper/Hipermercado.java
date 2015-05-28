import java.util.*;
import java.io.*;

/**
 * Classe que contém toda a informação do Hipermercado
 */
public class Hipermercado implements Serializable
{
    /**
     * Variáveis de instância
     */
    private String compras_nome;
    private CatalogoClientes catalogo_clientes;
    private CatalogoProdutos catalogo_produtos;
    private TreeMap<Integer, Compra> compras; 
    private Contabilidade contabilidade;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.compras_nome="N/A";
        this.catalogo_clientes=new CatalogoClientes();
        this.catalogo_produtos=new CatalogoProdutos();
        this.compras=new TreeMap<Integer, Compra>(new IntCompare()); 
        this.contabilidade=new Contabilidade();
    }
    
   public Hipermercado(Hipermercado h) {
        this.compras_nome=h.getComprasNome();
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.compras=h.getCompras(); 
        this.contabilidade=h.getContabilidade();
   }
   
   /**
    * Getters
    */
   public String getComprasNome() {
       return this.compras_nome;
   }
   
   public CatalogoClientes getCatalogoClientes() {
       return this.catalogo_clientes.clone();
   }
   
   public CatalogoProdutos getCatalogoProdutos() {
       return this.catalogo_produtos.clone();
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
   
   public Contabilidade getContabilidade() {
       return this.contabilidade.clone();
   }
   
   /**
    * Setters
    */
   public void setComprasNome(String compras_nome) {
       this.compras_nome=compras_nome;
   }
   
   public void setCatalogoClientes(CatalogoClientes catalogo_compras) {
       this.catalogo_clientes=catalogo_clientes.clone();
   }
   
   public void setCatalogoProdutos(CatalogoProdutos catalogo_produtos) {
       this.catalogo_produtos=catalogo_produtos.clone();
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
   
   public void setContabilidade(Contabilidade contabilidade) {
       this.contabilidade=contabilidade;
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
            this.catalogo_clientes.setClientesNome(ficheiro);
            this.catalogo_clientes.setCatalogoClientes(catalogo);
        }
        else {
            this.catalogo_produtos.setProdutosNome(ficheiro);
            this.catalogo_produtos.setCatalogoProdutos(catalogo);
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
        this.contabilidade.setFactMes(aux);
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
                Contabilidade cont=getContabilidade();
                if(this.catalogo_produtos.existe(codigo_produto) && this.catalogo_clientes.existe(codigo_cliente)) {
                    double contFact=cont.getFaturacaoTotal(); contFact+=preco*quantidade_comprada;
                    this.contabilidade.setFaturacaoTotal(contFact);
                    HashSet<String> clientes_compradores=this.catalogo_clientes.getClientesCompradores();
                    HashSet<String> produtos_comprados=this.catalogo_produtos.getProdutosComprados();
                    if(preco==0) {
                        int compGratis=cont.getComprasGratis()+1;
                        this.contabilidade.setComprasGratis(compGratis);
                    }
                    if(!produtos_comprados.contains(codigo_produto)) {
                        produtos_comprados.add(codigo_produto);
                        this.catalogo_produtos.setProdutosComprados(produtos_comprados);
                    }
                    if(!clientes_compradores.contains(codigo_cliente)) {
                        clientes_compradores.add(codigo_cliente);
                        this.catalogo_clientes.setClientesCompradores(clientes_compradores);
                    }
                    ArrayList<Double> f=cont.getFactMes();
                    double fact=f.get(mes-1);
                    f.set(mes-1, fact+(preco*quantidade_comprada));
                    this.contabilidade.setFactMes(f);
                    Compra c=new Compra(codigo_produto, preco, quantidade_comprada, modo, codigo_cliente, mes);
                    this.compras.put(mes, c.clone());
                }
                else {
                    ArrayList<String> s=cont.getInvalidComp();
                    s.add(line);
                    this.contabilidade.setInvalidComp(s);
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
   
   /**
    * Devolve a lista ordenada de códigos de produto ou cliente inválidos
    */
   public TreeSet<String> devolveLista(String type) {
       TreeSet<String> lista=new TreeSet<String>(new StringCompare());
       if(type.equals("produtos")) {
           CatalogoProdutos p=getCatalogoProdutos();
           Iterator<String> it=p.getCatalogoProdutos().iterator();
           while(it.hasNext()) {
               String prod=it.next();
               if(!p.getProdutosComprados().contains(prod)) lista.add(prod);
           }
       }
       else {
           CatalogoClientes c=getCatalogoClientes();
           Iterator<String> it=c.getCatalogoClientes().iterator();
           while(it.hasNext()) {
               String clnt=it.next();
               if(!c.getClientesCompradores().contains(clnt)) lista.add(clnt);
           }
       }
       return lista;
   }
}
