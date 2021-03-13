package pl.etroya.corejava.certification.oca.methodsInInterfaces.se8.ex1;

//Preparation for Java exam
//src: https://learn.oracle.com/ols/course/prepare-for-java-se-certification/82508/79482/124477

public abstract class testClass {
    double taxRate = 0.05;
    public void increaseTax(){
        taxRate = taxRate + 0.01;
    }

    public interface testClass2{
        static double taxRate = 0.05;
        abstract void increaseTax();
    }
}
