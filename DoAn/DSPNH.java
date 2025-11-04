
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class DSPNH implements CongCuCoBan {

    private PNH[] dspnh;
    private int n;

    public DSPNH() {
        n = 0;
        dspnh = new PNH[0];
    }

    public DSPNH(int n, PNH[] dspnh2) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            dspnh[i] = dspnh2[i];
        }
    }

    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong phieu nhap hang: ");
        n = sc.nextInt();
        dspnh = new PNH[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Phieu nhap hang thu " + i + 1 + " :");
            dspnh[i] = new PNH();
            dspnh[i].nhap();
        }
    }

    @Override
    public void Xuat() {
        XuatDS();
    }

    public void Xuat1Phieu(PNH pnh) {
        pnh.xuat();
    }

    public void XuatDS() {
        for (int i = 0; i < n; i++) {
            dspnh[i].xuat();
        }
    }

    @Override
    public void Them() {
        dspnh = Arrays.copyOf(dspnh, n + 1);
        dspnh[n] = new PNH();
        dspnh[n].nhap();
        n++;
    }

    public void ThemKPhanTu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong phieu nhap hang can them");
        int index = sc.nextInt();
        n = dspnh.length;
        for (int i = 0; i < index; i++) {
            System.out.print("Phieu nhap hang thu " + (i + 1) + " : \n");
            dspnh = Arrays.copyOf(dspnh, n + 1);
            dspnh[n] = new PNH();
            dspnh[n].nhap();
            n++;
        }
    }

    @Override
    public void Xoa() {
        Scanner sc = new Scanner(System.in);
        n = dspnh.length;
        String ma;
        System.out.print("Nhap ma phieu nhap hang can xoa: ");
        ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaPNH().equals(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay phieu nhap hang can xoa!");
            return;
        }
        for (int i = index; i < n - 1; i++) {
            dspnh[i] = dspnh[i + 1];
        }
        dspnh = Arrays.copyOf(dspnh, n - 1);
        n--;
    }

    @Override
    public void Sua() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma phieu nhap hang can sua: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaPNH().equals(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay phieu nhap hang can sua");
            return;
        }
        int choice;
        do {
            System.out.println("\n=== MENU SUA THONG TIN");
            System.out.println("1. Sua ma phieu nhap hang");
            System.out.println("2. Sua ma nhan vien");
            System.out.println("3. Sua ma nha cung cap");
            System.out.println("4. Sua ngay thanh lap phieu nhap hang");
            System.out.println("0. Thoat");
            System.out.println("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhap ma phieu nhap hang moi: ");
                    dspnh[index].setMaPNH(sc.nextLine());
                    System.out.println("Da cap nhat phieu nhap hang moi!");
                case 2:
                    System.out.print("Nhap ma nhan vien moi: ");
                    dspnh[index].setMaNV(sc.nextLine());
                    System.out.println("Da cap nhat ma nhan vien moi!");
                case 3:
                    System.out.print("Nhap ma nha cung cap moi: ");
                    dspnh[index].setMaNCC(sc.nextLine());
                    System.out.println("Da cap nhat ma nha cung cap moi!");
                case 4:
                    System.out.print("Nhap ngay thanh lap phieu nhap hang moi: ");
                    dspnh[index].setNgay(sc.nextLine());
                    System.out.println("Da cap nhat ngay thanh lap phieu nhap hang moi!");
                case 0:
                    System.out.println("Thoat sua thong tin!");
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    public PNH TimKiemMaPNH() {
        Scanner sc = new Scanner(System.in);
        String ma;
        System.out.println("Nhap ma phieu nhap hang can tim: ");
        ma = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaPNH().equals(ma)) {
                return dspnh[i];
            }
        }
        System.out.println("Khong tim thay phieu nhap hang");
        return null;
    }

    public PNH[] TimKiemMaNV() {
        Scanner sc = new Scanner(System.in);
        String ma;
        PNH[] ketQua = new PNH[0];
        int dem = 0;
        System.out.println("Nhap ma phieu nhap hang can tim: ");
        ma = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaNV().equals(ma)) {
                ketQua = Arrays.copyOf(ketQua, dem + 1);
                ketQua[dem++] = dspnh[i];
            }
        }
        if (dem == 0) {
            System.out.println("Khong tim thay phieu nhap hang co ma nhan vien " + ma);
            return null;
        }
        return ketQua;
    }

    // ================= THỐNG KÊ 1 KHÓA =================
    // Thống kê tổng tiền nhập hàng theo mã nhân viên
    public void thongKeTheoMaNV() {
        if (n == 0) {
            System.out.println("Danh sách phiếu nhập hàng trống!");
            return;
        }

        System.out.println("\n===== THỐNG KÊ TỔNG TIỀN THEO MÃ NHÂN VIÊN =====");

        // Lưu các mã NV đã thống kê
        String[] dsMaNV = new String[0];
        double[] tongTienTheoNV = new double[0];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            String maNV = dspnh[i].getMaNV();
            double tien = dspnh[i].getTongTien();

            boolean found = false;
            for (int j = 0; j < dem; j++) {
                if (dsMaNV[j].equalsIgnoreCase(maNV)) {
                    tongTienTheoNV[j] += tien;
                    found = true;
                    break;
                }
            }
            if (!found) {
                dsMaNV = Arrays.copyOf(dsMaNV, dem + 1);
                tongTienTheoNV = Arrays.copyOf(tongTienTheoNV, dem + 1);
                dsMaNV[dem] = maNV;
                tongTienTheoNV[dem] = tien;
                dem++;
            }
        }

        System.out.printf("%-15s %-15s\n", "Mã NV", "Tổng tiền nhập");
        System.out.println("----------------------------------");
        for (int i = 0; i < dem; i++) {
            System.out.printf("%-15s %-15.2f\n", dsMaNV[i], tongTienTheoNV[i]);
        }
    }

    // ================= THỐNG KÊ 2 KHÓA =================
    // Thống kê tổng tiền theo mã nhân viên và mã nhà cung cấp
    public void thongKeTheoNVvaNCC() {
        if (n == 0) {
            System.out.println("Danh sách phiếu nhập hàng trống!");
            return;
        }

        System.out.println("\n===== THỐNG KÊ TỔNG TIỀN THEO NV VÀ NCC =====");

        String[][] key = new String[0][2]; // [][0]: maNV, [][1]: maNCC
        double[] tongTien = new double[0];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            String maNV = dspnh[i].getMaNV();
            String maNCC = dspnh[i].getMaNCC();
            double tien = dspnh[i].getTongTien();

            boolean found = false;
            for (int j = 0; j < dem; j++) {
                if (key[j][0].equalsIgnoreCase(maNV) && key[j][1].equalsIgnoreCase(maNCC)) {
                    tongTien[j] += tien;
                    found = true;
                    break;
                }
            }
            if (!found) {
                key = Arrays.copyOf(key, dem + 1);
                tongTien = Arrays.copyOf(tongTien, dem + 1);
                key[dem] = new String[]{maNV, maNCC};
                tongTien[dem] = tien;
                dem++;
            }
        }

        System.out.printf("%-15s %-15s %-15s\n", "Mã NV", "Mã NCC", "Tổng tiền nhập");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < dem; i++) {
            System.out.printf("%-15s %-15s %-15.2f\n", key[i][0], key[i][1], tongTien[i]);
        }
    }

    public PNH getPNH(int i) {
        return dspnh[i];
    }

    public int getSoLuongPNH() {
        return dspnh.length;
    }

    public void ghiFile(String tenFilePNH, String tenFileCTPNH) {
        try {
            BufferedWriter bwPNH = new BufferedWriter(new FileWriter(tenFilePNH));
            BufferedWriter bwCTPNH = new BufferedWriter(new FileWriter(tenFileCTPNH));

            for (int i = 0; i < n; i++) {
                bwPNH.write(
                        dspnh[i].getMaPNH() + "|"
                        + dspnh[i].getMaNV() + "|"
                        + dspnh[i].getMaNCC() + "|"
                        + dspnh[i].getNgay() + "|"
                        + dspnh[i].getTongTien()
                );
                bwPNH.newLine();

                DSCTPNH dsct = dspnh[i].getDSCTPNH();
                for (int j = 0; j < dsct.getSoLuongChiTiet(); j++) {
                    CTPNH ct = dsct.getChiTiet(j);
                    bwCTPNH.write(
                            ct.getMaPNH() + "|" + ct.getMaGiay() + "|"
                            + ct.getSoLuong() + "|" + ct.getDonGia() + "|"
                            + ct.getThanhTien()
                    );
                    bwCTPNH.newLine();
                }
            }

            bwPNH.close();
            bwCTPNH.close();
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public void docFile(String tenFilePNH, String tenFileCTPNH) {
        try {
            BufferedReader brPNH = new BufferedReader(new FileReader(tenFilePNH));
            BufferedReader brCTPNH = new BufferedReader(new FileReader(tenFileCTPNH));
            String line;
            dspnh = new PNH[0];
            this.n = 0;

            while ((line = brPNH.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    PNH pnh = new PNH(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                    dspnh = Arrays.copyOf(dspnh, this.n + 1);
                    dspnh[this.n++] = pnh;
                }
            }
            brPNH.close();

            while ((line = brCTPNH.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String maPNH = parts[0];
                    String maGiay = parts[1];
                    int soLuong = Integer.parseInt(parts[2]);
                    double donGia = Double.parseDouble(parts[3]);
                    double thanhTien = Double.parseDouble(parts[4]);

                    for (int i = 0; i < this.n; i++) {
                        if (dspnh[i].getMaPNH().equals(maPNH)) {
                            DSCTPNH dsctpnh = dspnh[i].getDSCTPNH();
                            CTPNH ctpnh = new CTPNH(maPNH, maGiay, soLuong, donGia, thanhTien);
                            dsctpnh.themChiTiet(ctpnh);
                            dspnh[i].setTongTien(dspnh[i].getTongTien() + thanhTien);
                            break;
                        }
                    }
                }
            }
            brCTPNH.close();
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
}
