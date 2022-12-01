
import KHACHHANG.*;
public class main {
    public static void main(String[] args) {
        // KhachHang s=new KhachHang("Nguyen Ngoc Son","49/1Q","NHC","My Hue","Hoc Mon","TPHCM",20,05,2003,"0898504720");
        // s.Nhap();
        // s.Xuat();
        DanhSachKhachHang s=new DanhSachKhachHang();
        //s.Nhap();
        s.DocFile();
        s.Xuat();

        s.NhapThemKh();
        s.Xuat();
        // s.NhapThemKh();
        // s.suaKH();
        // s.writeFile();
        // s.Xuat();
    }
    
}