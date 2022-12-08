package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;
import EXCEPTION.*;

public class Manager extends NhanVien{
    private int capbac;
    private String congviec;

    public Scanner sc = new Scanner(System.in);
    
    public  Manager(){
        this.capbac = 0;
        this.congviec = null;
    }

    public Manager(String maNV, String hoten, String diachi, Date ngaySinh, String sdt, String congviec,  int capbac){
        super(maNV, hoten, diachi, ngaySinh, sdt);
        this.capbac=capbac;
        this.congviec=congviec;
    }

    public void setCapbac(int capbac) {
        System.out.println("Moi nhap vao cap bac quan ly: ");
        do{
            capbac = checkLoi.checkSo((sc.nextLine()));
            if(capbac == -1)
                System.out.println("Du lieu nhap khong hop le, moi nhap lai !");

        }while(capbac == -1);

        this.capbac = capbac;
    }

    public int getCapbac() {
        return capbac;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public String getCongviec() {
        return congviec;
    }

    public double calSalary(){
        if(getCapbac()==1){
            return getLuongCoBan()+2000000;
        }
        if(getCapbac()==2){
            return getLuongCoBan()+3000000;
        }
        if(getCapbac()==3){
            return getLuongCoBan()+4000000;
        }
        else{
            return -1;
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
        System.out.print("Nhap cap bac quan ly: ");
        setCapbac(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nhap cong viec cu the: ");
        setCongviec(scanner.nextLine());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + getCongviec() + ";" + getCapbac() + "\n";
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.print(" || " + "Cap bac quan ly: " + getCapbac() + " || " + "Cong viec: " + getCongviec() + "\n");
    }

    public void QuanLyNhanVien() {
        DanhSachNhanVien ds = new DanhSachNhanVien();
        int selection;
        while(true)
        {
            System.out.print("\n\n\t\t========== MENU ==========");
            System.out.print("\n\t1. Xuat danh sach nhan vien.");
            System.out.print("\n\t2. Them nhan vien vao danh sach.");
            System.out.print("\n\t3. Tim kiem nhan vien.");
            System.out.print("\n\t4. Sua nhan vien");
            System.out.print("\n\t4. Xoa nhan vien");
            System.out.print("\n\t4. Xem danh sach khach hang");
            System.out.print("\n\t4. Xem doanh thu");
            System.out.print("\n\t0. Exit.");
            System.out.print("\n\n\t\t========== END ==========");

            System.out.print("\nNhap lua chon: ");
            selection = sc.nextInt() ;

            if(selection < 0 || selection > 8)
            {
                System.out.print("\nLua chon khong hop le !");
            }

            else if(selection == 1) {
                ds.DocFile();
                ds.XuatDanhSach();
            }
                
            else if(selection == 2)
            {
                ds.ThemNhanVien();
            }
            else if(selection == 3)
            {
                ds.DocFile();
                System.out.print("\nNhap ma nhan vien can tim: ");
                String maNV = sc.next();
                System.out.println(maNV);
                ds.TimKiemNhanVien(maNV);
            }

            else if(selection == 4)
            {
                
            }

            else if(selection == 5)
            {
                
            }

            else if(selection == 6)
            {
               
            }
            
            else if(selection == 7)
            {
                
            }

            else if(selection == 8)
            {
                
            }

            else
                break;
        }
    }

    public static void main(String[] args) {
        // ds.DocFile();
        Manager mn1 = new Manager();
        mn1.QuanLyNhanVien();
        
    }
}

