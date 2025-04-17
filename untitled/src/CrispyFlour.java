import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CrispyFlour extends Material implements Discount {
    private double quantity;

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, double quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return quantity * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        long monthsLeft = ChronoUnit.MONTHS.between(LocalDate.now(), getExpiryDate());
        double discountRate;

        if (monthsLeft <= 2) {
            discountRate = 0.4;
        } else if (monthsLeft <= 4) {
            discountRate = 0.2;
        } else {
            discountRate = 0.05;
        }

        return getAmount() * (1 - discountRate);
    }

    @Override
    public String toString() {
        return super.toString() + ", Quantity: " + quantity;
    }
}
