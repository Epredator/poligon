public class Stream {
    public static void main(String[] args) {
        IntStream is = IntStream.of(1, 3, 5);
        int x = is.filter(i->i%2 == 0).average(); //1
        System.out.println();
    }
}
