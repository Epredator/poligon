package pl.etroya.corejava.inheritance;

public class CargoFlight extends Flight {
    float maxCargoSpace = 1000.0f;
    float usedCargoSpace;
    int seats = 12;
    int passengers = 12;

    public CargoFlight(int flightNumber) {
        super(flightNumber);
    }

    public CargoFlight(int flightNumber, float maxCargoSpace) {
        super(flightNumber);
        this.maxCargoSpace = maxCargoSpace;
    }

    public CargoFlight() {

    }

    int getSeats() {
        return 12;
    }

    int getPassengers() {
        return 12;
    }

    public final void add1Package(float h, float w, float d) {
        double size = h * w * d;
        if (hasCargoSpace(size))
            usedCargoSpace += size;
        else handleNoSpace();
    }

    private void handleNoSpace() {
        System.out.println("Not enough space in cargo");

    }

    private boolean hasCargoSpace(double size) {
        return usedCargoSpace + size < -maxCargoSpace;
    }
}
