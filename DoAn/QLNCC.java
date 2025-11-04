import java.util.Scanner;

public class QLNCC {
    DSNCC dsncc = new DSNCC();
    public void Menu() {
        Scanner sc = new Scanner(System.in);
        dsncc.docFile("ncc.txt");
        int choice;

        do {
            System.out.println("\n===== MENU QUAN LY NHA CUNG CAP =====");
            System.out.println("1. Them nha cung cap");
            System.out.println("2. Xuat danh sach nha cung cap");
            System.out.println("3. Xoa nha cung cap");
            System.out.println("4. Sua thong tin nha cung cap");
            System.out.println("5. Tim kiem nha cung cap");
            System.out.println("0. Thoat");
            System.out.print("-> Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    dsncc.Them();
                    break;
                case 2:
                    System.out.println("\n=== DANH SACH NHA CUNG CAP ===");
                    dsncc.Xuat();
                    break;
                case 3:
                    dsncc.Xoa();
                    break;
                case 4:
                    dsncc.Sua();
                    break;
                case 5:
                    int chonTK;
                    do {
                        System.out.println("\n=== TIM KIEM NHA CUNG CAP ===");
                        System.out.println("1. Tim theo ma");
                        System.out.println("2. Tim theo ten");
                        System.out.println("0. Quay lai menu chinh");
                        System.out.print("-> Nhap lua chon: ");
                        chonTK = sc.nextInt();
                        sc.nextLine();

                        switch (chonTK) {
                            case 1:
                                NCC foundByMa = dsncc.TimKiemMa();
                                if (foundByMa != null) {
                                    System.out.println("=== KET QUA TIM KIEM ===");
                                    foundByMa.xuat();
                                }
                                break;
                            case 2:
                                NCC foundByTen = dsncc.TimKiemTen();
                                if (foundByTen != null) {
                                    System.out.println("=== KET QUA TIM KIEM ===");
                                    foundByTen.xuat();
                                } else {
                                    System.out.println("Khong tim thay ten phu hop.");
                                }
                                break;
                            case 0:
                                System.out.println("Quay lai menu chinh...");
                                break;
                            default:
                                System.out.println("Lua chon khong hop le!");
                        }
                    } while (chonTK != 0);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);

        dsncc.ghiFile("ncc.txt");
        sc.close();
    }
}
