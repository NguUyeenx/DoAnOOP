import java.io.FileNotFoundException;
import java.util.Scanner;

public class QLKH {
     // ========== MENU KHACH HANG ==========
    private static void menuKhachHang(Scanner sc, DSKhachHang dsKH) {
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
            int ch = readIntInRange(sc, "Chon: ", 0, 8);
            if (ch == 0) break;

            switch (ch) {
                case 1: {
                    String path = readLine(sc, "Nhap duong dan file (mac dinh: khachhang.txt, Enter de dung mac dinh): ");
                    if (path.trim().length() == 0) path = "khachhang.txt";
                    try {
                        int n = dsKH.docFileTxt(path);
                        System.out.println("Da doc " + n + " khach hang tu " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Khong tim thay file: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    String path = readLine(sc, "Nhap duong dan file ghi (mac dinh: khachhang_out.txt, Enter de dung mac dinh): ");
                    if (path.trim().length() == 0) path = "khachhang_out.txt";
                    try {
                        int n = dsKH.ghiFileTxt(path);
                        System.out.println("Da ghi " + n + " khach hang ra " + path);
                    } catch (FileNotFoundException e) {
                        System.out.println("Loi ghi file: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    int k = readIntInRange(sc, "Nhap k (so luong can them): ", 1, Integer.MAX_VALUE);
                    int added = dsKH.themKTuBanPhim(sc, k);
                    System.out.println("Da them thanh cong: " + added + "/" + k);
                    break;
                }
                case 4: {
                    String ma = readNonEmpty(sc, "Nhap ma KH can xoa: ");
                    boolean ok = dsKH.xoaTheoMa(ma);
                    System.out.println(ok ? "Da xoa." : "Khong tim thay ma can xoa.");
                    break;
                }
                case 5: {
                    String ma = readNonEmpty(sc, "Nhap ma KH can cap nhat: ");
                    System.out.println("Chon truong can cap nhat:");
                    System.out.println("1. Ho");
                    System.out.println("2. Ten");
                    System.out.println("3. Dien thoai");
                    int field = readIntInRange(sc, "Chon (1-3): ", 1, 3);
                    String val = readNonEmpty(sc, "Nhap gia tri moi: ");
                    boolean ok = dsKH.capNhat(ma, field, val);
                    System.out.println(ok ? "Cap nhat thanh cong." : "Cap nhat that bai (ma khong ton tai hoac gia tri khong hop le).");
                    break;
                }
                case 6: {
                    String key = readNonEmpty(sc, "Nhap tu khoa tim gan dung: ");
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
                    int mode = readIntInRange(sc, "Chon (1-3): ", 1, 3);
                    String lineKeys = readNonEmpty(sc, "Nhap cac khoa, cach nhau boi dau phay: ");
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