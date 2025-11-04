public class KhachHang implements IConsoleIO, ITextSerializable {
    private String maKH;
    private String ho;
    private String ten;
    private String dienThoai;

    public KhachHang() {}

    public KhachHang(String maKH, String ho, String ten, String dienThoai) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.dienThoai = dienThoai;
    }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }

    @Override
    public void nhap() {
        this.maKH = Input.readNonEmpty("Nhap ma KH: ");
        this.ho = Input.readNonEmpty("Nhap ho: ");
        this.ten = Input.readNonEmpty("Nhap ten: ");
        this.dienThoai = Input.readNonEmpty("Nhap dien thoai: ");
    }

    @Override
    public void xuat() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "KhachHang{maKH='" + safe(maKH) + "', ho='" + safe(ho) + "', ten='" + safe(ten)
                + "', dienThoai='" + safe(dienThoai) + "'}";
    }

    @Override
    public String toDataLine() {
        return safe(maKH) + "|" + safe(ho) + "|" + safe(ten) + "|" + safe(dienThoai);
    }

    public static KhachHang parse(String line) {
        if (line == null) return null;
        String[] p = line.split("\\|", -1);
        if (p.length != 4) return null;
        String ma = p[0].trim();
        String ho = p[1].trim();
        String ten = p[2].trim();
        String dt = p[3].trim();
        return new KhachHang(ma, ho, ten, dt);
    }

    private static String safe(String s) { return s == null ? "" : s; }
}