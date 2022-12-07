package DOANHTHU;

import MONAN.*;

import java.util.Arrays;
import java.util.Scanner;
import NHANVIEN.*;
import KHACHHANG.*;

public class Bill extends Phieu{

    
    
    private KhachHang kh;


    Scanner sc=new Scanner(System.in);

    public Bill() {
        id = null;
        n = 0;
        Date = new Date();
        sp = new SanPham[n];
        soLuong = new int[n];
    }

    public Bill(String mhd) {
        id = mhd;
        n = 0;
        Date = new Date();
        sp = new SanPham[n];
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
    public void addSP(SanPham a, int sl) {
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
            price = price + sp[i].getGiaBan() * soLuong[i];
        }
        
            return price;
       
    }

}