package pl.etroya.corejava.inheritance;

public class Flight {
    int passengers = 150;
    private int seats = 150;

    int getPassengers(){
        return 150;
    }

    int getSeats(){
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
}
