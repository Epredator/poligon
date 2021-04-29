package designPatterns;

/**
 * Created by Adam on 2015-07-20.
 *
 * source: http://www.algorytm.org/wzorce-projektowe/singleton-singleton/singleton-j.html
 */
public class SingeltonSample {
    public static void main(String[] args){
        MenadzerPlikow[] tab = new MenadzerPlikow[3];
        tab[0] = MenadzerPlikow.dawajMenadzer();
        tab[1] = MenadzerPlikow.dawajMenadzer();
        tab[2] = MenadzerPlikow.dawajMenadzer();

        for(MenadzerPlikow object : tab)
            object.about();
    }
}

class MenadzerPlikow {
    private static MenadzerPlikow instancja = null;
    private int identyfikator;

    private MenadzerPlikow(){
        identyfikator = (int) (Math.random()*10%10);
    }

    public static synchronized MenadzerPlikow dawajMenadzer(){
        if(instancja == null){
            instancja = new MenadzerPlikow();
            return instancja;
        }
        System.out.println("Menadzer juz sotal wczesniej utworzony");
        return instancja;
    }

    public void about(){
        System.out.println("Identyfikator = " + identyfikator);
    }


}


