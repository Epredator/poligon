package pl.etroya.corejava.calcengine;

public class MathEquation {
    private double leftVal;
    private double rightVal;
    private char opCode;

    public double getLeftVal() {
        return leftVal;
    }

    public double getRightVal() {
        return rightVal;
    }

    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public char getOpCode() {
        return opCode;
    }

    public void setOpCode(char opCode) {
        this.opCode = opCode;
    }

    public void setResult(double result) {
        this.result = result;
    }

    private double result;



    public void execute() {
        switch (opCode){
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'd':
                result = rightVal != 0.0d ? leftVal  / rightVal : 0.0d;
                break;
            default:
               System.out.println("Error -invalid operation");
               result = 0.0d;
               break;
        }

    }

    public double getResult() {
        return result;
    }

    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }
}
