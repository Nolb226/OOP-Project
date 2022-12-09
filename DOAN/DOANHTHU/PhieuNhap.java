package DOANHTHU;


import NHANVIEN.*;
import MONAN.*;
import java.util.Arrays;
import CONNGUOI.Date;
import KHACHHANG.KhachHang;

public class PhieuNhap extends Phieu {

    public PhieuNhap() {
        id = null;
        n = 0;
        Date = new Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public PhieuNhap(String mpn) {
        id = mpn;
        n = 0;
        Date = new Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public void copyPN(PhieuNhap orther) {
        setId(orther.id);
        setNv(orther.nv);
        setDate(orther.Date);
        for (int i = 0; i < orther.n; i++) {
            addSP(orther.sp[i], orther.soLuong[i]);
        }
    }

    @Override
    public void taoPhieu(NhanVien a, KhachHang b) {
        // if (a instanceof Manager) {
        //     nv = new Manager((Manager) a);
        // } else if (a instanceof FullTime) {
        //     nv = new FullTime((FullTime) a);
        // } else if (a instanceof PartTime) {
        //     nv = new PartTime((PartTime) a);
        // }
        Date.Today();
    }

    @Override
    public void addSP(MonAn a, int sl) {
        sp = Arrays.copyOf(sp, n + 1);
        soLuong = Arrays.copyOf(soLuong, n + 1);
        setN(n + 1);
        sp[n - 1] = new MonAn((MonAn) a);
        soLuong[n - 1] = sl;
    }

    @Override
    public double price() {
        double price = 0;
        for (int i = 0; i < n; i++) {
            price = price + sp[i].getGiaNhap() * soLuong[i];
        }
        return price;
    }

    @Override
    public void inPhieu() {
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|                             PHIEU NHAP HANG                             |");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.printf("%-73s", "Ngay: " + Date.toString());
        System.out.println("|");
        System.out.print("|");
        System.out.printf("%-73s", "Ma phieu: " + id);
        System.out.println("|");
        if(nv == null) {
            System.out.println("|Thong tin nhan vien thuc hien:                                           |");
            System.out.print("|");
            System.out.printf("%-73s", "Ma nhan vien: " );
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "Ten nhan vien: " );
            System.out.println("|");
        } else {
            System.out.println("|Thong tin nhan vien thuc hien:                                           |");
            System.out.print("|");
            System.out.printf("%-73s", "Ma nhan vien: " + nv.getMaNV());
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "Ten nhan vien: " + nv.gethoten());
            System.out.println("|");
        }
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|Chi tiet phieu nhap:                                                     |");
        System.out.println("+----------+------------------------------+----------+--------------------+");
        System.out.print("|");
        System.out.printf("%-10s", "Ma SP");
        System.out.print("|");
        System.out.printf("%-30s", "Ten san pham");
        System.out.print("|");
        System.out.printf("%-10s", "So luong");
        System.out.print("|");
        System.out.printf("%-20s", "Gia tien");
        System.out.println("|");
        for (int i = 0; i < n; i++) {
            System.out.print("|");
            System.out.printf("%-10s", sp[i].getMaMon());
            System.out.print("|");
            System.out.printf("%-30s", sp[i].getTenMon());
            System.out.print("|");
            System.out.printf("%-10s", soLuong[i]);
            System.out.print("|");
            System.out.printf("%-20s", sp[i].getGiaNhap() * soLuong[i]);
            System.out.println("|");
        }
        System.out.println("+----------+------------------------------+----------+--------------------+");
        System.out.print("|");
        System.out.printf("%-52s", "Tong tien");
        System.out.print("|");
        System.out.printf("%-20.2f", price());
        System.out.println("|");
        System.out.println("+----------------------------------------------------+--------------------+");
    }

    @Override
    public String toString() {
        String s;
        if(nv == null) {
            s = Date.toString() + "," + id + "," + "null";
        } else {
            s = Date.toString() + "," + id + "," + nv.getMaNV();
        }
        for (int i = 0; i < n; i++) {
            s = s + "," + sp[i].getMaMon() + "#" + soLuong[i];
        }
        return s;
    }

    @Override
    public void xuat() {
        System.out.print("|");
        System.out.printf("%-12s", Date.toString());
        System.out.print("|");
        System.out.printf("%-12s", id);
        System.out.print("|");
        if(nv == null) {
            System.out.printf("%-25s", "null");
        } else {
            System.out.printf("%-25s", nv.getMaNV());
        }
        System.out.print("|");
        System.out.printf("%-20.2f", price());
        System.out.println("|");
    }

}
