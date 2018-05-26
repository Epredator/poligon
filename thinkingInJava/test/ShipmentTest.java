
import org.junit.Assert;
import org.junit.Test;
import pl.etroya.corejava.collection.shipment.Shipment;

import static pl.etroya.corejava.collection.shipment.product.ProductFixtures.*;

//import static org.hamcrest.Matchers.contains;

public class ShipmentTest {
    private Shipment shipment = new Shipment();

    @Test
    public void shouldAddItems() throws Exception {
        shipment.add(door);
        shipment.add(window);
        //assertThat(shipment, );

        //assertThat(shipment, contains(door, window)); //TODO check why contains not work
    }

    @Test
    public void shouldReplaceItems() throws Exception {
        shipment.add(door);
        shipment.add(window);

        shipment.replace(door, floorPanel);

        //MatcherAssert.assertThat(shipment, contains(door, window));  //TODO check why contains not work
    }

    @Test
    public void shouldNotReplaceMissingItems() throws Exception {
        shipment.add(window);

        shipment.replace(door, floorPanel);

        //MatcherAssert.assertThat(shipment, contains(door, window));  //TODO check why contains not work
    }

    @Test
    public void shouldIdentifyVanRequirements(){
        shipment.add(door);
        shipment.add(window);
        shipment.add(floorPanel);

        shipment.prepare();
//        Assert.assertThat(shipment.getLightVanProducts(), contains(window));
//        Assert.assertThat(shipment.getLightVanProducts(), contains(floorPanel, door));
    }
}
