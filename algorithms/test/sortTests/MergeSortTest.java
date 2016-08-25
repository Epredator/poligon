package sortTests;

import org.junit.Assert;
import org.junit.Test;
import sortAlgo.MergeSort;

/**
 * Created by Adam on 2015-07-14.
 */
public class MergeSortTest {
    @Test
    public void firstTest() throws Exception {
        MergeSort a = new MergeSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 5, 3, 4, 6, 7};
        tabAfterSort = new int[]{1, 3, 4, 5, 6, 7};
        MergeSort.mergeSort(tabBeforeSort, 0, tabBeforeSort.length);
//        Assert.assertArrayEquals(tabAfterSort, result);

    }
}
