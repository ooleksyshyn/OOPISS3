package test.java.ammunition;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.ammunition.*;
import java.util.List;
import java.util.ArrayList;

public class AmmunitionFinderTest {
    @Test
    public void testFinder() {
        double start = 18.524;
        double end = 101.728;

        List<AmmunitionItem> list = new ArrayList<>();

        Armor armor1 = new Armor("cncnf", 0, 15);
        Armor armor2 = new Armor("cncnf", 0, 30);
        Sword sword1 = new Sword("fndl", 30, 100);
        Sword sword2 = new Sword("cnfskl", 64273, 190);

        list.add(armor1);
        list.add(armor2);
        list.add(sword1);
        list.add(sword2);

        List<AmmunitionItem> chosen = AmmunitionFinder.find(list, start, end);
        assertEquals(chosen.size(), 2);
        assertEquals(chosen.get(0), armor2);
        assertEquals(chosen.get(1), sword1);

        assertEquals(AmmunitionFinder.find(list, 0, 10).size(), 0);

        Helmet helmet = new Helmet(45, 50);
        list.add(helmet);
        start = 40;
        end  = 200;
        chosen = AmmunitionFinder.find(list, 40, 200);
        assertTrue(chosen.contains(helmet));
        double finalStart = start;
        double finalEnd = end;
        assertFalse(chosen.stream().anyMatch(item -> item.getCost() < finalStart || item.getCost() > finalEnd));
    }
}
