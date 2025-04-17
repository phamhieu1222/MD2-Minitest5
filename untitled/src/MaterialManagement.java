import java.time.LocalDate;
import java.util.Scanner;

public class MaterialManagement {
    private int count;
    private Material[] materials;
    private int size;
    private static final int maxSize = 10;
    public boolean checkIdExistence(String id) {
        for (int i = 0; i < count; i++) {
            if (materials[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void deleteMaterial(String id) {
        for (int i = 0; i < count; i++) {
            if (materials[i].getId().equals(id)) {
                for (int j = i; j < count - 1; j++) {
                    materials[j] = materials[j + 1];
                }
                materials[count - 1] = null;
                count--;
                return;
            }
        }
        System.out.println("Không tìm thấy vật liệu có ID: " + id);
    }



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
        for (int i = 0; i < size; i++) {
            newMaterials[i] = materials[i];
        }
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
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("========================Menu========================");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Tính số chênh lệch giữa chiết khấu và không chiết khấu tại ngày hôm nay");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    int choice1 = -1;
                    while (choice1 != 1 && choice1 != 2) {
                        System.out.println("\n1. Thêm CrispyFlour");
                        System.out.println("2. Thêm Meat");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                manager.addMaterial(inputCrispyFlour(sc));
                                System.out.println("Đã thêm CrispyFlour!");
                                manager.printMaterials();
                                break;
                            case 2:
                                manager.addMaterial(inputMeat(sc));
                                System.out.println("Đã thêm Meat!");
                                manager.printMaterials();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    }
                    break;
                case 2:
                    int choice2 = -1;
                    while (choice2 != 1 && choice2 != 2) {
                        System.out.println("\n1. Sửa CrispyFlour");
                        System.out.println("2. Sửa Meat");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        choice2 = sc.nextInt();
                        sc.nextLine();  // Để tránh lỗi khi đọc dòng nhập tiếp theo

                        switch (choice2) {
                            case 1:
                                System.out.print("Nhập ID CrispyFlour cần sửa: ");
                                String idCrispyFlour = sc.nextLine();
                                if (!manager.checkIdExistence(idCrispyFlour)) {
                                    System.out.println("Không tìm thấy CrispyFlour với ID: " + idCrispyFlour);
                                } else {
                                    manager.addMaterial( inputCrispyFlour(sc));
                                    System.out.println("Đã sửa CrispyFlour!");
                                    manager.printMaterials();
                                }
                                break;

                            case 2:
                                System.out.print("Nhập ID Meat cần sửa: ");
                                String idMeat = sc.nextLine();
                                if (!manager.checkIdExistence(idMeat)) {
                                    System.out.println("Không tìm thấy Meat với ID: " + idMeat);
                                } else {
                                    manager.addMaterial( inputMeat(sc));
                                    System.out.println("Đã sửa Meat!");
                                    manager.printMaterials();
                                }
                                break;

                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Nhập ID để xóa sản phẩm: ");
                    String id = sc.nextLine();
                    manager.deleteMaterial(id);
                    manager.printMaterials();
                    break;
                case 4:
                    manager.getDiscountDifference();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");

            }
        }


    }

    private static CrispyFlour inputCrispyFlour(Scanner sc) {
        sc.nextLine();
        System.out.print("Id: ");
        String id = sc.nextLine();

        System.out.print("Tên: ");
        String name = sc.nextLine();

        System.out.print("Số tháng trừ đi hiện tại: ");
        int month = sc.nextInt();

        System.out.print("Giá: ");
        int cost = sc.nextInt();

        System.out.print("Số lượng: ");
        int quantity = sc.nextInt();

        return new CrispyFlour(id, name, LocalDate.now().minusMonths(month), cost, quantity);
    }

    private static Meat inputMeat(Scanner sc) {
        sc.nextLine();
        System.out.print("Id: ");
        String id = sc.nextLine();

        System.out.print("Tên: ");
        String name = sc.nextLine();

        System.out.print("Số ngày trừ đi hiện tại: ");
        int day = sc.nextInt();

        System.out.print("Giá: ");
        int cost = sc.nextInt();

        System.out.print("Trọng lượng: ");
        double quantity = sc.nextDouble();

        return new Meat(id, name, LocalDate.now().minusDays(day), cost, quantity);
    }
    }
