import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSCTHD implements HD_Interface {
    private CTHD[] dscthd;
    private int n;
    
    public DSCTHD() {
        this.n=0;
        this.dscthd=new CTHD[0];
    }
    public DSCTHD(int n,CTHD[] dscthd2) {
        this.n=n;
        this.dscthd=new CTHD[n];
        for(int i=0;i<n;i++) {
            this.dscthd[i]=new CTHD(dscthd2[i]);
        }
    }
    
    @Override
    public void nhap() {
        // Chức năng nhập toàn bộ danh sách thủ công đã bị loại bỏ
    }

    public boolean docFile(String tenFile) {
        this.n = 0;
        this.dscthd = new CTHD[0];
        System.out.println("Đang đọc dữ liệu Chi Tiết Hóa Đơn từ file: " + tenFile + "...");

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                CTHD cthd = CTHD.fromFileString(line);
                if (cthd != null) {
                    this.dscthd = Arrays.copyOf(this.dscthd, this.n + 1);
                    this.dscthd[this.n] = cthd;
                    this.n++;
                }
            }
            System.out.println("Đọc file Chi Tiết Hóa Đơn hoàn tất. Tổng số " + this.n + " Chi Tiết được tải.");
            return true;
        } catch (IOException e) {
            System.err.println("LỖI ĐỌC FILE CHI TIẾT HÓA ĐƠN: " + e.getMessage());
            System.out.println("Dữ liệu sẽ được khởi tạo rỗng.");
            return false;
        }
    }

    public boolean ghiFile(String tenFile) {
        System.out.println("Đang ghi dữ liệu Chi Tiết Hóa Đơn ra file: " + tenFile + "...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < this.n; i++) {
                bw.write(this.dscthd[i].toFileString());
                bw.newLine();
            }
            System.out.println("Ghi file Chi Tiết Hóa Đơn hoàn tất.");
            return true;
        } catch (IOException e) {
            System.err.println("LỖI GHI FILE CHI TIẾT HÓA ĐƠN: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public void them() {
        Scanner sc=new Scanner(System.in);
        this.dscthd=Arrays.copyOf(this.dscthd,this.n+1);
        this.dscthd[this.n]=new CTHD();
        System.out.println("nhập chi tiết hoá đơn cần thêm:");
        this.dscthd[this.n].nhap();
        this.n++;
        System.out.println("đã thêm thành công một chi tiết hoá đơn vào danh sách.");
    }
    
    @Override
    public void themKphantu() {
        Scanner sc =new Scanner(System.in);
        System.out.print("nhập số lượng chi tiết hóa đơn cần thêm (k): ");
        int k = sc.nextInt();
        sc.nextLine(); 
        if (k <= 0) {
            System.out.println("số lượng chi tiết hóa đơn cần thêm phải lớn hơn 0.");
            return;
        }
        this.dscthd = Arrays.copyOf(this.dscthd, this.n + k);
        for (int i = 0; i < k; i++) {
            System.out.println("nhập chi tiết hóa đơn thứ " + (this.n + i + 1));
            this.dscthd[this.n + i] = new CTHD();
            this.dscthd[this.n + i].nhap();
        }
        this.n += k;
        System.out.println("đã thêm thành công " + k + " chi tiết hóa đơn vào danh sách.");
    }
    
    @Override
    public void xuat(){
        if(this.n==0) {
            System.out.println("danh sách chi tiếT hoá đơn trống");
            return;
        }
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("               DANH SÁCH CHI TIẾT HÓA ĐƠN");
        System.out.println("-------------------------------------------------------------");
        System.out.println("| Mã HD  | Mã Giày| Số Lượng | Đơn Giá    | Thành Tiền |");
        System.out.println("-------------------------------------------------------------");
        for(int i=0;i<this.n;i++) {
            this.dscthd[i].xuat();
        }
        System.out.println("-------------------------------------------------------------");
    }

    @Override
    public void xoa() {
        System.out.println("Chức năng xóa chưa được triển khai cho DSCTHD.");
    }

    @Override
    public void sua() {
        System.out.println("Chức năng sửa chưa được triển khai cho DSCTHD.");
    }

    @Override
    public void timKiem() {
        if (this.n == 0) {
            System.out.println("Danh sách Chi Tiết Hóa Đơn trống, không thể tìm kiếm.");
            return;
        }
        
        CTHD[] ketQua = timkiem();
        if (ketQua.length > 0) {
            System.out.println("\n-------------------------------------------------------------");
            System.out.println("| Mã HD  | Mã Giày| Số Lượng | Đơn Giá    | Thành Tiền |");
            System.out.println("-------------------------------------------------------------");
            for (CTHD cthd : ketQua) {
                cthd.xuat();
            }
            System.out.println("-------------------------------------------------------------");
        }
    }
    
    public CTHD[] timkiem() {
        Scanner sc=new Scanner(System.in);
        if (this.n == 0) {
            return new CTHD[0];
        }
        
        System.out.println("--- Tìm kiếm Chi Tiết Hóa Đơn (Nhiều khóa & Gần đúng) ---");
        System.out.print("Nhập Mã Hóa Đơn cần tìm (Bỏ trống để bỏ qua): ");
        String maHDCanTim = sc.nextLine().trim().toLowerCase(); 

        System.out.print("Nhập Mã Giày cần tìm (Bỏ trống để bỏ qua): ");
        String maGiayCanTim = sc.nextLine().trim().toLowerCase(); 

        if (maHDCanTim.isEmpty() && maGiayCanTim.isEmpty()) {
            System.out.println("Vui lòng nhập ít nhất một tiêu chí tìm kiếm (Mã HD hoặc Mã Giày).");
            return new CTHD[0];
        }

        CTHD[] ketQuaTam = new CTHD[this.n];
        int count = 0;

        for (int i = 0; i < this.n; i++) {
            CTHD currentCTHD = this.dscthd[i];
            boolean matchHD = true;
            boolean matchGiay = true;

            if (!maHDCanTim.isEmpty()) {
                if (!currentCTHD.getMaHD().toLowerCase().contains(maHDCanTim)) {
                    matchHD = false;
                }
            }

            if (!maGiayCanTim.isEmpty()) {
                if (!currentCTHD.getMaGiay().toLowerCase().contains(maGiayCanTim)) {
                    matchGiay = false;
                }
            }

            if (matchHD && matchGiay) {
                ketQuaTam[count] = currentCTHD;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Không tìm thấy Chi Tiết Hóa Đơn nào phù hợp.");
            return new CTHD[0];
        } else {
            System.out.println("Tìm thấy " + count + " Chi Tiết Hóa Đơn phù hợp:");
            return Arrays.copyOf(ketQuaTam, count);
        }
    }
    
    public void thongKeTongThuTheoSP() {
        if (this.n == 0) {
            System.out.println("Danh sách Chi Tiết Hóa Đơn trống, không thể thống kê.");
            return;
        }
        String[] arr=new String[0];
        double[] totalSum=new double[0];
        int count=0;
        for(int i=0;i<this.n;i++) {
            CTHD cthd=this.dscthd[i];
            String maGiay=cthd.getMaGiay();
            double thanhTien=cthd.getThanhTien();
            int index=-1;
            for(int j=0;j<count;j++) {
                if(arr[j].equals(maGiay)) {
                    index=j;
                    break;
                }
            }
            if(index==-1) {
                count++;
                arr=Arrays.copyOf(arr, count);
                totalSum=Arrays.copyOf(totalSum, count);
                arr[count-1]=maGiay;
                totalSum[count-1]=thanhTien;
            }
            else {
                totalSum[index]+=thanhTien;
            }
        }
        System.out.println("\n=============================================");
        System.out.println("📊 THỐNG KÊ TỔNG THU THEO SẢN PHẨM");
        System.out.println("=============================================");
        System.out.printf("| %-15s | %-20s |%n", "Mã Sản Phẩm", "Tổng Tiền (VNĐ)");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("| %-15s | %,.2f |%n", 
                arr[i], 
                totalSum[i]);
        }
        System.out.println("---------------------------------------------");
    }

    public CTHD[] getDscthd() {
        return dscthd;
    }
    public void setDscthd(CTHD[] dscthd) {
        this.dscthd = dscthd;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
}