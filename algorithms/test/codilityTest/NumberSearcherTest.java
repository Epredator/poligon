package codilityTest;

import codility.NumberSearcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: trojnaradam@gmail.com
 * Date: 19.08.16
 * Time: 17:23
 */
public class NumberSearcherTest {

  @Test
  public void normalSearch(){
    NumberSearcher n = new NumberSearcher();
    int[] data;
    data = new int[] {10,20,30,40,50,60,-1,80,90,91};
    int rep = n.search1(data);
    Assert.assertEquals(7, rep);
  }
}
