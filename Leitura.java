import java.util.*;
import java.io.*;
import static java.lang.System.*;
/**
 * Escreva a descrição da classe dkjvb aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Leitura
{
   public static void clientes(String[] args) {
    BufferedReader br = null;
    try {
        String line;
        String ficheiro;
        Scanner sc = new Scanner (System.in); 
        out.println("Insira o nome do ficheiro de clientes que pretende ler");
        ficheiro=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+ficheiro+".txt"));
        while ((line = br.readLine()) != null) {
           System.out.println(line);
           StringTokenizer stringTokenizer = new StringTokenizer(line, "\n");
           while (stringTokenizer.hasMoreElements()) {
            String client_code = stringTokenizer.nextElement().toString();
            StringBuilder sb = new StringBuilder();
            sb.append("\nClient Code: " + client_code);
            sb.append("\n*******************\n");
            System.out.println(sb.toString());
           }
        }
        System.out.println("Terminou");
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
    try {
        String line;
        String ficheiro;
        Scanner sc = new Scanner (System.in); 
        out.println("Insira o nome do ficheiro de produtos que pretende ler");
        ficheiro=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+ficheiro+".txt"));
        while ((line = br.readLine()) != null) {
           System.out.println(line);
           StringTokenizer stringTokenizer = new StringTokenizer(line, "\n");
           while (stringTokenizer.hasMoreElements()) {
            String product_code = stringTokenizer.nextElement().toString();
            StringBuilder sb = new StringBuilder();
            sb.append("\nProduct Code: " + product_code);
            sb.append("\n*******************\n");
            System.out.println(sb.toString());
           }
        }
        System.out.println("Terminou");
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
    try {
        String line;
        String ficheiro;
        Scanner sc = new Scanner (System.in);
        out.println("Insira o nome do ficheiro de compras que pretende ler");
        ficheiro=sc.next();
        br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/"+ficheiro+".txt"));
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
            
            System.out.println(sb.toString());
           }
        }
        System.out.println("Terminou");
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
