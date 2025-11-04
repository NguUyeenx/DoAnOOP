
import java.util.Arrays;
import java.util.Scanner;

interface CongCuCTPNH {

    void Nhap(String maPNH);

    void Xuat(String maPNH);
}

class DSCTPNH implements CongCuCTPNH {

    private CTPNH[] dsctpnh;
    private int n;

    public DSCTPNH() {
        n = 0;
        dsctpnh = new CTPNH[0];
    }

    public DSCTPNH(int n, CTPNH[] dsctpnh2) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            dsctpnh[i] = dsctpnh2[i];
        }
    }

    @Override
    public void Nhap(String maPNH) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong chi tiet phieu nhap hang: ");
        n = sc.nextInt();
        dsctpnh = new CTPNH[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap chi tiet thu " + (i + 1) + " :");
            dsctpnh[i] = new CTPNH();
            dsctpnh[i].Nhap(maPNH);
        }
    }

    @Override
    public void Xuat(String maPNH) {
        for (int i = 0; i < n; i++) {
            dsctpnh[i].Xuat(maPNH);
        }
    }

    public void themChiTiet(CTPNH ct) {
        dsctpnh = Arrays.copyOf(dsctpnh, n + 1);
        dsctpnh[n] = ct;
        n++;
    }

    public void xoaChiTietTheoSTT(int stt) {
        if (stt < 1 || stt > n) {
            System.out.println("So thu tu khong hop le!");
            return;
        }

        for (int j = stt - 1; j < n - 1; j++) {
            dsctpnh[j] = dsctpnh[j + 1];
        }

        n--;
        dsctpnh = Arrays.copyOf(dsctpnh, n);
        System.out.println("Da xoa chi tiet thu " + stt);
    }

    public void suaChiTiet(String maPNH) {
        Scanner sc = new Scanner(System.in);
        boolean found = false;

        System.out.println("\n=== DANH SACH CHI TIET CUA PHIEU " + maPNH + " ===");
        for (int i = 0; i < n; i++) {
            if (dsctpnh[i].getMaPNH().equalsIgnoreCase(maPNH)) {
                System.out.println("STT " + (i + 1) + ":");
                dsctpnh[i].Xuat(maPNH);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong co chi tiet nao thuoc phieu " + maPNH + " !");
            return;
        }

        System.out.print("Nhap STT chi tiet muon sua: ");
        int stt = sc.nextInt();
        sc.nextLine();

        if (stt < 1 || stt > n || !dsctpnh[stt - 1].getMaPNH().equalsIgnoreCase(maPNH)) {
            System.out.println("STT khong hop le hoac chi tiet khong thuoc phieu " + maPNH);
            return;
        }

        CTPNH ct = dsctpnh[stt - 1];
        int choice;
        do {
            System.out.println("\n=== SUA CHI TIET THU " + stt + " ===");
            ct.Xuat(maPNH);
            System.out.println("1. Sua ma giay");
            System.out.println("2. Sua so luong");
            System.out.println("3. Sua don gia");
            System.out.println("0. Luu va thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhap ma giay moi: ");
                    String newMa = sc.nextLine();
                    ct.setMaGiay(newMa);
                    System.out.println("Da cap nhat ma giay!");
                }
                case 2 -> {
                    System.out.print("Nhap so luong moi: ");
                    int newSL = sc.nextInt();
                    ct.setSoLuong(newSL);
                    ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
                    System.out.println("Da cap nhat so luong!");
                }
                case 3 -> {
                    System.out.print("Nhap don gia moi: ");
                    double newDG = sc.nextDouble();
                    ct.setDonGia(newDG);
                    ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
                    System.out.println("Da cap nhat don gia!");
                }
                case 0 ->
                    System.out.println("Da luu thay doi va thoat!");
                default ->
                    System.out.println("Lua chon khong hop le!");
            }

        } while (choice != 0);
    }

    public CTPNH timChiTietTheoSTT(int stt) {
        if (stt < 1 || stt > n) {
            System.out.println("So thu tu khong hop le!");
            return null;
        }
        return dsctpnh[stt - 1];
    }

    public CTPNH[] TimKiemMaGiay() {
        int count = 0;
        n = dsctpnh.length;
        Scanner sc = new Scanner(System.in);
        String magiaycantim;
        System.out.println("Nhap ma giay can tim: ");
        magiaycantim = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dsctpnh[i].getMaGiay().toLowerCase().indexOf(magiaycantim.toLowerCase()) != -1) {
                count++;
            }
        }
        if (count == 0) {
            System.err.println("Khong tim thay sinh vien");
            return null;
        }
        CTPNH[] danhsachgiay = new CTPNH[count];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (dsctpnh[i].getMaGiay().equals(magiaycantim)) {
                danhsachgiay[index++] = dsctpnh[i];
            }
        }
        return danhsachgiay;
    }

    public double tinhTongTien() {
        double tong = 0;
        for (int i = 0; i < n; i++) {
            tong += dsctpnh[i].getThanhTien();
        }
        return tong;
    }

    public int getSoLuongChiTiet() {
        return n;
    }

    public CTPNH getChiTiet(int i) {
        if (i >= 0 && i < n) {
            return dsctpnh[i];
        }
        return null;
    }
}
