import java.util.Arrays;

/**
 * User: trojnaradam@gmail.com
 * Date: 24.05.15
 * Time: 10:02
 * znajdz liczbe, ktora jest najmniejsza i wysteouje w obydwu tablicach
 *
 * #@param A firts table to compare
 * #@param B second table
 * @return Result is the smallest value which is occurs in both tables
 * @see java.util.Random#nextInt(int)
 */
public class algo4 {

  int findMinVal(int[] tab1, int[] tab2){
      Arrays.sort(tab1);
      Arrays.sort(tab1);
      int n = tab1.length;
      int m = tab2.length;

      for (int j = 0; j < n; j++)
          for(int i = 0; i < m; i++)
              if (tab1[j] == tab2[i])
                  return tab1[j];
    return -1;
  }
}