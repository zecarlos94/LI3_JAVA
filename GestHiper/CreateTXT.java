
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
 
/**
 * This program demonstrates how to write characters to a text file
 * using a BufferedReader for efficiency.
 * @author www.codejava.net
 *
 */
public class CreateTXT {
 
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("/Users/zecarlos/LI3_JAVA/Test.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write("Olá");
            bufferedWriter.newLine();
            bufferedWriter.write("POO/LI3!");
 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
}