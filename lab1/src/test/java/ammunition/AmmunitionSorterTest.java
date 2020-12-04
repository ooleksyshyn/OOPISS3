package test.java.ammunition;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.ammunition.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AmmunitionSorterTest {
    @Test
    public void testFinder() {
        Helmet helmet = new Helmet(120.2389, 38129);
        Armor armor1 = new Armor("jdsvk", 738, 15);
        Armor armor2 = new Armor("vsf;", 920, 30);
        Sword sword1 = new Sword("fmpre", 93.14, 100);
        Sword sword2 = new Sword("gmvps", 8423189.3819, 190);

        var toSort = new AmmunitionItem[] {
                helmet,
                armor1,
                armor2,
                sword1,
                sword2
        };

        var expected = new AmmunitionItem[] {
                sword1,
                helmet,
                armor1,
                armor2,
                sword2
        };

        var sorted = AmmunitionSorter.sortByWeight(Arrays.asList(toSort));
        assertEquals(sorted, Arrays.asList(expected));
    }
}
