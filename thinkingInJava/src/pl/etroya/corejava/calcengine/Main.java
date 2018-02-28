package pl.etroya.corejava.calcengine;

import pl.etroya.calcengine.MathEquation;

public class Main {
    public static void main(String[] args){
        MathEquation testEquation = new MathEquation();
        testEquation.execute();
        System.out.println(testEquation.getResult());





        MathEquation[] equations = new MathEquation[4];
        equations[0] = create(100.0d, 50.0d, 'd');
        equations[0] = create(25.0d, 92.0d, 'a');
        equations[0] = create(225.0d, 17.0d, 's');
        equations[0] = create(11.0d, 3.0d, 'm');

        for(MathEquation equation : equations){
            equation.execute();
            System.out.print("result = ");
            System.out.println(equation.getResult());

        }
    }

    public static MathEquation create(double leftVal, double rightVal, char opCode){
        MathEquation equation = new MathEquation();
        equation.setLeftVal(leftVal);
        return equation;
    }
}
