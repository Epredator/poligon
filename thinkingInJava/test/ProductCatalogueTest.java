

import org.junit.Assert;
import org.junit.Test;

import static pl.etroya.corejava.collection.shipment.product.ProductFixtures.door;
import static pl.etroya.corejava.collection.shipment.product.ProductFixtures.floorPanel;

public class ProductCatalogueTest {

    @Test
    public void shouldOnlyHoldUniquesProducts(){
        ProductCatalogue catalogue = new ProductCatalogue();
        catalogue.isSuppliedBy(bobs);
        catalogue.isSuppliedBy(kates);
        Assert.assertThat(catalogue, containsInAnyOrder(door, floorPanel));
    }
}
