package pl.etroya.corejava.certification.oca.methodsInInterfaces.ex2;

public class RestictedChecking implements Accessible {
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
        //        Verify that the withdraw is under the deposit limit
        return 0;
    }
}
