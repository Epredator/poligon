package pl.etroya.corejava.certification.oca.methodsInInterfaces.se8.ex2;

public class RestictedChecking implements Accessible {
    @Override
    public double verifyDeposit(double amount, int pin) {
        //        verfy pin
        //        verfy amount is greater than 0
        return 0;
    }

    @Override
    public double verifyWithDraw(double amount, int pin) {
//        Call the interfaces implementation
        Accessible.super.verifyWithDraw(amount, pin);
        return 0;
    }
}
