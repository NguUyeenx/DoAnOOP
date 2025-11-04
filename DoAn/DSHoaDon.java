import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class DSHoaDon implements HD_Interface {
    private HoaDon[] dshd;
    private int n;

    public DSHoaDon(){
        this.n=0;
        this.dshd= new HoaDon[0];
    }
    public DSHoaDon(int n,HoaDon[] dshd2) {
        this.n=n;
        this.dshd=new HoaDon[n];
        for(int i=0;i<n;i++) {
            this.dshd[i] =new HoaDon(dshd2[i]);
        }
    }
    
    @Override
    public void nhap() {
        
    }

    public boolean docFile(String tenFile) {
        this.n = 0;
        this.dshd = new HoaDon[0];
        System.out.println("Đang đọc dữ liệu Hóa Đơn từ file: " + tenFile + "...");

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                HoaDon hd = HoaDon.fromFileString(line);
                if (hd != null) {
                    this.dshd = Arrays.copyOf(this.dshd, this.n + 1);
                    this.dshd[this.n] = hd;
                    this.n++;
                }
            }
            System.out.println("Đọc file Hóa Đơn hoàn tất. Tổng số " + this.n + " Hóa Đơn được tải.");
            return true;
        } catch (IOException e) {
            System.err.println("LỖI ĐỌC FILE HÓA ĐƠN: " + e.getMessage());
            System.out.println("Dữ liệu sẽ được khởi tạo rỗng.");
            return false;
        }
    }

    public boolean ghiFile(String tenFile) {
        System.out.println("Đang ghi dữ liệu Hóa Đơn ra file: " + tenFile + "...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < this.n; i++) {
                bw.write(this.dshd[i].toFileString());
                bw.newLine();
            }
            System.out.println("Ghi file Hóa Đơn hoàn tất.");
            return true;
        } catch (IOException e) {
            System.err.println("LỖI GHI FILE HÓA ĐƠN: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public void xuat() {
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống.");
            return;
        }
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                   DANH SÁCH HÓA ĐƠN");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| Mã HD  | Mã NV  | Ngày Lập     | Tổng Tiền  | Mã KH  | Mã NV Bán Hàng |");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < this.n; i++) {
            this.dshd[i].xuat();
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    public void them() {
        Scanner sc=new Scanner(System.in);
        this.dshd = Arrays.copyOf(this.dshd, this.n + 1);
        this.dshd[this.n] = new HoaDon();
        System.out.println("nhập hoá đơn cần thêm:");
        this.dshd[this.n].nhap();
        this.n++;
        System.out.println("đã thêm thành công một hoá đơn vào danh sách.");
    }
    
    @Override
    public void themKphantu() {
        Scanner sc =new Scanner(System.in);
        System.out.print("nhập số lượng hoá đơn cần thêm (k): ");
        int k = sc.nextInt();
        sc.nextLine(); 
        if (k <= 0) {
            System.out.println("số lượng hoá đơn cần thêm phải lớn hơn 0.");
            return;
        }
        this.dshd = Arrays.copyOf(this.dshd, this.n + k);
        for (int i = 0; i < k; i++) {
            System.out.println("nhập hoá đơn thứ " + (this.n + i + 1));
            this.dshd[this.n + i] = new HoaDon();
            this.dshd[this.n + i].nhap();
        }
        this.n += k;
        System.out.println("đã thêm thành công " + k + " hoá đơn vào danh sách.");
    }

    @Override
    public void xoa() {
        Scanner sc=new Scanner(System.in);
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể xóa.");
            return;
        }
        System.out.print("nhập mã hoá đơn cần xoá: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < this.n; i++) {
            if (this.dshd[i].getMaHD().equals(ma)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < this.n - 1; i++) {
                this.dshd[i] = this.dshd[i + 1];
            }
            this.dshd = Arrays.copyOf(this.dshd, this.n - 1);
            this.n--;
            System.out.println("đã xoá thành công hoá đơn có mã: " + ma);
        } else {
            System.out.println("không tìm thấy hoá đơn có mã: " + ma);
        }
    }

    @Override
    public void sua() {
        Scanner sc=new Scanner(System.in);
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể sửa.");
            return;
        }
        System.out.print("nhập mã hoá đơn cần sửa: ");
        String ma = sc.nextLine();
        HoaDon hdCanSua = null;
        for (int i = 0; i < this.n; i++) {
            if (this.dshd[i].getMaHD().equals(ma)) {
                hdCanSua = this.dshd[i];
                break;
            }
        }
        if (hdCanSua == null) {
            System.out.println("không tìm thấy hoá đơn có mã: " + ma);
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Menu Sửa Hoá Đơn Mã: " + ma + " ---");
            System.out.println("1. Sửa mã nhân viên lập");
            System.out.println("2. Sửa mã hoá đơn");
            System.out.println("3. Sửa ngày lập hoá đơn");
            System.out.println("4. Sửa tổng tiền");
            System.out.println("5. Sửa mã khách hàng");
            System.out.println("6. Sửa mã nhân viên bán hàng");
            System.out.println("0. Quay lại");
            System.out.print("nhập lựa chọn: ");
            
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("lựa chọn không hợp lệ, vui lòng nhập số.");
                sc.nextLine();
                choice = -1;
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("nhập mã nhân viên lập mới: ");
                    hdCanSua.setMaNV(sc.nextLine());
                    System.out.println("cập nhập mã nhân viên lập mới thành công");
                    break;
                case 2:
                    System.out.print("nhập mã hoá đơn mới: ");
                    hdCanSua.setMaHD(sc.nextLine());
                    System.out.println("cập nhập mã hoá đơn mới thành công");
                    break;
                case 3:
                    System.out.print("nhập ngày lập mới (dd/mm/yyyy): ");
                    hdCanSua.setNgayLapHD(sc.nextLine());
                    System.out.println("cập nhập ngày lập mới thành công");
                    break;
                case 4: 
                    System.out.print("nhập tổng tiền mới: ");
                    hdCanSua.setTongTien(sc.nextDouble()); 
                    sc.nextLine();
                    System.out.println("cập nhật tổng tiền mới thành công");
                    break;
                case 5:
                    System.out.print("nhập mã khách hàng mới: ");
                    hdCanSua.setMaKH(sc.nextLine()); 
                    System.out.println("cập nhật mã khách hàng mới thành công");
                    break;
                case 6:
                    System.out.print("nhập mã nhân viên bán hàng mới: ");
                    hdCanSua.setMaNVBanHang(sc.nextLine());
                    System.out.println("cập nhật mã nhân viên bán hàng mới hành công");
                    break;
                case 0:
                    System.out.println("đã thoát chức năng sửa, hoá đơn có mã "+ma+" đã được cập nhật thành công");   
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ,vui lòng chọn lại"); 
                    break;  
            }
        } while(choice!=0);
    }
    
    @Override
    public void timKiem() {
        Scanner sc=new Scanner(System.in);
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể tìm kiếm.");
            return;
        }

        HoaDon[] ketQua = timkiem();
        if (ketQua.length > 0) {
            System.out.println("\nThông tin Hóa Đơn tìm được:");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("| Mã HD  | Mã NV  | Ngày Lập     | Tổng Tiền  | Mã KH  | Mã NV Bán Hàng |");
            System.out.println("--------------------------------------------------------------------------------");
            for (HoaDon hdKetQua : ketQua) {
                hdKetQua.xuat();
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public HoaDon[] timkiem() {
        Scanner sc =new Scanner(System.in);
        if (this.n == 0) {
            return new HoaDon[0];
        }

        System.out.println("--- Tìm kiếm Hóa Đơn  ---");
        System.out.print("Nhập Mã Hóa Đơn (Bỏ trống để bỏ qua): ");
        String maHDCanTim = sc.nextLine().trim().toLowerCase(); 

        System.out.print("Nhập Mã Khách Hàng (Bỏ trống để bỏ qua): ");
        String maKHCanTim = sc.nextLine().trim().toLowerCase(); 

        System.out.print("Nhập Mã NV Bán Hàng (Bỏ trống để bỏ qua): ");
        String maNVBanHangCanTim = sc.nextLine().trim().toLowerCase(); 

        if (maHDCanTim.isEmpty() && maKHCanTim.isEmpty() && maNVBanHangCanTim.isEmpty()) {
            System.out.println("Vui lòng nhập ít nhất một tiêu chí tìm kiếm.");
            return new HoaDon[0];
        }

        HoaDon[] ketQuaTam = new HoaDon[this.n];
        int count = 0;

        for (int i = 0; i < this.n; i++) {
            HoaDon currentHD = this.dshd[i];
            boolean matchHD = true;
            boolean matchKH = true;
            boolean matchNVBH = true;

            if (!maHDCanTim.isEmpty()) {
                if (!currentHD.getMaHD().toLowerCase().contains(maHDCanTim)) {
                    matchHD = false;
                }
            }

            if (!maKHCanTim.isEmpty()) {
                if (!currentHD.getMaKH().toLowerCase().contains(maKHCanTim)) {
                    matchKH = false;
                }
            }

            if (!maNVBanHangCanTim.isEmpty()) {
                if (!currentHD.getMaNVBanHang().toLowerCase().contains(maNVBanHangCanTim)) {
                    matchNVBH = false;
                }
            }

            if (matchHD && matchKH && matchNVBH) {
                ketQuaTam[count] = currentHD;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Không tìm thấy Hóa Đơn nào phù hợp.");
            return new HoaDon[0];
        } else {
            return Arrays.copyOf(ketQuaTam, count);
        }
    }

    public void thongKeTongThuTheoNV() {
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể thống kê.");
            return;
        }
        String[] arr=new String[0];
        double[] totalSum=new double[0];
        int count=0;
        for(int i=0;i<this.n;i++) {
            HoaDon hd=this.dshd[i];
            String maNV=hd.getMaNVBanHang();
            double tongTienHD=hd.getTongTien();
            int index=-1;
            for(int j=0;j<count;j++) {
                if(arr[j].equals(maNV)) {
                    index=j;
                    break;
                }
            }
            if(index==-1) {
                count++;
                arr=Arrays.copyOf(arr, count);
                totalSum=Arrays.copyOf(totalSum, count);
                arr[count-1]=maNV;
                totalSum[count-1]=tongTienHD;
            }
            else {
                totalSum[index]+=tongTienHD;
            }
        }
        System.out.println("\n=============================================");
        System.out.println("📊 THỐNG KÊ TỔNG THU THEO NHÂN VIÊN");
        System.out.println("=============================================");
        System.out.printf("| %-15s | %-20s |%n", "Mã Nhân Viên", "Tổng Tiền (VNĐ)");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("| %-15s | %,.2f |%n", 
                arr[i], 
                totalSum[i]);
        }
        System.out.println("---------------------------------------------");
    }

    public void thongKeTongThuTheoKH() {
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể thống kê.");
            return;
        }
        String[] arr=new String[0];
        double[] totalSum=new double[0];
        int count=0;
        for(int i=0;i<this.n;i++) {
            HoaDon hd=this.dshd[i];
            String maKH=hd.getMaKH();
            double tongTienHD=hd.getTongTien();
            int index=-1;
            for(int j=0;j<count;j++) {
                if(arr[j].equals(maKH)) {
                    index=j;
                    break;
                }
            }
            if(index==-1) {
                count++;
                arr=Arrays.copyOf(arr, count);
                totalSum=Arrays.copyOf(totalSum, count);
                arr[count-1]=maKH;
                totalSum[count-1]=tongTienHD;
            }
            else {
                totalSum[index]+=tongTienHD;
            }
        }
        System.out.println("\n=============================================");
        System.out.println("📊 THỐNG KÊ TỔNG THU THEO KHÁCH HÀNG");
        System.out.println("=============================================");
        System.out.printf("| %-15s | %-20s |%n", "Mã Khách Hàng", "Tổng Tiền (VNĐ)");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("| %-15s | %,.2f |%n", 
                arr[i], 
                totalSum[i]);
        }
        System.out.println("---------------------------------------------");
    }

    public void thongKeTongThuTheoQuy() {
        if (this.n == 0) {
            System.out.println("Danh sách Hóa Đơn trống, không thể thống kê.");
            return;
        }
        String[] arrQuy = new String[0];
        double[] totalSum = new double[0];
        int count = 0;

        for (int i = 0; i < this.n; i++) {
            HoaDon hd = this.dshd[i];
            LocalDate date = hd.getNgayLapLocalDate();
            if (date == null) {
                System.err.println("Bỏ qua Hóa Đơn " + hd.getMaHD() + " do ngày lập không hợp lệ.");
                continue;
            }
            
            int quy = (date.getMonthValue() - 1) / 3 + 1;
            String key = date.getYear() + " - Q" + quy;
            double tongTienHD = hd.getTongTien();
            
            int index = -1;
            for (int j = 0; j < count; j++) {
                if (arrQuy[j].equals(key)) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                count++;
                arrQuy = Arrays.copyOf(arrQuy, count);
                totalSum = Arrays.copyOf(totalSum, count);
                arrQuy[count - 1] = key;
                totalSum[count - 1] = tongTienHD;
            } else {
                totalSum[index] += tongTienHD;
            }
        }
        
        System.out.println("\n=============================================");
        System.out.println("📊 THỐNG KÊ TỔNG THU THEO QUÝ (Năm - Quý)");
        System.out.println("=============================================");
        System.out.printf("| %-15s | %-20s |%n", "Năm - Quý", "Tổng Tiền (VNĐ)");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("| %-15s | %,.2f |%n", 
                arrQuy[i], 
                totalSum[i]);
        }
        System.out.println("---------------------------------------------");
    }
}