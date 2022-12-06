package NHANVIEN;

import CONNGUOI.Date;

public class testNV {
    public static void main(String[] args) {
        // Date date = new Date(16, 5, 2003);
        // Manager nv1 = new Manager("MN123", "Nguyen Thanh Dat", "12/ dang thuc vinh, dong thanh, hoc mon, hcm", date , "0961198450", 2, "Quan Ly");
       
        Manager nv1 = new Manager();
        nv1.Nhap();
        nv1.Xuat();
    }
}
