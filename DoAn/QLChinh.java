import java.util.Scanner;

public class QLChinh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QLNV qlnv = new QLNV();
        QLKH qlkh = new QLKH();
        QLNCC qlncc = new QLNCC();
        QLPNH qlpnh = new QLPNH();
        QuanLyHD qlhd = new QuanLyHD();
        QLGiay qlgiay = new QLGiay(); // thêm đối tượng quản lý giày

        int choice;

        do {
            System.out.println("\n===========================================");
            System.out.println("||          MENU QUAN LY TONG HOP        ||");
            System.out.println("===========================================");
            System.out.println("1. Quan ly NHAN VIEN");
            System.out.println("2. Quan ly KHACH HANG");
            System.out.println("3. Quan ly NHA CUNG CAP");
            System.out.println("4. Quan ly PHIEU NHAP HANG");
            System.out.println("5. Quan ly HOA DON");
            System.out.println("6. Quan ly GIAY"); // ✅ thêm mục mới
            System.out.println("0. Thoat chuong trinh");
            System.out.println("===========================================");
            System.out.print("-> Nhap lua chon: ");
            choice = readIntInRange(sc, 0, 6);

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== QUAN LY NHAN VIEN ===");
                    QLNVMenu(sc, qlnv);
                }
                case 2 -> {
                    System.out.println("\n=== QUAN LY KHACH HANG ===");
                    QLKHMenu(sc, qlkh);
                }
                case 3 -> {
                    System.out.println("\n=== QUAN LY NHA CUNG CAP ===");
                    qlncc.Menu();
                }
                case 4 -> {
                    System.out.println("\n=== QUAN LY PHIEU NHAP HANG ===");
                    qlpnh.Menu();
                }
                case 5 -> {
                    System.out.println("\n=== QUAN LY HOA DON ===");
                    QuanLyHD.main(null);
                }
                case 6 -> { // ✅ gọi menu giày
                    System.out.println("\n=== QUAN LY GIAY ===");
                    qlgiay.menu();
                }
                case 0 -> System.out.println("Da thoat chuong trinh tong hop!");
            }

        } while (choice != 0);
        sc.close();
    }

    // ================== HÀM GỌI MENU CON ==================
    private static void QLNVMenu(Scanner sc, QLNV qlnv) {
        try {
            java.lang.reflect.Method m = QLNV.class.getDeclaredMethod("menuNhanVien", Scanner.class, DSNhanVien.class);
            m.setAccessible(true);
            m.invoke(null, sc, new DSNhanVien());
        } catch (Exception e) {
            System.out.println("Khong goi duoc menu nhan vien: " + e.getMessage());
        }
    }

    private static void QLKHMenu(Scanner sc, QLKH qlkh) {
        try {
            java.lang.reflect.Method m = QLKH.class.getDeclaredMethod("menuKhachHang", Scanner.class, DSKhachHang.class);
            m.setAccessible(true);
            m.invoke(null, sc, new DSKhachHang());
        } catch (Exception e) {
            System.out.println("Khong goi duoc menu khach hang: " + e.getMessage());
        }
    }

    // ================== HÀM NHẬP SỐ AN TOÀN ==================
    private static int readIntInRange(Scanner sc, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (Exception e) {}
            System.out.print("Gia tri khong hop le. Nhap lai (" + min + "-" + max + "): ");
        }
    }
}
