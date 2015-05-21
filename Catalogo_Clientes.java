import java.util.*;

/**
 * Implementação do Cátalogo de Clientes utilizando um HashSet
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Catalogo_Clientes {
  private HashSet<String> clientes;
  
  public Catalogo_Clientes(){
    this.clientes=new HashSet<String>();
  }
  
  public void insereCC(String cod){ /**que insere um código de cliente no Cátalogo de Clientes*/
      clientes.add(cod);
  }
 
  public boolean existeCC(String cod){ /**que determina se um código de cliente está no Cátalogo de Clientes*/
      return clientes.contains(cod);
  } 
    
  public boolean existeCodClnt(String cod){ /**que determina se código de cliente está no Cátalogo de Clientes*/
      boolean found=false;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = clientes.iterator();
      while(it.hasNext() && !found){
          String s= it.next();
          found=s.equals(cod);
      }
      return found;
  }
    
  public void removeCC(String cod){ /**que remove o código de cliente do Cátalogo de Clientes*/
      boolean found=false;
      String res = "N/A";
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = clientes.iterator();
      while(it.hasNext() && !found){
          String s = it.next();
          found=s.equals(cod);
           if(found) res=s;
      }
      if(res!=null) clientes.remove(res);
  }    

  /*
  public Set<String> listagemNomeclientes(){ /**que devolve a listagem ordenada alfabeticamente dos códigos de clientes
     TreeSet<String> lne = new TreeSet<String> (new NomeCompare());
     for(String s : this.clientes.values()){
        lne.add(s);
     }
     return lne;
  }
 
 
  public boolean equals(Object o) {
     if(this==o) {
          return true;
        }
        
     if ((o==null)||(this.getClass()!=o.getClass())) {
          return false;
        }
     
     else{
           Catalogo_Clientes a = (Catalogo_Clientes) o;
           return (this.clientes.equals(a.getCatalogo_Clientes()));
        }
  } 
  
  public String toString() {
     StringBuilder sb = new StringBuilder();
      sb.append("Lista de Códigos de Clientes: ");
      /**Quando queremos percorrer todos os elementos
      for(String s : this.clientes){
         sb.append(s.toString()+"\n");
        }  
      sb.append("O número de Códigos de Clientes é ");
      sb.append(this.clientes.size()+"\n");
      return (sb.toString());
  }
    
  public Catalogo_Clientes clone() {
     return new Catalogo_Clientes(this);
  }

   HashSet<String> getCatalogo_Clientes(){
     HashSet<String> cc = new HashSet<String> (this.clientes.size());
     for(int i=0;i<this.clientes.size();i++){
        cc.add(i,this.clientes.get(i).clone());  
     }
     return cc;
    }
  */
}
