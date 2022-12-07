package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;

public abstract class NhanVien extends ConNguoi {
    private String maNV;
    private Account taiKhoan; 

    private static double luongCoBanTheoGio = 24000;
    private static double luongCoBan = 5000000;        //Muc luong cua Full-time va Manager

    public Scanner scanner = new Scanner(System.in);
    
    public NhanVien() {
        super();
        this.maNV = null;
        taiKhoan=new Account();
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

    // public void sethoten(String hoTen){
    //     System.out.println("Moi nhap vao ho ten nhan vien: ");
    //     hoTen=sc.nextLine();
    //     super.hoTen = hoTen;
    // }
    
    // public String gethoten(){
    //     return getHoten();
    // }

    // public void setdiachi(String diachi){
    //     System.out.println("Moi nhap vao dia chi cua nhan vien: ");
    //     diachi=sc.nextLine();
    //     this.diaChi=diachi;
    // }

    // public String getdiachi(){
    //     return diaChi;
    // }

    // public void setNamsinh(){
    //     // System.out.println("Moi nhap vao nam sinh nhan vien: ");
    //     // namsinh=Integer.parseInt(sc.nextLine());
    //     // this.namsinh=namsinh;
    //     ngaySinh.nhap();
    // }

    // public String getnamsinh(){
    //     return ngaySinh.toString();
    // }

    // public void setSdt(){
    //     setSdt();
    // }

    // public String getsdt(){
    //     return getSdt();
    // }

    public double getLuongCoBanTheoGio(){
        return luongCoBanTheoGio;
    }

    public double getLuongCoBan(){
        return luongCoBan;
    }

    public abstract double calSalary();

    @Override
    public void Nhap(){
        super.Nhap();
        // System.out.print("Nhap ma nhan vien: ");
        // setMaNV(scanner.nextLine());
    }

    @Override
    public String toString() {
        return getMaNV() + ";" + getHoten() + ";"+getDiaChi()+";" + getNgaySinh() + ";" + getSdt();
    }

    
    public void Xuat(){
        System.out.print("Ma nhan vien: " + getMaNV() + " || " + "Ho ten : " + getHoten() + " || " + "Dia chi : " + getDiaChi() + " || " + "Nam sinh: " + getNgaySinh() + " || " + "So dien thoai: " + getSdt());
    }
}