import java.util.*;
/**
 * Escreva a descrição da classe Catálogo_Produtos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Catálogo_Produtos
{
  // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
  private HashSet<String> catalogo_produtos;

  /**
     * COnstrutor para objetos da classe Catálogo_Produtos
     */
  public Catálogo_Produtos(){
        this.catalogo_produtos=new HashSet<String>();
  }
 
  public void insereCP(String e){ /**que insere um codigo produto no catalogo*/
      catalogo_produtos.add(e);
  }
 
  public boolean existeCP(String e){ /**que determina se um codigo produto está no catalogo*/
      return catalogo_produtos.contains(e);
  } 
    
  public boolean existeCodProd(String cod){
      boolean found=false;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = catalogo_produtos.iterator();
      while(it.hasNext() && !found){
          String e = it.next();
          found=e.equals(cod);
      }
      return found;
  }
    
  public void removeCP(String cod){ /**que remove o empregado de código cod do produto*/
      boolean found=false;
      String res = null;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = catalogo_produtos.iterator();
      while(it.hasNext() && !found){
          String e = it.next();
          found=e.equals(cod);
          if(found)  res=e;
      }
      if(res!=null) catalogo_produtos.remove(res);
  }    
  /*
  public boolean equals(Object obj) {
     if(this==o) {
          return true;
        }
        
     if ((o==null)||(this.getClass()!=o.getClass())) {
          return false;
        }
     
     else{
           Catálogo_Produtos a = (Catálogo_Produtos) o;
           return (this.catalogo_produtos.equals(a.getCatalogo_produtos()));
        }
  } 
  
  public String toString() {
     StringBuilder sb = new StringBuilder();
      sb.append("Lista de Códigos de Produtos:");
      /**Quando queremos percorrer todos os códigos sem parar
      for(String cp : this.catalogo_produtos){
         sb.append(cp.toString()+"\n");
        }  
      sb.append("O número de códigos de produtos é ");
      sb.append(this.catalogo_produtos.size()+"\n");
      return (sb.toString());
  }
    
  public Catálogo_Produtos clone() {
     return new Catálogo_Produtos(this);
  }*/
}