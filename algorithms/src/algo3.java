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
  int counter = 0;

  int exAlgo(int A[], int N) {
    n = N;
    tab = A;
    sortTab = new int[n];

    for (int num = 0; num < A.length; num++)
      if (checkExist(A[num]) == false) {
        sortTab[counter] = A[num];
        counter++;

      }
    sumSortTab();

    return sum;
  }

  private boolean checkExist(int num) {
    boolean status = false;
    for (int i = 0; i < sortTab.length; i++) {
      if (sortTab[i] == num) {
        status = true;
        break;
      }
    }
    return status;
  }

  private void sumSortTab() {
    for (int i = 0; i < sortTab.length; i++)
      sum += sortTab[i];
  }

}
