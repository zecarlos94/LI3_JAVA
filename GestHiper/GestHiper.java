import java.util.*;
import java.io.*;

public class GestHiper
{
    public static void main(String args[]) throws IOException {
        Scanner sc=new Scanner(System.in);
        int opção;
        
        Hipermercado h=new Hipermercado();
        
        String fichCompras;
        String fichClientes;
        String fichProdutos;
        String fichData;
        
        System.out.println("Bem-vindo ao GestHiper!");
        System.out.printf("\n\nDigite o nome do ficheiro de compras: "); fichCompras=sc.next();
        System.out.printf("Digite o nome do ficheiro de clientes: "); fichClientes=sc.next();
        System.out.printf("Digite o nome do ficheiro de produtos: "); fichProdutos=sc.next();
        
        System.out.printf("\nA carregar estruturas de dados...");
        
        Crono.start();
        
        h.readCatalogos(fichClientes, "clientes");
        h.readCatalogos(fichProdutos, "produtos");
        h.readCompras(fichCompras);
        
        Crono.stop();
       
        System.out.printf("\n\nTerminou a leitura dos ficheiros!\n");   
        System.out.println("\nTempo de execução: " +Crono.print()+ " segundos");
        
        System.out.printf("\n\nDeseja guardar o estado atual da aplicação num ficheiro binário?\n   1-Sim\n   2-Não\n"); opção=sc.nextInt();
        if(opção==1) {
            System.out.printf("\nInsira o nome do ficheiro: "); fichData=sc.next();
            guardaBinario(h, fichData);
            System.out.printf("\nFicheiro criado com sucesso!");
        }
        
        System.out.printf("\n\nSaiu da aplicação! Adeus!");
    }  
    
   /**
    * Grava o estado atual do programa num ficheiro binário
    */
   public static void guardaBinario(Hipermercado h, String filename) throws IOException {
        FileOutputStream fos=new FileOutputStream(filename);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(h);
        oos.close();
   }
}
