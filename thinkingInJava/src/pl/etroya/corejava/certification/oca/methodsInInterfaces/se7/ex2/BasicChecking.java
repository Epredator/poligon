package pl.etroya.corejava.certification.oca.methodsInInterfaces.se7.ex2;

public class BasicChecking implements Accessible {
    @Override
    public double verifyDeposit(double amount, int pin) {
        //        verfy pin
        //        verfy amount is greater than 0
        return 0;
    }

    @Override
    public double verifyWithDraw(double amount, int pin) {
        //        verfy pin
        //        verfy amount is greater than 0
        //        veryfiy account balance will not go negative
        return 0;
    }
}
