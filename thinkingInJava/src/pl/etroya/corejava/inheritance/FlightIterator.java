package pl.etroya.corejava.inheritance;

import java.util.Iterator;

public class FlightIterator implements Iterator<Person> {
    private Passenger[] roster;
    private CrewMember[] crew;
    private int index = 0;


    public FlightIterator(Passenger[] roster, CrewMember[] crew) {
        this.roster = roster;
        this.crew = crew;
    }

    @Override
    public boolean hasNext() {
        return index < (roster.length + crew.length);
    }

    @Override
    public Person next() {
       Person p = (index < crew.length) ? crew[index] : roster[index - crew.length];
       index++;
       return p;
    }
}
