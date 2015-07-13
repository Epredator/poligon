import org.junit.Assert;
import org.junit.Test;
import sortAlgo.InsertionSort;

/**
 * Created by Adam on 2015-07-13.
 */
public class insertionSortTest {
    @Test
    public void firstTest() throws Exception {
        InsertionSort a = new InsertionSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 5, 3, 4};
        tabAfterSort = new int[]{1, 3, 4, 5};
        int[] result = a.sort(tabBeforeSort);
        Assert.assertArrayEquals(tabAfterSort, result);
    }
}
