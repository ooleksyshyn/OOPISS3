package main.java.ammunition;

public class AmmunitionItem implements EquippableItem {
    
        private final double weight;
        private final double cost;
        private boolean isEquipped;

        public AmmunitionItem(double weight, double cost) {
            this.weight = weight;
            this.cost = cost;
            this.isEquipped = false;
        }

        public double getCost() {
            return this.cost;
        }

        public double getWeight() {
            return this.weight;
        }

        @Override
        public void equip() throws ItemEquipException {
            if (this.isEquipped) {
                throw new ItemEquipException("Cannot equip item: it is already equipped");
            }
            this.isEquipped = true;
        }

        @Override
        public void unequip() throws ItemEquipException {
            if (!this.isEquipped) {
                throw new ItemEquipException("cannot unequip item: it is not equipped");
            }
            this.isEquipped = false;
        }

        @Override
        public boolean isEquipped() {
            return this.isEquipped;
        }

        public String itemName() {
            return "ammunition";
        }

        public String toString() {
            String equippedStr = this.isEquipped ? "equipped" : "not equipped";
            return itemName() + ": weight " + this.weight + ", cost " + this.cost + ", " + equippedStr;
        }
}
