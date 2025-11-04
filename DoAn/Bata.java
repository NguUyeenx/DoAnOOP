import java.util.Scanner;

public class Bata extends Giay {
    private int soDay;
    private double trongLuong;

    public Bata() {
        super();
        soDay = 0;
        trongLuong = 0;
    }

    public Bata(String maGiay, String tenGiay, int size, double donGia, int soLuong, String mau, String loaiDe, int soDay, double trongLuong) {
        super(maGiay, tenGiay, size, donGia, soLuong, mau, loaiDe);
        this.soDay = soDay;
        this.trongLuong = trongLuong;
    }

    public Bata(Bata d) {
        super(d);
        this.soDay = d.soDay;
        this.trongLuong = d.trongLuong;
    }
    
    @Override
    public void nhap() {
        Scanner sc=new Scanner(System.in);
        super.nhap();
        System.out.print("Nhap so day: ");
        soDay = sc.nextInt();
        System.out.print("Nhap trong luong: ");
        trongLuong = sc.nextDouble();
    }
    @Override
     public  void thuocTinh(){
        System.out.printf(" Soday: %-10s TL: %.1f\n", soDay, trongLuong);
     }

    @Override
    public void xuat() {
        super.xuat();
        thuocTinh();
    }

    public int getSoDay() { return soDay; }
    public void setSoDay(int soDay) { this.soDay = soDay; }

    public double getTrongLuong() { return trongLuong; }
    public void setTrongLuong(double trongLuong) { this.trongLuong = trongLuong; }
@Override
public String toString() {
    return "BATA | Ma: " + getMaGiay() +
           " | Ten: " + getTenGiay() +
           " | Size: " + getSize() +
           " | Don gia: " + getDonGia() +
           " | SL: " + getSoLuong() +
           " | Mau: " + getMau() +
           " | Loai de: " + getLoaiDe() +
           " | So day: " + soDay +
           " | Trong luong: " + trongLuong;
}

}
