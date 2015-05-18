import java.io.*;
import static java.lang.System.*;
import java.util.*;
public class Leitura {
  private Catálogo_Clientes cat_cl = new Catálogo_Clientes(new ComparatorNome());  
  private Catálogo_Produtos cat_prd = new Catálogo_Produtos(new ComparatorNome()); 
  public static void clientes(String[] args) {
    BufferedReader br = null;
    Scanner sc = new Scanner(System.in);
    String resposta;
    resposta="N/A";
    try {
        String line;
        out.println("\nInsira o nome do ficheiro .txt de clientes que pretende ler\n");
        resposta=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+resposta));
        while ((line = br.readLine()) != null) {
           System.out.println(line);
           StringTokenizer stringTokenizer = new StringTokenizer(line, "\n");
           while (stringTokenizer.hasMoreElements()) {
            String product_code = stringTokenizer.nextElement().toString();
            StringBuilder sb = new StringBuilder();
            sb.append("\nProduct Code: " + product_code);
            sb.append("\n*******************\n");
            
            cat_cl.insereCC(product_code);
            //System.out.println(sb.toString());
           }
        }
        System.out.println("Acabou");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null)
                br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 }   
    
  public static void produtos(String[] args) {
    BufferedReader br = null;
    Scanner sc = new Scanner(System.in);
    String resposta;
    resposta="N/A";
    try {
        String line;
        out.println("\nInsira o nome do ficheiro .txt de produtos que pretende ler\n");
        resposta=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+resposta));
        while ((line = br.readLine()) != null) {
           System.out.println(line);
           StringTokenizer stringTokenizer = new StringTokenizer(line, "\n");
           while (stringTokenizer.hasMoreElements()) {
            String product_code = stringTokenizer.nextElement().toString();
            StringBuilder sb = new StringBuilder();
            sb.append("\nProduct Code: " + product_code);
            sb.append("\n*******************\n");
            
            cat_prd.insereCP(product_code);
            //System.out.println(sb.toString());
           }
        }
        System.out.println("Acabou");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null)
                br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 } 
 
  public static void compras(String[] args) {
    BufferedReader br = null;
    Scanner sc = new Scanner(System.in);
    String resposta;
    resposta="N/A";
    try {
        String line;
        out.println("\nInsira o nome do ficheiro .txt de compras que pretende ler\n");
        resposta=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+resposta));
        while ((line = br.readLine()) != null) {
           System.out.println(line);
           StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
           while (stringTokenizer.hasMoreElements()) {
            String product_code = stringTokenizer.nextElement().toString();
            Double price = Double.parseDouble(stringTokenizer.nextElement().toString());
            Integer quantity = Integer.parseInt(stringTokenizer.nextElement().toString());
            String purchase_mode = stringTokenizer.nextElement().toString();
            String client_code = stringTokenizer.nextElement().toString();
            Integer month = Integer.parseInt(stringTokenizer.nextElement().toString());
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("\nProduct Code: " + product_code);
            sb.append("\nPrice: " + price);
            sb.append("\nQuantity: " + quantity);
            sb.append("\nPurchase Mode: " + purchase_mode);
            sb.append("\nClient Code: " + client_code);
            sb.append("\nMonth : " + month);
            sb.append("\n*******************\n");
            
            //System.out.println(sb.toString());
           }
        }
        System.out.println("Done");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (br != null)
                br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
		}
	}
 }
}