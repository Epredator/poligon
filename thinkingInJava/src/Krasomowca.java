/**
 * User: trojnaradam@gmail.com
 * Date: 21.07.16
 * Time: 22:46
 */
public class Krasomowca {
  public static void Main(String[] args){
    //trzy grupy slow, ktore beda wybierane do zdania

    String[] listaSlow = {"architektura wielowarstwowa", "3000 metrów"};
    String[] listaSlow2 = {"zwieksza mozliwosci", "pomnaza wartosc"};
    String[] listaSlow3 = {"procesu", "rozwiazania"};

    //okreslenie, ile jest slow w kazdej z list
    int lista1Dlugosc = listaSlow.length;
    int lista2Dlugosc = listaSlow2.length;
    int lista3Dlugosc = listaSlow3.length;

    //generacja 3 losowych slow
    int rnd1 = (int) (Math.random() * lista1Dlugosc);
    int rnd2 = (int) (Math.random() * lista2Dlugosc);
    int rnd3 = (int) (Math.random() * lista3Dlugosc);

    //stworzenie zdania
    String zdanie = listaSlow[rnd1] + " " + listaSlow2[rnd2] + " " + listaSlow3[rnd3];

    //wyswietlenie zdania
    System.out.println("To jest to czego nam trzeba: " + zdanie);
  }
}
