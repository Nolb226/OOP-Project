package DOANHTHU;

import java.util.Scanner;
import EXCEPTION.*;

public class QuanLyDoanhThu {
    
    private static Scanner input = new Scanner(System.in);

    public static void QuanLyDT() {
        DanhSachPhieuNhap DSPN = new DanhSachPhieuNhap();
        DSPN.DocFile();
        DanhSachHoaDon DSHD = new DanhSachHoaDon();
        DSHD.DocFile();
        DoanhThuThang DTT = new DoanhThuThang();
        DTT.DocFile();
        int choice;
        do {
            System.out.println("");
            System.out.println("+---------------------------------------+");
            System.out.println("|            QUAN LY DOANH THU          |");
            System.out.println("+---------------------------------------+");
            System.out.println("|1. Lich su phieu nhap                  |");
            System.out.println("|2. Lich su hoa don                     |");
            System.out.println("|3. Thong ke doanh thu                  |");
            System.out.println("|0. Tro ve menu chinh.                  |");
            System.out.println("+---------------------------------------+");
            do {
                System.out.print("Moi nhap lua chon: ");
                choice = checkLoi.inputIntNumberError(input.nextLine());
                if (choice < 0 || choice > 3) {
                    System.out.println("Lua chon khong hop le, moi nhap lai!");
                }
            } while (choice > 3 || choice < 0);

            switch (choice) {
                case 1: {
                    DSPN.xemPhieuNhap();
                    break;
                }

                case 2: {
                    DSHD.xemHoaDon();
                    break;
                }

                case 3: {
                    DTT.thongKe();
                    System.out.print("Nhan phim bat ki de tiep tuc.");
                    input.nextLine();
                }

                case 0: {
                    break;
                }
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        QuanLyDoanhThu a = new QuanLyDoanhThu();
        a.QuanLyDT();
    }
}
