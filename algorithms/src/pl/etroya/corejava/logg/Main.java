package pl.etroya.corejava.logg;

import java.util.logging.*;

public class Main {
    static Logger logger = Logger.getLogger("pl.etroya.corejava.logg");

    public static void main(String... args) {
        Handler h = new ConsoleHandler();
        h.setLevel(Level.ALL);
        h.setFormatter(new SimpleFormatter());
        logger.addHandler(h);
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "We're logging!");
    }
}
