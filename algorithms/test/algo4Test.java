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
    public void firstTest() {
        algo4 a = new algo4();
        int[] tabA;
        int[] tabB;
        tabA = new int[]{1, 2, 3, 4};
        tabB = new int[]{21, 42, 22, 3};
        int result = a.findMinVal(tabA, tabB);
        Assert.assertEquals(3, result);

    }

    @Test
    public void testWinouthCorrespondentValue() {
        algo4 a = new algo4();
        int[] tabA;
        int[] tabB;
        tabA = new int[]{1, 2, 3, 4};
        tabB = new int[]{21, 42, 22, 3};

        int result = a.findMinVal(tabA, tabB);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testWithMin0ValLenght600() {
        algo4 a = new algo4();
        int[] tabA = new int[600];
        int[] tabB = new int[600];
        Random rand = new Random();
        int max = 1000000000;
        int min = 0;

        for (int i = 0; i < 600; i++) {
            int randomNum = rand.nextInt((max - min) + 1) + min;
            int anotheRandomNum = rand.nextInt((max - min) + 1) + min;
            tabA[i] = randomNum;
            tabB[i] = anotheRandomNum;
        }
        int result = a.findMinVal(tabA, tabB);
        Assert.assertEquals(0, result);
    }

    @Test //TODO: make better test case.
    public void randValues() {
        algo4 a = new algo4();
        int[] tabA = new int[600];
        int[] tabB = new int[600];
        Random rand = new Random();
        for (int i = 0; i < 600; i++) {
            int randomNum = rand.nextInt();
            int anotheRandomNum = rand.nextInt();
            tabA[i] = randomNum;
            tabB[i] = anotheRandomNum;
        }

        int result = a.findMinVal(tabA, tabB);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void zeroOnEndOfTable() {
        algo4 a = new algo4();
        int[] tabA = new int[601];
        int[] tabB = new int[601];
        Random rand = new Random();
        for (int i = 0; i < 600 - 1; i++) {
            int randomNum = rand.nextInt();
            int anotheRandomNum = rand.nextInt();
            tabA[i] = randomNum;
            tabB[i] = anotheRandomNum;
        }
        tabA[600] = 0;
        tabB[600] = 0;


        int result = a.findMinVal(tabA, tabB);
        Assert.assertEquals(0, result);
    }


}
