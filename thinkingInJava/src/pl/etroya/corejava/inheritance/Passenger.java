package pl.etroya.corejava.inheritance;

public final class Passenger implements Comparable {
    private int memberLevel; //3 -platinum, 2-gold, 3 -silver types of member
    private int memberDays;


    public Passenger(int i, int i1) {

    }

    public Passenger() {

    }

    @Override
    public int compareTo(Object o) {
        Passenger p = (Passenger) o;
        if (memberLevel > p.memberLevel) {
            return -1;
        } else if (memberLevel < p.memberLevel) {
            return 1;
        } else {
            if (memberDays > p.memberDays) {
                return -1;
            } else if (memberDays < p.memberDays) {
                return 1;
            } else return 0;
        }
    }

    public int setLevelAndDays(int i, int i1) {
        return 0;
    }
}
