import java.util.*;

/**
 * Informação de um produto
 */
public class InfoProduto
{
    private ArrayList<InfoProdutoMes> informacaoMensal;
    private int unidadesVendidas;
    private TreeSet<ClienteDespesa> despesas; //Ordenado por quantidade de produtos comprados
    
    public InfoProduto() {
        this.informacaoMensal=new ArrayList<InfoProdutoMes>(12);
        for(int i=0; i<12; i++) {
            InfoProdutoMes p=new InfoProdutoMes();
            this.informacaoMensal.add(i, p);
        }
        this.unidadesVendidas=0;
        this.despesas=new TreeSet<ClienteDespesa>(new ClienteDespesaQuantidadeCompara());
    }
    
    public InfoProduto(InfoProduto p) {
        this.informacaoMensal=p.getInformacaoMensal();
        this.unidadesVendidas=p.getUnidadesVendidas();
        this.despesas=p.getDespesas();
    }

    public void add(String codigo_produto, double preco, int quantidade_comprada, String modo, String codigo_cliente, int mes) {
        boolean encontrou=false;
        this.informacaoMensal.get(mes-1).add(codigo_produto, preco, quantidade_comprada, modo, codigo_cliente);
        this.unidadesVendidas+=quantidade_comprada;
        Iterator<ClienteDespesa> it=this.despesas.iterator();
        while(it.hasNext() && !encontrou) {
             ClienteDespesa elem=it.next();
             if(elem.getCliente().equals(codigo_cliente)) { 
                 elem.add(preco, quantidade_comprada); encontrou=true; 
             }
        }
        if(!encontrou) {
            ClienteDespesa novo=new ClienteDespesa(codigo_cliente, preco*quantidade_comprada, quantidade_comprada);
            this.despesas.add(novo.clone());
        }
    }
    
    public int getClientesUnicos() {
        TreeSet<String> clientes=new TreeSet<String>(new StringCompare());
        for(int i=0; i<12; i++){
            TreeSet<String> clientesMensais=this.informacaoMensal.get(i).getClientes();
            if(clientesMensais.size()>0) clientes.addAll(clientesMensais);
        }
        return clientes.size();
    }
  
    public int getUnidadesVendidas() {return this.unidadesVendidas;}
    
    public TreeSet<ClienteDespesa> getDespesas() {
        TreeSet<ClienteDespesa> aux=new TreeSet<ClienteDespesa>(new ClienteDespesaQuantidadeCompara());
        Iterator<ClienteDespesa> it=this.despesas.iterator();
        while(it.hasNext()) {
            ClienteDespesa c=it.next().clone();
            aux.add(c);
        }
        return aux;
    }
    
    public ArrayList<InfoProdutoMes> getInformacaoMensal() {
        ArrayList<InfoProdutoMes> aux=new ArrayList<InfoProdutoMes>();
        for(int i=0; i<12; i++) {
            InfoProdutoMes p=this.informacaoMensal.get(i).clone();
            aux.add(i, p);
        }
        return aux;
    }
    
    public InfoProduto clone() {
        return new InfoProduto(this);
    }
}
