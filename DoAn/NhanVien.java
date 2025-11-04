public class NhanVien implements IConsoleIO, ITextSerializable {
    private String maNV;
    private String ho;
    private String ten;
    private String dienThoai;
    private double luongThang;

    public NhanVien() {}

    public NhanVien(String maNV, String ho, String ten, String dienThoai, double luongThang) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.luongThang = luongThang;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }
    public double getLuongThang() { return luongThang; }
    public void setLuongThang(double luongThang) { this.luongThang = luongThang; }

    @Override
    public void nhap() {
        this.maNV = Input.readNonEmpty("Nhap ma NV: ");
        this.ho = Input.readNonEmpty("Nhap ho: ");
        this.ten = Input.readNonEmpty("Nhap ten: ");
        this.dienThoai = Input.readNonEmpty("Nhap dien thoai: ");
        this.luongThang = Input.readNonNegativeDouble("Nhap luong thang: ");
    }

    @Override
    public void xuat() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "NhanVien{maNV='" + safe(maNV) + "', ho='" + safe(ho) + "', ten='" + safe(ten)
                + "', dienThoai='" + safe(dienThoai) + "', luongThang=" + formatVND(luongThang) + "}";
    }

    @Override
    public String toDataLine() {
        return safe(maNV) + "|" + safe(ho) + "|" + safe(ten) + "|" + safe(dienThoai) + "|" + luongThang;
    }

    public static NhanVien parse(String line) {
        if (line == null) return null;
        String[] p = line.split("\\|", -1);
        if (p.length != 5) return null;
        String ma = p[0].trim();
        String ho = p[1].trim();
        String ten = p[2].trim();
        String dt = p[3].trim();
        double luong;
        try {
            luong = Double.parseDouble(p[4].trim());
        } catch (Exception e) {
            return null;
        }
        return new NhanVien(ma, ho, ten, dt, luong);
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }

    private static String formatVND(double v) {
        String s = String.format("%,.0f", v);
        s = s.replace(',', '.');
        return s + "vnd";
    }
}