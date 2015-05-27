import java.util.ArrayList;

/**
 * niechaj suma liczb z ogona będzie równa sumie liczb z głowy podanej tablicy typu int.
 */
public class algo1 {

  int splitTableValues(int A[], int N) {
    int headResult = 0;
    int tailResult = 0;
    int p = -1;
    ArrayList tempHead = new ArrayList<Integer>();

    for (int i = 0; i < N; i++) {
      headResult += A[i];
      tempHead.add(headResult);
      tailResult += A[N - i];
      if (tempHead.contains(tailResult))
        if (sumHead(A, i, N) == tailResult)
          p = tailResult;
    }
    return p;
  }

  private int sumHead(int[] a, int i, int N) {
    int test = 0;
    for (int j = 0; j < i; j++) {
      test += a[j];
    }
    return test;
  }


}


