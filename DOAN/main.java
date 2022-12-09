
import KHACHHANG.*;
import NHANVIEN.*;

import java.util.Scanner;

import EXCEPTION.*;
public class main {
    public static void main(String[] args) {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsNV = new DanhSachNhanVien();
        Account a = new Account();

        Scanner scanner = new Scanner(System.in);

        int choose;
        // DanhSachNhanVien dsnv = new DanhSachNhanVien();
        do{
            System.out.print(   "|---------------    MENU    ---------------|\n"+
                                "|1.Dang nhap                               |\n"+
                                "|2.Thoat                                   |\n"+
                                "|------------------------------------------|\n");
            choose=checkLoi.checkSo(scanner.nextLine());
            switch (choose) {
                case 1:
                    int userName = a.Kt_account(dsNV);
                    NhanVien user = dsNV.dsnv[userName];
                    if(user instanceof Manager) {
                        user = ((Manager)user);
                        user.QuanLy(dsNV);
                    }
                    else {
                        user.QuanLy(dsNV);
                    }
                    dskh.QuanlyKH();
                    
                    break;
                case 2:
                    break;    
                default:
                    break;
            }
        }while(choose != 2);   
    }
    
}