package main.java.ammunition;

public class Armor extends AmmunitionItem {
    private final String material;

    public Armor(String material, double weight, double cost) {
        super(weight, cost);
        this.material = material;
    }

    public String getMaterial() {
        return this.material;
    }

    @Override
    public boolean equals(Object other){
        if (other == this) {
            return true;
        }
        if (!(other instanceof Armor)) {
            return false;
        }

        var casted = (Armor) other;
        return casted.getCost() == this.getCost() &&
                casted.getWeight() == this.getWeight() &&
                casted.getMaterial() == this.getMaterial();
    }

    @Override
    public String itemName() {
        return "armor made of " + this.material;
    }
}
