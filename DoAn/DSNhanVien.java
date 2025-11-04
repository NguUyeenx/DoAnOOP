import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DSNhanVien {
    private NhanVien[] danhSach;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public DSNhanVien() {
        this.danhSach = new NhanVien[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= danhSach.length) return;
        int newCap = Math.max(danhSach.length + (danhSach.length >> 1), minCapacity);
        if (newCap <= 0) newCap = minCapacity;
        NhanVien[] newArr = new NhanVien[newCap];
        for (int i = 0; i < size; i++) newArr[i] = danhSach[i];
        danhSach = newArr;
    }

    private int indexOfByMa(String maNV) {
        if (maNV == null) return -1;
        for (int i = 0; i < size; i++) {
            String ma = danhSach[i].getMaNV();
            if (ma != null && ma.equalsIgnoreCase(maNV)) return i;
        }
        return -1;
    }

    private void removeAt(int idx) {
        if (idx < 0 || idx >= size) return;
        int numMoved = size - idx - 1;
        if (numMoved > 0) System.arraycopy(danhSach, idx + 1, danhSach, idx, numMoved);
        danhSach[--size] = null;
    }

    public boolean them(NhanVien nv) {
        if (nv == null) return false;
        String ma = nv.getMaNV();
        if (ma == null || ma.trim().isEmpty()) return false;
        if (timTheoMa(ma) != null) return false;
        ensureCapacity(size + 1);
        danhSach[size++] = nv;
        return true;
    }

    public int themKTuBanPhim(int k) {
        if (k <= 0) return 0;
        int dem = 0;
        for (int i = 0; i < k; i++) {
            System.out.println("== Nhap nhan vien thu " + (i + 1) + " ==");
            NhanVien nv = new NhanVien();
            nv.nhap();
            if (them(nv)) dem++;
            else System.out.println("-> Them that bai (trung ma hoac du lieu khong hop le).");
        }
        return dem;
    }

    public boolean xoaTheoMa(String maNV) {
        int idx = indexOfByMa(maNV);
        if (idx == -1) return false;
        removeAt(idx);
        return true;
    }

    public boolean capNhat(String maNV, int field, String giaTri) {
        NhanVien nv = timTheoMa(maNV);
        if (nv == null) return false;
        switch (field) {
            case 1: nv.setHo(giaTri); return true;
            case 2: nv.setTen(giaTri); return true;
            case 3: nv.setDienThoai(giaTri); return true;
            case 4:
                double v = parseNonNegativeDouble(giaTri);
                if (v < 0) return false;
                nv.setLuongThang(v);
                return true;
            default: return false;
        }
    }

    public NhanVien timTheoMa(String maNV) {
        if (maNV == null) return null;
        for (int i = 0; i < size; i++) {
            String ma = danhSach[i].getMaNV();
            if (ma != null && ma.equalsIgnoreCase(maNV)) return danhSach[i];
        }
        return null;
    }

    public NhanVien[] timGanDung(String tuKhoa) {
        if (tuKhoa == null) return new NhanVien[0];
        String key = tuKhoa.trim().toLowerCase();
        if (key.isEmpty()) return new NhanVien[0];

        NhanVien[] tmp = new NhanVien[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            NhanVien nv = danhSach[i];
            String s1 = safe(nv.getMaNV()).toLowerCase();
            String s2 = safe(nv.getHo()).toLowerCase();
            String s3 = safe(nv.getTen()).toLowerCase();
            String s4 = safe(nv.getDienThoai()).toLowerCase();
            if (s1.contains(key) || s2.contains(key) || s3.contains(key) || s4.contains(key)) {
                tmp[count++] = nv;
            }
        }
        NhanVien[] kq = new NhanVien[count];
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
                NhanVien nv = danhSach[j];
                switch (mode) {
                    case 1: if (nv.getHo() != null && nv.getHo().equalsIgnoreCase(key)) c++; break;
                    case 2: if (nv.getTen() != null && nv.getTen().equalsIgnoreCase(key)) c++; break;
                    case 3: if (nv.getDienThoai() != null && nv.getDienThoai().startsWith(key)) c++; break;
                    case 4:
                        double v = parseNonNegativeDouble(key);
                        if (v >= 0 && nv.getLuongThang() == v) c++;
                        break;
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
            NhanVien nv = NhanVien.parse(line);
            if (nv != null && them(nv)) dem++;
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

    private static double parseNonNegativeDouble(String s) {
        if (s == null) return -1;
        try {
            double v = Double.parseDouble(s.trim());
            return v >= 0 ? v : -1;
        } catch (Exception e) {
            return -1;
        }
    }
}