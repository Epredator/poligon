import org.junit.Assert;
import org.junit.Test;
import sortAlgo.SelectionSort;

/**
 * Created by Adam on 2015-07-14.
 */
public class selectionSortTest {
    @Test
    public void firstTest(){
        SelectionSort a = new SelectionSort();
        int[] tabBeforeSort;
        int[] tabAfterSort;
        tabBeforeSort = new int[]{1, 5, 3, 4, 6, 7};
        tabAfterSort = new int[]{1, 3, 4, 5, 6, 7};
        int[] result = a.sort(tabBeforeSort);
        Assert.assertArrayEquals(tabAfterSort, result);

    }
}
