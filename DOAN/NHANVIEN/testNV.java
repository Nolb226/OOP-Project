package NHANVIEN;

import CONNGUOI.Date;
import NHANVIEN.Account;

public class testNV {
    public static void main(String[] args) {
        
        // Manager nv1 = new Manager();
        // nv1.Nhap();
        // nv1.Xuat();

        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        Account a= new Account();
        a.Kt_account(dsnv);
    }
}
