class Solution {
  int length;
  int[] headTab = new int[length];
  int[] tailTab = new int[length];
  int ex(int X, int[] A) {
    length = A.length;

    int result = 0;
    int counter = 0;
    for (int i = 0; i < length; i++) {
      if (i < X - 1) {
        headTab[i] = A[i];
      } else {
        tailTab[counter] = A[i];
        counter++;
      }
    }

    for (int h = 0; h < length; h++)
      if (headTab[h] == X)
        result++;


    for (int t = 0; t < length; t++)
      if (tailTab[t] != X && tailTab[t] != 0)
        result++;

    if (result == 0)
      result = -1;


      return result;
  }


}


//class Solution {
//  int solution(int[] A, int[] B) {
//    int n = A.length;
//    int m = B.length;;
//    Arrays.sort(A);
//    Arrays.sort(B);
//    int i = 0;
//    for (int k = 0; k < n; k++) {
//      if (i < m - 1 && B[i] < A[k])
//        i += 1;
//      if (A[k] == B[i] && A[k] != 0 )
//        return A[k];
//    }
//    return -1;
//  }
//}
