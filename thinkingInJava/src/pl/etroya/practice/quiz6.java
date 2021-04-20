package pl.etroya.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.IntPredicate;

public class quiz6 {
    public static void main(String[] args) {
        String s = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Type e to exit: ");
            s = in.readLine();
            while (s != null) {
                System.out.println("Output: " + s.trim());
                if (s.equals("e")) {
                    System.exit(0);
                }
                System.out.print("Type e to exit: ");
                s = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

}
