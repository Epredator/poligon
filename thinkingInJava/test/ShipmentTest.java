import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import pl.etroya.corejava.collection.shipment.Shipment;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static pl.etroya.corejava.collection.shipment.product.ProductFixtures.*;

public class ShipmentTest {
    private Shipment shipment = new Shipment();

    @Test
    public void shouldAddItems() throws Exception{
        shipment.add(door);
        shipment.add(window);
        //assertThat(shipment, );

        //assertThat(shipment, contains(door, window)); //TODO check why containt not work
    }

    @Test
    public void shouldReplaceItems() throws Exception{
        shipment.add(door);
        shipment.add(window);

        shipment.replace(door, floorPanel);

        //assertThat(shipment, contains(door, window));  TODO check why containt not work
    }
}
