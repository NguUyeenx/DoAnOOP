import java.util.Scanner;

class NCC {
    private String maNCC;
    private String ten;
    private String dienThoai;
    private String diaChi;

    public NCC() {
        maNCC = "";
        ten = "";
        dienThoai = "";
        diaChi = "";
    }

    public NCC(String maNCC, String ten, String dienThoai, String diaChi) {
        this.maNCC = maNCC;
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }

    public NCC(NCC ncc) {
        this.maNCC = ncc.maNCC;
        this.ten = ncc.ten;
        this.dienThoai = ncc.dienThoai;
        this.diaChi = ncc.diaChi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void nhap() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NCC: ");
        maNCC = sc.nextLine();
        System.out.print("Nhap ten NCC: ");
        ten = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        dienThoai = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();
        System.out.println("");
    }

    public void xuat() 
    {
        System.out.println("Ma nha cung cap  : " + maNCC);
        System.out.println("Ten nha cung cap : " + ten);
        System.out.println("So dien thoai    : " + dienThoai);
        System.out.println("Dia chi          : " + diaChi);
        System.out.println("");
    }
}
