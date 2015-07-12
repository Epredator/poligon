package sortAlgo;

/**
 * Created by Adam on 2015-07-12.
 * Bubble sort is a na?ve sorting algorithm that operates by making multiple passes through the array,
 * each time moving the largest unsorted value to the right (end) of the array.
 * Average Case: O(n^2)
 */
public class bubbleSort {
    public int[] bubbleSort(int[] items){
        boolean swapped;
        int tempInt;
        do {
            swapped = false;
            for (int i = 1; i < items.length; i++) {
                if (items[i - 1] > (items[i])) {
                    tempInt = items[i];
                    items[i]=items[i-1];
                    items[i-1]= tempInt;
                    swapped = true;
                }
            }
        } while (swapped != false);


        return items;
    }
}
