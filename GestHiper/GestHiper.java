import java.util.*;
import java.io.*;

public class GestHiper
{
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        int opção;
        String fichData;
        Hipermercado h=new Hipermercado();
        System.out.println("Bem-vindo ao GestHiper!");
        System.out.printf("\n\nEscolha uma opção:\n   (1) Ler ficheiros\n   (2) Carregar estado guardado anteriormente\n"); opção=sc.nextInt();
        if(opção==1) leitura(h);
        else {
            System.out.printf("\nInsira o nome do ficheiro: "); fichData=sc.next();
            h=carregaBinario(fichData);
        }
        do {
            menu(); 
            System.out.printf("\n\nIntroduza uma opção: "); opção=sc.nextInt();
            switch(opção) {
                case 1:
                    System.out.printf("\nInsira o nome do ficheiro: "); fichData=sc.next();
                    guardaBinario(h, fichData);
                    System.out.printf("\nEstado guardado com sucesso!");
                    break;
                case 2:
                    info(h);
                    break;
                case 3:
                    mensalData(h);
                    break;
                case 4:
                    break;
                default:
                    System.out.printf("\nInsira uma opção válida!\n");
                    break;
            }
        } while(opção!=4);
        System.out.printf("\n\nSaiu da aplicação! Adeus!");
    }
    
    public static void menu() {
        System.out.printf("\n\n------------------------------------------------------MENU---------------------------------------------------------");
        System.out.printf("\n\n(1) Guardar estado atual num ficheiro binário");
        System.out.printf("\n(2) Informação do hipermercado");
        System.out.printf("\n(3) Dados mensais");
        System.out.printf("\n(4) Sair");
        System.out.printf("\n\n-------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * Efetua a leitura dos ficheiros e posterior escrita nas estruturas de dados
     */
    public static void leitura(Hipermercado h) {
        Scanner sc=new Scanner(System.in);
        String fichCompras;
        String fichClientes;
        String fichProdutos;
        System.out.printf("\n\nDigite o nome do ficheiro de compras: "); fichCompras=sc.next();
        System.out.printf("Digite o nome do ficheiro de clientes: "); fichClientes=sc.next();
        System.out.printf("Digite o nome do ficheiro de produtos: "); fichProdutos=sc.next();
        System.out.printf("\nA carregar estruturas de dados...");
        Crono.start();
        h.readCatalogos(fichClientes, "clientes");
        h.readCatalogos(fichProdutos, "produtos");
        h.readCompras(fichCompras);
        Crono.stop();
        System.out.println("\n\nTempo de leitura: " +Crono.print()+ " segundos");
    }
    
   /**
    * Grava o estado atual do programa num ficheiro binário
    */
   public static void guardaBinario(Hipermercado h, String filename) throws IOException {
        System.out.printf("\nA guardar estado...");
        Crono.start();
        FileOutputStream fos=new FileOutputStream(filename);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(h);
        oos.close();
        Crono.stop();
        System.out.println("\n\nTempo de escrita: " +Crono.print()+ " segundos");
   }
   
   /**
    * Carrega um estado previamente guardado num ficheiro binário
    */
   public static Hipermercado carregaBinario(String filename) throws IOException, ClassNotFoundException {
        System.out.printf("\nA carregar estruturas de dados...");
        Crono.start();
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream(filename));
        Hipermercado h = (Hipermercado) ois.readObject();
        ois.close();
        Crono.stop();
        System.out.println("\n\nTempo de leitura: " +Crono.print()+ " segundos");
        return h;
   }
   
   /**
    * Informações do hipermercado
    */
   public static void info(Hipermercado h) {
       Crono.start();
       System.out.printf("\n\nNome do ficheiro de compras: %s", h.getComprasNome());
       System.out.printf("\nNome do ficheiro de produtos: %s", h.getProdutosNome());
       System.out.printf("\nNome do ficheiro de clientes: %s", h.getClientesNome());
       System.out.printf("\nTotal de produtos: %d", h.getCatalogoProdutos().size());
       System.out.printf("\nTotal de diferentes produtos comprados: %d", h.getProdutosComprados().size());
       System.out.printf("\nTotal de produtos não comprados: %d", h.totalProdutosNaoComprados());
       System.out.printf("\nTotal de clientes: %d", h.getCatalogoClientes().size());
       System.out.printf("\nTotal de clientes que realizaram compras: %d", h.getClientesCompradores().size());
       System.out.printf("\nTotal de clientes que não fizeram compras: %d", h.totalClientesNaoCompradores());
       System.out.printf("\nTotal de ofertas: %d", h.getComprasGratis());
       System.out.printf("\nFaturação total: %.2f euros", h.getFaturacaoTotal());
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
   }
   
   /**
    * Dados mensais do hipermercado
    */
   public static void mensalData(Hipermercado h) {
       Scanner sc=new Scanner(System.in);
       int opção;
       String line;
       System.out.printf("\n\nInsira um mês: "); opção=sc.nextInt();
       Crono.start();
       System.out.printf("\nTotal de compras por mês: %d", h.comprasMes(opção));
       System.out.printf("\nFaturação total do mês: %.2f euros", h.getFactMes().get(opção-1));
       System.out.printf("\nTotal anual de compras inválidas: %d", h.getInvalidComp().size());
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
       System.out.printf("\n\nInsira o nome do ficheiro para guardar as compras inválidas: "); line=sc.next();
       guardInvalidos(h, line);
   }
   
   /**
    * Guarda todas as linhas inválidas do ficheiro de compras num ficheiro de texto
    */
   public static void guardInvalidos(Hipermercado h, String filename) {
       ArrayList<String> lista=h.getInvalidComp();
       try {
            FileWriter writer=new FileWriter(filename, true);
            BufferedWriter bufferedWriter=new BufferedWriter(writer);
            for(String line: lista) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
       } catch (IOException e) {
            e.printStackTrace();
       }
   }
}
