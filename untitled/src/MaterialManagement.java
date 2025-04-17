import java.time.LocalDate;

public class MaterialManagement {
    private Material[] materials;
    private int size;
    private static final int maxSize = 10;

    public MaterialManagement() {
        materials = new Material[maxSize];
        size = 0;
        initializeMaterials();
    }

    private void initializeMaterials() {
        addMaterial(new CrispyFlour("CF1", "Flour A", LocalDate.of(2024, 10, 1), 100, 10));
        addMaterial(new CrispyFlour("CF2", "Flour B", LocalDate.of(2024, 12, 1), 120, 8));
        addMaterial(new CrispyFlour("CF3", "Flour C", LocalDate.of(2025, 1, 1), 90, 15));
        addMaterial(new CrispyFlour("CF4", "Flour D", LocalDate.of(2025, 2, 1), 110, 12));
        addMaterial(new CrispyFlour("CF5", "Flour E", LocalDate.of(2025, 3, 1), 130, 5));

        addMaterial(new Meat("M1", "Beef", LocalDate.of(2025, 4, 10), 200, 2.5));
        addMaterial(new Meat("M2", "Pork", LocalDate.of(2025, 4, 11), 180, 3.0));
        addMaterial(new Meat("M3", "Chicken", LocalDate.of(2025, 4, 12), 150, 1.5));
        addMaterial(new Meat("M4", "Lamb", LocalDate.of(2025, 4, 13), 220, 2.0));
        addMaterial(new Meat("M5", "Fish", LocalDate.of(2025, 4, 14), 170, 1.8));
    }

    private void resize() {
        Material[] newMaterials = new Material[materials.length * 2];
        System.arraycopy(materials, 0, newMaterials, 0, size);
        materials = newMaterials;
    }

    public void addMaterial(Material material) {
        if (size == materials.length) {
            resize();
        }
        materials[size++] = material;
    }

    public double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += materials[i].getAmount();
        }
        return total;
    }

    public double getTotalRealMoney() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            if (materials[i] instanceof Discount) {
                total += ((Discount) materials[i]).getRealMoney();
            }
        }
        return total;
    }

    public double getDiscountDifference() {
        return getTotalAmount() - getTotalRealMoney();
    }

    public void printMaterials() {
        for (int i = 0; i < size; i++) {
            System.out.println(materials[i]);
            System.out.println("Amount: " + materials[i].getAmount());
            if (materials[i] instanceof Discount) {
                System.out.println("Real Money: " + ((Discount) materials[i]).getRealMoney());
            }
            System.out.println("Expiry Date: " + materials[i].getExpiryDate());
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        MaterialManagement manager = new MaterialManagement();
        manager.printMaterials();

        System.out.println("Total Amount: " + manager.getTotalAmount());
        System.out.println("Total Real Money: " + manager.getTotalRealMoney());
        System.out.println("Discount Difference: " + manager.getDiscountDifference());
    }
}
