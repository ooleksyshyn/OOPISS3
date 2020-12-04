package main.java.ammunition;

public interface EquippableItem {
    
    void equip() throws ItemEquipException;
    void unequip() throws ItemEquipException;

    boolean isEquipped();
}
