package pl.etroya.design.bridge;
public class ShapeABridgeSample {
    public static void main(String args[]){
        Circle circle = new BlueCircle();
        Square square = new RedSquare();

        circle.applyColor();
        square.applyColor();
    }
}
