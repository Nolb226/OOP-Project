
import KHACHHANG.*;
import NHANVIEN.*;
public class main {
    public static void main(String[] args) {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsNV = new DanhSachNhanVien();
        Account a=new Account();
        do{
            int userName = a.Kt_account(dsNV);
            NhanVien user = dsNV.dsnv[userName];
            if(user instanceof Manager) {
                user.QuanLy();
            }
            else {
                user.QuanLy();
            }
            // s.QuanlyKH();
        }while (true);

    }
    
}