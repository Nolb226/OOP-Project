package KHACHHANG;

import java.util.Scanner;
import CONNGUOI.*;
import EXCEPTION.checkLoi;

public class KhachHang extends ConNguoi {
    private String MSKH;
    Scanner inp = new Scanner(System.in);
    Scanner scanner =new Scanner(System.in);

    public KhachHang()
    {
        super();
        MSKH = null;
    }

    public KhachHang(KhachHang temp)
    {
        super(temp);
        setMSKH(temp.MSKH);  
    }

    public void setMSKH(String mkh) {
        this.MSKH = mkh;
    };


    public void suattKH()
    {
        int n;
        do {
            System.out.println("+---------------------Chuc nang---------------------+\n"+
                               "| Nhap 1 de sua ten                                 |\n"+
                               "| Nhap 2 de sua Dia chi                             |\n"+
                               "| Nhap 3 de sua So dien thoai                       |\n"+
                               "| Nhap 4 de sua ngay sinh                           |\n"+
                               "| Nhap 5 de thoat                                   |\n"+
                               "+---------------------------------------------------+\n");
            System.out.print("Nhap lua chon");  n = checkLoi.checkSo(scanner.nextLine());
            switch (n) {
                case 1:
                    super.setHoten();
                    System.out.println("Da chinh sua, nhan phim bat ki de tiep tuc.");
                    scanner.nextLine();
                    break;
                case 2:
                    super.setDiaChi();
                    System.out.println("Da chinh sua, nhan phim bat ki de tiep tuc.");
                    scanner.nextLine();
                    break;
                case 3:
                    super.setSdt();
                    System.out.println("Da chinh sua, nhan phim bat ki de tiep tuc.");
                    scanner.nextLine();
                    break;
                case 4:
                    super.setNgaySinh();
                    System.out.println("Da chinh sua, nhan phim bat ki de tiep tuc.");
                    scanner.nextLine();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Nhap lua chon sai moi nhap lai:");
                    break;
            }
            // System.out.println("nhap t de tiep tuc sua");
        } while(n!=5);
    }

    // public KhachHang(String Hoten,String diachi,int ngay,int thang,int nam,String Sdt)
    // {
    //     super( Hoten, diachi, ngay, thang, nam,Sdt);
    //     setMSKH();
    // }

    public  String getMSKH() {
        return MSKH;
    }

    public void Nhap(String mkh)
    {
        System.out.println("Nhap thong tin khach hang");
        super.Nhap();
        setMSKH(mkh);
    }

    public void Xuat()
    {
        System.out.printf("| %-20s%-30s%-20s%-20s%-60s |\n", this.MSKH, this.hoTen, this.sdt, this.ngaySinh.toString(), this.diaChi);
        System.out.print("|");
        for(int i=0;i<152;i++)
        {
            System.out.printf("_");
        }
        System.out.print("|\n");
    }

    public String toString()
    {
        return MSKH + "," + super.toString();
    }

}
