import java.util.*;
import java.io.*;

/**
 * Classe que contém toda a informação relativa à contabilidade do hipermercado
 */
public class Contabilidade implements Serializable
{
    /**
     * Variáveis de instância
     */
    private ArrayList<String> invalidComp;
    private ArrayList<Integer> totalCompMes;
    private ArrayList<Double> factMes;
    private ArrayList<ClientesMes> clientesMes;
    private double faturacao_total;
    private int compras_gratis;
    
    /**
     * Construtores
     */
    public Contabilidade() {
        this.invalidComp=new ArrayList<String>();
        this.totalCompMes=new ArrayList<Integer>();
        this.factMes=new ArrayList<Double>(); 
        this.clientesMes=initClientesMes();
        for(int i=0; i<12; i++) {
            this.factMes.add(i, 0.00);
            this.totalCompMes.add(i, 0);
        }
        this.faturacao_total=0;
        this.compras_gratis=0;
    }
    
    public Contabilidade(Contabilidade c) {
        this.invalidComp=c.getInvalidComp();
        this.totalCompMes=c.getTotalCompMes();
        this.clientesMes=c.getClientesMes();
        this.factMes=c.getFactMes();
        this.faturacao_total=c.getFaturacaoTotal();
        this.compras_gratis=c.getComprasGratis();
    }
    
    /**
     * Getters
     */
   public ArrayList<String> getInvalidComp() {
       return this.invalidComp;
   }
   
   public ArrayList<Integer> getTotalCompMes() {
       return this.totalCompMes;
   }
   
   public ArrayList<Double> getFactMes() {
       return this.factMes;
   }
   
   public ArrayList<ClientesMes> getClientesMes() {
       ArrayList<ClientesMes> aux=new ArrayList<ClientesMes>();
       for(ClientesMes cm: this.clientesMes) aux.add(cm.clone());
       return aux;
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
   public void setInvalidComp(ArrayList<String> invalidComp) {
       this.invalidComp=invalidComp;
   }
   
   public void setTotalCompMes(ArrayList<Integer> totalCompMes) {
       this.totalCompMes=totalCompMes;
   }
   
   public void setFactMes(ArrayList<Double> factMes) {
       this.factMes=factMes;
   }
   
   public void setClientesMes(ArrayList<ClientesMes> clientesMes) {
       ArrayList<ClientesMes> aux=new ArrayList<ClientesMes>();
       for(ClientesMes cm: this.clientesMes) aux.add(cm.clone());
       this.clientesMes=aux;
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
   public Contabilidade clone() {
       return new Contabilidade(this);
   }
   
   /**
    * Inicializa o clientesMes
    */
   public ArrayList<ClientesMes> initClientesMes() {
       ArrayList<ClientesMes> aux=new ArrayList<ClientesMes>(12);
       for(int i=0; i<12; i++) {
           ClientesMes cm=new ClientesMes();
           aux.add(i, cm);
       }
       return aux;
   }
   
   /**
    * Faz a leitura dos ficheiros e insere na Contabilidade
    */
   public void leitura(double preco, int quantidade_comprada, int mes, String cliente) {
       double fact=this.factMes.get(mes-1);
       int comp=this.totalCompMes.get(mes-1);
       this.faturacao_total+=preco*quantidade_comprada;
       if(preco==0) this.compras_gratis++;
       this.factMes.set(mes-1, fact+(preco*quantidade_comprada));
       this.totalCompMes.set(mes-1, comp+1);
       HashSet<String> clientes=this.clientesMes.get(mes-1).getClientes();
       clientes.add(cliente);
       this.clientesMes.get(mes-1).setClientes(clientes);
   }
   
   /**
    * Retorna o número de clientes que fizeram compras num mês
    */
   public int clientesPorMes(int mes) {
       return this.clientesMes.get(mes).getClientes().size();
   }
}
