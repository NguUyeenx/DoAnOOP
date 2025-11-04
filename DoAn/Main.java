import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        DSNhanVien dsNV = new DSNhanVien();
        DSKhachHang dsKH = new DSKhachHang();

        while (true) {
            System.out.println("==== MENU CHINH ====");
            System.out.println("1. Quan ly Nhan vien");
            System.out.println("2. Quan ly Khach hang");
            System.out.println("0. Thoat");
            int ch = Input.readIntInRange("Chon: ", 0, 2);
            if (ch == 0) {
                System.out.println("Ket thuc chuong trinh.");
                break;
            }
            if (ch == 1) menuNhanVien(dsNV);
            else menuKhachHang(dsKH);
        }
    }

    // ========== MENU NHAN VIEN ==========
    private static void menuNhanVien(DSNhanVien dsNV) {
        while (true) {
            System.out.println("\n---- QUAN LY NHAN VIEN ----");
            System.out.println("1. Doc danh sach tu file .txt");
            System.out.println("2. Ghi danh sach ra file .txt");
            System.out.println("3. Them k nhan vien");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Cap nhat (switch-case)");
            System.out.println("6. Tim kiem gan dung");
            System.out.println("7. Thong ke (switch-case)");
            System.out.println("8. Xem danh sach");
            System.out.println("0. Quay lai");
            int ch = Input.readIntInRange("Chon: ", 0, 8);
            if (ch == 0) break;

            switch (ch) {
                case 1: {
                    String path = Input.readLine("Nhap duong dan file (mac dinh: nhanvien.txt, Enter de dung mac dinh): ");
                    if (path.trim().isEmpty()) path = "nhanvien.txt";
                    try {
                        int n = dsNV.docFileTxt(path);
                        System.out.println("Da doc " + n + " nhan vien tu " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Khong tim thay file: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    String path = Input.readLine("Nhap duong dan file ghi (mac dinh: nhanvien_out.txt, Enter de dung mac dinh): ");
                    if (path.trim().isEmpty()) path = "nhanvien_out.txt";
                    try {
                        int n = dsNV.ghiFileTxt(path);
                        System.out.println("Da ghi " + n + " nhan vien ra " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Loi ghi file: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    int k = Input.readIntInRange("Nhap k (so luong can them): ", 1, Integer.MAX_VALUE);
                    int added = dsNV.themKTuBanPhim(k);
                    System.out.println("Da them thanh cong: " + added + "/" + k);
                    break;
                }
                case 4: {
                    String ma = Input.readNonEmpty("Nhap ma NV can xoa: ");
                    boolean ok = dsNV.xoaTheoMa(ma);
                    System.out.println(ok ? "Da xoa." : "Khong tim thay ma can xoa.");
                    break;
                }
                case 5: {
                    String ma = Input.readNonEmpty("Nhap ma NV can cap nhat: ");
                    System.out.println("Chon truong can cap nhat:");
                    System.out.println("1. Ho");
                    System.out.println("2. Ten");
                    System.out.println("3. Dien thoai");
                    System.out.println("4. Luong thang");
                    int field = Input.readIntInRange("Chon (1-4): ", 1, 4);
                    String val = Input.readNonEmpty("Nhap gia tri moi: ");
                    boolean ok = dsNV.capNhat(ma, field, val);
                    System.out.println(ok ? "Cap nhat thanh cong." : "Cap nhat that bai (ma khong ton tai hoac gia tri khong hop le).");
                    break;
                }
                case 6: {
                    String key = Input.readNonEmpty("Nhap tu khoa tim gan dung: ");
                    NhanVien[] rs = dsNV.timGanDung(key);
                    System.out.println("Ket qua (" + rs.length + "):");
                    for (NhanVien nv : rs) nv.xuat();
                    break;
                }
                case 7: {
                    System.out.println("Chon che do thong ke:");
                    System.out.println("1. Theo Ho (so sanh bang, khong phan biet hoa thuong)");
                    System.out.println("2. Theo Ten (so sanh bang, khong phan biet hoa thuong)");
                    System.out.println("3. Theo tien to Dien thoai (startsWith)");
                    System.out.println("4. Theo Luong thang (nhap so)");
                    int mode = Input.readIntInRange("Chon (1-4): ", 1, 4);
                    String lineKeys = Input.readNonEmpty("Nhap cac khoa, cach nhau boi dau phay: ");
                    String[] keys = splitKeys(lineKeys);
                    int[] counts = dsNV.thongKe(mode, keys);
                    printCounts(keys, counts, mode == 4);
                    break;
                }
                case 8: {
                    System.out.println("Danh sach nhan vien (" + dsNV.soLuong() + "):");
                    dsNV.xuatDanhSach();
                    break;
                }
            }
        }
    }

    // ========== MENU KHACH HANG ==========
    private static void menuKhachHang(DSKhachHang dsKH) {
        while (true) {
            System.out.println("\n---- QUAN LY KHACH HANG ----");
            System.out.println("1. Doc danh sach tu file .txt");
            System.out.println("2. Ghi danh sach ra file .txt");
            System.out.println("3. Them k khach hang");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Cap nhat (switch-case)");
            System.out.println("6. Tim kiem gan dung");
            System.out.println("7. Thong ke (switch-case)");
            System.out.println("8. Xem danh sach");
            System.out.println("0. Quay lai");
            int ch = Input.readIntInRange("Chon: ", 0, 8);
            if (ch == 0) break;

            switch (ch) {
                case 1: {
                    String path = Input.readLine("Nhap duong dan file (mac dinh: khachhang.txt, Enter de dung mac dinh): ");
                    if (path.trim().isEmpty()) path = "khachhang.txt";
                    try {
                        int n = dsKH.docFileTxt(path);
                        System.out.println("Da doc " + n + " khach hang tu " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Khong tim thay file: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    String path = Input.readLine("Nhap duong dan file ghi (mac dinh: khachhang_out.txt, Enter de dung mac dinh): ");
                    if (path.trim().isEmpty()) path = "khachhang_out.txt";
                    try {
                        int n = dsKH.ghiFileTxt(path);
                        System.out.println("Da ghi " + n + " khach hang ra " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Loi ghi file: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    int k = Input.readIntInRange("Nhap k (so luong can them): ", 1, Integer.MAX_VALUE);
                    int added = dsKH.themKTuBanPhim(k);
                    System.out.println("Da them thanh cong: " + added + "/" + k);
                    break;
                }
                case 4: {
                    String ma = Input.readNonEmpty("Nhap ma KH can xoa: ");
                    boolean ok = dsKH.xoaTheoMa(ma);
                    System.out.println(ok ? "Da xoa." : "Khong tim thay ma can xoa.");
                    break;
                }
                case 5: {
                    String ma = Input.readNonEmpty("Nhap ma KH can cap nhat: ");
                    System.out.println("Chon truong can cap nhat:");
                    System.out.println("1. Ho");
                    System.out.println("2. Ten");
                    System.out.println("3. Dien thoai");
                    int field = Input.readIntInRange("Chon (1-3): ", 1, 3);
                    String val = Input.readNonEmpty("Nhap gia tri moi: ");
                    boolean ok = dsKH.capNhat(ma, field, val);
                    System.out.println(ok ? "Cap nhat thanh cong." : "Cap nhat that bai (ma khong ton tai hoac gia tri khong hop le).");
                    break;
                }
                case 6: {
                    String key = Input.readNonEmpty("Nhap tu khoa tim gan dung: ");
                    KhachHang[] rs = dsKH.timGanDung(key);
                    System.out.println("Ket qua (" + rs.length + "):");
                    for (KhachHang kh : rs) kh.xuat();
                    break;
                }
                case 7: {
                    System.out.println("Chon che do thong ke:");
                    System.out.println("1. Theo Ho (so sanh bang, khong phan biet hoa thuong)");
                    System.out.println("2. Theo Ten (so sanh bang, khong phan biet hoa thuong)");
                    System.out.println("3. Theo tien to Dien thoai (startsWith)");
                    int mode = Input.readIntInRange("Chon (1-3): ", 1, 3);
                    String lineKeys = Input.readNonEmpty("Nhap cac khoa, cach nhau boi dau phay: ");
                    String[] keys = splitKeys(lineKeys);
                    int[] counts = dsKH.thongKe(mode, keys);
                    printCounts(keys, counts, false);
                    break;
                }
                case 8: {
                    System.out.println("Danh sach khach hang (" + dsKH.soLuong() + "):");
                    dsKH.xuatDanhSach();
                    break;
                }
            }
        }
    }

    // ====== Helpers ======
    private static String[] splitKeys(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] != null) parts[i] = parts[i].trim();
        }
        return parts;
    }

    private static void printCounts(String[] keys, int[] counts, boolean isLuong) {
        int n = Math.min(keys.length, counts.length);
        for (int i = 0; i < n; i++) {
            if (isLuong) System.out.println("= " + keys[i] + " -> " + counts[i]);
            else System.out.println(keys[i] + " -> " + counts[i]);
        }
    }
}