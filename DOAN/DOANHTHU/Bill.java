package DOANHTHU;

import MONAN.*;
import java.util.Arrays;
import java.util.Scanner;
import NHANVIEN.*;
import CONNGUOI.*;
import KHACHHANG.*;

public class Bill extends Phieu {

    private KhachHang kh;
    protected CONNGUOI.Date Date;

    Scanner sc = new Scanner(System.in);

    public Bill() {
        id = null;
        n = 0;
        Date = new CONNGUOI.Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public Bill(String maHoaDon) {
        id = maHoaDon;
        n = 0;
        Date = new Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public void copyHD(Bill orther) {
        setId(orther.id);
        setNv(orther.nv);
        setKh(orther.kh);
        setDate(orther.Date);
        for (int i = 0; i < orther.n; i++) {
            addSP(orther.sp[i], orther.soLuong[i]);
        }
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    @Override
    public void addSP(MonAn a, int sl) {
        sp = Arrays.copyOf(sp, n + 1);
        soLuong = Arrays.copyOf(soLuong, n + 1);
        setN(n + 1);
        // them mon an
        soLuong[n - 1] = sl;
    }

    @Override
    public double price() {
        double price = 0;
        for (int i = 0; i < n; i++) {
            price = price + sp[i].getGiaTien() * soLuong[i];
        }
        return price;
    }

    @Override
    public void taoPhieu(NhanVien a, KhachHang b) {
        // if (a instanceof Manager) {
        // nv = new Manager((Manager) a);
        // }
        // else if (a instanceof FullTime) {
        // nv = new FullTime((Fulltime) a);
        // }
        // else if (a instanceof PartTime) {
        // nv = new PartTime((PartTime) a);
        // }
        Date.Today();
        if (b != null) {
            kh = new KhachHang(b);
        } else {
            kh = null;
        }
    }

    @Override
    public void inPhieu() {
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|                                 QUAN AN                                 |");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.printf("|%-50s", "Ngay: " + Date.toString());
        System.out.println("|");
        System.out.printf("|%-50s", "Ma Bill: " + id);
        System.out.println("|");
        System.out.printf("|%-50s", "Ten NV: " + nv.getHoten());
        System.out.println("|");
        if (kh != null) {
            System.out.println("+-------------------------------------------------------------------------+");
            System.out.println("|Khach hang:                                                    |");
            System.out.println("|");
            System.out.printf("%-50s", "Ten KH: " + kh.getHoten());
            System.out.println("|");
            System.out.printf("%-50s", "Sdt: " + kh.getSdt());
            System.out.println("|");
        }
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.printf("|%-10s", "STT");
        System.out.printf("|%-30s", "Ten mon");
        System.out.printf("|%-10s", "So luong");
        System.out.printf("|%-20s", "Gia tien");
        System.out.println("|");
        for (int i = 0; i < n; i++) {
            System.out.printf("|%-10s", i+1);
            System.out.printf("|%-30s", sp[i].getTenMon());
            System.out.printf("|%-10s", soLuong[i]);
            System.out.printf("|%-20.2f", sp[i].getGiaTien() * soLuong[i]);
            System.out.println("|");
        }
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.printf("|%-50s", "Tien thanh toan: ");
        System.out.printf("|%-20.2f", price());
        System.out.println("|");
        System.out.println("+-------------------------------------------------------------------------+");
    }

    @Override
    public void TachTT(String data, DanhSachNhanVien a, DanhSachMonAn b, DanhSachKhachHang c) {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void xuat() {
        // TODO Auto-generated method stub

    }

}