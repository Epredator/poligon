import org.junit.Assert;
import org.junit.Test;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.05.15
 * Time: 10:07
 */
public class algo4Test {

  @Test
  public void evaluateAlgo4(){


//    Assert.assertArrayEquals();
  }


  @Test
  public void solutionTest(){
    Solution s = new Solution();
    int[] A = new int[7];
//    int[] B = new int[5];

//    A[0] = 1;
//    A[1] = 3;
//    A[2] = 2;
//    A[3] = 1;

    A[0] = 5 ;
    A[1] = 5  ;
    A[2] = 1   ;
    A[3] = 7    ;
    A[4] = 2     ;
    A[5] = 3      ;
    A[6] = 5       ;



    int result = s.solution(5, A);
    Assert.assertEquals(4, result);


//    Assert.assertArrayEquals();
  }
}
