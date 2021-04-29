package functional.staticMethods;

import java.util.function.Predicate;

public class PredicateJoining {
    public static void main(String[] args) {
        int[]x = {0, 5, 10, 15, 20, 25, 30 };
        Predicate<Integer> p1 = i -> i>10;
        Predicate<Integer> p2 = i -> i%2==0;
        System.out.println("Numbers greater than 10: ");
        check(p1, x);

        System.out.println("The even numbers: ");
        check(p2, x);

        System.out.println("Numbers NOT greater than 10: ");
        check(p1.negate(), x);

        System.out.println("Numbers greater than 10 and even numbers: ");
        check(p1.and(p2), x);

        System.out.println("Numbers greater than 10 or even numbers: ");
        check(p1.or(p2), x);
    }

    private static void check(Predicate<Integer> p1, int[] x) {
        for (int i: x) {
            System.out.println("Predicate test for " + i + " is: " + p1.test(i));

        }
    }
}
