package com.company;

/**
 * User: trojnaradam@gmail.com
 * Date: 08.04.15
 * Time: 18:49
 */
public class exp119 {
  int obliczPole(int wysokosc, int szerokosc){
    return wysokosc * szerokosc;
  }



  public void niceMethod(){
    int a = obliczPole(7, 12); //poprawny
    short c = 7;
//    int d = obliczPole(57);    //nie poprawne, ponieważ jest przekazywany tylko jeden argument do funkcji
    obliczPole(2, 3);  //poprawny
    long t = 42;
//    int f = obliczPole(t, 17);  //nie jest poprawny, ponieważ jest przekazywany jako argument typ long (powinien byc int)
//    int g = obliczPole();   //nie jest poprawny, ponieważ w argumentach przekazywanych do funkcji nie są podane żadne wartości
//    obliczPole();  //nie poprawny jak wyzej
//    byte h = obliczPole(4,20);  //nie jest poprawny, ponieważ funkcja zwraca typ int. Nie można zmiennej lokalnej typu byte przypisać innego typu
//    int j = obliczPole(2, 3, 5);   //nie poprawnie, jest podane zbyt dużo parametrów
  }
}

class Xcopy {
  public static void main(String[] args){
    int org = 42;
    Xcopy x = new Xcopy();
    int y = x.jazda(org);
    System.out.println(org + " " + y); //wypisze w konsoli: 42 84
  }

  private int jazda(int arg) {
    arg = arg * 2;
    return arg;
  }
}

class Zegar {
  String czas;


  void setCzas(String c){
    czas = c;
  }

  String getCzas(){
    return czas;
  }
}

class ZegarTester{
  public static void main(String[] args){
    Zegar z = new Zegar();
    z.setCzas("12345");
    String dta = z.getCzas();
    System.out.println("Czas: " + dta);
  }
}

class Mix4{
  int licznik = 0;

  public static void main(String[] args){
    int ilosc = 0;
    Mix4[] m4a = new Mix4[20];
    int x = 0;

    while(x<19){
      m4a[x] = new Mix4();
      m4a[x].licznik = m4a[x].licznik + 1;
      ilosc = ilosc + 1;
      ilosc = ilosc + m4a[x].mozeNowa(x);
      x = x + 1;
    }
    System.out.print(ilosc + " " + m4a[1].licznik);
  }

  private int mozeNowa(int indeks) {
    if(indeks < 1){
      Mix4 m4 = new Mix4();
      m4.licznik = m4.licznik + 1;
      return 1;
    }
    return 0;
  }

}
