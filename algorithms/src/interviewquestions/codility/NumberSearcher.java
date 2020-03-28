package interviewquestions.codility;

/**
 * User: trojnaradam@gmail.com
 * Date: 19.08.16
 * Time: 16:58
 */
public class NumberSearcher {

  public int search1(int[] a) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == -1) {
        return i+1;
      }


    }
    return 0;
  }
}
