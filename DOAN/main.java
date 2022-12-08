
import KHACHHANG.*;
import NHANVIEN.*;
public class main {
    public static void main(String[] args) {
        DanhSachKhachHang s=new DanhSachKhachHang();
        DanhSachNhanVien ds=new DanhSachNhanVien();
        Account a=new Account();
        do{
            a.Kt_account(ds);
            s.QuanlyKH();
        }while (true);

    }
    
}