package main.java.ammunition;

public class Helmet extends AmmunitionItem {
    public Helmet(double weight, double cost) {
        super(weight, cost);
    }

    @Override
    public boolean equals(Object other){
        if (other == this) {
            return true;
        }
        if (!(other instanceof Helmet)) {
            return false;
        }

        var casted = (Helmet) other;
        return casted.getCost() == this.getCost() &&
                casted.getWeight() == this.getWeight();
    }

    @Override
    public String itemName() {
        return "helmet";
    }
}
