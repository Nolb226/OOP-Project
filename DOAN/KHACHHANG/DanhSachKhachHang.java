package KHACHHANG;


import INTERFACE.*;
import java.util.ArrayList;
// import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import CONNGUOI.*;
import EXCEPTION.*;

public class DanhSachKhachHang implements DocGhiFile {
    private ArrayList<KhachHang> List;
    static Scanner scanner = new Scanner(System.in);
    static Scanner string = new Scanner(System.in);
    String[] data={"KH1234,Nguyen Ngoc Son,20/5/2003,0898504720,49/1Q NHC MyHue TrungChanh HocMon.",
                "KH2345,Nguyen Ngoc Son,20/5/2003,0898504720,49/1Q NHC MyHue TrungChanh HocMon.",
                "KH3456,Nguyen Truong Khanh Hoang,20/5/2003,0898504720,49/1Q NHC MyHue TrungChanh HocMon.",
                "KH4567,Nguyen Thanh Dat,20/5/2003,0898504720,49/1Q NHC MyHue TrungChanh HocMon."};
    public DanhSachKhachHang() {
        List = new ArrayList<KhachHang>();
    }

    public DanhSachKhachHang(ArrayList<KhachHang> a) {
        this.List = a;
    }

    public DanhSachKhachHang(DanhSachKhachHang orther) {
        this.List = orther.List;
    }

    public ArrayList<KhachHang> getKHList() {
        return List;
    }
    public void xulytt(String s) {
        String word[] = s.split(",");
        KhachHang temp = new KhachHang();
        temp.setMSKH(word[0]);
        temp.setHoten(word[1]);
        Date d = new Date();
        d.xulyngay(word[2]);
        temp.setNgaySinh(d);
        temp.setSdt(word[3]);
        temp.setDiaChi(word[4]);
        // temp.Xuat();
        List.add(temp);
    }

    public boolean checkmaKH(String mkh) {
        boolean diff = true;
        for (KhachHang a : List) {
            if (a.getMSKH().equalsIgnoreCase(mkh)) {
                diff = false;
                break;
            }
        }
        return diff;
    }

    public String taoMKH(){
        // Random rd=new Random();
        String mkh;
        while (true) {
            // mkh="KH"+(int)((Math.random()) * ((9999 - 1000) + 1) + 9999);
            // mkh="KH"+(int)((Math.random()) * ((9999-1000)+1)+1000);
            mkh="KH"+(int)((Math.random()) * (10000));
            if(checkmaKH(mkh))
                break;
        }
        return mkh;
    }

    public void NhapThemKh() {
        System.out.println("nhap danh sach khach hang");
        do {
            KhachHang a = new KhachHang();
            a.Nhap(taoMKH());
            this.themKH(a);
            System.out.println("Nhap 't' de dung");
        } while (scanner.nextLine().charAt(0) != 't');
        // GhiFile();
    }

