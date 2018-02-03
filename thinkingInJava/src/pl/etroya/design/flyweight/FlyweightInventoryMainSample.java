package pl.etroya.design.flyweight;

public class FlyweightInventoryMainSample {
    public static void main(String[] args){
        InventorySystem ims  = new InventorySystem();

        ims.takeOrder("Tvp1", 1);
        ims.takeOrder("Tvp1", 8);
        ims.takeOrder("Tvp2", 2);
        ims.takeOrder("Tv Trwam", 3);
        ims.takeOrder("Tv Trwam", 7);
        ims.takeOrder("Tv Trwam", 6);

        ims.process();
        System.out.println(ims.report());

    }


}
