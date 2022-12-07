package DOANHTHU;

import MONAN.*;

import java.util.Arrays;
import java.util.Scanner;
import NHANVIEN.*;
import CONNGUOI.*;
import MONAN.*;
import KHACHHANG.*;

public class Bill extends Phieu{
  
    private KhachHang kh;
    protected CONNGUOI.Date Date;
    
    Scanner sc=new Scanner(System.in);

    public Bill() {
        id = null;
        n = 0;
        Date = new CONNGUOI.Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public Bill(String mhd) {
        id = mhd;
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
        for(int i=0;i<orther.n;i++) {
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
        //them mon an
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inPhieu() {
        // TODO Auto-generated method stub
        
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