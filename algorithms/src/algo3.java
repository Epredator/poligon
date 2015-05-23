/**
 * User: trojnaradam@gmail.com
 * Date: 23.05.15
 * Time: 22:01
 * zsumuj unikalne wartości występujące w tablicy
 */


public class algo3 {
  int n = 0;
  int sum;
  int[] tab = new int[n];
  int[] sortTab;

  int exAlgo(int A[], int N) {
    n = N;
    tab = A;
    sortTab = new int[n];

    for (int i = 0; i < A.length; i++)
      checkExist(A[i]);
    sumSortTab();

    return sum;
  }

  private void checkExist(int num) {
    int counter = 0;

    for (int i = 0; i < tab.length; i++) {
      boolean duplicateStatus = false;
      if (sortTab[i] == num)
        continue;
      else if (sortTab[i] != num && duplicateStatus == false) {
        sortTab[counter] = num;
        duplicateStatus = true;
        counter++;
      }
    }
  }

  private void sumSortTab() {
    for (int i = 0; i < sortTab.length; i++)
      sum += sortTab[i];
  }

}
