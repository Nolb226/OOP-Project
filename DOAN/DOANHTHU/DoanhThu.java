package DOANHTHU;

import NHANVIEN.*;

public class DoanhThu extends QuanLyDoanhThu{
    Thue t=new Thue();
    QuanLyDoanhThu b=new QuanLyDoanhThu();
    ChiPhiTonThat cp=new ChiPhiTonThat();
    DanhSachNhanVien dsnv=new DanhSachNhanVien();
    @Override
    public void money(){
        System.out.println("Tong tien bill: "+b.SumOfBill());
    }
    @Override
    public void money_usage(){
        System.out.println("Chi phi: "+cp.usage());
    }
    @Override
    public void money_salary(){
        System.out.println("Luong nhan vien: "+dsnv.calSalary());
    }
    @Override
    public void money_tax(){
        System.out.println("Thue: "+t.tax());
    }
    @Override
    public void profit(){
        System.out.println("Tong loi nhuan: "+(b.SumOfBill()-t.tax()-cp.usage()-dsnv.calSalary()));
    }
}