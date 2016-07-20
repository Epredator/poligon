/**
 * User: trojnaradam@gmail.com
 * Date: 20.07.16
 * Time: 22:56
 */
public class PiosenkaOPiwie {
  public static void main(String[] args){
    int iloscButelek = 99;
    String slowo = "bootles";
    while (iloscButelek > 0){
      if(iloscButelek == 1){
        slowo = "bootle"; //liczba pojedyncza
      }
      System.out.println(iloscButelek + " " + slowo + " of beer on the wall");
      System.out.println(iloscButelek + " " + slowo + " of beer");
      System.out.println("Take one down");
      System.out.println("Pass it around");
      iloscButelek = iloscButelek -1 ;
      if(iloscButelek > 0){
        System.out.println(iloscButelek + " " + slowo + " of beer on the wall");
      } else {
        System.out.println("No moew bottles of beer on the wall");

      }
    }
  }
}
