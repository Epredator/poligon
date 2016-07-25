/**
 * User: trojnaradam@gmail.com
 * Date: 25.07.16
 * Time: 18:17
 */
public class Zgadywanka {
  Gracz p1;
  Gracz p2;
  Gracz p3;

  public void rozpocznijGre(){
    p1 = new Gracz();
    p2 = new Gracz();
    p3 = new Gracz();

    int typp1 = 0;
    int typp2 = 0;
    int typp3 = 0;

    boolean p1odgadl = false;
    boolean p2odgadl = false;
    boolean p3odgadl = false;

    int liczbaOdgadywana = (int) (Math.random() * 10);
    System.out.println("Mysle o liczbie z zakresu od 0 do 9...");

    while (true) {
      System.out.println("Nalezy wytypowac liczbe: " + liczbaOdgadywana);
      p1.zgaduj();
      p2.zgaduj();
      p3.zgaduj();


    }


  }
}

class Gracz{
  int liczba = 0;
  public void zgaduj(){
    liczba = (int) (Math.random() * 10);
    System.out.println("Typuje liczbe: " + liczba);
  }



}
