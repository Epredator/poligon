package codilityTest;

import codility.Factoria;
import junit.framework.Assert;
import org.junit.Test;

/**
 * User: trojnaradam@gmail.com
 * Date: 19.08.16
 * Time: 15:43
 */
public class FactoriaTest {

  @Test
  public void testFactoriaFor120() throws Exception {
    Factoria f = new Factoria();
    int result = f.factoria(5);
    Assert.assertEquals(120, result);
  }

  @Test
  public void testFactoriaFor0() throws Exception {
    Factoria f = new Factoria();
    int result = f.factoria(0);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testFactoriaForNull() throws Exception {
    Factoria f = new Factoria();
    int result = f.factoria(0);
    Assert.assertEquals(null, result);
  }
}
