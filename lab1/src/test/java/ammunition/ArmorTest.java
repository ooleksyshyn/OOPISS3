package test.java.ammunition;

import static org.junit.Assert.*;
import main.java.ammunition.*;
import org.junit.Test;

public class ArmorTest {
    @Test
    public void test() {
        final String material1 = "diamond";
        final double weight = 100.5;
        final double cost = 12.32;
        Armor diamondArmor = new Armor(material1, weight, cost);
        final String itemName = "armor made of " + material1;

        final double delta = 0.0001;
        assertEquals(diamondArmor.getWeight(), weight, delta);
        assertEquals(diamondArmor.getCost(), cost, delta);
        assertEquals(diamondArmor.getMaterial(), material1);
        assertFalse(diamondArmor.isEquipped());
        assertEquals(diamondArmor.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", not equipped");
        assertEquals(diamondArmor.itemName(), itemName);

        try {
            diamondArmor.equip();
        } catch (ItemEquipException ex) {
            fail("exception should not be thrown here");
        }

        assertEquals(diamondArmor.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", equipped");
    }


    @Test
    public void testEquals() {
        final double weight1 = 1.05;
        final double weight2 = 2.34;
        final double cost1 = 124.423;
        final double cost2 = 4315.4231;
        final String material1 = "diamond";
        final String material2 = "leather";

        Armor a1 = new Armor(material1, weight1, cost1);
        Armor a2 = new Armor(material1, weight1, cost1);
        AmmunitionItem item1 = new AmmunitionItem(weight1, cost1);

        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertNotEquals(a1, weight1);
        assertNotEquals(a1, material1);
        assertNotEquals(item1, a1);

        AmmunitionItem a3 = new Armor(material1, weight1, cost1);
        assertEquals(a3, a1);

        AmmunitionItem a4 = new Armor(material1, weight1, cost2);
        Armor a5 = new Armor(material1, weight1, cost2);

        assertNotEquals(a1, a4);
        assertNotEquals(a1, a5);
    }
}
