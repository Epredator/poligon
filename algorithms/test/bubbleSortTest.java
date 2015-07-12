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
        Assert.assertEquals(tabAfterSort, result);

    }

}
