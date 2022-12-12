package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;
import DOANHTHU.*;
import KHACHHANG.DanhSachKhachHang;
import MONAN.DanhSachMonAn;
import EXCEPTION.*;

public abstract class NhanVien extends ConNguoi {
    private String maNV;
    protected Account taiKhoan=new Account(); 

    private static double luongCoBanTheoGio = 24000;
    private static double luongCoBan = 5000000;        //Muc luong cua Full-time va Manager

    public Scanner scanner = new Scanner(System.in);
    
    public NhanVien() {
        super();
        this.maNV = null;
        // this.taiKhoan=new Account();
    }

    public void set_Username(String username)
    {
        taiKhoan.setUserName(username);;
    }

    public NhanVien(String tk ) {
        super();
        this.maNV = null;
        taiKhoan=new Account(tk);
    }

    public String getpassword() {
        return taiKhoan.password;
    }

    public NhanVien(String maNV, String hoten, String diachi, Date ngaysinh, String sdt){
        super(hoten,diachi,ngaysinh,sdt);
        this.maNV = maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNV(){
        return maNV;
    }

    public void sethoten(String hoTen){
        System.out.println("Moi nhap vao ho ten nhan vien: ");
        hoTen=scanner.nextLine();
        super.hoTen = hoTen;
    }
    
    public String gethoten(){
        return getHoten();
    }

    public void setdiachi(String diachi){
        System.out.println("Moi nhap vao dia chi cua nhan vien: ");
        diachi=scanner.nextLine();
        this.diaChi=diachi;
    }

    public String getdiachi(){
        return diaChi;
    }

    public void setNamsinh(){
        // System.out.println("Moi nhap vao nam sinh nhan vien: ");
        // namsinh=Integer.parseInt(sc.nextLine());
        // this.namsinh=namsinh;
        ngaySinh.nhap();
    }

    public String getnamsinh(){
        return ngaySinh.toString();
    }

    public void setSdt(){
        setSdt();
    }

    public String getsdt(){
        return getSdt();
    }

    public double getLuongCoBanTheoGio(){
        return luongCoBanTheoGio;
    }

    public double getLuongCoBan(){
        return luongCoBan;
    }

    public abstract double calSalary();


    public void QuanLy(DanhSachNhanVien dsnv, DanhSachKhachHang dskh, DanhSachMonAn dsma, DanhSachHoaDon dshd, DoanhThu DT, NhanVien user, DanhSachPhieuNhap dspn, DoanhThuThang DTT) {
        DoanhThu doanhThu = new DoanhThu();

        int selection;
        if(user instanceof FullTime) {

            while(true)
            {
                System.out.print("\n\n\t\t\t========== MENU ==========");
                System.out.print("\n\t\t1. Quan ly khach hang");
                System.out.print("\n\t\t2. Quan ly tai khoan ca nhan");
                System.out.print("\n\t\t3. Quan ly doanh thu");
                System.out.print("\n\t\t4. Quan ly san pham");
                System.out.print("\n\t\t5. Ban hang");
                System.out.print("\n\t\t0. Exit.");
                System.out.print("\n\n\t\t\t========== END ==========");
    
                System.out.print("\nNhap lua chon: ");
                selection=checkLoi.checkSo(scanner.nextLine());
                
    
                if(selection < 0 || selection > 5)
                {
                    System.out.print("\nLua chon khong hop le !");
                }
    
                else if(selection == 1) {
                    dskh.QuanlyKH();
                }
                    
                else if(selection == 2)
                {
                    taiKhoan.doi_pass(dsnv);
                    System.out.println(this.getpassword());
                    dsnv.GhiFile();
                }
    
                else if(selection == 3)
                {
                    DT.QuanLyDT(dspn, dshd, DTT);
                }
    
                else if(selection == 4)
                {
                    dsma.menu();
                }
    
                else if(selection == 5)
                {
                    System.out.println("Nhap ma khach hang: ");
                    String mkh = scanner.next();
                    dsma.banHang(dsma, dshd, mkh, dskh, user, DT);
                    DTT.updateDT(DT);
                    DTT.GhiFile();
                }
    
                else
                    break;
            }
        }
        else {
            while(true)
            {
                System.out.print("\n\n\t\t\t========== MENU ==========");
                System.out.print("\n\t\t1. Quan ly khach hang");
                System.out.print("\n\t\t2. Quan ly tai khoan ca nhan");
                System.out.print("\n\t\t3. Ban hang");
                System.out.print("\n\t\t0. Exit.");
                System.out.print("\n\n\t\t\t========== END ==========");
    
                System.out.print("\nNhap lua chon: ");
                selection=checkLoi.checkSo(scanner.nextLine());
                
    
                if(selection < 0 || selection > 3)
                {
                    System.out.print("\nLua chon khong hop le !");
                }
    
                else if(selection == 1) {
                    dskh.QuanlyKH();
                }
                    
                else if(selection == 2)
                {
                    taiKhoan.doi_pass(dsnv);
                    System.out.println(this.getpassword());
                    dsnv.GhiFile();
                }
    
                else if(selection == 3)
                {
                    dsma.banHang(dsma, dshd, diaChi, dskh, user, DT);
                }
    
                else
                    break;
            }
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
    }

    @Override
    public String toString() {
        return getMaNV() + ";" + getpassword() + ";" + getHoten() + ";"+getDiaChi()+";" + getNgaySinh() + ";" + getSdt();
    }

    
    public void Xuat(){
        System.out.print("Ma nhan vien: " + getMaNV() + " || " + "Mat khau: " + getpassword() + " || " + "Ho ten : " + getHoten() + " || " + "Dia chi : " + getDiaChi() + " || " + "Nam sinh: " + getNgaySinh() + " || " + "So dien thoai: " + getSdt());
    }
}