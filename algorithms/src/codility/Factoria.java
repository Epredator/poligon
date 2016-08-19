package codility;

/**
 * User: trojnaradam@gmail.com
 * Date: 19.08.16
 * Time: 15:35
 * napisz funkcję, która jako argument pobiera liczbę n i obilicza n!
 * Dla wywołania factorial(5) powinno zwrócić nam 120.
 * Ile operacji wykonuje nasz program?
 */
public class Factoria {

  public int factoria(int m) throws Exception {
    int result = 1;

    for(int i=1; i<=m; i++){
      result = result*i;
    }
    return result;

  }
}
