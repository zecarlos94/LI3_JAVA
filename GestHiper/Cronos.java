import java.util.*;
/**
 * Escreva a descrição da classe Cronos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cronos
{
   /**long System.nanoTime()
 * Crono = mede um tempo entre start() e stop()
 * O tempo é medido em nanosegundos e convertido para 
 *  um double que representa os segs na sua parte inteira.
 * 
 * @author FMM 
 * @version 1.0 / 2006
 */
public class Crono {

  private static long inicio = 0L;
  private static long fim = 0L;
  
 /** inicia a contagem de tempo */
  public static void start() { 
      fim = 0L; inicio = System.nanoTime();  
  }
  
 /** termina a contagem de tempo  e devolve diferença em segs */
  public static double stop() { 
      fim = System.nanoTime();
      long elapsedTime = fim - inicio;
      double segs = elapsedTime / 1.0E09;
      return segs;
  }
  
 /** converte a diferença numa string */
  public static String print() {
      return "" + stop();
  }
}
}
