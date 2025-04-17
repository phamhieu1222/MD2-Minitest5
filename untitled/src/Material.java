import java.time.LocalDate;

public abstract class Material {
    private String id;
    private String name;
    private int cost;
    private LocalDate manufacturingDate;

    public Material(String id, String name, LocalDate manufacturingDate, int cost) {
        this.id = id;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public abstract double getAmount();
    public abstract LocalDate getExpiryDate();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Cost: " + cost + ", MFG: " + manufacturingDate;
    }
}
