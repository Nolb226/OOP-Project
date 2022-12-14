
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
        // DTT.DocFile();
        DanhSachPhieuNhap dspn = new DanhSachPhieuNhap();
        dspn.DocFile();
        DoanhThu DT = new DoanhThu();
        DTT.addDT(DT);
        Account a = new Account();
        Scanner scanner = new Scanner(System.in);

        int choose;
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        do{
            System.out.print(   "|---------------    MENU    ---------------|\n"+
                                "|1.Dang nhap                               |\n"+
                                "|2.Thoat                                   |\n"+
                                "|------------------------------------------|\n");
            System.out.println("Nhap lua chon: ");
            choose=checkLoi.checkSo(scanner.nextLine());
            switch (choose) {
                case 1:
                    int userName = a.Kt_account(dsNV);
                    NhanVien user = dsNV.dsnv[userName];
                    if(user instanceof Manager) {
                        Manager userMN = ((Manager)user);
                        userMN.chamCong();
                        dsNV.GhiFile();
                        user.QuanLy(dsNV, dskh, dsma, dshd, DT, user, dspn, DTT);
                    }
                    else {
                        if(user instanceof PartTime) {
                            PartTime userPT = (PartTime)user;
                            userPT.chamCong();
                        }
                        else {
                            FullTime userFT = (FullTime)user;
                            userFT.chamCong();
                        }
                        
                        dsNV.GhiFile();
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