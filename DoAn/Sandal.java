import java.util.Scanner;

public class Sandal extends Giay {
    private String kieuQuai;
    private double doCaoDe;

    public Sandal() {
        super();
        kieuQuai = "";
        doCaoDe = 0;
    }

    public Sandal(String maGiay, String tenGiay, int size, double donGia, int soLuong, String mau, String loaiDe, String kieuQuai, double doCaoDe) {
        super(maGiay, tenGiay, size, donGia, soLuong, mau, loaiDe);
        this.kieuQuai = kieuQuai;
        this.doCaoDe = doCaoDe;
    }

    public Sandal(Sandal d) {
        super(d);
        this.kieuQuai = d.kieuQuai;
        this.doCaoDe = d.doCaoDe;
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.print("Nhap kieu quai: ");
        kieuQuai = sc.nextLine();
        System.out.print("Nhap do cao de: ");
        doCaoDe = sc.nextDouble();
    }
    @Override 
     public void thuocTinh(){
         System.out.printf(" Kquai: %-10s Dcde: %.1f\n", kieuQuai, doCaoDe);
     }
    @Override
    public void xuat() {
        super.xuat(); // gọi xuất phần chung từ Giay
        thuocTinh();
    }
    public String getKieuQuai() { return kieuQuai; }
    public void setKieuQuai(String kieuQuai) { this.kieuQuai = kieuQuai; }

    public double getDoCaoDe() { return doCaoDe; }
    public void setDoCaoDe(double doCaoDe) { this.doCaoDe = doCaoDe; }
    @Override
public String toString() {
    return "SANDAL | Ma: " + getMaGiay() +
           " | Ten: " + getTenGiay() +
           " | Size: " + getSize() +
           " | Don gia: " + getDonGia() +
           " | SL: " + getSoLuong() +
           " | Mau: " + getMau() +
           " | Loai de: " + getLoaiDe() +
           " | Kieu quai: " + kieuQuai +
           " | Do cao de: " + doCaoDe;
}
}
