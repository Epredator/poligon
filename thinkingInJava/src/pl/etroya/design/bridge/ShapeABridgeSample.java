package pl.etroya.design.bridge;
public class ShapeABridgeSample {
    public static void main(String args[]){
        Circle circle = new RedCircle();
        Square square = new BlueSquare();

        circle.applyColor();
        square.applyColor();
    }
}
