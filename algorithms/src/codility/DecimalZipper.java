package codility;

/**
 * User: trojnaradam@gmail.com
 * Date: 20.08.16
 * Time: 11:31
 */
public class DecimalZipper {
  public int solution(int A, int B) {
    // write your code in Java SE 8
    String stringA = String.valueOf(A);
    String stringB = String.valueOf(B);
    String text = "";

    char[] digitsA = stringA.toCharArray();
    char[] digitsB = stringB.toCharArray();
    for(int i=0; i<stringA.length() || i<stringB.length(); i++){
      if(i < stringA.length()){
        text = text + digitsA[i];
      }
      if(i < stringB.length()){
        text = text + digitsB[i];
      }
    }
    return (Integer.parseInt(text));
  }
}
