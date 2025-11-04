import java.util.Arrays;
import java.util.Scanner;

public class DSSG {
    Giay[] dssgiay = new Giay[0];
    int n;

    public DSSG() {}

    public DSSG(int nn, Giay[] ds2) {
        n = nn;
        dssgiay = new Giay[nn];
        for (int i = 0; i < n; i++) {
            dssgiay[i] = ds2[i]; // ✅ Không thể new Giay() vì là abstract
        }
    }

    public DSSG(DSSG other) {
        n = other.n;
        dssgiay = new Giay[other.n];
        for (int i = 0; i < n; i++) {
            dssgiay[i] = other.dssgiay[i]; // ✅ Gán trực tiếp
        }
    }

    public void nhapds() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong giay: ");
        n = sc.nextInt();
        sc.nextLine();
        dssgiay = new Giay[n]; // ✅ Cấp phát lại mảng

        for (int i = 0; i < n; i++) {
            System.out.println("Chon loai giay (1.Bata  2.Sandal): ");
            int k = sc.nextInt();
            sc.nextLine();
            if (k == 1) {
                dssgiay[i] = new Bata();
            } else if (k == 2) {
                dssgiay[i] = new Sandal();
            } else {
                System.out.println("Lua chon khong hop le!");
                i--; // cho nhập lại
                continue;
            }
            dssgiay[i].nhap();
        }
    }
    public void xuat(){
    System.out.println("==========================================================================================================");
    System.out.printf("%-6s %-15s %-6s %-10s %-10s %-10s %-12s %-15s %-14s",
            "Ma", "Ten giay", "Size", "Don gia", "So luong", "Mau", "Loai de", "Thuoc tinh1", "Thuoc tinh2");
    System.out.println("==========================================================================================================");
       for(int i=0;i<n;i++){
        dssgiay[i].xuat();
       }

        System.out.println("==========================================================================================================");
}
    public void themGiay() {
        Scanner sc = new Scanner(System.in);
        System.out.println("chon so luong muon them");
        int them=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<them;i++){
        System.out.println("Chon loai giay (1.Bata  2.Sandal): ");
        int k = sc.nextInt();
        sc.nextLine();
        if(k!=1&&k!=2){
            System.out.println("Lua chon khong hop le!nhap lai:");
            i--;
            continue;
        }
        dssgiay = Arrays.copyOf(dssgiay, n + 1);
        if (k == 1) {
            dssgiay[n] = new Bata();
        } else if (k == 2) {
            dssgiay[n] = new Sandal();
        }
        dssgiay[n].nhap();
        n++;
    }
    }

    public void themGiay(Giay x) {
        dssgiay = Arrays.copyOf(dssgiay, n + 1);
        dssgiay[n] = x;
        n++;
    }

    public Giay timTheoMa1(String magiay) {
        for (int i = 0; i < n; i++) {
            if (dssgiay[i].getMaGiay().equals(magiay)) {
                return dssgiay[i];
            }
        }
        return null;
    }

    public int timTheoMa2(String magiay) {
        for (int i = 0; i < n; i++) {
            if (dssgiay[i].getMaGiay().equals(magiay)) {
                return i;
            }
        }
        return -1;
    }
    public void timKiemGiay(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap ma giay can tim:");
        String ma=sc.nextLine();
        Giay tk=timTheoMa1(ma);
        if(tk!=null){
            System.out.println("Da tim thay san pham:");
            tk.xuat();
        }
        else{
            System.out.println("khong tim thay san pham!");
        }
    }
    public void timGiayTheoMau(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap mau giay can tim:");
        String mau=sc.nextLine();
        System.out.println("==Danh sach giay co mau "+mau+"==");
       for(int i=0;i<n;i++){
        if(dssgiay[i].getMau().indexOf(mau)!=-1){
            dssgiay[i].xuat();
        }
       }
    }
    public void xoaGiay(String magiay) {
        int vt = timTheoMa2(magiay);
        if (vt == -1) {
            System.out.println("Khong tim thay san pham nay!");
            return;
        }

        for (int i = vt; i < n - 1; i++) {
            dssgiay[i] = dssgiay[i + 1];
        }

        dssgiay = Arrays.copyOf(dssgiay, n - 1);
        n--;
        System.out.println("Da xoa thanh cong!");
    }

    public void setGiay() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma giay muon sua: ");
        String magiay = sc.nextLine();
        int vt = timTheoMa2(magiay);

        if (vt == -1) {
            System.out.println("Khong tim thay san pham nay!");
            return;
        }

        Giay d = dssgiay[vt];
        System.out.println("Thong tin giay truoc khi sua:");
        d.xuat();

        int choice;
        do {
            System.out.println("\n===== MENU SUA THONG TIN =====");
            System.out.println("1. Ma giay");
            System.out.println("2. Ten giay");
            System.out.println("3. Size");
            System.out.println("4. Don gia");
            System.out.println("5. So luong");
            System.out.println("6. Mau");
            System.out.println("7. Loai de");

            if (d instanceof Bata) {
                System.out.println("8. So day");
                System.out.println("9. Trong luong");
            } else if (d instanceof Sandal) {
                System.out.println("8. Kieu quai");
                System.out.println("9. Do cao de");
            }

            System.out.println("10. Sua toan bo thong tin");
            System.out.println("0. Thoat");
            System.out.print("Lua chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ma giay moi: ");
                    d.setMaGiay(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nhap ten giay moi: ");
                    d.setTenGiay(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Nhap size moi: ");
                    d.setSize(sc.nextInt());
                    sc.nextLine();
                    break;
                case 4:
                    System.out.print("Nhap don gia moi: ");
                    d.setDonGia(sc.nextDouble());
                    sc.nextLine();
                    break;
                case 5:
                    System.out.print("Nhap so luong moi: ");
                    d.setSoLuong(sc.nextInt());
                    sc.nextLine();
                    break;
                case 6:
                    System.out.print("Nhap mau moi: ");
                    d.setMau(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Nhap loai de moi: ");
                    d.setLoaiDe(sc.nextLine());
                    break;
                case 8:
                    if (d instanceof Bata) {
                        System.out.print("Nhap so day moi: ");
                        ((Bata) d).setSoDay(sc.nextInt());
                        sc.nextLine();
                    } else if (d instanceof Sandal) {
                        System.out.print("Nhap kieu quai moi: ");
                        ((Sandal) d).setKieuQuai(sc.nextLine());
                    }
                    break;
                case 9:
                    if (d instanceof Bata) {
                        System.out.print("Nhap trong luong moi: ");
                        ((Bata) d).setTrongLuong(sc.nextDouble());
                        sc.nextLine();
                    } else if (d instanceof Sandal) {
                        System.out.print("Nhap do cao de moi: ");
                        ((Sandal) d).setDoCaoDe(sc.nextDouble());
                    }
                    break;
                case 10:
                    if (d instanceof Bata) {
                        System.out.println("==> Day la giay Bata. Nhap lai toan bo thong tin:");
                        ((Bata) d).nhap();
                    } else if (d instanceof Sandal) {
                        System.out.println("==> Day la giay Sandal. Nhap lai toan bo thong tin:");
                        ((Sandal) d).nhap();
                    }
                    break;
                case 0:
                    System.out.println("Thoat sua thong tin!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);

        System.out.println("Da cap nhat thanh cong!");
        System.out.println("Thong tin hien tai:");
        d.xuat();
    }
    public int[] thongKeTheoLoai(){
         int bata=0;
         int sandal=0;
         for(int i=0;i<n;i++){
            if(dssgiay[i] instanceof Bata){
                bata++;
            }
            else if(dssgiay[i] instanceof Sandal){
                sandal++;
            }
         }
         System.out.println("so luong san pham thuoc loai |Bata:"+bata+"|Sandal:"+sandal);
        int[] A={bata,sandal};
        return A;
    }
    public void danhSachGiamDan(){
        System.out.println("==Danh sach san pham theo gia giam dan==");
        boolean[] daIn=new boolean[n];
        for(int i=0;i<n;i++){
            double max=-1;
           int vtMax=-1;
           for(int j=0;j<n;j++){
            if(!daIn[j]&&dssgiay[j].getDonGia()>max){
                max=dssgiay[j].getDonGia();
                vtMax=j;
            }
           }
           if(vtMax!=-1){
            dssgiay[vtMax].xuat();
            daIn[vtMax]=true;
           }
        }
    }
public int[] thongKeTheoGia(){
    Scanner sc=new Scanner(System.in);
    System.out.println("nhap gia san pham:");
    double dgia =sc.nextDouble();
    sc.nextLine();
    final double EPS = 1e-6;
    int soluong=0;
    int lonhon=0;
    int nhohon=0;
    for(int i=0;i<n;i++){
        if(Math.abs(dssgiay[i].getDonGia()-dgia)<EPS){
            soluong++;
        }
        else if(Math.abs(dssgiay[i].getDonGia()-dgia)>EPS){
            if(dssgiay[i].getDonGia()>dgia){
                lonhon++;
            }
            else if(dssgiay[i].getDonGia()<dgia){
                nhohon++;
            }
        }
    }
    System.out.println("so luong sp co gia: "+dgia+" la "+soluong+"-sp | gia cao hon:"+lonhon+"-sp | gia nho hon:"+nhohon+"-sp");
    int[] C={soluong,lonhon,nhohon};
    return C;
}
public int[] thongKeTheoloaiDe(){
     Scanner sc=new Scanner(System.in);
    System.out.println("nhap loai de :");
    String loaide =sc.nextLine();
    int smau=0;
    int khac=0;
    for(int i=0;i<n;i++){
        if(dssgiay[i].getMau().indexOf(loaide)!=-1){
            smau++;
        }
        else{
            khac++;
        }
    }
    System.out.println("so luong sp loaide"+loaide+":"+smau+" sp co loai de khac:"+khac);
    int[] B= {smau,khac};
    return B;
}
public void bangThongKe(){
    Scanner sc=new Scanner(System.in);
    System.out.println("chon loai thong ke");
     int choice;
    do{
        System.out.println("1.thong ke theo loai");
        System.out.println("2.thong ke theo gia");
        System.out.println("3.thong ke theo loai de");
        System.out.println("0.thoat");
        System.out.println("nhap lua chon");
        choice=sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                 thongKeTheoLoai();
                break;
            case 2:
                thongKeTheoGia();
                break;
            case 3:
                thongKeTheoloaiDe();
                break;
            case 0:
                System.out.println("thoat!");
                break;
            default:
                System.out.println("khong hop le");
        }
    }while(choice!= 0);

}
    public void ghiFile(String tenFile) {
    try {
        java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(tenFile));

        pw.println("=== DANH SACH GIAY ===");
        for (int i = 0; i < n; i++) {
            pw.println("Thong tin san pham " + (i + 1) + ":");
            pw.println(dssgiay[i].toString());
            pw.println(); // dòng trống giữa các sản phẩm
        }

        pw.close();
        System.out.println(" Da ghi danh sach vao file: " + tenFile);
    } catch (java.io.IOException e) {
        System.out.println(" Loi ghi file: " + e.getMessage());
    }
}
public void docFile(String tenFile) {
    try {
        java.util.Scanner sc = new java.util.Scanner(new java.io.File(tenFile));
        dssgiay = new Giay[0]; // reset mảng
        n = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty() || line.startsWith("===") || line.startsWith("Thong tin")) continue;

            // Nếu dòng bắt đầu bằng BATA
            if (line.startsWith("BATA")) {
                String[] parts = line.split("\\|");
                Bata b = new Bata();
                b.setMaGiay(parts[1].split(":")[1].trim());
                b.setTenGiay(parts[2].split(":")[1].trim());
                b.setSize(Integer.parseInt(parts[3].split(":")[1].trim()));
                b.setDonGia(Double.parseDouble(parts[4].split(":")[1].trim()));
                b.setSoLuong(Integer.parseInt(parts[5].split(":")[1].trim()));
                b.setMau(parts[6].split(":")[1].trim());
                b.setLoaiDe(parts[7].split(":")[1].trim());
                b.setSoDay(Integer.parseInt(parts[8].split(":")[1].trim()));
                b.setTrongLuong(Double.parseDouble(parts[9].split(":")[1].trim()));
                themGiay(b);
            }

            // Nếu dòng bắt đầu bằng SANDAL
            else if (line.startsWith("SANDAL")) {
                String[] parts = line.split("\\|");
                Sandal s = new Sandal();
                s.setMaGiay(parts[1].split(":")[1].trim());
                s.setTenGiay(parts[2].split(":")[1].trim());
                s.setSize(Integer.parseInt(parts[3].split(":")[1].trim()));
                s.setDonGia(Double.parseDouble(parts[4].split(":")[1].trim()));
                s.setSoLuong(Integer.parseInt(parts[5].split(":")[1].trim()));
                s.setMau(parts[6].split(":")[1].trim());
                s.setLoaiDe(parts[7].split(":")[1].trim());
                s.setKieuQuai(parts[8].split(":")[1].trim());
                s.setDoCaoDe(Double.parseDouble(parts[9].split(":")[1].trim()));
                themGiay(s);
            }
        }

        sc.close();
        System.out.println(" Da doc file va nap vao danh sach thanh cong!");
    } catch (java.io.FileNotFoundException e) {
        System.out.println(" Khong tim thay file: " + tenFile);
    } catch (Exception e) {
        System.out.println(" Loi doc file: " + e.getMessage());
    }
}

        }
    