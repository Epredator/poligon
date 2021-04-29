package sortAlgo;

/**
 * Created by Adam on 2015-07-13.
 * Insertion sort works by making a single pass through the array and inserting the current value
 * into the already sorted (beginning) portion of the array.
 * After each index is processed, it is known that everything encountered
 * so far is sorted and everything that follows is unknown.
 *
 * Zasada dzia�ania tego algorytmu jest cz�sto por�wnywana
 * do porz�dkowania kart w wachlarz podczas gry.
 * Ka�d� kart� wstawiamy w odpowiednie miejsce,
 * tzn. po m�odszej, ale przed starsz�. Podobnie jest z liczbami.
 * Pierwszy element pozostaje na swoim miejscu. Nast�pnie bierzemy drugi i sprawdzamy,
 * w jakiej relacji jest on z pierwszym.
 * Je�li jest niemniejszy,
 * to zostaje na drugim miejscu,
 * w przeciwnym wypadku w�druje na pierwsze miejsce.
 * Dalej sprawdzamy trzeci element
 * (por�wnujemy go do dw�ch pierwszych i wstawiamy w odpowiednie miejsce),
 * czwarty (por�wnujemy z trzema pierwszymi), pi�ty itd.
 * Idea dzia�ania algorytmu opiera si� na podziale ci�gu na dwie cz�ci:
 * pierwsza jest posortowana, druga jeszcze nie.
 * Wybieramy kolejn� liczb� z drugiej cz�ci i wstawiamy j� do pierwszej.
 * Poniewa� jest ona posortowana, to szukamy dla naszej liczby takiego miejsca,
 * aby liczba na lewo by�a niewi�ksza a liczba na prawo niemniejsza.
 * Wstawienie liczby do posortowanej tablicy wymaga wi�c czasu O(n).
 * Wynika z tego z�o�ono�� algorytmy: O(n^2)
 *
 Oto przyk�ad dla nieposortowanego ci�gu <2, 4, 1, 3>
 * Average Case: O(n^2)
 */
public class insertionSort {
    public int[] sort(int[] items) throws Exception {
        int key, j;
        for(int i=1; i<items.length; i++){
            j=i;
            key = items[i];
//poszukaj miejsca dla aktualnego elementu
//szukaj tylko w posortowanej juz czescie tablicy
//(czyli wsrod elementow o indeksach mniejszych od aktualnego)
//przesuwaj element w kiedunku poczatku tablicy
//tak dlugo, az przed nim jest element wiekszy i
//nie znajduje sie na poczatku tablicy
            while(j>0 && items[j-1]>key){
                items[j] = items[j-1];
                j--;
            }
            items[j] = key;

        }
        return items;
    }

}
