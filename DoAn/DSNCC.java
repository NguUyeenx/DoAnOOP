import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

interface CongCuCoBan
{
    void Nhap();
    void Xuat();
    void Them();
    void Xoa();
    void Sua();
}

class DSNCC implements CongCuCoBan
{
    private NCC[] dsncc;
    private int n;
    
    public DSNCC()
    {
        int n=0;
        dsncc = new NCC[0];
    }

    public DSNCC(int n,NCC[] dsncc2)
    {
        this.n=n;
        for(int i=0;i<n;i++)
        {
            dsncc[i]=new NCC(dsncc2[i]);
        }
    }

    public void Nhap()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong nha cung cap: ");
        n=sc.nextInt();
        dsncc=new NCC[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhap nha cung cap thu "+i+1+" :");
            dsncc[i]=new NCC();
            dsncc[i].nhap();
        }
    }

    public void Xuat()
    {
        for(int i=0;i<n;i++)
        {
            dsncc[i].xuat();
        }
    }
    public void Them()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap so luong nha cung cap can them: ");
        int index=sc.nextInt();
        n=dsncc.length;
        for(int i=0;i<index;i++)
        {
            System.out.println("Nhap nha cung cap thu "+(i + 1)+" :");
            dsncc= Arrays.copyOf(dsncc, n+1);
            dsncc[n]= new NCC();
            dsncc[n].nhap();
            n++;
        }
    }

    public void Xoa()
    {
        Scanner sc = new Scanner(System.in);
        String ma;
        System.out.println("Nhap ma nha cung cap can xoa: ");
        ma=sc.nextLine();
        int index=-1;
        for(int i=0;i<n;i++)
        {
            if(dsncc[i].getMaNCC().equals(ma))
            {
                index=i;
                break;
            }
        }
        if(index==-1)
        {
            System.out.println("Khong tim thay nha cung cap can xoa");
            return;
        }
        for(int i=index;i<n-1;i++)
        {
            dsncc[i]=dsncc[i+1];
        }
        dsncc=Arrays.copyOf(dsncc,n-1);
        n--;
        System.out.println("Da xoa thanh cong");
    }

    public void Sua()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha cung cap can sua: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++)
        {
            if (dsncc[i].getMaNCC().equals(ma))
            {
                index = i;
                break;
            }
        }
        if (index == -1)
        {
            System.out.println("Khong tim thay nha cung cap can sua.");
            return;
        }
        int choice;
        do
        {
            System.out.println("\n=== MENU SUA THONG TIN ===");
            System.out.println("1. Sua ma nha cung cap");
            System.out.println("2. Sua ten nha cung cap");
            System.out.println("3. Sua so dien thoai");
            System.out.println("4. Sua dia chi");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice)
            {
                case 1:
                    System.out.print("Nhap ma moi: ");
                    dsncc[index].setMaNCC(sc.nextLine());
                    System.out.println("Da cap nhat ma NCC!");
                    break;
                case 2:
                    System.out.print("Nhap ten moi: ");
                    dsncc[index].setTen(sc.nextLine());
                    System.out.println("Da cap nhat ten!");
                    break;
                case 3:
                    System.out.print("Nhap so dien thoai moi: ");
                    dsncc[index].setDienThoai(sc.nextLine());
                    System.out.println("Da cap nhat so dien thoai!");
                    break;
                case 4:
                    System.out.print("Nhap dia chi moi: ");
                    dsncc[index].setDiaChi(sc.nextLine());
                    System.out.println("Da cap nhat dia chi!");
                    break;
                case 0:
                    System.out.println("Thoat sua thong tin!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    } 

    public NCC TimKiemMa()
    {
        Scanner sc = new Scanner(System.in);
        String ma;
        System.out.println("Nhap ma nha cung cap can tim: ");
        ma=sc.nextLine();
        for(int i=0;i<n;i++)
        {
            if(dsncc[i].getMaNCC().equals(ma))
            {
                return dsncc[i];
            }
        }
        System.out.println("Khong tim thay nha cung cap");
        return null;
    }

    public NCC TimKiemTen()
    {
        Scanner sc = new Scanner(System.in);
        String tenncc;
        System.out.println("Nhap ten nha cung cap can tim: ");
        tenncc=sc.nextLine();
        for(int i=0;i<n;i++)
        {
            if(dsncc[i].getTen().toLowerCase().indexOf(tenncc.toLowerCase())!=-1)
            {
                return dsncc[i];
            }
        }
        return null;
    }

    public void ghiFile(String tenFile) {
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile));
        for (int i = 0; i < n; i++) {
            // mỗi dòng là 1 NCC, các trường cách nhau bằng dấu '|'
            bw.write(
                dsncc[i].getMaNCC() + "|" +
                dsncc[i].getTen() + "|" +
                dsncc[i].getDienThoai() + "|" +
                dsncc[i].getDiaChi()
            );
            bw.newLine(); // xuống dòng
        }
        bw.close();
        System.out.println("Ghi file thanh cong!");
    } catch (IOException e) {
        System.out.println("Loi khi ghi file: " + e.getMessage());
    }
}
    public void docFile(String tenFile) {
    try {
        BufferedReader br = new BufferedReader(new FileReader(tenFile));
        String line;
        n = 0;
        dsncc = new NCC[0]; // reset danh sách cũ

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                // Tạo NCC mới từ dữ liệu trong dòng
                NCC ncc = new NCC(parts[0], parts[1], parts[2], parts[3]);

                // Tăng kích thước mảng và thêm NCC vào cuối
                dsncc = Arrays.copyOf(dsncc, n + 1);
                dsncc[n] = ncc;
                n++;
            }
        }
        br.close();
        System.out.println("Doc file thanh cong! So NCC doc duoc: " + n);
    } catch (FileNotFoundException e) {
        System.out.println("Khong tim thay file: " + tenFile);
    } catch (IOException e) {
        System.out.println("Loi khi doc file: " + e.getMessage());
    }
}
}