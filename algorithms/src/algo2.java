import java.util.*;

/**
 * User: trojnaradam@gmail.com
 * Date: 21.05.15
 * Time: 19:18
 * <p/>
 * wypisz liczbę, która występuje w ArrayList więcej niż połowę razy, w innym przypadku wyrzuć 1;
 */
public class algo2 {

  HashMap<Integer, Integer> sortedMap = new HashMap<Integer, Integer>();
  ArrayList<Integer> list = new ArrayList<Integer>();
  int result = -1;

  int exAlgo(ArrayList<Integer> l) {
    list = l;
    sortNumbers();
    findBiggestVal();
    return result;

  }

  private void findBiggestVal() {
    Collection<Integer> vals = sortedMap.values();
    Integer bigVal = Integer.MIN_VALUE;
    for (Integer v : vals) {
      if (v > vals.size() / 2 && v > bigVal) {
        bigVal = v;

      }
    }

    result = getKeyByValue(sortedMap, bigVal);
  }

  public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Map.Entry<T, E> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  private void sortNumbers() {
    for (Integer l : list) {
      if (sortedMap.containsKey(l))
        sortedMap.put(l, sortedMap.get(l) + 1);
      else
        sortedMap.put(l, 1);
    }
  }


}




