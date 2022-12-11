
import KHACHHANG.*;
import MONAN.DanhSachMonAn;
import NHANVIEN.*;

import java.util.Scanner;

import DOANHTHU.DanhSachHoaDon;
import DOANHTHU.DanhSachPhieuNhap;
import DOANHTHU.DoanhThu;
import DOANHTHU.DoanhThuThang;
import EXCEPTION.*;
public class main {
    public static void main(String[] args) {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsNV = new DanhSachNhanVien();
        DanhSachMonAn dsma = new DanhSachMonAn();
        DanhSachHoaDon dshd = new DanhSachHoaDon();
        dshd.DocFile();
        DoanhThuThang DTT = new DoanhThuThang();
        DTT.DocFile();
        DanhSachPhieuNhap dspn = new DanhSachPhieuNhap();
        dspn.DocFile();
        DoanhThu DT = new DoanhThu();
        DTT.addDT(DT);
        Account a = new Account();

        Scanner scanner = new Scanner(System.in);

        int choose;
        // DanhSachNhanVien dsnv = new DanhSachNhanVien();
        do{
            System.out.print(   "\t\t=========    MENU    =========\n\n"+
                                "\t\t\t1.Dang nhap                   \n"+
                                "\t\t\t2.Thoat                       \n"+
                                "\n\t\t==============================\n\n");
            System.out.print("Nhap lua chon: ");
            choose=checkLoi.checkSo(scanner.nextLine());
            switch (choose) {
                case 1:
                    int userName = a.Kt_account(dsNV);
                    NhanVien user = dsNV.dsnv[userName];
                    if(user instanceof Manager) {
                        user = ((Manager)user);
                        user.QuanLy(dsNV, dskh, dsma, dshd, DT, user, dspn, DTT);
                    }
                    else {
                        user.QuanLy(dsNV, dskh, dsma, dshd, DT, user, dspn, DTT);
                    }
                    
                    
                    break;
                case 2:
                    break;    
                default:
                    break;
            }
        }while(choose != 2);   
    }
    
}