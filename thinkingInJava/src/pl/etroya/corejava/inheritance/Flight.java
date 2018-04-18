package pl.etroya.corejava.inheritance;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Flight implements Comparable<Flight>, Iterable<Person> {
    int passengers = 140;
    private int seats = 150;
    private int flightNumber;
    private char flightClass;
    private int flighTime;
    private Passenger[] roster;
    private CrewMember[] crew;
    static int allPassengers;

    public static int getAllPassengers() {
        return allPassengers;
    }

    public static int resetAllPassengers() {
        return allPassengers = 0;
    }

    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight() {

    }

    int getPassengers() {
        return 150;
    }

    int getSeats() {
        return 150;
    }

    public void add1Pasenger(Passenger p) {
        if (hasSeating()) {
            passengers += 1;
            allPassengers += 1;
        } else handleTooMany();
    }

    public void addPasengers(Passenger[] passengers) {
        if (hasSeating()) {
            Collection<Passenger> listOfPassengers = Arrays.asList(roster);
            listOfPassengers.addAll(Arrays.asList(passengers));
            roster = (Passenger[]) listOfPassengers.toArray();
        } else handleTooMany();
    }

    public void addCrewMembers(CrewMember[] crew) {
        Collection<CrewMember> listOfCrew = Arrays.asList(crew);
        listOfCrew.addAll(Arrays.asList(crew));
        crew = (CrewMember[]) listOfCrew.toArray();
    }


    private void handleTooMany() {
        System.out.println("Not enough free seats in a plane");
    }

    private boolean hasSeating() {
        return passengers < seats;
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) return true;
        if (!(o instanceof Flight)) return false;

        Flight other = (Flight) o;
        return flightClass == other.flightClass && flightNumber == other.flightNumber;

    }

    @Override
    public String toString() {
        if (flightNumber > 0) {
            return "Flight #" + flightNumber;
        } else
            return "Flight class " + flightClass;
    }

    @Override
    public int compareTo(Flight o) {
        Flight f = (Flight) o;
        if (flighTime > f.flighTime) {
            return -1;
        } else if (flighTime < f.flighTime)
            return 1;
        else {
            return 0;
        }
    }

    public void setFlightTime(int i) {
        this.flighTime = i;

    }

    @Override
    public Iterator<Person> iterator() {
        return new FlightIterator(roster, crew);
    }


}
