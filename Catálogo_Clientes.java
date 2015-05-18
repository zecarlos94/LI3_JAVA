import java.util.*;
/**
 * Escreva a descrição da classe Catálogo_Clientes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Catálogo_Clientes
{
  // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
  private HashSet<String> catalogo_clientes;

  /**
     * COnstrutor para objetos da classe Catálogo_Clientes
     */
  public Catálogo_Clientes(){
        this.catalogo_clientes=new HashSet<String>();
  }
  
  public void insereCC(String e){ /**que insere um codigo cliente no catalogo*/
      catalogo_clientes.add(e);
  }
 
  public boolean existeCC(String e){ /**que determina se um codigo cliente está no catalogo*/
      return catalogo_clientes.contains(e);
  } 
    
  public boolean existeCodClt(String cod){
      boolean found=false;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = catalogo_clientes.iterator();
      while(it.hasNext() && !found){
          String e = it.next();
          found=e.equals(cod);
      }
      return found;
  }
    
  public void removeCódigo(String cod){ /**que remove o empregado de código cod do cliente*/
      boolean found=false;
      String res = null;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = catalogo_clientes.iterator();
      while(it.hasNext() && !found){
          String e = it.next();
          found=e.equals(cod);
          if(found)  res=e;
      }
      if(res!=null) catalogo_clientes.remove(res);
  }    
  /*
  public boolean equals(Object o) {
     if(this==o) {
          return true;
        }
        
     if ((o==null)||(this.getClass()!=o.getClass())) {
          return false;
        }
     
     else{
           Catálogo_Clientes a = (Catálogo_Clientes) o;
           return (this.catalogo_clientes.equals(a.getCatalogo_clientes()));
        }
  } 
  
  public String toString() {
     StringBuilder sb = new StringBuilder();
      sb.append("Lista de Códigos de Clientes:");
      /**Quando queremos percorrer todos os códigos sem parar
      for(String cc : this.catalogo_clientes){
         sb.append(cc.toString()+"\n");
        }  
      sb.append("O número de códigos de clientes é ");
      sb.append(this.catalogo_clientes.size()+"\n");
      return (sb.toString());
  }
    
  public Catálogo_Clientes clone() {
     return new Catálogo_Clientes(this);
  }*/
}