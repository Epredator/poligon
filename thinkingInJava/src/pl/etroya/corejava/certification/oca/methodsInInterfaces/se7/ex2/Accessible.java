package pl.etroya.corejava.certification.oca.methodsInInterfaces.se7.ex2;

//Example: Implementing abstract methods
// src: https://learn.oracle.com/ols/course/prepare-for-java-se-certification/82508/79482/124475

public interface Accessible {
    public static final double FEE_OVERDRAFT = 25;

    public abstract double verifyDeposit(double amount, int pin);
    public abstract double verifyWithDraw(double amount, int pin);
}
