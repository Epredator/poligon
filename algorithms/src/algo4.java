import java.util.Arrays;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.05.15
 * Time: 10:02
 * znajdŸ liczbê, która jest najmniejsza i wystêpuje w obydwu podanych tablicach
 *
 * #@param A firts table to compare
 * #@param B second table
 * @return Result is the smallest value which is occurs in both tables
 * @see java.util.Random#nextInt(int)
 */
public class Algo4 {

  int findMinVal(int[] tab1, int[] tab2){
      Arrays.sort(tab1);
      Arrays.sort(tab1);
      int n = tab1.length;
      int m = tab2.length;

      int i = 0;
      for (int j = 0; j < n; j++) {
          for(int z = 0; z < n; z++){
              if (tab1[j] == tab2[z])
                  return tab1[j];

          }
      }
    return -1;
  }
}