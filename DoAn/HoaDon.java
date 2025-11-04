import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String ngayLapHD;
    private double tongTien;
    private String maKH;
    private String maNVBanHang;

    public HoaDon(){
        this.maHD="";
        this.maNV="";
        this.ngayLapHD="";
        this.tongTien=0.0;
        this.maKH="";
        this.maNVBanHang="";
    }
    public HoaDon(String maHD,String maNV,String ngayLapHD,double tongTien,String maKH,String maNVBanHang) {
        this.maHD=maHD;
        this.maNV=maNV;
        this.ngayLapHD=ngayLapHD;
        this.tongTien=tongTien;
        this.maKH=maKH;
        this.maNVBanHang=maNVBanHang;
    }
    public HoaDon(HoaDon hd) {
        this.maHD=hd.maHD;
        this.maNV=hd.maNV;
        this.ngayLapHD=hd.ngayLapHD;
        this.tongTien=hd.tongTien;
        this.maKH=hd.maKH;
        this.maNVBanHang=hd.maNVBanHang;
    }
    public void nhap() {
        Scanner sc= new Scanner(System.in);
        System.out.println("---nhập thông tin hoá đơn---");
        System.out.print("nhập mã hoá đơn: ");
        this.maHD = sc.nextLine();
        System.out.print("nhập mã nhân viên lập hoá đơn: ");
        this.maNV = sc.nextLine();
        System.out.print("nhập ngày lập hoá đơn(dd/mm/yyyy): ");
        this.ngayLapHD = sc.nextLine();

        System.out.print("nhập tổng tiền: ");
        this.tongTien = sc.nextDouble();
        sc.nextLine();
        System.out.print("nhập mã khách hàng: ");
        this.maKH = sc.nextLine();

        System.out.print("nhập mã nhân viên bán hàng: ");
        this.maNVBanHang = sc.nextLine();
    }
    public void xuat() {
        System.out.printf("| %-8s | %-8s | %-12s | %-10.2f | %-8s | %-16s |%n",
                maHD, maNV, ngayLapHD, tongTien, maKH, maNVBanHang);
    }
    
    public String toFileString() {
        return String.format("%s,%s,%s,%.2f,%s,%s", 
            this.maHD, this.maNV, this.ngayLapHD, this.tongTien, this.maKH, this.maNVBanHang);
    }

    public static HoaDon fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            System.err.println("Lỗi định dạng dòng Hóa Đơn: " + line);
            return null;
        }
        try {
            String maHD = parts[0].trim();
            String maNV = parts[1].trim();
            String ngayLapHD = parts[2].trim();
            double tongTien = Double.parseDouble(parts[3].trim());
            String maKH = parts[4].trim();
            String maNVBanHang = parts[5].trim();
            
            return new HoaDon(maHD, maNV, ngayLapHD, tongTien, maKH, maNVBanHang);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi số trong dòng Hóa Đơn: " + line);
            return null;
        }
    }

    public LocalDate getNgayLapLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(this.ngayLapHD, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Lỗi định dạng ngày '" + this.ngayLapHD + "': " + e.getMessage());
            return null;
        }
    }

    public String getMaHD() { 
        return maHD; 
    }
    public void setMaHD(String maHD) { 
        this.maHD = maHD; 
    }
    public String getMaNV() {
        return maNV; 
    }
    public void setMaNV(String maNV) { 
        this.maNV = maNV; 
    }
    public String getNgayLapHD() { 
        return ngayLapHD; 
    }
    public void setNgayLapHD(String ngayLapHD) { 
        this.ngayLapHD = ngayLapHD; 
    }
    public double getTongTien() { 
        return tongTien; 
    }
    public void setTongTien(double tongTien) { 
        this.tongTien = tongTien; 
    }
    public String getMaKH() { 
        return maKH; 
    }
    public void setMaKH(String maKH) { 
        this.maKH = maKH; 
    }
    public String getMaNVBanHang() { 
        return maNVBanHang; 
    }
    public void setMaNVBanHang(String maNVBanHang) { 
        this.maNVBanHang = maNVBanHang; 
    }
}