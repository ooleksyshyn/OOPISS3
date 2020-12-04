package test.java.ammunition;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import main.java.ammunition.*;

public class KnightTest {
    @Test
    public void testCreation() {
        final String name = "Sir jorah Mormont";
        final double maxWeight = 150.150;
        Knight knight = new Knight(name, maxWeight);

        final double delta = 0.0001;
        assertEquals(knight.getName(), name);
        assertEquals(knight.equippedWeight(), 0, delta);
        assertTrue(knight.getEquippedItems().isEmpty());

        Armor armor = new Armor("bronze", 100, 15);

        try {
            knight.equipItem(armor);
        } catch (Exception ex) {
            fail("this item is not equipped yet");
        }

        assertEquals(knight.getEquippedItems().get(0), armor);
        assertEquals(knight.equippedWeight(), armor.getWeight(), delta);
        assertTrue(armor.isEquipped());

        try {
            knight.equipItem(armor);
            fail("exception should be thrown here");
        } catch (TooManyItemsException ex) {
            assertTrue(armor.isEquipped());
        } catch (ItemEquipException ex) {
            fail("this exception is thrown later");
        }

        Sword sword = new Sword("burning ice", 50, 123);

        try {
            knight.equipItem(sword);
        } catch (Exception ex) {
            fail("item must be equipped");
        }

        assertTrue(sword.isEquipped());
        assertEquals(knight.getEquippedItems().get(0), armor);
        assertEquals(knight.getEquippedItems().get(1), sword);
        assertEquals(knight.getEquippedItems().size(), 2);
        assertEquals(knight.equippedWeight(), armor.getWeight() + sword.getWeight(), delta);

        Helmet helmet = new Helmet(10, 10);

        try {
            knight.equipItem(helmet);
            fail("item should not be equipped");
        } catch (TooManyItemsException ex) {
            assertFalse(helmet.isEquipped());
        } catch (ItemEquipException ex) {
            fail("item is not equipped yet");
        }

        assertEquals(knight.getEquippedItems().get(0), armor);
        assertEquals(knight.getEquippedItems().get(1), sword);
        assertEquals(knight.getEquippedItems().size(), 2);
        assertEquals(knight.equippedWeight(), armor.getWeight() + sword.getWeight(), delta);
    }
}
