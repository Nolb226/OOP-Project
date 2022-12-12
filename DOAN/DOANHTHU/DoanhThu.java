package DOANHTHU;


import NHANVIEN.*;

import java.util.Scanner;

import CONNGUOI.*;
import EXCEPTION.*;

public class DoanhThu{
    private Date ngay;
    private double out;
    private double in;

    private static Scanner input = new Scanner(System.in);

    DanhSachNhanVien dsnv=new DanhSachNhanVien();

    public DoanhThu() {
        ngay = new Date();
        ngay.Today();
        out = 0;
        in = 0;
    }

    public DoanhThu(Date d, double o, double i) {
        this.ngay = d;
        this.out = o;
        this.in = i;
    }

    public DoanhThu(DoanhThu orther) {
        this.ngay = orther.ngay;
        this.in = orther.in;
        this.out = orther.out;   
    }

    public void copyDT(DoanhThu orther) {
        ngay.setNgay(orther.ngay.getNgay());
        ngay.setThang(orther.ngay.getThang());
        ngay.setNam(orther.ngay.getNam());
        this.in = orther.in;
        this.out = orther.out;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getNgay() {
        return ngay.toString();
    }

    public void setIn(double in) {
        this.in = in;
    }

    public double getIn() {
        return in;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public double getOut() {
        return out;
    }

    public String toString() {
        return ngay.toString() + "," + out + "," + in;
    }

    public void moreIN(double newIn) { //Cong them vao tien ban hang
        in = in + newIn;
    }

    public void moreOut(double newOut) { //Cong them vao tien nhap hang
        out = out + newOut;
    }

    public void xuatDT() {
        System.out.print("|");
        System.out.printf("%-12s", ngay.toString());
        System.out.print("|");
        System.out.printf("%-20.2f", out);
        System.out.print("|");
        System.out.printf("%-20.2f", in);
        System.out.print("|");
        System.out.printf("%-20.2f", in-out);
        System.out.println("|");
    }

    public void QuanLyDT(DanhSachPhieuNhap DSPN, DanhSachHoaDon DSHD, DoanhThuThang DTT ) {
        
        int choice;
        do {
            System.out.println("");
            System.out.println("\t---------- QUAN LY DOANH THU ----------\n");
            System.out.println("\t1. Lich su phieu nhap                  ");
            System.out.println("\t2. Lich su hoa don                     ");
            System.out.println("\t3. Thong ke doanh thu                  ");
            System.out.println("\t0. Tro ve menu chinh.                  ");
            System.out.println("\n\t---------------------------------------");
            do {
                System.out.print("Moi nhap lua chon: ");
                choice = checkLoi.inputIntNumberError(input.nextLine());
                if (choice < 0 || choice > 3) {
                    System.out.println("Lua chon khong hop le, moi nhap lai!");
                }
            } while (choice > 3 || choice < 0);

            switch (choice) {
                case 1: {
                    
                    DSPN.xemPhieuNhap();
                    break;
                }

                case 2: {
                    
                    DSHD.xemHoaDon();
                    break;
                }

                case 3: {
                    
                    DTT.thongKe();
                    System.out.print("Nhan phim bat ki de tiep tuc.");
                    input.nextLine();
                }

                case 0: {
                    break;
                }
            }
        } while (choice != 0);
    }
}