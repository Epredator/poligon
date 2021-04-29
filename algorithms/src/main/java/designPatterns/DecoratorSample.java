package designPatterns;

/**
 * Created by Adam on 2015-07-20.
 * zrobione na podstawie: http://www.algorytm.org/wzorce-projektowe/dekorator-decorator/decorator-j.html
 *
 * Dekorator jest wzorcem strukturalnym,
 * który s³u¿y do rozbudowywania obiektów.
 * Je¿eli chcesz do istniej¹cej klasy dodaæ metodê dziêki dekoratorowi nie trzeba modyfikowaæ tej klasy
 * ale mo¿na nadaæ obiektowi nowe zachowanie podczas dzia³ania programu.

 Do dekoratora przekazujemy dekorowany obiekt dlatego dekorator musi posiadaæ identyczny interfejs jak obiekt,
 który ma byæ dekorowany.
 Inaczej jak w przypadku dziedziczenia obiekty dostaj¹ nowe funkcjonalnoœci dynamicznie
 czyli w trakcie dzia³ania programu a nie na etapie kompilacji.

 source: http://www.algorytm.org/wzorce-projektowe
 */
public class DecoratorSample {
    public static void main(String[] args){
        Samochod s1 = new Mercedes();
        Samochod s2 = new Fiat();

        System.out.println("\n Bez wyposazenia: ");
        System.out.println(s1.about() + " " + s1.cena());
        System.out.println(s2.about() + " " + s2.cena());


        //po dodaniu klimatyzacji
        s1 = new Klimatyzacja(s1);
        s2 = new Klimatyzacja(s2);

        System.out.println("\n z klimatyzacja: ");
        System.out.println(s1.about() + " " + s1.cena());
        System.out.println(s1.about() + " " + s1.cena());

        //po dodaniu opon zimowych
        s1 = new OponyZimowe(s1);
        s2 = new OponyZimowe(s2);

        System.out.println("\n z klimatyzacja: ");
        System.out.println(s1.about() + " " + s1.cena());
        System.out.println(s1.about() + " " + s1.cena());

        //odrazu tworzymy wyposazony samochod
        System.out.println("\nPelne wyposazenie");
        Samochod s3 = new OponyZimowe(new Klimatyzacja(new Mercedes()));
        System.out.println(s3.about() + " " + s1.cena());

    }
}


//standardowy nieudekorowany samochod
abstract class Samochod {
    protected String samochod = "Samochod podstawowy";

    public String about(){
        return samochod;
    }

    public abstract double cena();
}

//abstrakcyjny dekorator
abstract class Decorator extends Samochod {
    public abstract String about();
}


//poni¿ej 2 przyk³adowe klasy reprezentujace marki samochodow
class Mercedes extends Samochod {

    public Mercedes(){
        samochod = "Mercedes";
    }

    public double cena() {
        return 1000000;
    }
}

class Fiat extends Samochod {

    public Fiat(){
        samochod = "Mercedes";
    }

    public double cena() {
        return 100;
    }
}

//dodatki do samochodow
class Klimatyzacja extends Decorator {
    Samochod samochod;

    public Klimatyzacja(Samochod samochod){
        this.samochod = samochod;
    }

    @Override
    public String about() {
        return samochod.about() + " + klimatyzacja";
    }

    @Override
    public double cena() {
        if (samochod instanceof Mercedes){
            return samochod.cena() + 100000000;
        } else if (samochod instanceof Fiat){
            return samochod.cena() + 20;

        }
        return 0;
    }
}

class OponyZimowe extends Decorator {
    Samochod samochod;

    public OponyZimowe(Samochod samochod){
        this.samochod = samochod;
    }

    @Override
    public String about() {
        return samochod.about() + " + opony zimowe";
    }

    @Override
    public double cena() {
        return samochod.cena() + 322;
    }
}



