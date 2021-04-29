package other;

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
