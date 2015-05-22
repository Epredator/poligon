import org.junit.Test;

import java.util.ArrayList;

/**
 * User: trojnaradam@gmail.com
 * Date: 21.05.15
 * Time: 23:15
 */
public class algo2Test {
  @Test
  public void evaluatesExpression() {
    algo2 al = new algo2();
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(2);
    l.add(2);
    l.add(2);
    l.add(3);
    l.add(3);
    l.add(3);
    l.add(3);

    int result = al.exAlgo(l);
    System.out.println(result);
//    assertEquals(7, result);
  }
}
