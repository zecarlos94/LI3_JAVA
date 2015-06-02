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
                    imprimeInvalidos(h, "produtos");
                    break;
                case 5:
                    imprimeInvalidos(h, "clientes");
                    break;
                case 6:
                    compClntMes(h);
                    break;
                case 7:
                    clntInfoMensal(h);
                    break;
                case 8:
                    break;
                default:
                    System.out.printf("\nInsira uma opção válida!\n");
                    break;
            }
        } while(opção!=8);
        System.out.printf("\n\nSaiu da aplicação! Adeus!");
    }
    
    public static void menu() {
        System.out.printf("\n\n------------------------------------------------------MENU---------------------------------------------------------");
        System.out.printf("\n\n(1) Guardar estado atual num ficheiro binário");
        System.out.printf("\n(2) Informação do hipermercado");
        System.out.printf("\n(3) Dados mensais");
        System.out.printf("\n(4) Lista ordenada com os códigos dos produtos nunca comprados e respectivo total");
        System.out.printf("\n(5) Lista ordenada com os códigos dos clientes que nunca compraram e respectivo total");
        System.out.printf("\n(6) Total de compras e clientes ativos num dado mês");
        System.out.printf("\n(7) Informação mensal de um cliente");
        System.out.printf("\n(8) Sair");
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
    * Grava o estado atual do programa num ficheiro binário (Opção 1)
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
    * Informações do hipermercado (Opção 2)
    */
   public static void info(Hipermercado h) {
       Crono.start();
       CatalogoClientes clnt=h.getCatalogoClientes();
       CatalogoProdutos prod=h.getCatalogoProdutos();
       Contabilidade cont=h.getContabilidade();
       System.out.printf("\n\nNome do ficheiro de compras: %s", h.getComprasNome());
       System.out.printf("\nNome do ficheiro de produtos: %s", prod.getProdutosNome());
       System.out.printf("\nNome do ficheiro de clientes: %s", clnt.getClientesNome());
       System.out.printf("\nTotal de produtos: %d", prod.getCatalogoProdutos().size());
       System.out.printf("\nTotal de diferentes produtos comprados: %d", prod.getProdutosComprados().size());
       System.out.printf("\nTotal de produtos não comprados: %d", prod.totalProdutosNaoComprados());
       System.out.printf("\nTotal de clientes: %d", clnt.getCatalogoClientes().size());
       System.out.printf("\nTotal de clientes que realizaram compras: %d", clnt.getClientesCompradores().size());
       System.out.printf("\nTotal de clientes que não fizeram compras: %d", clnt.totalClientesNaoCompradores());
       System.out.printf("\nTotal de ofertas: %d", cont.getComprasGratis());
       System.out.printf("\nFaturação total: %.2f euros", cont.getFaturacaoTotal());
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
   }
   
   /**
    * Dados mensais do hipermercado (Opção 3)
    */
   public static void mensalData(Hipermercado h) {
       Scanner sc=new Scanner(System.in);
       int opção;
       String line;
       Contabilidade cont=h.getContabilidade();
       System.out.printf("\n\nInsira um mês: "); opção=sc.nextInt();
       Crono.start();
       System.out.printf("\nTotal de compras por mês: %d", h.comprasMes(opção));
       System.out.printf("\nFaturação total do mês: %.2f euros", cont.getFactMes().get(opção-1));
       System.out.printf("\nTotal de clientes que fizeram compras durante o mês: %d", h.clientesMes(opção));
       System.out.printf("\nTotal anual de compras inválidas: %d", cont.getInvalidComp().size());
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
       System.out.printf("\n\nDeseja guardar as linhas de compras inválidas num ficheiro de texto?\n   (1) Sim\n   (2) Não\n"); opção=sc.nextInt();
       if(opção==1) {
           System.out.printf("\nInsira o nome do ficheiro para guardar as compras inválidas: "); line=sc.next();
           guardInvalidos(h, line);
       }
   }
   
   /**
    * Guarda todas as linhas inválidas do ficheiro de compras num ficheiro de texto
    */
   public static void guardInvalidos(Hipermercado h, String filename) {
       Contabilidade cont=h.getContabilidade();
       ArrayList<String> lista=cont.getInvalidComp();
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
   
   /**
    * Imprime no ecrã a lista dos produtos ou clientes inválidos (Opções 4 e 5)
    */
   public static void imprimeInvalidos(Hipermercado h, String type) {
       TreeSet<String> lista=h.devolveLista(type);
       ArrayList<String> aux=new ArrayList<String>();
       Iterator<String> it=lista.iterator();
       while(it.hasNext()) {
           String s=it.next();
           aux.add(s);
       }
       navigation(aux);
       System.out.printf("\n\nTotal de %s inválidos: %d", type, lista.size());
   }
   
   /**
    * Devolve o número de clientes e de compras num mês (Opção 6)
    */
   public static void compClntMes(Hipermercado h) {
       Scanner sc=new Scanner(System.in);
       int mes;
       System.out.printf("\n\nInsira um mês: "); mes=sc.nextInt();
       Crono.start();
       System.out.printf("\nTotal de compras: %d", h.comprasMes(mes));
       System.out.printf("\nTotal de clientes: %d", h.clientesMes(mes));
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
   }
   
   /**
    * Imprime a informação mensal de um cliente (Opção 7)
    */
   public static void clntInfoMensal(Hipermercado h) {
       Scanner sc=new Scanner(System.in);
       String cliente;
       System.out.printf("\n\nInsira o código do cliente: "); cliente=sc.next();
       Crono.start();
       for(int i=0; i<12; i++) {
           System.out.printf("\nMês %d: ", i+1);
           System.out.printf("\n   Total de compras efetuadas: %d | Total de produtos comprados: %d | Gasto mensal: %.2f euros", h.comprasClntMes(cliente, i), h.clientesProdMes(cliente, i), h.gastosClnt(cliente, i));
       }
       System.out.printf("\n\nTotal gasto durante o ano: %.2f", h.gastoAnualClnt(cliente));
       Crono.stop();
       System.out.println("\n\nTempo de execução: " +Crono.print()+ " segundos");
   }
   
   /**
    * Função geral de navegação em ArrayList
    */
   public static void navigation(ArrayList<String> list) {
       Scanner sc=new Scanner(System.in);
       int res, opção;
       System.out.printf("\n\nInsira o número de códigos que deseja ver no ecrã: "); res=sc.nextInt();
       int intervalo=list.size()/res;
       System.out.printf("\n**Ajuda** Insira primeiro o número da página antes de visualizar no ecrã\n"); 
       do {
           System.out.printf("\n   (0) Sair\n   (1 a %d) Escolher página: ", intervalo+1); opção=sc.nextInt();
           System.out.printf("\n");
           if(opção==intervalo+1) for(int i=(opção-1)*res; i<(opção-1)*res+(list.size()-(intervalo*res)); i++) System.out.printf("%s\n", list.get(i));
           else if(opção>0 && opção<=intervalo+1) for(int i=(opção-1)*res; i<(opção-1)*res+res; i++) System.out.printf("%s\n", list.get(i));
           if(opção<0 || opção>intervalo+1) System.out.printf("\n\nInsira um valor correto!");
       } while(opção!=0);
   }
   
   /**
    *   Query 5
    */
   
   public static void query5(String produto,Hipermercado h){
        ArrayList<InfoProdutoMes> info = h.getInformacaoMensalProduto(produto);
        int i = 0;
        
        System.out.println("Informaçao mensal do produto" + produto);
        for(i=0; i < 12; i++)
        {
            InfoProdutoMes infoM = info.get(i);
            System.out.println("Mes " + i);
            System.out.printf("Número de compras: %d\nNúmero de clientes distintos:%d\nTotal facturado:%.2f\n",(infoM.getComprasP() + infoM.getComprasN()),infoM.numeroClientesDistintos(),(infoM.getFactP()+infoM.getFactN()));
            
            
        }
    }
    
    public static void query6(String produto,Hipermercado h){
         ArrayList<InfoProdutoMes> info = h.getInformacaoMensalProduto(produto);
        int i = 0;
        
        System.out.println("Informaçao mensal do produto" + produto);
        for(i=0; i < 12; i++)
        {
            InfoProdutoMes infoM = info.get(i);
            System.out.println("Mes " + i);
            System.out.printf("Número de compras sem promoção %d com o total facturado %.2f\n",infoM.getComprasN(),infoM.getFactN());
            System.out.printf("Número de compras em promoção %d com o total facturado %.2f\n",infoM.getComprasP(),infoM.getFactP());
        }
    }
   
    
    

}