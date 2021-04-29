package sortTests;

import org.junit.Assert;
import org.junit.Test;
import sortAlgo.bubbleSort;

/**
 * Created by Adam on 2015-07-12.
 */
public class bubbleSortTest {
    @Test
    public void firstTest() {
        bubbleSort a = new bubbleSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 5, 3, 4};
        tabAfterSort = new int[]{1, 3, 4, 5};
        int[] result = a.bubbleSort(tabBeforeSort);
        Assert.assertArrayEquals(tabAfterSort, result);
    }

    @Test
    public void sameValuesTest() {
        bubbleSort a = new bubbleSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 2, 3, 4};
        tabAfterSort = new int[]{1, 2, 3, 4};
        int[] result = a.bubbleSort(tabBeforeSort);
        Assert.assertArrayEquals(tabAfterSort, result);
    }

    @Test
    public void diffrentValuesInTablesTest() {
        bubbleSort a = new bubbleSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 2, 3, 4};
        tabAfterSort = new int[]{5, 6, 7, 8};
        int[] result = a.bubbleSort(tabBeforeSort);
//        Assert.assertArrayEquals(tabAfterSort, result);
        Assert.assertNotEquals(tabAfterSort, result);
    }

    @Test
    public void zerosValues() {
        bubbleSort a = new bubbleSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{0, 2, 0, 4};
        tabAfterSort = new int[]{0, 0, 2, 4};
        int[] result = a.bubbleSort(tabBeforeSort);
        Assert.assertArrayEquals(tabAfterSort, result);
    }

}
