package pl.etroya.corejava.certification.oca.methodsInInterfaces.se8.ex2;

//Example: Implementing abstract methods
// src: https://learn.oracle.com/ols/course/prepare-for-java-se-certification/82508/79482/124475
//In Java 8 we can use default methods

public interface Accessible {
    public static final double FEE_OVERDRAFT = 25;

    public default double verifyDeposit(double amount, int pin) {
        //        verfy pin
        //        verfy amount is greater than 0
        return 0;

    }

    public default double verifyWithDraw(double amount, int pin) {
        //        verfy pin
        //        verfy amount is greater than 0
        //        veryfiy account balance will not go negative
        return 0;

    }
}
