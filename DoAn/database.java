public class Database {
    public static DSNhanVien dsNV = new DSNhanVien();
    public static DSKhachHang dsKH = new DSKhachHang();
    public static DSNCC dsNCC = new DSNCC();
    public static DSPNH dsPNH = new DSPNH();
    public static DSHoaDon dsHD = new DSHoaDon();

    public static void docTatCa() {
        dsNV.docFile("nhanvien.txt");
        dsKH.docFile("khachhang.txt");
        dsNCC.docFile("ncc.txt");
        dsPNH.docFile("pnh.txt", "ctpnh.txt");
        dsHD.docFile("hoadon.txt", "cthd.txt");
    }

    public static void ghiTatCa() {
        dsNV.ghiFile("nhanvien.txt");
        dsKH.ghiFile("khachhang.txt");
        dsNCC.ghiFile("ncc.txt");
        dsPNH.ghiFile("pnh.txt", "ctpnh.txt");
        dsHD.ghiFile("hoadon.txt", "cthd.txt");
    }
}
1. Bản chất vấn đề

Khi bạn nhập hàng (PNH), mỗi chi tiết (CTPNH) sẽ chứa:

maGiay, soLuongNhap, donGiaNhap


→ Tức là bạn nhập thêm hàng vào kho.

Vậy nên, khi thêm chi tiết phiếu nhập, bạn phải:

Cập nhật soLuong của sản phẩm (Giay) tương ứng trong danh sách sản phẩm (DSGiay hoặc Database.dsGiay nếu có).

Cập nhật tongTien của phiếu nhập.

📦 2. Mối quan hệ cần có
Thành phần	Vai trò	Liên kết với
PNH	Phiếu nhập	có nhiều CTPNH
CTPNH	Chi tiết phiếu nhập	có maGiay
DSGiay	Danh sách sản phẩm	chứa từng Giay có maGiay, soLuongTon
Database	Dữ liệu dùng chung	lưu dsGiay, dsPNH, …
⚙️ 3. Giải pháp khi dùng Database

Giả sử bạn có:

public static DSGiay dsGiay = new DSGiay();


Thì khi thêm chi tiết phiếu nhập trong QLPNH, bạn làm thế này 👇

✅ Sửa đoạn thêm chi tiết phiếu nhập (case 6)
case 6:
    System.out.println("\n=== THEM CHI TIET PHIEU NHAP ===");
    PNH pnhThem = Database.dsPNH.TimKiemMaPNH();
    if (pnhThem == null) {
        System.out.println("Khong tim thay phieu nhap hang de them chi tiet!");
    } else {
        CTPNH ctMoi = new CTPNH();
        ctMoi.Nhap(pnhThem.getMaPNH()); // nhập dữ liệu chi tiết

        // ====== CẬP NHẬT TỒN KHO ======
        Giay g = Database.dsGiay.timTheoMaGiay(ctMoi.getMaGiay());
        if (g != null) {
            g.setSoLuongTon(g.getSoLuongTon() + ctMoi.getSoLuong());
            System.out.println(">> Da cap nhat ton kho cho ma giay: " + g.getMaGiay());
        } else {
            System.out.println("⚠ Khong tim thay ma giay trong danh sach san pham!");
        }

        // Thêm chi tiết vào phiếu
        pnhThem.getDSCTPNH().themChiTiet(ctMoi);

        // Cập nhật tổng tiền
        double tongMoi = pnhThem.getDSCTPNH().tinhTongTien();
        pnhThem.setTongTien(tongMoi);

        System.out.println("✅ Da them chi tiet cho phieu nhap hang!");
    }
    break;

🧮 4. Khi xóa chi tiết phiếu nhập (case 8)

Ngược lại, nếu bạn xóa chi tiết nhập hàng, thì phải trừ lại số lượng tồn:

case 8:
    System.out.println("\n=== XOA CHI TIET PHIEU NHAP ===");
    PNH pnhXoa = Database.dsPNH.TimKiemMaPNH();
    if (pnhXoa != null) {
        System.out.print("Nhap chi tiet thu may can xoa trong phieu " + pnhXoa.getMaPNH() + ": ");
        int stt = sc.nextInt();

        // Lấy chi tiết cần xóa để cập nhật kho
        CTPNH ctXoa = pnhXoa.getDSCTPNH().getChiTietTheoSTT(stt);
        if (ctXoa != null) {
            Giay g = Database.dsGiay.timTheoMaGiay(ctXoa.getMaGiay());
            if (g != null) {
                g.setSoLuongTon(g.getSoLuongTon() - ctXoa.getSoLuong());
                System.out.println(">> Da tru ton kho cua ma giay: " + g.getMaGiay());
            }
        }

        // Xóa chi tiết trong phiếu
        pnhXoa.getDSCTPNH().xoaChiTietTheoSTT(stt);

        // Cập nhật tổng tiền
        pnhXoa.setTongTien(pnhXoa.getDSCTPNH().tinhTongTien());
    }
    break;

📘 5. Khi ghi file

Vì bạn đã có Database.ghiTatCa(),
nên sau khi làm bất kỳ hành động nào (nhập, sửa, xóa phiếu, thêm chi tiết...),
bạn chỉ cần gọi:

Database.ghiTatCa();


→ Tất cả file (giay.txt, pnh.txt, ctpnh.txt, …) đều được lưu lại đồng bộ.

🧠 6. Tóm tắt cơ chế hoạt động
Hành động	Ảnh hưởng đến
Thêm chi tiết phiếu nhập	+ Tăng số lượng tồn của sản phẩm
Xóa chi tiết phiếu nhập	− Giảm số lượng tồn của sản phẩm
Sửa chi tiết phiếu nhập (thay đổi SL)	Cập nhật lại tồn theo chênh lệch
Ghi dữ liệu	Database.ghiTatCa() để lưu toàn bộ 