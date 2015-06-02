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
    private Contabilidade contabilidade;
    private ArrayList<ComprasMes> compras_mes;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.compras_nome="N/A";
        this.catalogo_clientes=new CatalogoClientes();
        this.catalogo_produtos=new CatalogoProdutos();
        this.contabilidade=new Contabilidade();
        this.compras_mes=new ArrayList<ComprasMes>(12); for(int i=0; i<12; i++) this.compras_mes.add(i, new ComprasMes());
    }
    
   public Hipermercado(Hipermercado h) {
        this.compras_nome=h.getComprasNome();
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.contabilidade=h.getContabilidade();
        this.compras_mes=h.getComprasMes();
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
 
   public Contabilidade getContabilidade() {
       return this.contabilidade.clone();
   }
   
   public ArrayList<ComprasMes> getComprasMes() {
       ArrayList<ComprasMes> aux=new ArrayList<ComprasMes>(12);
       for(ComprasMes cm: this.compras_mes) aux.add(cm.clone());
       return aux;
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
   
   public void setContabilidade(Contabilidade contabilidade) {
       this.contabilidade=contabilidade;
   }
   
   public void setComprasMes(ArrayList<ComprasMes> compras_mes) {
       ArrayList<ComprasMes> aux=new ArrayList<ComprasMes>(12);
       for(ComprasMes cm: compras_mes) aux.add(cm.clone());
       this.compras_mes=aux;
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
                
                Contabilidade cont=this.getContabilidade();
                
                if(this.catalogo_produtos.existe(codigo_produto) && this.catalogo_clientes.existe(codigo_cliente)) {
                    ComprasMes cmp=this.getComprasMes().get(mes-1);
                    CatalogoClientes cc=this.getCatalogoClientes(); CatalogoProdutos cp=this.getCatalogoProdutos(); 
                    cc.guardaCliente(codigo_cliente); this.setCatalogoClientes(cc); // catalogo_clientes
                    cp.guardaProduto(codigo_produto); this.setCatalogoProdutos(cp); // catalogo_produtos
                    cont.leitura(preco, quantidade_comprada, mes); this.setContabilidade(cont); // contabilidade
                    cmp.leitura(codigo_cliente, codigo_produto, preco, quantidade_comprada); this.compras_mes.set(mes-1, cmp.clone()); // compras_mes
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
       return this.compras_mes.get(mes).getTotalCompras();
   }
   
   /**
    * Calcula o número total de clientes que efetuaram compras num dado mes
    */
   public int clientesMes(int mes) {
       return this.compras_mes.get(mes).getComprasCliente().size();
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
   
   /**
    * Devolve o número de compras feitas por um cliente num dado mês
    */
   public int comprasClntMes(String cliente, int mes) {
       ComprasMes cm=getComprasMes().get(mes);
       if(cm.getComprasCliente().containsKey(cliente)) return cm.getComprasCliente().get(cliente);
       else return 0;
   }
   
   /**
    * Devolve o número de produtos distintos comprados por um cliente num dado mês
    */
   public int clientesProdMes(String cliente, int mes) {
       ComprasMes cm=getComprasMes().get(mes);
       return cm.clientesProdMes(cliente);
   }
   
   /**
    * Devolve o total gasto por um cliente num dado mês
    */
   public double gastosClnt(String cliente, int mes) {
       ComprasMes cm=getComprasMes().get(mes);
       if(cm.getComprasCliente().containsKey(cliente)) return cm.getGastosCliente().get(cliente);
       else return 0;
   }
   
   /**
    * Retorna o total anual gasto pelo cliente
    */
   public double gastoAnualClnt(String cliente) {
       double gastos=0;
       for(int i=0; i<12; i++) gastos+=gastosClnt(cliente, i);
       return gastos;
   }
}
