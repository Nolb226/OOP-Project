package DOANHTHU;

import NHANVIEN.*;

public class DoanhThu extends QuanLyDoanhThu{
    Thue t=new Thue();
    Bill b=new Bill();
    ChiPhiTonThat cp=new ChiPhiTonThat();
    DanhSachNhanVien dsnv=new DanhSachNhanVien();
    @Override
    public void money(){
        System.out.println("tong tien bill: "+b.SumOfBill());
    }
    @Override
    public void profit(){
        System.out.println("tong tien bill: "+(b.SumOfBill()-t.tax()-cp.usage()-dsnv.calSalary()));
    }
}
