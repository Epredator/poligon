import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.05.15
 * Time: 10:07
 */
public class algo4Test {

  @Test
  public void firstTest(){
    Algo4 a = new Algo4();
    int[] tabA = new int[5];
    int[] tabB = new int[5];
    tabA = new int[]{1, 2, 3, 4};
    tabB = new int[]{21, 42, 22, 3};
    int result = a.findMinVal(tabA, tabB);
    Assert.assertEquals(3, result);

  }

  @Test
  public void testWinouthCorrespondentValue(){
    Algo4 a = new Algo4();
    int[] tabA = new int[5];
    int[] tabB = new int[5];
    tabA = new int[]{1, 2, 3, 4};
    tabB = new int[]{21, 42, 22, 3};

    int result = a.findMinVal(tabA, tabB);
    Assert.assertEquals(-1, result);
  }

  @Test
  public void testWithMin0ValLenght600(){
    Algo4 a = new Algo4();
    int[] tabA = new int[600];
    int[] tabB = new int[600];
    Random rand = new Random();
    int max = 1000000000;
    int min = 0;
//    int n = maximum - minimum + 1;
//    int i = rn.nextInt() % n;
//    randomNum =  minimum + i;
//    tabA[0]= 0;
//    tabB[0]= 0;

    for(int i=0; i<600; i++){
      int randomNum = rand.nextInt((max - min) + 1) + min;
      int anotheRandomNum = rand.nextInt((max - min) + 1) + min;
      tabA[i]= randomNum;
      tabB[i]= anotheRandomNum;
    }


    int result = a.findMinVal(tabA, tabB);
    Assert.assertEquals(0, result);


//    Assert.assertArrayEquals();
  }


//  @Test
//  public void solutionTest(){
//    Solution s = new Solution();
//    int[] A = new int[7];
////    int[] B = new int[5];
//
////    A[0] = 1;
////    A[1] = 3;
////    A[2] = 2;
////    A[3] = 1;
//
//    A[0] = 5 ;
//    A[1] = 5  ;
//    A[2] = 1   ;
//    A[3] = 7    ;
//    A[4] = 2     ;
//    A[5] = 3      ;
//    A[6] = 5       ;
//
//
//
//    int result = s.solution(5, A);
//    Assert.assertEquals(4, result);
//
//
////    Assert.assertArrayEquals();
//  }
}
