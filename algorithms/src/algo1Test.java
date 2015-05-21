import org.junit.Test;

/**
 * User: trojnaradam@gmail.com
 * Date: 20.05.15
 * Time: 22:17
 * Nie zrobione poprawnie w skarjnych przypadkach. Znaleść błąd!
 */
public class algo1Test {

  @Test
  public void evaluatesExpression() {
    algo1 al = new algo1();
    int[] A = new int[8];
    A[0] = -1;
    A[1] =  -2;
    A[2] = 1;
    A[3] = -4;

    int result = al.exAlgo(A, 4);
    System.out.println(result);
//    assertEquals(7, result);
  }

}
