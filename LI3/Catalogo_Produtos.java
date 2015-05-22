import java.util.*;

/**
 * Implementação do Cátalogo de Produtos utilizando um HashSet
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Catalogo_Produtos {
  private HashSet<String> produtos;
  
  public Catalogo_Produtos(){
    this.produtos=new HashSet<String>();
  }
  
  public void insereCP(String cod){ /**que insere um código de produto no Cátalogo de Produtos*/
      produtos.add(cod);
  }
 
  public boolean existeCP(String cod){ /**que determina se um código de produto está no Cátalogo de Produtos*/
      return produtos.contains(cod);
  } 
    
  public boolean existeCodPrd(String cod){ /**que determina se código de produto está no Cátalogo de Produtos*/
      boolean found=false;
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = produtos.iterator();
      while(it.hasNext() && !found){
          String s= it.next();
          found=s.equals(cod);
      }
      return found;
  }
    
  public void removeCP(String cod){ /**que remove o código de produto do Cátalogo de Produtos*/
      boolean found=false;
      String res = "N/A";
      /**Quando queremos percorrer até encontrar algo que pretendemos*/
      Iterator<String> it = produtos.iterator();
      while(it.hasNext() && !found){
          String s = it.next();
          found=s.equals(cod);
           if(found) res=s;
      }
      if(res!=null) produtos.remove(res);
  }    

  /*
  public Set<String> listagemNomeProdutos(){ /**que devolve a listagem ordenada alfabeticamente dos códigos de Produtos
     TreeSet<String> lne = new TreeSet<String> (new NomeCompare());
     for(String s : this.produtos.values()){
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
           Catalogo_Produtos a = (Catalogo_Produtos) o;
           return (this.produtos.equals(a.getCatalogo_Produtos()));
        }
  } 
  
  public String toString() {
     StringBuilder sb = new StringBuilder();
      sb.append("Lista de Códigos de Produtos: ");
      /**Quando queremos percorrer todos os elementos
      for(String s : this.produtos){
         sb.append(s.toString()+"\n");
        }  
      sb.append("O número de Códigos de Produtos é ");
      sb.append(this.produtos.size()+"\n");
      return (sb.toString());
  }
    
  public Catalogo_Produtos clone() {
     return new Catalogo_Produtos(this);
  }

   HashSet<String> getCatalogo_Produtos(){
     HashSet<String> cp = new HashSet<String> (this.produtos.size());
     for(int i=0;i<this.produtos.size();i++){
        cp.add(i,this.produtos.get(i).clone());  
     }
     return cp;
    }
  */
}
