package DOANHTHU;

import NHANVIEN.*;

public class Thue extends QuanLyDoanhThu{
    final float landtax=5000000;
    float thuebaohiem=0;
    float sumtax=0;
    DanhSachNhanVien dsnv=new DanhSachNhanVien();
    public float tax(){
        thuebaohiem+=dsnv.calSalary()*0.05;
        sumtax=thuebaohiem+landtax;
        return sumtax;
    }
}
