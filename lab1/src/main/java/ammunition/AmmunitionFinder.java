package main.java.ammunition;

import java.util.List;
import java.util.stream.Collectors;

public class AmmunitionFinder {
    public static List<AmmunitionItem> find(List<AmmunitionItem> items, double start, double end) {
        return items.stream().filter((item) -> item.getCost() >= start && item.getCost() <= end)
                             .collect(Collectors.toList());
    }
}
