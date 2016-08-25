package codility;

import java.util.Arrays;

/**
 * User: trojnaradam@gmail.com
 * Date: 20.08.16
 * Time: 11:05
 */
class Solution {
  public boolean solution(int[] A) {
    int k = 0;
    int[]B = Arrays.copyOf(A, A.length);
    Arrays.sort(B);

    for(int i=0; i<A.length; i++){
      if(A[i] != B[i]){
        k++;
      }
      if(k>2){
        return false;
      }
    }
    return true;
  }
}
