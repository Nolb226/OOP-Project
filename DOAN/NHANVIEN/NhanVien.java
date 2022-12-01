package NHANVIEN;

import java.util.Scanner;
import KHACHHANG.*;

public abstract class NhanVien extends connguoi {
    private String maNV;

    private static double luongcobantheogio = 24000;
    private static double luongcoban = 5000000;        //Muc luong cua FT va MN

    public Scanner sc = new Scanner(System.in);
    
    public NhanVien() {
    }

    public NhanVien(String hoten, String diachi, Date ngaysinh, String sdt){
        super(hoten,diachi,ngaysinh,sdt);
    }
    
    public void setmaNV(String maNV){
        System.out.println("Moi nhap vao ma nhan vien: ");
        maNV=sc.nextLine();
        this.maNV=maNV;
    }

    public String getmaNV(){
        return maNV;
    }

    public void sethoten(String hoten){
        System.out.println("Moi nhap vao ho ten nhan vien: ");
        hoten=sc.nextLine();
        super.Hoten=hoten;
    }
    
    public String gethoten(){
        return getHoten();
    }

    public void setdiachi(String diachi){
        System.out.println("Moi nhap vao dia chi cua nhan vien: ");
        diachi=sc.nextLine();
        this.diachi=diachi;
    }

    public String getdiachi(){
        return diachi;
    }

    public void setNamsinh(){
        // System.out.println("Moi nhap vao nam sinh nhan vien: ");
        // namsinh=Integer.parseInt(sc.nextLine());
        // this.namsinh=namsinh;
        ngaysinh.nhap();
    }

    public String getnamsinh(){
        return ngaysinh.toString();
    }

    public void setsdt(){
        setSdt();
    }

    public String getsdt(){
        return getSdt();
    }

    public double getluongcobantheogio(){
        return luongcobantheogio;
    }

    public double getluongcoban(){
        return luongcoban;
    }

    public abstract double calSalary();

    public void Nhap(){
        setmaNV(maNV);
        setHoten();
        setdiachi(diachi);
        setNgaysinh(ngaysinh);
        setsdt();
    }

    @Override
    public String toString() {
        return getmaNV()+";"+gethoten()+";"+getdiachi()+";"+getnamsinh()+";"+getsdt();
    }

    public void Xuat(){
        System.out.println("Ma nhan vien: "+getmaNV()+"||"+"\tHo ten : "+gethoten()+"||"+"\tDia chi : "+getdiachi()+"||"+"\tNam sinh: "+getnamsinh()+"||"+"\tSo dien thoai: "+getsdt());
    }
}