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
    private ArrayList<Double> factMes;
    private double faturacao_total;
    private int compras_gratis;
    
    /**
     * Construtores
     */
    public Contabilidade() {
        this.invalidComp=new ArrayList<String>();
        this.factMes=new ArrayList<Double>(); for(int i=0; i<12; i++) this.factMes.add(i, 0.00);
        this.faturacao_total=0;
        this.compras_gratis=0;
    }
    
    public Contabilidade(Contabilidade c) {
        this.invalidComp=c.getInvalidComp();
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
   public Contabilidade clone() {
       return new Contabilidade(this);
   }
   
   /**
    * Faz a leitura dos ficheiros e insere na Contabilidade
    */
   public void leitura(double preco, int quantidade_comprada, int mes) {
       double fact=this.factMes.get(mes-1);
       this.faturacao_total+=preco*quantidade_comprada;
       if(preco==0) this.compras_gratis++;
       this.factMes.set(mes-1, fact+(preco*quantidade_comprada));
   }
}