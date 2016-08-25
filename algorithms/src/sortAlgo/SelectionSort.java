package sortAlgo;

/**
 * Created by Adam on 2015-07-14.
 *
 * Metoda ta nazywana jest sortowaniem przez wymianê
 * gdy¿ na pocz¹tku szukany jest najmniejszy element,
 * po znalezieniu go jest on zamieniany z pierwszym elementem tablicy.
 * Nastêpnie szukany jest znów najmniejszy element,
 * ale pocz¹wszy od elementu drugiego
 * (pierwszy - najmniejszy jest ju¿ wstawiony na odpowiednie miejsce),
 * po jego znalezieniu jest on zamieniany z drugim elementem.
 * Czynnoœæ t¹ powtarzamy kolejno na elementach od trzeciego, czwartego, a¿ do n-tego
 */
public class SelectionSort {
    public int[] sort(int[] items) {
        int sortedRangeEnd = 0;

        while (sortedRangeEnd < items.length){
            int nextIndex = findIndexOfSmallestFromIndex(items, sortedRangeEnd);
            swap(items, sortedRangeEnd, nextIndex);
            sortedRangeEnd++;
        }
        return items;
    }

    private void swap(int[] items, int left, int right) {
        if(left != right){
            int temp = items[left];
            items[left] = items[right];
            items[right] = temp;
        }
    }

    private int findIndexOfSmallestFromIndex(int[] items, int sortedRangeEnd) {
        int currentSmallestIndex = sortedRangeEnd;
        int currentSmallest = items[sortedRangeEnd];

        for(int i = sortedRangeEnd + 1; i< items.length; i++){
            if(currentSmallest > items[i]){
                currentSmallest = items[i];
                currentSmallestIndex = i;
            }
        }
        return currentSmallestIndex;
    }
}
