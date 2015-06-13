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
    private HashMap<String, TreeSet<InfoProduto>> produtosPorCliente;
    private HashMap<String, TreeSet<InfoCliente>> clientesPorProduto;
    
    /**
     * Construtores
     */
    public Hipermercado() {
        this.compras_nome="N/A";
        this.catalogo_clientes=new CatalogoClientes();
        this.catalogo_produtos=new CatalogoProdutos();
        this.contabilidade=new Contabilidade();
        this.produtosPorCliente=new HashMap<String, TreeSet<InfoProduto>>();
        this.clientesPorProduto=new HashMap<String, TreeSet<InfoCliente>>();
    }
    
   public Hipermercado(Hipermercado h) {
        this.compras_nome=h.getComprasNome();
        this.catalogo_clientes=h.getCatalogoClientes();
        this.catalogo_produtos=h.getCatalogoProdutos();
        this.contabilidade=h.getContabilidade();
        this.produtosPorCliente=h.getProdutosPorCliente();
        this.clientesPorProduto=h.getClientesPorProduto();
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
   
   public HashMap<String, TreeSet<InfoProduto>> getProdutosPorCliente() {
       HashMap<String, TreeSet<InfoProduto>> aux1=new HashMap<String, TreeSet<InfoProduto>>();
       Iterator<Map.Entry<String, TreeSet<InfoProduto>>> it1=this.produtosPorCliente.entrySet().iterator();
       while(it1.hasNext()) {
           Map.Entry<String, TreeSet<InfoProduto>> elem=it1.next();
           TreeSet<InfoProduto> aux2=new TreeSet<InfoProduto>(new QtdProdComparator());
           Iterator<InfoProduto> it2=elem.getValue().iterator();
           while(it2.hasNext()) {
               InfoProduto p=it2.next().clone();
               aux2.add(p);
           }
           aux1.put(elem.getKey(), aux2);
       }
       return aux1;
   }
   
   public HashMap<String, TreeSet<InfoCliente>> getClientesPorProduto() {
       HashMap<String, TreeSet<InfoCliente>> aux1=new HashMap<String, TreeSet<InfoCliente>>();
       Iterator<Map.Entry<String, TreeSet<InfoCliente>>> it1=this.clientesPorProduto.entrySet().iterator();
       while(it1.hasNext()) {
           Map.Entry<String, TreeSet<InfoCliente>> elem=it1.next();
           TreeSet<InfoCliente> aux2=new TreeSet<InfoCliente>(new QtdClntComparator());
           Iterator<InfoCliente> it2=elem.getValue().iterator();
           while(it2.hasNext()) {
               InfoCliente p=it2.next().clone();
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
        TreeSet<String> catalogo=new TreeSet<String>(new StringCompare());
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
                
                if(this.catalogo_produtos.existe(codigo_produto) && this.catalogo_clientes.existe(codigo_cliente) && valida(preco, modo, mes)) {
                    CatalogoClientes cc=this.getCatalogoClientes(); CatalogoProdutos cp=this.getCatalogoProdutos(); 
                    cc.guardaCliente(codigo_cliente); this.setCatalogoClientes(cc); // catalogo_clientes
                    cp.guardaProduto(codigo_produto); this.setCatalogoProdutos(cp); // catalogo_produtos
                    cont.leitura(preco, quantidade_comprada, mes, codigo_cliente); this.setContabilidade(cont); // contabilidade
                    
                    if(!this.produtosPorCliente.containsKey(codigo_cliente)) { //produtosPorCliente
                        TreeSet<InfoProduto> aux=new TreeSet<InfoProduto>(new QtdProdComparator());
                        InfoProduto novo=new InfoProduto(codigo_produto, quantidade_comprada, mes, preco, modo);
                        aux.add(novo.clone());
                        this.produtosPorCliente.put(codigo_cliente, aux);
                    }
                    else {
                        TreeSet<InfoProduto> arv=this.produtosPorCliente.get(codigo_cliente);
                        InfoProduto p=new InfoProduto(codigo_produto, quantidade_comprada, mes, preco, modo);
                        arv.add(p.clone());
                        this.produtosPorCliente.put(codigo_cliente, arv);
                    }
                    if(!this.clientesPorProduto.containsKey(codigo_produto)) { //clientesPorProduto
                        TreeSet<InfoCliente> auxNew=new TreeSet<InfoCliente>(new QtdClntComparator());
                        InfoCliente clntNew=new InfoCliente(codigo_cliente, quantidade_comprada, mes, preco, modo);
                        auxNew.add(clntNew.clone());
                        this.clientesPorProduto.put(codigo_produto, auxNew);
                    }
                    else {
                        TreeSet<InfoCliente> arvNew=this.clientesPorProduto.get(codigo_produto);
                        InfoCliente c=new InfoCliente(codigo_cliente, quantidade_comprada, mes, preco, modo);
                        arvNew.add(c.clone());
                        this.clientesPorProduto.put(codigo_produto, arvNew);
                    }
                }
                else {
                    Vector<String> s=cont.getInvalidComp();
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
    * Valida o preço, o mês e o modo
    */
   public boolean valida(double preço, String modo, int mês) {
       if(preço<0) return false;
       else if(!modo.equals("N") && !modo.equals("P")) return false;
       else if(mês<1 || mês>12) return false;
       else return true;
   }
   
   /**
    * Devolve o número de compras efetuadas num dado mês
    */
   public int comprasMes(int mes) {
       return this.contabilidade.getTotalCompMes().get(mes);
   }
   
   /**
    * Calcula o número total de clientes que efetuaram compras num dado mes
    */
   public int clientesMes(int mes) {
       return this.contabilidade.clientesPorMes(mes);
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
       int total=0;
       if(this.produtosPorCliente.containsKey(cliente)) {
           TreeSet<InfoProduto> produtos=this.produtosPorCliente.get(cliente);
           Iterator<InfoProduto> it=produtos.iterator();
           while(it.hasNext()) {
               InfoProduto p=it.next().clone();
               if(p.getMes()==mes+1) total++;
           }
       }
       return total;
   }
   
   /**
    * Devolve o número de clientes que compraram o produto num mês
    */
   public int comprasProdMes(String produto, int mes) {
       int total=0;
       if(this.clientesPorProduto.containsKey(produto)) {
           TreeSet<InfoCliente> clientes=this.clientesPorProduto.get(produto);
           Iterator<InfoCliente> it=clientes.iterator();
           while(it.hasNext()) {
               InfoCliente c=it.next().clone();
               if(c.getMes()==mes+1) total++;
           }
       }
       return total;
   }
   
   /**
    * Devolve o número de produtos distintos comprados por um cliente num dado mês
    */
   public int clientesProdMes(String cliente, int mes) {
       int total=0;
       ArrayList<String> comprados=new ArrayList<String>();
       if(this.produtosPorCliente.containsKey(cliente)) {
           TreeSet<InfoProduto> produtos=this.produtosPorCliente.get(cliente);
           Iterator<InfoProduto> it=produtos.iterator();
           while(it.hasNext()) {
               InfoProduto p=it.next().clone();
               if(p.getMes()==mes+1 && !comprados.contains(p.getCodigo())) {
                   comprados.add(p.getCodigo());
                   total++;
               }
           }
       }
       return total;
   }
   
   /**
    * Devolve o número de clientes que compraram um produto num dado mês
    */
   public int produtosClntMes(String produto, int mes) {
       int total=0;
       ArrayList<String> comprados=new ArrayList<String>();
       if(this.clientesPorProduto.containsKey(produto)) {
           TreeSet<InfoCliente> clientes=this.clientesPorProduto.get(produto);
           Iterator<InfoCliente> it=clientes.iterator();
           while(it.hasNext()) {
               InfoCliente p=it.next().clone();
               if(p.getMes()==mes+1 && !comprados.contains(p.getCodigo())) {
                   comprados.add(p.getCodigo());
                   total++;
               }
           }
       }
       return total;
   }
   
   /**
    * Devolve o total gasto por um cliente num dado mês
    */
   public double gastosClnt(String cliente, int mes) {
       double total=0;
       if(this.produtosPorCliente.containsKey(cliente)) {
           TreeSet<InfoProduto> produtos=this.produtosPorCliente.get(cliente);
           Iterator<InfoProduto> it=produtos.iterator();
           while(it.hasNext()) {
               InfoProduto p=it.next().clone();
               if(p.getMes()==mes+1) total+=p.getPrecoProduto()*p.getQuantidade();
           }
       }
       return total;
   }
   
   /**
    * Devolve o total faturado por um produto num dado mês
    */
   public double factProduto(String produto, int mes) {
       double total=0;
       if(this.clientesPorProduto.containsKey(produto)) {
           TreeSet<InfoCliente> clientes=this.clientesPorProduto.get(produto);
           Iterator<InfoCliente> it=clientes.iterator();
           while(it.hasNext()) {
               InfoCliente p=it.next().clone();
               if(p.getMes()==mes+1) total+=p.getPrecoProduto()*p.getQuantidade();
           }
       }
       return total;
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
    * Retorna o total anual faturado pelo produto
    */
   public double factAnualProd(String produto) {
       double fact=0;
       for(int i=0; i<12; i++) fact+=factProduto(produto, i);
       return fact;
   }
   
   /**
    * Retorna um array em que a primeira posição corresponde ao número de compras e modo N e a segunda em modo P
    */
   public ArrayList<Integer> prodComprasModo(String produto, int mes) {
       int modoP=0, modoN=0;
       ArrayList<Integer> aux=new ArrayList<Integer>(2);
       aux.add(0, 0); aux.add(1, 0);
       if(this.clientesPorProduto.containsKey(produto)) {
           TreeSet<InfoCliente> clientes=this.clientesPorProduto.get(produto);
           Iterator<InfoCliente> it=clientes.iterator();
           while(it.hasNext()) {
               InfoCliente p=it.next().clone();
               if(p.getMes()==mes+1) {
                   if(p.getModo().equals("N")) modoN++;
                   else modoP++;
               }
           }
           aux.add(0, modoN); aux.add(1, modoP);
       }
       return aux;
   }
   
   /**
    * Retorna um array em que a primeira posição corresponde à faturação em modo N e a segunda em modo P
    */
   public ArrayList<Double> prodFactModo(String produto, int mes) {
       double modoP=0, modoN=0;
       ArrayList<Double> aux=new ArrayList<Double>(2);
       aux.add(0, 0.0); aux.add(1, 0.0);
       if(this.clientesPorProduto.containsKey(produto)) {
           TreeSet<InfoCliente> clientes=this.clientesPorProduto.get(produto);
           Iterator<InfoCliente> it=clientes.iterator();
           while(it.hasNext()) {
               InfoCliente p=it.next().clone();
               if(p.getMes()==mes+1) {
                   if(p.getModo().equals("N")) modoN+=p.getPrecoProduto()*p.getQuantidade();
                   else modoP+=p.getPrecoProduto()*p.getQuantidade();
               }
           }
           aux.add(0, modoN); aux.add(1, modoP);
       }
       return aux;
   }
   
   /**
    * Retorna o TreeSet de produtos comprados por cliente, ordenado por ordem decrescente de quantidade
    */
   public TreeSet<InfoProduto> prodCliente(String cliente) {
       TreeSet<InfoProduto> novo=new TreeSet<InfoProduto>(new QtdProdComparator());
       if(this.produtosPorCliente.containsKey(cliente)) {
           Iterator<InfoProduto> it=this.produtosPorCliente.get(cliente).iterator();
           while(it.hasNext()) {
               InfoProduto p=it.next().clone();
               novo.add(p);
           }
       }
       return novo;
   }
   
   /**
    * Retorna o TreeSet dos produtos mais comprados, ordenado por ordem decrescente de unidades compradas
    */
   public TreeSet<InfoProduto> prodMaisVendidos() {
       TreeSet<InfoProduto> novo=new TreeSet<InfoProduto>(new QtdProdComparator());
       Iterator<Map.Entry<String, TreeSet<InfoCliente>>> it=this.clientesPorProduto.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<String, TreeSet<InfoCliente>> elem=it.next();
           InfoProduto aux=new InfoProduto(elem.getKey(), elem.getValue().size());
           novo.add(aux.clone());
       }
       return novo;
   }
   
   /**
    * Retorna o TreeSet dos clientes mais compradores, ordenado por ordem decrescente de unidades compradas
    */
   public TreeSet<InfoCliente> clntMaisCompradores() {
       TreeSet<InfoCliente> novo=new TreeSet<InfoCliente>(new QtdClntComparator());
       Iterator<Map.Entry<String, TreeSet<InfoProduto>>> it=this.produtosPorCliente.entrySet().iterator();
       while(it.hasNext()) {
           Map.Entry<String, TreeSet<InfoProduto>> elem=it.next();
           InfoCliente aux=new InfoCliente(elem.getKey(), elem.getValue().size());
           novo.add(aux.clone());
       }
       return novo;
   }
   
   /**
    * Retorna o TreeSet de clientes que compraram determinado produto, ordenado por ordem decrescente de quantidade
    */
   public TreeSet<InfoCliente> clntProduto(String produto) {
       TreeSet<InfoCliente> novo=new TreeSet<InfoCliente>(new QtdClntComparator());
       if(this.clientesPorProduto.containsKey(produto)) {
           Iterator<InfoCliente> it=this.clientesPorProduto.get(produto).iterator();
           while(it.hasNext()) {
               InfoCliente p=it.next().clone();
               novo.add(p);
           }
       }
       return novo;
   }
}
