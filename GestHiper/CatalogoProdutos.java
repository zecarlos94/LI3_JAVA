import java.util.*;
import java.io.*;

/**
 * Classe que contém todas as estruturas relativas aos clientes
 */
public class CatalogoProdutos implements Serializable
{
    /**
     * Variáveis de instância
     */
    private String produtos_nome;
    private HashSet<String> catalogo_produtos;
    private HashSet<String> produtos_comprados;
    private HashSet<String> produtos_invalidos;
    
    /**
     * Construtores
     */
    public CatalogoProdutos() {
        this.produtos_nome="N/A";
        this.catalogo_produtos=new HashSet<String>();
        this.produtos_comprados=new HashSet<String>();
        this.produtos_invalidos=new HashSet<String>();
    }
    
    public CatalogoProdutos(CatalogoProdutos p) {
        this.produtos_nome=p.getProdutosNome();
        this.catalogo_produtos=p.getCatalogoProdutos();
        this.produtos_comprados=p.getProdutosComprados();
        this.produtos_invalidos=p.getProdutosInvalidos();
    }
    
    /**
     * Getters
     */
   public String getProdutosNome() {
       return this.produtos_nome;
   }
   
   public HashSet<String> getCatalogoProdutos() {
       return this.catalogo_produtos;
   }
   
   public HashSet<String> getProdutosComprados() {
       return this.produtos_comprados;
   }
   
   public HashSet<String> getProdutosInvalidos() {
       return this.produtos_invalidos;
   }
   
   /**
    * Setters
    */
   public void setProdutosNome(String produtos_nome) {
       this.produtos_nome=produtos_nome;
   }
   
   public void setCatalogoProdutos(HashSet<String> catalogo_produtos) {
       this.catalogo_produtos=catalogo_produtos;
   }
   
   public void setProdutosComprados(HashSet<String> produtos_comprados) {
       this.produtos_comprados=produtos_comprados;
   }
   
   public void setProdutosInvalidos(HashSet<String> produtos_invalidos) {
       this.produtos_invalidos=produtos_invalidos;
   }
   
   /**
    * Clone
    */
   public CatalogoProdutos clone() {
       return new CatalogoProdutos(this);
   }
   
   /**
    * Guarda um produto que tenha sido comprado
    */
   public void guardaProduto(String codigo_produto) {
       if(!this.produtos_comprados.contains(codigo_produto)) this.produtos_comprados.add(codigo_produto);
   }
   
   /**
    * Verifica se um produto com um dado código existe no catálogo
    */
   public boolean existe(String code) {
       return this.catalogo_produtos.contains(code);
   }
   
   /**
    * Calcula o total de produtos não comprados
    */
   public int totalProdutosNaoComprados() {
       return this.catalogo_produtos.size()-this.produtos_comprados.size();
   }
}
