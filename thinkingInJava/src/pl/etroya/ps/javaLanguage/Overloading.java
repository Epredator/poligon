package pl.etroya.ps.javaLanguage;

public class Overloading {
    int seats = 150, passengers;
    int totalCheckedBags;
    int maxCarryOns = seats * 2, totalCarryOns;

    public void add1Passenger() {
        if (hasSeating())
            passengers += 1;
        else
            handleTooMany();
    }

    public void add1Passenger(int bags) {
        if (hasSeating()) {
            add1Passenger();
            totalCheckedBags += bags;
        }
    }

    public void add1Passenger(int bags, int carryOns) {
        if (hasSeating()) {
            add1Passenger(bags);
            totalCarryOns += carryOns;
        }
    }

    public void add1Passenger(Passenger p, int carryOns) {
        if (hasSeating()) {
            add1Passenger(p.getCheckedBags(), carryOns);
            totalCarryOns += carryOns;
        }
    }

    public void add1Passenger(Passenger p) {
        add1Passenger(p.getCheckedBags());
    }

    private boolean hasSeating() {
        return passengers < seats;
    }


    private void handleTooMany() {

    }

    private boolean hasCarryOnSpace(int carryOns) {
        return totalCarryOns + carryOns <= maxCarryOns;
    }

    private class Passenger {
        public int getCheckedBags() {
            return 0;
        }
    }
}
