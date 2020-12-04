package test.java.ammunition;

import static org.junit.Assert.*;
import main.java.ammunition.AmmunitionItem;
import main.java.ammunition.ItemEquipException;
import org.junit.Test;

public class AmmunitionItemTest {
    @Test
    public void testItem() {
        final double weight = 100;
        final double cost = 250.23;
        String ammunition = "ammunition";
        AmmunitionItem item = new AmmunitionItem(weight, cost);

        assertEquals(item.getWeight(), weight, 0.001);
        assertEquals(item.getCost(), cost, 0.001);
        assertFalse(item.isEquipped());
        assertEquals(item.itemName(), ammunition);
        assertEquals(item.toString(), ammunition + ": weight " + weight + ", cost " + cost + ", not equipped");

        try {
            item.equip();
        } catch (ItemEquipException ex) {
            fail("exception should not be thrown here");
        }

        assertTrue(item.isEquipped());
        assertEquals(item.toString(), ammunition + ": weight " + weight + ", cost " + cost + ", equipped");
    }

    @Test
    public void testEquipItem() {
        AmmunitionItem item = new AmmunitionItem(10, 20);
        assertFalse(item.isEquipped());
        try {
            item.equip();
        } catch (ItemEquipException ex) {
            fail("exception cannot be thrown here");
        }

        assertTrue(item.isEquipped());

        try {
            item.equip();
            fail("exception should be thrown here, item is already equipped");
        } catch (ItemEquipException ex) {
            assertTrue(item.isEquipped());
        }

        try {
            item.unequip();
        } catch (ItemEquipException ex) {
            fail("exception cannot be thrown here");
        }

        assertFalse(item.isEquipped());

        try {
            item.unequip();
            fail("exception should bt thrown here, item is already equipped");
        } catch (ItemEquipException ex) {
            assertFalse(item.isEquipped());
        }
    }
}
