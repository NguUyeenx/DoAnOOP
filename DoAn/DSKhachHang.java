import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DSKhachHang {
    private KhachHang[] danhSach;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public DSKhachHang() {
        this.danhSach = new KhachHang[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= danhSach.length) return;
        int newCap = Math.max(danhSach.length + (danhSach.length >> 1), minCapacity);
        if (newCap <= 0) newCap = minCapacity;
        KhachHang[] newArr = new KhachHang[newCap];
        for (int i = 0; i < size; i++) newArr[i] = danhSach[i];
        danhSach = newArr;
    }

    private int indexOfByMa(String maKH) {
        if (maKH == null) return -1;
        for (int i = 0; i < size; i++) {
            String ma = danhSach[i].getMaKH();
            if (ma != null && ma.equalsIgnoreCase(maKH)) return i;
        }
        return -1;
    }

    private void removeAt(int idx) {
        if (idx < 0 || idx >= size) return;
        int numMoved = size - idx - 1;
        if (numMoved > 0) System.arraycopy(danhSach, idx + 1, danhSach, idx, numMoved);
        danhSach[--size] = null;
    }

    public boolean them(KhachHang kh) {
        if (kh == null) return false;
        String ma = kh.getMaKH();
        if (ma == null || ma.trim().isEmpty()) return false;
        if (timTheoMa(ma) != null) return false;
        ensureCapacity(size + 1);
        danhSach[size++] = kh;
        return true;
    }

    public int themKTuBanPhim(int k) {
        if (k <= 0) return 0;
        int dem = 0;
        for (int i = 0; i < k; i++) {
            System.out.println("== Nhap khach hang thu " + (i + 1) + " ==");
            KhachHang kh = new KhachHang();
            kh.nhap();
            if (them(kh)) dem++;
            else System.out.println("-> Them that bai (trung ma hoac du lieu khong hop le).");
        }
        return dem;
    }

    public boolean xoaTheoMa(String maKH) {
        int idx = indexOfByMa(maKH);
        if (idx == -1) return false;
        removeAt(idx);
        return true;
    }

    public boolean capNhat(String maKH, int field, String giaTri) {
        KhachHang kh = timTheoMa(maKH);
        if (kh == null) return false;
        switch (field) {
            case 1: kh.setHo(giaTri); return true;
            case 2: kh.setTen(giaTri); return true;
            case 3: kh.setDienThoai(giaTri); return true;
            default: return false;
        }
    }

    public KhachHang timTheoMa(String maKH) {
        if (maKH == null) return null;
        for (int i = 0; i < size; i++) {
            String ma = danhSach[i].getMaKH();
            if (ma != null && ma.equalsIgnoreCase(maKH)) return danhSach[i];
        }
        return null;
    }

    public KhachHang[] timGanDung(String tuKhoa) {
        if (tuKhoa == null) return new KhachHang[0];
        String key = tuKhoa.trim().toLowerCase();
        if (key.isEmpty()) return new KhachHang[0];

        KhachHang[] tmp = new KhachHang[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            KhachHang kh = danhSach[i];
            String s1 = safe(kh.getMaKH()).toLowerCase();
            String s2 = safe(kh.getHo()).toLowerCase();
            String s3 = safe(kh.getTen()).toLowerCase();
            String s4 = safe(kh.getDienThoai()).toLowerCase();
            if (s1.contains(key) || s2.contains(key) || s3.contains(key) || s4.contains(key)) {
                tmp[count++] = kh;
            }
        }
        KhachHang[] kq = new KhachHang[count];
        for (int i = 0; i < count; i++) kq[i] = tmp[i];
        return kq;
    }

    public int[] thongKe(int mode, String[] keys) {
        if (keys == null) return new int[0];
        int[] counts = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i] == null ? "" : keys[i].trim();
            int c = 0;
            for (int j = 0; j < size; j++) {
                KhachHang kh = danhSach[j];
                switch (mode) {
                    case 1: if (kh.getHo() != null && kh.getHo().equalsIgnoreCase(key)) c++; break;
                    case 2: if (kh.getTen() != null && kh.getTen().equalsIgnoreCase(key)) c++; break;
                    case 3: if (kh.getDienThoai() != null && kh.getDienThoai().startsWith(key)) c++; break;
                    default: break;
                }
            }
            counts[i] = c;
        }
        return counts;
    }

    public void xuatDanhSach() {
        for (int i = 0; i < size; i++) danhSach[i].xuat();
    }

    public int soLuong() { return size; }

    public int docFileTxt(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        int dem = 0;
        size = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            KhachHang kh = KhachHang.parse(line);
            if (kh != null && them(kh)) dem++;
        }
        sc.close();
        return dem;
    }

    public int ghiFileTxt(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path);
        for (int i = 0; i < size; i++) pw.println(danhSach[i].toDataLine());
        int n = size;
        pw.flush();
        pw.close();
        return n;
    }

    private static String safe(String s) { return s == null ? "" : s; }
}