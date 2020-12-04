package main.java.ammunition;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AmmunitionSorter {
    public static List<AmmunitionItem> sortByWeight (List<AmmunitionItem> items) {
        return items.stream()
                        .sorted(Comparator.comparing(AmmunitionItem::getWeight))
                        .collect(Collectors.toList());
    }
}
