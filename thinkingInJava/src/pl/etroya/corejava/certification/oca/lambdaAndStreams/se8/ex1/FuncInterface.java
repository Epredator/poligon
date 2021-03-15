package pl.etroya.corejava.certification.oca.lambdaAndStreams.se8.ex1;

//Lambda expressions
//src: https://learn.oracle.com/ols/course/prepare-for-java-se-certification/82508/79482/124478

public interface FuncInterface {
    int sum =0;
    static final int divisor =3;
    double quotient();
    default void product(){
        System.out.println(3);
    }

    abstract int remainder(int x, int y);
    static int result(){
        return 0;
    }

//    private int count(){
//        return sum++; //incrementation not work because is declared as a static final in the FuncInterface
//    }
}
