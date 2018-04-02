package pl.etroya.corejava.inheritance;

public class Flight {
    int passengers = 150;
    private int seats = 150;
    private int flightNumber;
    private char flightClass;

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
        if (hasSeating())
            passengers += 1;
        else handleTooMany();

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
}
