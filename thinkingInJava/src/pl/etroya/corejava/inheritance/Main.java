package pl.etroya.corejava.inheritance;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CargoFlight cf = new CargoFlight();
        cf.add1Package(1.0f, 2.3f, 3.0f);
        Passenger piotr = new Passenger(0, 2);
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


        //String representation
        System.out.println("My flight is: " + cf878);

        //Exercise with StringBuilder
        StringBuilder sb = new StringBuilder(30);
        Flight nameOfFlight = new Flight(555);
        String location = "Gdansk";

        sb.append("I flew to ");
        sb.append(location);
        sb.append(" on " + nameOfFlight);

        //find position of text to write in
        int time = 8;
        int pos = sb.length() - " on ".length() - nameOfFlight.toString().length();

        sb.insert(pos, " at ");
        sb.insert(pos + 4, time);

        String msg = sb.toString();
        System.out.println(msg);


        CrewMember jan = new CrewMember();
        CrewMember marta = new CrewMember();


        //1 excercice with implementing an Interface
        Passenger kamil = new Passenger();
        kamil.setLevelAndDays(1, 180);

        Passenger katarzyna = new Passenger();
        kamil.setLevelAndDays(1, 90);

        Passenger karol = new Passenger();
        kamil.setLevelAndDays(2, 180);

        Passenger krystyna = new Passenger();
        kamil.setLevelAndDays(3, 765);

        Passenger[] passengers = {kamil, katarzyna, karol, krystyna};
        Arrays.sort(passengers);

        //2 excercice with implementing an Interface
        Flight flx021 = new Flight();
        flx021.setFlightTime(32);
        Flight flx0211 = new Flight();
        flx0211.setFlightTime(60);
        Flight flx02211 = new Flight();
        flx02211.setFlightTime(32);
        Flight[] flights = {flx021, flx0211, flx02211};

        Arrays.sort(flights);

        Flight kax011 = new Flight(11);
        Passenger[] passengersKax011 = {kamil, katarzyna, karol, krystyna};
        CrewMember[] crewKax011 = {jan, marta};
        //kax011.addPasengers(passengersKax011); TODO analyse & repair
        //kax011.addCrewMembers(crewKax011); TODO analyse & repair


//        for(Person p : kax011){
//            System.out.println(p.getName());
//        }

        Flight.resetAllPassengers();
        System.out.println(Flight.getAllPassengers());
        Flight lx032 = new Flight();
        Passenger p = new Passenger();
        p.setName("Lola");
        lx032.add1Pasenger(p);
        System.out.println(Flight.getAllPassengers());



    }
}
