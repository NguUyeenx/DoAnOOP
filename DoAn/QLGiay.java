import java.util.Scanner;

public class QLGiay {
    private DSSG dsg= new DSSG();
    public void menu(){
        Scanner sc=new Scanner(System.in);
        dsg.docFile("text.txt");
        int chon;
        do{
            System.out.println("  ==Menu Giay==  ");
            System.out.println("1.Xem danh sach giay");
            System.out.println("2.Them giay(nhap tu ban phim)");
            System.out.println("3.Tim giay theo ma");
            System.out.println("4.Xoa giay theo ma");
            System.out.println("5.Sua thong tin giay");
            System.out.println("6.Thong ke");
            System.out.println("7.Tim theo mau");
            System.out.println("8.xuat danh sach giam dan theo gia");
            System.out.println("0.Thoat chuong trinh!");
            System.out.println("nhap lua chon:");
            chon=sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    System.out.println("Danh sach san pham:");
                    dsg.xuat();
                    break;
                case 2:
                    dsg.themGiay();
                    break;
                case 3:
                    dsg.timKiemGiay();
                    break;
                case 4:
                    System.out.println("nhap ma giay muon xoa:");
                     String ma=sc.nextLine();
                    dsg.xoaGiay(ma);
                    break;
                case 5:
                    dsg.setGiay();
                    break;
                case 6:
                    dsg.bangThongKe();
                    break;
                case 7:
                    dsg.timGiayTheoMau();
                    break;
                case 8:
                    dsg.danhSachGiamDan();
                    break;
                default:
                System.out.println("lua chon khong hop le!");
                break;
            }
        }while(chon!=0);
     dsg.ghiFile("text.txt");
    }
}
