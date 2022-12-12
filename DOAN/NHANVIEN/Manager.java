package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;
import DOANHTHU.QuanLyDoanhThu;
import EXCEPTION.*;
import KHACHHANG.DanhSachKhachHang;
import MONAN.DanhSachMonAn;
import DOANHTHU.*;

public class Manager extends NhanVien{
    private String congviec;
    private int ngaycong;

    public Scanner sc = new Scanner(System.in);
    
    public  Manager(){
        this.ngaycong = 0;
        this.congviec = null;
    }

    public Manager(Manager a){
        super(a.getMaNV(),a.getHoten(),a.getdiachi(),a.getNgaySinh(),a.getSdt());
        this.ngaycong=a.ngaycong;
        this.congviec=a.congviec;
    }

    public Manager(String maNV, String hoten, String diachi, Date ngaySinh, String sdt, String congviec,  int ngaycong){
        super(maNV, hoten, diachi, ngaySinh, sdt);
        this.ngaycong=ngaycong;
        this.congviec=congviec;
    }

    public void setCongviec(String congviec) {
        System.out.println("Moi nhap cong viec: ");
        congviec = scanner.next();
        this.congviec = congviec;
    }

    public String getCongviec() {
        return congviec;
    }

    public void setngaycong(int ngaycong){
        System.out.println("Moi nhap vao so ngay cong: ");
        do{
            ngaycong = checkLoi.checkSo((sc.next()));
            if(ngaycong == -1)
                System.out.println("Du lieu nhap khong hop le, moi nhap lai !");

        }while(ngaycong == -1);

        this.ngaycong=ngaycong;
    }

    public int getngaycong(){
        return ngaycong;
    }

    @Override
    public double calSalary(){
        return getLuongCoBan()+20000*ngaycong;
    }

    @Override
    public void chamCong() {
        this.ngaycong += 1;        
    }

    public void xuatLuong(NhanVien dsnv[]) {
        System.out.println("\n\t\t\t========== Bang luong =========");
        System.out.printf("\n\t%-20s||\t%-35s||\t%-30s", "Ma nhan vien", "Ho ten", "Luong"); 
        System.out.print("\n-------------------------------------------------------------------------------------");
        for(int i = 0; i < dsnv.length; i++) {
            System.out.printf("\n\t%-20s||\t%-35s||\t%-30s", dsnv[i].getMaNV(), dsnv[i].getHoten(), dsnv[i].calSalary()); 
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
        setngaycong(ngaycong);
        // scanner.next();
        System.out.print("Nhap cong viec cu the: ");
        setCongviec(scanner.nextLine());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + getCongviec() + ";" + getngaycong() + "\n";
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.print(" || " + "Ngay cong: " + getngaycong() + " || " + "Cong viec: " + getCongviec() + "\n");
    }

    @Override
    public void QuanLy(DanhSachNhanVien dsnv, DanhSachKhachHang dskh, DanhSachMonAn dsma, DanhSachHoaDon dshd, DoanhThu DT, NhanVien user, DanhSachPhieuNhap dspn, DoanhThuThang DTT) {
        
        int selection;

        while(true)
        {
            System.out.print("\n\n\t\t=========== MENU MANAGER ===========\n");
            System.out.print("\n\t\t1. Xuat danh sach nhan vien.");
            System.out.print("\n\t\t2. Them nhan vien vao danh sach.");
            System.out.print("\n\t\t3. Tim kiem nhan vien.");
            System.out.print("\n\t\t4. Sua nhan vien");
            System.out.print("\n\t\t5. Xoa nhan vien");
            System.out.print("\n\t\t6. Quan ly khach hang");
            System.out.print("\n\t\t7. Quan ly doanh thu");
            System.out.print("\n\t\t8. Quan ly tai khoan ca nhan");
            System.out.print("\n\t\t9. Quan ly san pham");
            System.out.print("\n\t\t10. Ban hang");
            System.out.print("\n\t\t11. Nhap hang");
            System.out.print("\n\t\t12. Xuat bang luong");
            System.out.print("\n\t\t0. Exit.");
            System.out.print("\n\n\t\t================ END ================");

            System.out.print("\nNhap lua chon: ");
            selection=checkLoi.checkSo(scanner.nextLine());
            

            if(selection < 0 || selection > 12)
            {
                System.out.print("\nLua chon khong hop le !");
            }

            else if(selection == 1) {
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
                DTT.GhiFile();
            }

            else if(selection == 11) {
                dsma.nhapHang(dsma, dspn, user, DT);
                DTT.updateDT(DT);
                DTT.GhiFile();
            }

            else if(selection == 12)
            {
                xuatLuong(dsnv.dsnv);
            }

            else if(selection == 0) {
                System.out.println("exit");
                break;
            }
        }
    }
}

