import java.util.Scanner;

public class QuanLyHD {
    
    private static final String FILE_HOADON = "HOADON_DATA.txt";
    private static final String FILE_CTHD = "CTHD_DATA.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        DSHoaDon danhSachHoaDon = new DSHoaDon();
        DSCTHD danhSachCTHD = new DSCTHD();

        do {
            System.out.println("\n=========================================");
            System.out.println("||        MENU QUẢN LÝ HÓA ĐƠN         ||");
            System.out.println("=========================================");
            System.out.println("|| 1. Đọc dữ liệu từ file (Khởi tạo)   ||");
            System.out.println("|| 2. Quản lý Danh Sách Hóa Đơn        ||");
            System.out.println("|| 3. Quản lý Danh Sách Chi Tiết HĐ    ||");
            System.out.println("|| 0. Thoát                            ||");
            System.out.println("=========================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                sc.nextLine(); 
                choice = -1;
            }

            switch (choice) {
                case 1:
                    danhSachHoaDon.docFile(FILE_HOADON);
                    danhSachCTHD.docFile(FILE_CTHD);
                    break;
                case 2:
                    menuHoaDon(danhSachHoaDon);
                    break;
                case 3:
                    menuCTHD(danhSachCTHD);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default: 
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }
    
    public static void menuHoaDon( DSHoaDon ds) {
        Scanner sc= new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Quản lý Danh Sách Hóa Đơn ---");
            System.out.println("1. Thêm 1 Hóa Đơn"); 
            System.out.println("2. Thêm K Hóa Đơn"); 
            System.out.println("3. Xuất danh sách Hóa Đơn (Màn hình)"); 
            System.out.println("4. Xóa Hóa Đơn"); 
            System.out.println("5. Sửa Hóa Đơn"); 
            System.out.println("6. Tìm kiếm Hóa Đơn "); 
            System.out.println("7. Thống kê tổng thu theo Nhân Viên"); 
            System.out.println("8. Thống kê tổng thu theo Khách hàng"); 
            System.out.println("9. Thống kê tổng thu theo Quý"); 
            System.out.println("10. Xuất danh sách Hóa Đơn ra file (HOADON_DATA_OUT.txt)"); 
            System.out.println("0. Quay lại Menu chính");
            System.out.print("Nhập lựa chọn: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                sc.nextLine(); 
                choice = -1;
                continue;
            }
            
            switch (choice) {
                case 1: ds.them(); break; 
                case 2: ds.themKphantu(); break; 
                case 3: ds.xuat(); break; 
                case 4: ds.xoa(); break; 
                case 5: ds.sua(); break; 
                case 6: ds.timKiem(); break; 
                case 7: ds.thongKeTongThuTheoNV(); break; 
                case 8: ds.thongKeTongThuTheoKH(); break; 
                case 9: ds.thongKeTongThuTheoQuy(); break; 
                case 10: ds.ghiFile("HOADON_DATA_OUT.txt"); break;
                case 0: System.out.println("Quay lại Menu chính..."); break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
        sc.close();
    }
    
    public static void menuCTHD(DSCTHD ds) {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Quản lý Danh Sách Chi Tiết Hóa Đơn ---");
            System.out.println("1. Thêm 1 Chi Tiết Hóa Đơn"); 
            System.out.println("2. Thêm K Chi Tiết Hóa Đơn"); 
            System.out.println("3. Xuất danh sách Chi Tiết Hóa Đơn (Màn hình)"); 
            System.out.println("4. Tìm kiếm Chi Tiết Hóa Đơn "); 
            System.out.println("5. Thống kê tổng thu theo Sản Phẩm"); 
            System.out.println("6. Xuất danh sách Chi Tiết Hóa Đơn ra file (CTHD_DATA_OUT.txt)"); 
            System.out.println("0. Quay lại Menu chính");
            System.out.print("Nhập lựa chọn: ");

             if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                sc.nextLine(); 
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1: ds.them(); break;
                case 2: ds.themKphantu(); break;
                case 3: ds.xuat(); break;
                case 4: ds.timKiem(); break;
                case 5: ds.thongKeTongThuTheoSP(); break;
                case 6: ds.ghiFile("CTHD_DATA_OUT.txt"); break;
                case 0: System.out.println("Quay lại Menu chính..."); break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }
}