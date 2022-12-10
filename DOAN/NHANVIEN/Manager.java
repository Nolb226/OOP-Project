package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;
import DOANHTHU.QuanLyDoanhThu;
import EXCEPTION.*;
import KHACHHANG.DanhSachKhachHang;
import MONAN.DanhSachMonAn;
import DOANHTHU.*;

public class Manager extends NhanVien{
    private int capbac;
    private String congviec;

    public Scanner sc = new Scanner(System.in);
    
    public  Manager(){
        this.capbac = 0;
        this.congviec = null;
    }

    public Manager(Manager a){
        super(a.getMaNV(),a.getHoten(),a.getdiachi(),a.getNgaySinh(),a.getSdt());
        this.capbac=a.capbac;
        this.congviec=a.congviec;
    }

    public Manager(String maNV, String hoten, String diachi, Date ngaySinh, String sdt, String congviec,  int capbac){
        super(maNV, hoten, diachi, ngaySinh, sdt);
        this.capbac=capbac;
        this.congviec=congviec;
    }

    public void setCapbac(int capbac) {
        System.out.println("Moi nhap vao cap bac quan ly: ");
        do{
            capbac = checkLoi.checkSo((sc.next()));
            if(capbac == -1)
                System.out.println("Du lieu nhap khong hop le, moi nhap lai !");

        }while(capbac == -1);

        this.capbac = capbac;
    }

    public int getCapbac() {
        return capbac;
    }

    public void setCongviec(String congviec) {
        System.out.println("Moi nhap cong viec: ");
        congviec = scanner.next();
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
        setCapbac(capbac);
        // scanner.next();
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

    @Override
    public void QuanLy(DanhSachNhanVien dsnv, DanhSachKhachHang dskh, DanhSachMonAn dsma, DanhSachHoaDon dshd, DoanhThu DT, NhanVien user, DanhSachPhieuNhap dspn, DoanhThuThang DTT) {
        
        int selection;

        while(true)
        {
            System.out.print("\n\n\t\t========== MENU ==========");
            System.out.print("\n\t1. Xuat danh sach nhan vien.");
            System.out.print("\n\t2. Them nhan vien vao danh sach.");
            System.out.print("\n\t3. Tim kiem nhan vien.");
            System.out.print("\n\t4. Sua nhan vien");
            System.out.print("\n\t5. Xoa nhan vien");
            System.out.print("\n\t6. Quan ly khach hang");
            System.out.print("\n\t7. Quan ly doanh thu");
            System.out.print("\n\t8. Quan ly tai khoan ca nhan");
            System.out.print("\n\t9. Quan ly san pham");
            System.out.print("\n\t10. Ban hang");
            System.out.print("\n\t11. Nhap hang");
            System.out.print("\n\t0. Exit.");
            System.out.print("\n\n\t\t========== END ==========");

            System.out.print("\nNhap lua chon: ");
            selection=checkLoi.checkSo(scanner.nextLine());
            

            if(selection < 0 || selection > 11)
            {
                System.out.print("\nLua chon khong hop le !");
            }

            else if(selection == 1) {
                // dsnv.DocFile();
                dsnv.XuatDanhSach();
            }
                
            else if(selection == 2)
            {
                dsnv.ThemNhanVien();
            }
            else if(selection == 3)
            {
                dsnv.DocFile();
                System.out.print("\nNhap ma nhan vien can tim: ");
                String maNV = sc.next();
                System.out.println(maNV);
                dsnv.TimKiemNhanVien(maNV);
            }

            else if(selection == 4)
            {
                System.out.println("Nhap ma nhan vien cua nhan vien can sua");
                String manv = scanner.next();
                if(dsnv.TimKiemNhanVienReturnIndex(manv) != -1)
                    dsnv.suaNhanVien(dsnv.TimKiemNhanVienReturnIndex(manv));
                else 
                    System.out.println("Khong tim thay nhan vien !");
            }

            else if(selection == 5)
            {
                System.out.println("Nhap ma nhan vien cua nhan vien can xoa");
                String manv = scanner.next();
                if(dsnv.TimKiemNhanVienReturnIndex(manv) != -1)
                    dsnv.XoaNhanVien(dsnv.TimKiemNhanVienReturnIndex(manv));
                else 
                    System.out.println("Khong tim thay nhan vien !");
            }

            else if(selection == 6)
            {
               dskh.QuanlyKH();
            }
            
            else if(selection == 7)
            {
                DT.QuanLyDT(dspn, dshd, DTT);
            }

            else if(selection == 8)
            {
                taiKhoan.doi_pass(dsnv);
                // dsnv.GhiFile();
            }

            else if(selection == 9)
            {
                dsma.menu();
            }

            else if(selection == 10)
            {
                System.out.println("Nhap ma khach hang: ");
                String mkh = scanner.next();
                dsma.banHang(dsma, dshd, mkh, dskh, user, DT);
                DTT.updateDT(DT);
            }

            else if(selection == 11) {
                dsma.nhapHang(dsma, dspn, user, DT);
                DTT.updateDT(DT);
            }

            else if(selection == 0) {
                System.out.println("exit");
                break;
            }
        }
    }

    // public static void main(String[] args) {
    //     // ds.DocFile();
    //     DanhSachNhanVien dsnv = new DanhSachNhanVien();
        
    // }
}

