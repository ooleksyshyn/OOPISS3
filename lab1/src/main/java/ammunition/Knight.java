package main.java.ammunition;

import java.util.ArrayList;
import java.util.List;

public class Knight {
    private final String name;

    private ArrayList<AmmunitionItem> equippedItems;
    private double maxWeight;
    private double equippedWeight;

    public Knight(String name, double maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.equippedWeight = 0;
        this.equippedItems = new ArrayList<>();
    }

    public List<AmmunitionItem> getEquippedItems() {
        return this.equippedItems;
    }

    public void equipItem(AmmunitionItem newItem) throws TooManyItemsException, ItemEquipException {
        if (this.equippedWeight + newItem.getWeight() > this.maxWeight) {
            throw new TooManyItemsException("A knight can`t equip more than" + this.maxWeight + "kg of equipment");
        }

        newItem.equip(); // may throw exception
        this.equippedWeight += newItem.getWeight();
        equippedItems.add(newItem);
    }

    public double equippedWeight() {
        return this.equippedWeight;
    }

    public String getName() {
        return this.name;
    }
}
