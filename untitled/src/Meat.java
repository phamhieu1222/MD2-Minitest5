import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Meat extends Material implements Discount {
    private double weight;

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return weight * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getExpiryDate());
        double discountRate = (daysLeft <= 5) ? 0.3 : 0.1;
        return getAmount() * (1 - discountRate);
    }

    @Override
    public String toString() {
        return super.toString() + ", Weight: " + weight;
    }
}
