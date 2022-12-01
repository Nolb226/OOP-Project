package KHACHHANG;

import java.util.Scanner;

public class KhachHang extends connguoi {
    private String MSKH;
    // private int diemThuong;
    Scanner inp=new Scanner(System.in);

    public KhachHang()
    {
        super();
        // diemThuong=0;
        MSKH=null;
    }

    public KhachHang(KhachHang temp)
    {
        super(temp);
        setMSKH(temp.MSKH);  
    }

    public void setMSKH(String mkh) {
        this.MSKH=mkh;
    };


    public void suattKH()
    {
        do {
            System.out.println("Nhap 1 de sua ten\n"+
                               "Nhap 2 de sua Dia chi\n"+
                               "Nhap 3 de sua So dien thoai\n"+
                               "Nhap 4 de sua ngay sinh");
            int n=inp.nextInt();

            while(n<1 || n>4)
            {
                System.out.println("Moi nhap lai");
                n=inp.nextInt();
            }

            switch (n) {
                case 1:
                    super.setHoten();
                    break;
                case 2:
                    super.setDiachi();
                    break;
                case 3:
                    super.setSdt();
                    break;
                case 4:
                    super.setNgaysinh();
                    break;
            }
            System.out.println("nhap t de tiep tuc sua");
        } while (scanner.nextLine().charAt(0) == 't');
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
        super.nhap();
        setMSKH(mkh);
    }

    public void Xuat()
    {
        System.out.printf("| %-20s%-30s%-20s%-20s%-60s |\n",MSKH,Hoten,Sdt,ngaysinh.toString(),diachi);
        System.out.print("|");
        for(int i=0;i<152;i++)
        {
            System.out.printf("_");
        }
        System.out.print("|\n");
    }

    public String toString()
    {
        return MSKH + ","+super.toString();
    }

}
