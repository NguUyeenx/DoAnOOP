import java.util.Scanner;
public class CTHD {
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String maGiay;
    private String maHD;

    public CTHD() {
        this.soLuong=0;
        this.donGia=0.0;
        this.thanhTien=0.0;
        this.maGiay="";
        this.maHD="";
    }
    public CTHD(int soLuong,double donGia,double thanhTien,String maGiay,String maHD){
        this.soLuong=soLuong;
        this.donGia=donGia;
        this.thanhTien=thanhTien;
        this.maGiay=maGiay;
        this.maHD=maHD;
    }
    public CTHD(CTHD cthd) {
        this.soLuong=cthd.soLuong;
        this.donGia=cthd.donGia;
        this.thanhTien=cthd.thanhTien;
        this.maGiay=cthd.maGiay;
        this.maHD=cthd.maHD;
    }
    public void tinhThanhTien() {
        this.thanhTien=this.soLuong*this.donGia;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.println("---nhập thông tin chi tiết hoá đơn---");
        System.out.print("nhập mã hoá đơn: ");
        this.maHD = sc.nextLine();
        System.out.print("nhập mã giày: ");
        this.maGiay = sc.nextLine();
        System.out.print("nhập số lượng: ");
        this.soLuong=sc.nextInt();
        sc.nextLine();
        System.out.print("nhập đơn giá: ");
        this.donGia=sc.nextDouble();
        sc.nextLine();
        tinhThanhTien();
        System.out.println("thành tiền: " + String.format("%.2f", this.thanhTien));
    }
    public void xuat() {
        System.out.printf("| %-8s | %-8s | %-8d | %-10.2f | %-10.2f |%n", 
            maHD, maGiay, soLuong, donGia, thanhTien);
    }

    public String toFileString() {
        return String.format("%s,%s,%d,%.2f", 
            this.maHD, this.maGiay, this.soLuong, this.donGia);
    }

    public static CTHD fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            System.err.println("Lỗi định dạng dòng Chi Tiết Hóa Đơn: " + line);
            return null;
        }
        try {
            String maHD = parts[0].trim();
            String maGiay = parts[1].trim();
            int soLuong = Integer.parseInt(parts[2].trim());
            double donGia = Double.parseDouble(parts[3].trim());
            
            CTHD cthd = new CTHD(soLuong, donGia, 0.0, maGiay, maHD);
            cthd.tinhThanhTien();
            return cthd;
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi số trong dòng Chi Tiết Hóa Đơn: " + line);
            return null;
        }
    }
    
    public int getSoLuong() { 
        return soLuong; 
    }
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong; tinhThanhTien(); 
    }
    public double getDonGia() { 
        return donGia; 
    }
    public void setDonGia(double donGia) { 
        this.donGia = donGia; tinhThanhTien(); 
    }
    public double getThanhTien() { 
        return thanhTien; 
    }
    public void setThanhTien(double thanhTien) { 
        this.thanhTien = thanhTien; 
    }
    public String getMaGiay() { 
        return maGiay; 
    }
    public void setMaGiay(String maGiay) { 
        this.maGiay = maGiay; 
    }
    public String getMaHD() { 
        return maHD; 
    }
    public void setMaHD(String maHD) { 
        this.maHD = maHD; 
    }
}