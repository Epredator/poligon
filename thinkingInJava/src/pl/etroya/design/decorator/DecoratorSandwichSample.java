package pl.etroya.design.decorator;

public class DecoratorSandwichSample {
    public static void main(String args[]){
        Sandwich sandwich = new DressingDecorator(new MeatDecorator(new SampleSandwich()));
    System.out.println(sandwich.make());
    }
}
