package test.java.ammunition;

import static org.junit.Assert.*;
import main.java.ammunition.*;
import org.junit.Test;

public class HelmetTest {
    @Test
    public void test() {
        final double weight = 100.5;
        final double cost = 12.32;
        Helmet helmet = new Helmet(weight, cost);
        final String itemName = "helmet";

        final double delta = 0.0001;
        assertEquals(helmet.getWeight(), weight, delta);
        assertEquals(helmet.getCost(), cost, delta);
        assertFalse(helmet.isEquipped());
        assertEquals(helmet.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", not equipped");
        assertEquals(helmet.itemName(), itemName);

        try {
            helmet.equip();
        } catch (ItemEquipException ex) {
            fail("exception should not be thrown here");
        }

        assertEquals(helmet.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", equipped");
    }


    @Test
    public void testEquals() {
        final double weight1 = 1.05;
        final double weight2 = 2.34;
        final double cost1 = 124.423;
        final double cost2 = 4315.4231;

        Helmet h1 = new Helmet(weight1, cost1);
        Helmet h2 = new Helmet(weight1, cost1);
        AmmunitionItem item1 = new AmmunitionItem(weight1, cost1);

        assertEquals(h1, h1);
        assertEquals(h1, h2);
        assertNotEquals(h1, weight1);
        assertNotEquals(item1, h1);

        AmmunitionItem h3 = new Helmet(weight1, cost1);
        assertEquals(h3, h1);

        AmmunitionItem h4 = new Helmet(weight1, cost2);
        Helmet h5 = new Helmet(weight1, cost2);

        assertNotEquals(h1, h4);
        assertNotEquals(h1, h5);
    }
}
