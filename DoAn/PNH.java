import java.util.Scanner;

class PNH {
    private String maPNH;
    private String maNV;
    private String maNCC;
    private String ngay;
    private double tongTien;
    DSCTPNH dsctpnh;

   public PNH() {
    maPNH = "";
    maNV = "";
    maNCC = "";
    ngay = "";
    tongTien = 0.0;
    dsctpnh = new DSCTPNH(); 
}

public PNH(String maPNH, String maNV, String maNCC, String ngay, double tongTien) {
    this.maPNH = maPNH;
    this.maNV = maNV;
    this.maNCC = maNCC;
    this.ngay = ngay;
    this.tongTien = tongTien;
    this.dsctpnh = new DSCTPNH(); 
}

public PNH(PNH pnh) {
    this.maPNH = pnh.maPNH;
    this.maNV = pnh.maNV;
    this.maNCC = pnh.maNCC;
    this.ngay = pnh.ngay;
    this.tongTien = pnh.tongTien;
    this.dsctpnh = new DSCTPNH(); 
}

    public String getMaPNH() {
        return maPNH;
    }

    public void setMaPNH(String maPNH) {
        this.maPNH = maPNH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double  tongtien) {
        this.tongTien = tongTien;
    }

    public void nhap() 
    {
        Scanner sc = new Scanner(System.in);
        dsctpnh=new DSCTPNH();
        System.out.print("Nhap ma PNH: ");
        maPNH = sc.nextLine();
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine();
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = sc.nextLine();
        System.out.print("Nhap ngay (dd/mm/yyyy): ");
        ngay = sc.nextLine();
        System.out.println("So luong chi tiet phieu nhap hang: ");
        dsctpnh.Nhap(maPNH);
        tongTien=dsctpnh.tinhTongTien();
        System.out.println("");
    }
    
    public void xuat() 
    {
        System.out.println("Ma phieu nhap hang : " + maPNH);
        System.out.println("Ma nhan vien       : " + maNV);
        System.out.println("Ma nha cung cap    : " + maNCC);
        System.out.println("Ngay nhap          : " + ngay);
        System.out.println("");
        dsctpnh.Xuat(maPNH);
        System.out.printf("Tong tien          : %.2f\n", tongTien);
        System.out.println("");
    }

    public void xuatKoChiTiet()
    {
        System.out.println("Ma phieu nhap hang : " + maPNH);
        System.out.println("Ma nhan vien       : " + maNV);
        System.out.println("Ma nha cung cap    : " + maNCC);
        System.out.println("Ngay nhap          : " + ngay);
        System.out.printf("Tong tien          : %.2f\n", tongTien);
        System.out.println("");
    }
    
    public DSCTPNH getDSCTPNH()
    {
        return dsctpnh;
    }
}