    public void DocFile() {
        File fin = new File("DOAN/KHACHHANG/text.txt");
        List.clear();
        try {
            BufferedReader brsp = Files.newBufferedReader(fin.toPath());
            if(brsp==null)
            {
                for ( String s : data ) {
                    xulytt(s);  
                }
            }
            String s = null;
            while (true) {
                s = brsp.readLine();
                if (s == null) {
                    break;
                } else {
                    xulytt(s);
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            for ( String s : data ) {
                xulytt(s);
            }
        }
    }

    public void GhiFile()
    {
        try {
            // FileWriter fw = new FileWriter("KHACHHANG/text");
            PrintWriter pw = new PrintWriter("DOAN/KHACHHANG/text.txt");
            // BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<List.size();i++){
                // s=s+List.get(i).toString();
                pw.println(List.get(i).toString());
            }
        // bw.write(temp);
        // bw.close();
        pw.flush();
        pw.close();
        } catch (Exception e) {
            System.out.println("Loi ghhi File");
        }
        System.out.println("Success...");
    }

    public void timxuatMskhKhachHang()
    {
        System.out.println("Nhap ma so khach hang can tim");
        String temp=scanner.nextLine();
        for(int i=0;i<List.size();i++)
        {
            if(List.get(i).getMSKH().equalsIgnoreCase(temp))
            {
                List.get(i).Xuat();
            }
        }
    }
    
    public KhachHang timMskhKhachHang()
    {
        System.out.println("Nhap ma so khach hang can tim");
        String temp=scanner.nextLine();
        int index=-1;
        for(int i=0;i<List.size();i++)
        {
            if(List.get(i).getMSKH().equalsIgnoreCase(temp))
            {
                index=i;
            }
        }
        return List.get(index);
    }

    public void themKH(KhachHang temp)
    {
        List.add(temp);
        GhiFile();
    }


    public KhachHang TimHotenKhachHang()
    {
        System.out.println("Nhap Ho ten can tim");
        String temp=scanner.nextLine();
        int index=-1;
        for(int i=0;i<List.size();i++)
        {
            if(List.get(i).getHoten().equals(temp))
            {
                index=i;
            }
        }
        return List.get(index);
    }

    // public DanhSachKhachHang timMskhKhachHang()
    // {
    //     System.out.println("Nhap ma so khach hang can tim");
    //     String temp=scanner.nextLine();
    //     DanhSachKhachHang temp2=new DanhSachKhachHang();
    //     for(int i=0;i<List.size();i++)
    //     {
    //         if(List.get(i).getMSKH().equalsIgnoreCase(temp))
    //         {
    //             temp2.themKH(List.get(i));
    //         }
    //     }
    //     return temp2;
    // }

    public void timHotenKhachHang()
    {
        System.out.println("Nhap Ho ten can tim");
        String temp = scanner.nextLine();
        for(int i = 0; i < List.size(); i++)
        {
            if(List.get(i).getHoten().equalsIgnoreCase(temp))
            {
                List.get(i).Xuat();
            }
        }
    }

    public void timSDTKhachHang()
    {
        System.out.println("Nhap So dien thoai can tim");
        String temp=scanner.nextLine();
        for(int i = 0; i < List.size(); i++)
        {
            if(List.get(i).getSdt().equalsIgnoreCase(temp))
            {
                List.get(i).Xuat();
            }
        }
    }

    public void xoaKH()
    {
        System.out.println("Nhap ma so khach hang can xoa");
        String temp=scanner.nextLine();
        for(int i = 0; i < List.size(); i++)
        {
            if(List.get(i).getMSKH().equalsIgnoreCase(temp))
            {
                List.remove(i);
            }
        }
        GhiFile();
    }

    public void suaKH()
    {
        int n=0;
        System.out.print("Nhap ma so khach hang can sua:");
        String temp=scanner.nextLine();
        for(int i = 0; i < List.size(); i++)
        {
            if(List.get(i).getMSKH().equalsIgnoreCase(temp))
            {
                List.get(i).suattKH();
                n=1;
            }
        }
        if(n==0)
        {
            System.out.println("Khong tim thay");
        }
        else{GhiFile();}
    }

    public void Xuat() {
        DocFile();
        int j;
        System.out.print(" ");
        for (int i = 0; i < 152; i++) {
            System.out.printf("_");
        }
        System.out.print("\n");
        System.out.printf("| %85s ", "DANH SACH KHACH HANG");
        System.out.printf("%64s |\n", " ");
        System.out.print("|");
        for ( j = 0; j < 152; j++) {
            System.out.printf("_");
        }
        System.out.print("|\n");
        System.out.printf("| %-20s%-30s%-20s%-20s%-60s |\n","MSKH","Hoten","So dien thoai","ngaysinh","Diachi");
        System.out.print("|");
        for ( j = 0; j < 152; j++) {
            System.out.printf("_");
        }
        System.out.print("|\n");
        for (int k = 0; k < List.size(); k++) {
            List.get(k).Xuat();
        }
    }

    public void TimKH()
    {
        int choose;
        do{
            System.out.print("+---------------------Quan ly khach hang---------------------+\n"
                            +"|1.Tim theo ma so khach hang                                 |\n"
                            +"|2.Tim theo ho ten khach hang                                |\n"
                            +"|3.Tim theo so dien thoai khach hang                         |\n"
                            +"|4.Quay ve menu chinh                                        |\n"
                            +"+------------------------------------------------------------+\n");
            choose=checkLoi.checkSo(string.nextLine());
            switch (choose) {
                case 1:
                    timxuatMskhKhachHang();
                    break;
                case 2:
                    timHotenKhachHang();
                    break;
                case 3:
                    timSDTKhachHang();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lua chon khong dung moi nhap lai");
                    break;
            }
        }while(choose!=4);
    }

    public void QuanlyKH()
    {
        int choose;
        do{
            System.out.print("+---------------------Quan ly khach hang---------------------+\n"
                            +"|1.Xuat danh sach khach hang                                 |\n"
                            +"|2.Them khach hang                                           |\n"
                            +"|3.Xoa khach hang                                            |\n"
                            +"|4.Tim khach hang                                            |\n"
                            +"|5.Sua thong tin khach hang                                  |\n"
                            +"|6.Quay ve menu chinh                                        |\n"
                            +"+------------------------------------------------------------+\n");
            System.out.print("Nhap lua chon");
            choose=checkLoi.checkSo(string.nextLine());
            switch (choose) {
                case 1:
                    System.out.println(List.size());
                    Xuat();
                    break;
                case 2:
                    NhapThemKh();
                    break;
                case 3:
                    xoaKH();
                    break;
                case 4:
                    TimKH();
                    break;
                case 5:
                    suaKH();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong dung moi nhap lai");
                    break;
            }
        }while(choose != 6);
    }

}
