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
    private ArrayList<ClientesMes> clientes_mes;
    private ArrayList<ProdutosMes> produtos_mes;
    private HashMap<String, TreeSet<InfoProduto>> produtosPorCliente;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.compras_nome="N/A";
        this.catalogo_clientes=new CatalogoClientes();
        this.catalogo_produtos=new CatalogoProdutos();
        this.contabilidade=new Contabilidade();
        this.clientes_mes=new ArrayList<ClientesMes>(12); 
        this.produtos_mes=new ArrayList<ProdutosMes>(12);
        for(int i=0; i<12; i++) {
            this.clientes_mes.add(i, new ClientesMes());
            this.produtos_mes.add(i, new ProdutosMes());
        }
        this.produtosPorCliente=new HashMap<String, TreeSet<InfoProduto>>();
    }
    
   public Hipermercado(Hipermercado h) {
        this.compras_nome=h.getComprasNome();
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.contabilidade=h.getContabilidade();
        this.clientes_mes=h.getClientesMes();
        this.produtos_mes=h.getProdutosMes();
        this.produtosPorCliente=h.getProdutosPorCliente();
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
   
   public ArrayList<ClientesMes> getClientesMes() {
       ArrayList<ClientesMes> aux=new ArrayList<ClientesMes>(12);
       for(ClientesMes cm: this.clientes_mes) aux.add(cm.clone());
       return aux;
   }
   
   public ArrayList<ProdutosMes> getProdutosMes() {
       ArrayList<ProdutosMes> aux=new ArrayList<ProdutosMes>(12);
       for(ProdutosMes cm: this.produtos_mes) aux.add(cm.clone());
       return aux;
   }
   
   public HashMap<String, TreeSet<InfoProduto>> getProdutosPorCliente() {
       HashMap<String, TreeSet<InfoProduto>> aux1=new HashMap<String, TreeSet<InfoProduto>>();
       Iterator<Map.Entry<String, TreeSet<InfoProduto>>> it1=this.produtosPorCliente.entrySet().iterator();
       while(it1.hasNext()) {
           Map.Entry<String, TreeSet<InfoProduto>> elem=it1.next();
           TreeSet<InfoProduto> aux2=new TreeSet<InfoProduto>(new QtdComparator());
           Iterator<InfoProduto> it2=elem.getValue().iterator();
           while(it2.hasNext()) {
               InfoProduto p=it2.next().clone();
               aux2.add(p);
           }
           aux1.put(elem.getKey(), aux2);
       }
       return aux1;
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
   
   public void setClientesMes(ArrayList<ClientesMes> clientes_mes) {
       ArrayList<ClientesMes> aux=new ArrayList<ClientesMes>(12);
       for(ClientesMes cm: clientes_mes) aux.add(cm.clone());
       this.clientes_mes=aux;
   }
   
   public void setProdutosMes(ArrayList<ProdutosMes> produtos_mes) {
       ArrayList<ProdutosMes> aux=new ArrayList<ProdutosMes>(12);
       for(ProdutosMes cm: produtos_mes) aux.add(cm.clone());
       this.produtos_mes=aux;
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
                    ClientesMes clnt=this.getClientesMes().get(mes-1);
                    ProdutosMes prod=this.getProdutosMes().get(mes-1);
                    CatalogoClientes cc=this.getCatalogoClientes(); CatalogoProdutos cp=this.getCatalogoProdutos(); 
                    cc.guardaCliente(codigo_cliente); this.setCatalogoClientes(cc); // catalogo_clientes
                    cp.guardaProduto(codigo_produto); this.setCatalogoProdutos(cp); // catalogo_produtos
                    cont.leitura(preco, quantidade_comprada, mes); this.setContabilidade(cont); // contabilidade
                    clnt.leitura(codigo_cliente, codigo_produto, preco, quantidade_comprada); this.clientes_mes.set(mes-1, clnt.clone()); // clientes_mes
                    prod.leitura(codigo_cliente, codigo_produto, preco, quantidade_comprada, modo); this.produtos_mes.set(mes-1, prod.clone()); // produtos_mes
                    
                    if(!this.produtosPorCliente.containsKey(codigo_cliente)) { //produtosPorCliente
                        TreeSet<InfoProduto> aux=new TreeSet<InfoProduto>(new QtdComparator());
                        InfoProduto novo=new InfoProduto(codigo_produto, quantidade_comprada);
                        aux.add(novo.clone());
                        this.produtosPorCliente.put(codigo_cliente, aux);
                    }
                    else {
                        TreeSet<InfoProduto> arv=this.produtosPorCliente.get(codigo_cliente);
                        InfoProduto p=new InfoProduto(codigo_produto, quantidade_comprada);
                        arv.add(p.clone());
                        this.produtosPorCliente.put(codigo_cliente, arv);
                    }
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
       return this.contabilidade.getTotalCompMes().get(mes-1);
   }
   
   /**
    * Calcula o número total de clientes que efetuaram compras num dado mes
    */
   public int clientesMes(int mes) {
       return this.clientes_mes.get(mes).getComprasCliente().size();
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
       ClientesMes clnt=getClientesMes().get(mes);
       if(clnt.getComprasCliente().containsKey(cliente)) return clnt.getComprasCliente().get(cliente);
       else return 0;
   }
   
   /**
    * Devolve o número de clientes que compraram o produto num mês
    */
   public int comprasProdMes(String produto, int mes) {
       ProdutosMes prod=getProdutosMes().get(mes);
       if(prod.getComprasProduto().containsKey(produto)) return prod.getComprasProduto().get(produto);
       else return 0;
   }
   
   /**
    * Devolve o número de produtos distintos comprados por um cliente num dado mês
    */
   public int clientesProdMes(String cliente, int mes) {
       ClientesMes clnt=getClientesMes().get(mes);
       return clnt.clientesProdMes(cliente);
   }
   
   /**
    * Devolve o número de clientes que compraram um produto num dado mês
    */
   public int produtosClntMes(String produto, int mes) {
       ProdutosMes prod=getProdutosMes().get(mes);
       return prod.produtosClntMes(produto);
   }
   
   /**
    * Devolve o total gasto por um cliente num dado mês
    */
   public double gastosClnt(String cliente, int mes) {
       ClientesMes clnt=getClientesMes().get(mes);
       if(clnt.getComprasCliente().containsKey(cliente)) return clnt.getGastosCliente().get(cliente);
       else return 0;
   }
   
   /**
    * Devolve o total faturado por um produto num dado mês
    */
   public double factProduto(String produto, int mes) {
       ProdutosMes prod=getProdutosMes().get(mes);
       if(prod.getComprasProduto().containsKey(produto)) return prod.getFactProduto().get(produto);
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
   
   /**
    * Retorna um array em que a primeira posição corresponde ao número de compras e modo N e a segunda em modo P
    */
   public ArrayList<Integer> prodComprasModo(String produto, int mes) {
       ArrayList<Integer> aux=new ArrayList<Integer>(2);
       ProdutosMes prod=getProdutosMes().get(mes);
       aux.add(0, prod.comprasModoN(produto)); aux.add(1, prod.comprasModoP(produto));
       return aux;
   }
   
   /**
    * Retorna um array em que a primeira posição corresponde à faturação em modo N e a segunda em modo P
    */
   public ArrayList<Double> prodFactModo(String produto, int mes) {
       ArrayList<Double> aux=new ArrayList<Double>(2);
       ProdutosMes prod=getProdutosMes().get(mes);
       aux.add(0, prod.factModoN(produto)); aux.add(1, prod.factModoP(produto));
       return aux;
   }
   
   /**
    * Retorna o TreeSet de produtos comprados por cliente, ordenado por ordem decrescente de quantidade
    */
   public TreeSet<InfoProduto> prodCliente(String cliente) {
       TreeSet<InfoProduto> novo=new TreeSet<InfoProduto>(new QtdComparator());
       Iterator<InfoProduto> it=this.produtosPorCliente.get(cliente).iterator();
       while(it.hasNext()) {
           InfoProduto p=it.next().clone();
           novo.add(p);
       }
       return novo;
   }
}
