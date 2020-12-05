package Devices;

public class Device implements Comparable {
    private String name;
    private String origin;
    private double price;
    private Type deviceType;
    private boolean critical;

    public Device() {
        this.deviceType = new Type();
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Device) o).getName());
    }

    @Override
    public String toString() {
        return "\nDevice{ "
                + "\n\tName: " + this.name
                + ", \n\tOrigin: " + this.origin
                + ", \n\tPrice: " + this.price
                + ", \n\tType: " + this.deviceType
                + ", \n\tCritical: " + ((this.critical) ? "Yes" : "No")
                + "\n}";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device p = (Device) o;
        return this.name.equals(p.getName())
                && this.deviceType.equals(p.getType())
                && this.critical == p.isCritical()
                && this.origin.equals(p.getOrigin())
                && Math.abs(this.price - p.getPrice()) < 0.01;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return this.deviceType;
    }

    public void setType(Type type) {
        deviceType = type;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}
