package test.java.ammunition;

import static org.junit.Assert.*;
import main.java.ammunition.*;
import org.junit.Test;

public class SwordTest {
    @Test
    public void test() {
        final String name = "black widow";
        final double weight = 100.5;
        final double cost = 12.32;
        Sword sword = new Sword(name, weight, cost);
        final String itemName = "sword " + name;

        final double delta = 0.0001;
        assertEquals(sword.getWeight(), weight, delta);
        assertEquals(sword.getCost(), cost, delta);
        assertEquals(sword.getName(), name);
        assertFalse(sword.isEquipped());
        assertEquals(sword.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", not equipped");
        assertEquals(sword.itemName(), itemName);

        try {
            sword.equip();
        } catch (ItemEquipException ex) {
            fail("exception should not be thrown here");
        }

        assertEquals(sword.toString(),
                itemName + ": weight " + weight + ", cost " + cost + ", equipped");
    }


    @Test
    public void testEquals() {
        final double weight1 = 1.05;
        final double weight2 = 2.34;
        final double cost1 = 124.423;
        final double cost2 = 4315.4231;
        final String name1 = "escalibur";
        final String name2 = "gragon fire";

        Sword s1 = new Sword(name1, weight1, cost1);
        Sword s2 = new Sword(name1, weight1, cost1);
        AmmunitionItem item1 = new AmmunitionItem(weight1, cost1);

        assertEquals(s1, s1);
        assertEquals(s1, s2);
        assertNotEquals(s1, weight1);
        assertNotEquals(s1, name1);
        assertNotEquals(item1, s1);

        AmmunitionItem s3 = new Sword(name1, weight1, cost1);
        assertEquals(s3, s1);

        AmmunitionItem s4 = new Sword(name1, weight1, cost2);
        Sword s5 = new Sword(name2, weight1, cost2);

        assertNotEquals(s1, s4);
        assertNotEquals(s1, s5);
    }
}
