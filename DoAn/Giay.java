import java.util.Scanner;

public abstract  class Giay {
   private String maGiay;
   private String tenGiay;
   private int size;
   private double donGia;
   private int soLuong;
   private String mau;
   private String loaiDe;
   public Giay(){
    maGiay="";
    tenGiay="";
    size=0;
    donGia=0;
    soLuong=0;
    mau="";
    loaiDe="";
   }
   public Giay(String maGiay,String tenGiay,int size,double donGia,int soLuong,String mau,String loaiDe){
    this.maGiay=maGiay;
    this.tenGiay=tenGiay;
    this.size=size;
    this.donGia=donGia;
    this.soLuong=soLuong;
    this.mau=mau;
    this.loaiDe=loaiDe;
   }
   public Giay(Giay d){
    maGiay=d.maGiay;
    tenGiay=d.tenGiay;
    size=d.size;
    donGia=d.donGia;
    soLuong=d.soLuong;
    mau=d.mau;
    loaiDe=d.loaiDe;
   }
   public   void nhap(){
       Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma giay: ");
        setMaGiay(sc.nextLine());
        System.out.print("Nhap ten giay: ");
        setTenGiay(sc.nextLine());
        System.out.print("Nhap size: ");
        setSize(sc.nextInt());
        System.out.println("Nhap don gia:");
        setDonGia(sc.nextDouble());
        System.out.print("Nhap so luong: ");
        setSoLuong(sc.nextInt());
        sc.nextLine();
        System.out.print("Nhap mau: ");
        setMau(sc.nextLine());
        System.out.print("Nhap loai de: ");
        setLoaiDe(sc.nextLine());
   };
   public   void xuat(){
        System.out.printf("%-6s %-15s %-6d %-10.0f %-10d %-10s %-12s",
                        getMaGiay(), getTenGiay(), getSize(), getDonGia(),
                        getSoLuong(), getMau(), getLoaiDe());  
   }
   public String getMaGiay(){
      return maGiay;
   }
   public String getTenGiay(){
      return tenGiay;
   }
   public int getSize(){
      return size;
   }
   public double getDonGia(){
      return donGia;
   }
   public int getSoLuong(){
      return soLuong;
   }
   public String getMau(){
      return mau;
   }
   public String getLoaiDe(){
      return loaiDe;
   }
   public void setMaGiay(String maGiay){
      this.maGiay=maGiay;
   }
   public void setTenGiay(String tenGiay){
      this.tenGiay=tenGiay;
   }
   public void setSize(int size){
      this.size=size;
   }
   public void setDonGia(double donGia){
      this.donGia=donGia;
   }
   public void setSoLuong(int soLuong){
      this.soLuong=soLuong;
   }
   public void setMau(String mau){
      this.mau=mau;
   }
   public void setLoaiDe(String loaiDe){
      this.loaiDe=loaiDe;
   }
    public void capNhatSoLuong(int soLuongNhapThem) {
        this.soLuong += soLuongNhapThem;
    }
    public abstract void thuocTinh();
}
