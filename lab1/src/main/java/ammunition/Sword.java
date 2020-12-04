package main.java.ammunition;

public class Sword extends AmmunitionItem {

    private final String name;

    public Sword(String name, double weight, double cost) {
        super(weight, cost);
        this.name = name;
    }

    @Override
    public String itemName() {
        return "sword " + name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Sword)) {
            return false;
        }

        var casted = (Sword) other;
        return casted.getCost() == this.getCost() &&
                casted.getWeight() == this.getWeight() &&
                casted.getName() == this.getName();
    }
}
