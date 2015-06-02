
import java.util.*;

/**
 * Write a description of class InfoProduto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoProduto
{
    private ArrayList<InfoProdutoMes> informacaoMensal;
    
    private int unidadesVendidas;
    
    private TreeSet<ClienteDespesa>  despesas; //Ordenado por quantidade de produtos comprados
    
    public InfoProduto()
    {
        informacaoMensal = new ArrayList<InfoProdutoMes>(12);
        unidadesVendidas = 0;
        despesas = new TreeSet<ClienteDespesa>(new ClienteDespesaQuantidadeCompara());
        
        
    }

    public void add(Compra compra)
    {
        String cliente = new String(compra.getCodigoCliente());
        boolean encontrou = false;
        informacaoMensal.get( compra.getMes() - 1).add(compra);
        
        this.unidadesVendidas+= compra.getQuantidadeComprada();
     
        Iterator<ClienteDespesa> it = despesas.iterator();
        while(it.hasNext() && !encontrou )
        {
             ClienteDespesa elem = it.next();
             if(elem.getCliente().equals(cliente)) 
                               { elem.add(compra); encontrou = true; }
        }
        if(!encontrou) despesas.add(new ClienteDespesa(compra));
         
    }
    
    /**
     *  Warning nesta função !!!
     */
    public int getClientesUnicos()
    {
        int i;
        TreeSet clientes = new TreeSet<String>(new StringCompare());
        for(i = 0; i < 12; i++){
            TreeSet<String> clientesMensais = this.informacaoMensal.get(i).getClientes();
            if(clientesMensais.size() > 0)clientes.addAll( clientesMensais );
        }
        return clientes.size();
    }
    
    public int getUnidadesVendidas(){   return this.unidadesVendidas;}
   
    
    public ArrayList<InfoProdutoMes> getInformacaoMensal(){
            ArrayList<InfoProdutoMes> clone = new ArrayList<InfoProdutoMes>(this.informacaoMensal);
            return clone;
    }
    
    
}
