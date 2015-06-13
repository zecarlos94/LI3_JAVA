import java.util.*;
import java.io.*;

/**
 * Classe que contém todas as estruturas relativas aos clientes
 */
public class CatalogoClientes implements Serializable
{
    /**
     * Variáveis de instância
     */
    private String clientes_nome;
    private TreeSet<String> catalogo_clientes;
    private TreeSet<String> clientes_compradores;
    private TreeSet<String> clientes_invalidos;
    
    /**
     * Construtores
     */
    public CatalogoClientes() {
        this.clientes_nome="N/A";
        this.catalogo_clientes=new TreeSet<String>();
        this.clientes_compradores=new TreeSet<String>();
        this.clientes_invalidos=new TreeSet<String>();
    }
    
    public CatalogoClientes(CatalogoClientes c) {
        this.clientes_nome=c.getClientesNome();
        this.catalogo_clientes=c.getCatalogoClientes();
        this.clientes_compradores=c.getClientesCompradores();
        this.clientes_invalidos=c.getClientesInvalidos();
    }
    
    /**
     * Getters
     */
   public String getClientesNome() {
       return this.clientes_nome;
   }
   
   public TreeSet<String> getCatalogoClientes() {
       return this.catalogo_clientes;
   }
   
   public TreeSet<String> getClientesCompradores() {
       return this.clientes_compradores;
   }
   
   public TreeSet<String> getClientesInvalidos() {
       return this.clientes_invalidos;
   }
   
   /**
    * Setters
    */
   public void setClientesNome(String clientes_nome) {
       this.clientes_nome=clientes_nome;
   }
   
   public void setCatalogoClientes(TreeSet<String> catalogo_clientes) {
       this.catalogo_clientes=catalogo_clientes;
   }
   
   public void setClientesCompradores(TreeSet<String> clientes_compradores) {
       this.clientes_compradores=clientes_compradores;
   }
   
   public void setClientesInvalidos(TreeSet<String> clientes_invalidos) {
       this.clientes_invalidos=clientes_invalidos;
   }
   
   /**
    * Clone
    */
   public CatalogoClientes clone() {
       return new CatalogoClientes(this);
   }
   
   /**
    * Guarda um cliente que tenha feito uma compra
    */
   public void guardaCliente(String codigo_cliente) {
       if(!this.clientes_compradores.contains(codigo_cliente)) this.clientes_compradores.add(codigo_cliente);
   }
   
   /**
    * Verifica se um cliente com um dado código existe no catálogo
    */
   public boolean existe(String code) {
       return this.catalogo_clientes.contains(code);
   }
   
   /**
    * Devolve o total de clientes não compradores
    */
   public int totalClientesNaoCompradores() {
       return this.catalogo_clientes.size()-this.clientes_compradores.size();
   }
}
