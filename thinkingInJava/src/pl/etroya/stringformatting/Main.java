package pl.etroya.stringformatting;

public class Main {
    public static void main(String[] args){
        int david = 13;
        int dawson = 11;
        int gordon = 2;

        double avgDiff = (david - dawson) / 3.0d;

        String s1 = "My nephews are " + david + ", " + dawson + ", " + " and " + gordon + " years old";
        String s2 = String.format("My nephews are %d, %d and %d  years old", david, dawson, gordon);

        String s4 = String.format("The average age between each is %.1f years", avgDiff);

        System.out.println(s4);

    }
}
