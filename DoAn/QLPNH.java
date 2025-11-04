import java.util.Scanner;

public class QLPNH {

    DSPNH dspnh = new DSPNH();

    public void Menu() {
        Scanner sc = new Scanner(System.in);
        dspnh.docFile("pnh.txt", "ctpnh.txt");
        int choice;
        do {
            System.out.println("\n========== MENU QUAN LY PHIEU NHAP HANG ==========");
            System.out.println("1. Them phieu nhap hang");
            System.out.println("2. Xuat danh sach phieu nhap hang");
            System.out.println("3. Xoa phieu nhap hang");
            System.out.println("4. Sua phieu nhap hang");
            System.out.println("5. Tim kiem phieu nhap hang");
            System.out.println("6. Them chi tiet phieu nhap hang");
            System.out.println("7. Xuat chi tiet phieu nhap hang");
            System.out.println("8. Xoa chi tiet phieu nhap hang");
            System.out.println("9. Sua chi tiet phieu nhap hang");
            System.out.println("10. Tim kiem chi tiet phieu nhap hang");
            System.out.println("11. Thong ke");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("=================================================");
            System.out.print("-> Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    int subThem;
                    System.out.println("\n=== THEM PHIEU NHAP HANG ===");
                    System.out.println("1. Them 1 phieu");
                    System.out.println("2. Them nhieu phieu");
                    System.out.print("Chon: ");
                    subThem = sc.nextInt();
                    switch (subThem) {
                        case 1 -> dspnh.Them();
                        case 2 -> dspnh.ThemKPhanTu();
                        default -> System.out.println("Lua chon khong hop le!");
                    }
                }

                case 2 -> {
                    int subXuat;
                    System.out.println("\n=== XUAT PHIEU NHAP HANG ===");
                    System.out.println("1. Xuat 1 phieu (gom chi tiet)");
                    System.out.println("2. Xuat danh sach phieu");
                    System.out.print("Chon: ");
                    subXuat = sc.nextInt();
                    sc.nextLine();
                    switch (subXuat) {
                        case 1 -> {
                            PNH pnh = dspnh.TimKiemMaPNH();
                            if (pnh == null)
                                System.out.println("Khong tim thay phieu nhap hang can xuat!");
                            else
                                dspnh.Xuat1Phieu(pnh);
                        }
                        case 2 -> dspnh.XuatDS();
                        default -> System.out.println("Lua chon khong hop le!");
                    }
                }

                case 3 -> dspnh.Xoa();

                case 4 -> dspnh.Sua();

                case 5 -> {
                    int subTim;
                    System.out.println("\n=== TIM KIEM PHIEU NHAP HANG ===");
                    System.out.println("1. Tim theo ma phieu nhap hang");
                    System.out.println("2. Tim theo ma nhan vien");
                    System.out.print("Chon: ");
                    subTim = sc.nextInt();
                    sc.nextLine();
                    switch (subTim) {
                        case 1 -> {
                            PNH foundMa = dspnh.TimKiemMaPNH();
                            if (foundMa != null) {
                                System.out.println("\n=== KET QUA TIM KIEM ===");
                                foundMa.xuat();
                            }
                        }
                        case 2 -> {
                            PNH[] foundNV = dspnh.TimKiemMaNV();
                            if (foundNV != null) {
                                System.out.println("\n=== DANH SACH PHIEU CUA NHAN VIEN ===");
                                for (PNH p : foundNV) p.xuatKoChiTiet();
                            }
                        }
                        default -> System.out.println("Lua chon khong hop le!");
                    }
                }

                case 6 ->  {
                    System.out.println("\n=== THEM CHI TIET PHIEU NHAP ===");
                    PNH pnhThem = dspnh.TimKiemMaPNH();
                    if (pnhThem == null) {
                        System.out.println("Khong tim thay phieu nhap hang de them chi tiet!");
                    } else {
                        CTPNH ctMoi = new CTPNH();
                        ctMoi.Nhap(pnhThem.getMaPNH());
                        pnhThem.getDSCTPNH().themChiTiet(ctMoi);
                        pnhThem.setTongTien(pnhThem.getDSCTPNH().tinhTongTien());
                        System.out.println("Da them chi tiet cho phieu nhap hang!");
                    }
                }

                case 7 ->  {
                    System.out.println("\n=== XUAT CHI TIET PHIEU NHAP ===");
                    PNH pnhXuat = dspnh.TimKiemMaPNH();
                    if (pnhXuat != null)
                        pnhXuat.getDSCTPNH().Xuat(pnhXuat.getMaPNH());
                    else
                        System.out.println("Khong tim thay phieu nhap hang!");
                }

                case 8 ->  {
                    System.out.println("\n=== XOA CHI TIET PHIEU NHAP ===");
                    PNH pnhXoa = dspnh.TimKiemMaPNH();
                    if (pnhXoa != null) {
                        System.out.print("Nhap chi tiet thu may can xoa trong phieu " + pnhXoa.getMaPNH() + ": ");
                        int stt = sc.nextInt();
                        pnhXoa.getDSCTPNH().xoaChiTietTheoSTT(stt);
                        pnhXoa.setTongTien(pnhXoa.getDSCTPNH().tinhTongTien());
                    }
                }

                case 9 ->  {
                    System.out.println("\n=== SUA CHI TIET PHIEU NHAP ===");
                    PNH foundpnh = dspnh.TimKiemMaPNH();
                    if (foundpnh != null) {
                        foundpnh.getDSCTPNH().suaChiTiet(foundpnh.getMaPNH());
                        double tongMoi = foundpnh.getDSCTPNH().tinhTongTien();
                        foundpnh.setTongTien(tongMoi);
                        System.out.println(">> Da cap nhat tong tien cua phieu " + foundpnh.getMaPNH() + "!");
                    }
                }

                case 10 ->  {
                    System.out.println("\n=== TIM KIEM CHI TIET PHIEU NHAP ===");
                    PNH pnhTim = dspnh.TimKiemMaPNH();
                    if (pnhTim != null) {
                        System.out.println("1. Tim chi tiet theo STT");
                        System.out.println("2. Tim chi tiet theo ma giay");
                        System.out.print("Chon: ");
                        int subTimCT = sc.nextInt();
                        sc.nextLine();
                        switch (subTimCT) {
                            case 1 -> {
                                System.out.print("Nhap STT chi tiet can tim: ");
                                int sttTim = sc.nextInt();
                                CTPNH ctTim = pnhTim.getDSCTPNH().timChiTietTheoSTT(sttTim);
                                if (ctTim != null) {
                                    System.out.println("\n=== KET QUA TIM KIEM ===");
                                    ctTim.Xuat(pnhTim.getMaPNH());
                                }
                            }
                            case 2 -> {
                                CTPNH[] dsTim = pnhTim.getDSCTPNH().TimKiemMaGiay();
                                if (dsTim != null) {
                                    System.out.println("\n=== CAC CHI TIET TIM THAY ===");
                                    for (CTPNH ct : dsTim) ct.Xuat(pnhTim.getMaPNH());
                                }
                            }
                            default -> System.out.println("Lua chon khong hop le!");
                        }
                    }
                }

            
                case 11 ->  {
                    int subThongKe;
                    System.out.println("\n=== THONG KE ===");
                    System.out.println("1. Thong ke tong tien theo ma nhan vien");
                    System.out.println("2. Thong ke tong tien theo ma NV va ma NCC");
                    System.out.print("Chon: ");
                    subThongKe = sc.nextInt();
                    sc.nextLine();

                    switch (subThongKe) {
                        case 1 -> dspnh.thongKeTheoMaNV();
                        case 2 -> dspnh.thongKeTheoNVvaNCC();
                        default -> System.out.println("Lua chon khong hop le!");
                    }
                }

                case 0 -> System.out.println("Thoat chuong trinh quan ly phieu nhap hang!");

                default -> System.out.println("Lua chon khong hop le, vui long nhap lai!");
            }
        } while (choice != 0);

        dspnh.ghiFile("pnh.txt", "ctpnh.txt");
    }
}
