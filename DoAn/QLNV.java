import java.io.FileNotFoundException;
import java.util.Scanner;

public class QLNV {
     // ========== MENU NHAN VIEN ==========
    private static void menuNhanVien(Scanner sc, DSNhanVien dsNV) {
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
            int ch = readIntInRange(sc, "Chon: ", 0, 8);
            if (ch == 0) break;

            switch (ch) {
                case 1: {
                    String path = readLine(sc, "Nhap duong dan file (mac dinh: nhanvien.txt, Enter de dung mac dinh): ");
                    if (path.trim().length() == 0) path = "nhanvien.txt";
                    try {
                        int n = dsNV.docFileTxt(path);
                        System.out.println("Da doc " + n + " nhan vien tu " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Khong tim thay file: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    String path = readLine(sc, "Nhap duong dan file ghi (mac dinh: nhanvien_out.txt, Enter de dung mac dinh): ");
                    if (path.trim().length() == 0) path = "nhanvien_out.txt";
                    try {
                        int n = dsNV.ghiFileTxt(path);
                        System.out.println("Da ghi " + n + " nhan vien ra " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Loi ghi file: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    int k = readIntInRange(sc, "Nhap k (so luong can them): ", 1, Integer.MAX_VALUE);
                    int added = dsNV.themKTuBanPhim(sc, k);
                    System.out.println("Da them thanh cong: " + added + "/" + k);
                    break;
                }
                case 4: {
                    String ma = readNonEmpty(sc, "Nhap ma NV can xoa: ");
                    boolean ok = dsNV.xoaTheoMa(ma);
                    System.out.println(ok ? "Da xoa." : "Khong tim thay ma can xoa.");
                    break;
                }
                case 5: {
                    String ma = readNonEmpty(sc, "Nhap ma NV can cap nhat: ");
                    System.out.println("Chon truong can cap nhat:");
                    System.out.println("1. Ho");
                    System.out.println("2. Ten");
                    System.out.println("3. Dien thoai");
                    System.out.println("4. Luong thang");
                    int field = readIntInRange(sc, "Chon (1-4): ", 1, 4);
                    String val = readNonEmpty(sc, "Nhap gia tri moi: ");
                    boolean ok = dsNV.capNhat(ma, field, val);
                    System.out.println(ok ? "Cap nhat thanh cong." : "Cap nhat that bai (ma khong ton tai hoac gia tri khong hop le).");
                    break;
                }
                case 6: {
                    String key = readNonEmpty(sc, "Nhap tu khoa tim gan dung: ");
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
                    int mode = readIntInRange(sc, "Chon (1-4): ", 1, 4);
                    String lineKeys = readNonEmpty(sc, "Nhap cac khoa, cach nhau boi dau phay: ");
                    String[] keys = splitKeys(lineKeys);
                    int[] counts = dsNV.thongKe(mode, keys);
                    printCounts(keys, counts, mode == 4);
                    break;
                }
                case 8: { // list
                    System.out.println("Danh sach nhan vien (" + dsNV.soLuong() + "):");
                    dsNV.xuatDanhSach();
                    break;
                }
            }
        }
    }
     // ====== Helpers (toi thieu) ======
    private static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s != null) s = s.trim();
            if (s != null && s.length() > 0) return s;
            System.out.println("Khong duoc de trong, vui long nhap lai.");
        }
    }

    private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                int v = Integer.parseInt(s.trim());
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.println("Gia tri khong hop le. Can so nguyen trong khoang [" + min + ", " + max + "].");
        }
    }

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
            if (isLuong) {
                System.out.println("= " + keys[i] + " -> " + counts[i]);
            } else {
                System.out.println(keys[i] + " -> " + counts[i]);
            }
        }
    }
}
