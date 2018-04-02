package pl.etroya.corejava.inheritance;

public class Main {
    public static void Main(String [] args){
        CargoFlight cf = new CargoFlight();
        cf.add1Package(1.0f, 2.3f, 3.0f);
        Passenger piotr = new Passenger(0,2);
        cf.add1Pasenger(piotr);


        //how to insert in Array different flight types
        Flight[] squadron = new Flight[4];
        squadron[0] = new Flight();
        squadron[1] = new CargoFlight();
        squadron[2] = new Flight();
        squadron[3] = new CargoFlight();

        //how many passangers return initialized variables?
        Flight f1 = new Flight();
        System.out.println(f1.passengers); //return 150

        CargoFlight cf1 = new CargoFlight();
        System.out.println(cf1.passengers); //return 12


        Flight cf2 = new CargoFlight(); //because reference is to Flight
        System.out.println(cf2.passengers); //return 150


        //but with accesing to getter. The return value is diffenert
        Flight f2 = new Flight();
        System.out.println(f2.getPassengers()); //return 150

        CargoFlight cf3 = new CargoFlight();
        System.out.println(cf3.getPassengers()); //return 12


        Flight cf4 = new CargoFlight(); //because reference is to Flight
        System.out.println(cf4.getPassengers()); //return 12

        //create different objects to test how to work constuctor
        Flight f175 = new Flight(175);
        CargoFlight cf007 = new CargoFlight();
        CargoFlight cf294 = new CargoFlight(294);
        CargoFlight cf878 = new CargoFlight(878, 2000.0f);


    }
}
