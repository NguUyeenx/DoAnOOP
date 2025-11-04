import java.util.Scanner;
class CTPNH
{
    private String maPNH;
    private String maGiay;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    
    public CTPNH()
    {
        maPNH="";
        maGiay="";
        soLuong=0;
        donGia=0;
        thanhTien=0;
    }
    public CTPNH(String maPNH, String maGiay, int soLuong,double donGia,double thanhTien)
    {
        this.maPNH=maPNH;
        this.maGiay=maGiay;
        this.soLuong=soLuong;
        this.donGia=donGia;
        this.thanhTien=thanhTien;
    }
    public CTPNH(CTPNH ctpnh)
    {
        this.maPNH=ctpnh.maPNH;
        this.maGiay=ctpnh.maGiay;
        this.soLuong=ctpnh.soLuong;
        this.donGia=ctpnh.donGia;
        this.thanhTien=ctpnh.thanhTien;
    }
    public void setMaPNH(String maPNH)
    {
        this.maPNH=maPNH;
    }
    public void setMaGiay(String maGiay)
    {
        this.maGiay=maGiay;
    }
    public void setSoLuong(int soLuong)
    {
        this.soLuong=soLuong;
    }
    public void setDonGia(double donGia)
    {
        this.donGia=donGia;
    }
    public void setThanhTien(double thanhTien)
    {
        this.thanhTien=thanhTien;
    }
    public String getMaPNH()
    {
        return maPNH;
    }
    public String getMaGiay()
    {
        return maGiay;
    }
    public int getSoLuong()
    {
        return soLuong;
    }
    public double getDonGia()
    {
        return donGia;
    }
    public double getThanhTien()
    {
        return thanhTien;
    }
    public void Nhap(String maPNH)
    {
        Scanner sc = new Scanner(System.in);
        this.maPNH=maPNH;  // gán mã phiếu từ đối tượng PNH

        System.out.println("Nhap ma giay: ");
        maGiay=sc.nextLine();
        System.out.println("So luong: ");
        soLuong=sc.nextInt();
        System.out.println("Nhap don gia: ");
        donGia=sc.nextDouble();
        thanhTien=soLuong*donGia;
        System.out.println("");
    }
    public void Xuat(String maPNH)
    {
       System.out.println("Ma phieu nhap hang  : "+maPNH); 
       System.out.println("Ma giay             : "+maGiay);
       System.out.println("So luong            : "+soLuong);
       System.out.println("Don gia             : "+donGia);
       System.out.println("Thanh tien          :"+thanhTien);
       System.out.println("");
    }
}