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
    private HashSet<String> catalogo_clientes;
    private HashSet<String> clientes_compradores;
    private HashSet<String> clientes_invalidos;
    
    /**
     * Construtores
     */
    public CatalogoClientes() {
        this.clientes_nome="N/A";
        this.catalogo_clientes=new HashSet<String>();
        this.clientes_compradores=new HashSet<String>();
        this.clientes_invalidos=new HashSet<String>();
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
   
   public HashSet<String> getCatalogoClientes() {
       return this.catalogo_clientes;
   }
   
   public HashSet<String> getClientesCompradores() {
       return this.clientes_compradores;
   }
   
   public HashSet<String> getClientesInvalidos() {
       return this.clientes_invalidos;
   }
   
   /**
    * Setters
    */
   public void setClientesNome(String clientes_nome) {
       this.clientes_nome=clientes_nome;
   }
   
   public void setCatalogoClientes(HashSet<String> catalogo_clientes) {
       this.catalogo_clientes=catalogo_clientes;
   }
   
   public void setClientesCompradores(HashSet<String> clientes_compradores) {
       this.clientes_compradores=clientes_compradores;
   }
   
   public void setClientesInvalidos(HashSet<String> clientes_invalidos) {
       this.clientes_invalidos=clientes_invalidos;
   }
   
   /**
    * Clone
    */
   public CatalogoClientes clone() {
       return new CatalogoClientes(this);
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
