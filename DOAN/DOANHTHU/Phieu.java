package DOANHTHU;

import KHACHHANG.DanhSachKhachHang;
import MONAN.*;
import CONNGUOI.*;
import KHACHHANG.*;
import NHANVIEN.DanhSachNhanVien;
import NHANVIEN.NhanVien;

public abstract class Phieu {
    protected String id;
    protected Date Date;
    protected NhanVien nv;
    protected int n;
    protected MonAn[] sp;
    protected int[] soLuong;

    public Phieu() {
        id = null;
        Date = new Date();
        nv = null;
        n = 0;
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setSp(MonAn[] sp) {
        this.sp = sp;
    }

    public MonAn[] getSp() {
        return sp;
    }

    public void setSoLuong(int[] soLuong) {
        this.soLuong = soLuong;
    }

    public int[] getSoLuong() {
        return soLuong;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Date getDate() {
        return Date;
    }

    public abstract void taoPhieu(NhanVien a, KhachHang b);

    public abstract void addSP(MonAn a, int sl);

    public abstract double price();

    public abstract void inPhieu();

    public abstract void TachTT(String data, DanhSachNhanVien a, DanhSachMonAn b, DanhSachKhachHang c);

    public abstract String toString();

    public abstract void xuat();
}



