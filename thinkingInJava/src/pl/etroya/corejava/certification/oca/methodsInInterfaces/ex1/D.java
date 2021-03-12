package pl.etroya.corejava.certification.oca.methodsInInterfaces.ex1;

public class D implements A, B{
    @Override
    public void m() {
        int fromA = A.X;
        int fromB = B.X;
    }
}
