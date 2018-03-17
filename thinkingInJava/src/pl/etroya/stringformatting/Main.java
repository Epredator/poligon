package pl.etroya.stringformatting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;

//java formatter class: https://docs.oracle.com/javase/9/docs/api/java/util/Formatter.html (2018.03.04)
public class Main {
    public static void main(String[] args) throws IOException {
        int david = 13;
        int dawson = 11;
        int gordon = 2;

        double avgDiff = (david - dawson) / 3.0d;

        String s1 = "My nephews are " + david + ", " + dawson + ", " + " and " + gordon + " years old";
        String s2 = String.format("My nephews are %d, %d and %d  years old", david, dawson, gordon);

        String s4 = String.format("The average age between each is %.1f years", avgDiff);

        String s5 = String.format("%d", 32); //out 32
        String s6 = String.format("%o", 32); //out octal value 40
        String s7 = String.format("%x", 32); //out hex 20
        String s8 = String.format("%#o", 32); //out 040 (include zero-padding)
        String s9 = String.format("%#x", 32); //out 0x20 (include radix)
        String s10 = String.format("%#X", 32); //out 0X20
        String printParamsWith4Spaces = String.format("W:%4d X:%4d", 6, 212);
        String printParamsWith4SpacesAndZeros = String.format("W:%04d X:%04d", 6, 212);

        String printParamsWith4SpacesToLeftSide = String.format("W:%-4d X:%-4d", 6, 212);

        //argument index
        String s11 = String.format("%d %d %d", 100, 200, 300);
        String s11 = String.format("%$3d %$1d %$2d", 100, 200, 300); //out 300 100 200

        System.out.println(s4);

        //writing formatted content to a stream

        void doWrite(int a, int b, int c, int d, double p_avgDiff){
            BufferedWriter writer =  Files.newBufferedWriter(Paths.get("file.txt"));

            try(Formatter f = new Formatter(writer)){
                f.format("My nephews are %d, %d, %d and %d years old", a, b, c, d);
                f.format("The average age between each is ", a, b, c, d);
            }
        }



        //regular expression
        String r1 = "apple, apple and orange please";
        String r2 = s1.replaceAll("ple\\b", "ricot");

    }
}
