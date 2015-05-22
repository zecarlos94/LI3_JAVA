
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class ReadTXT {
  public static void main(String[] args) {
	BufferedReader br = null;
	try {
		String line;
		br = new BufferedReader(new FileReader("/Users/zecarlos/LI3_JAVA/test.txt"));
		while ((line = br.readLine()) != null) {
		   System.out.println(line);
		   StringTokenizer stringTokenizer = new StringTokenizer(line, "/n");
		   while (stringTokenizer.hasMoreElements()) {
		    String linha = stringTokenizer.nextElement().toString();
			StringBuilder sb = new StringBuilder();
			sb.append("\nLinha lida foi: " + linha);
			System.out.println(sb.toString());
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