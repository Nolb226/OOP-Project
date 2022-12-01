package DOANHTHU;

import MONAN.*;
import java.util.Scanner;
import NHANVIEN.*;

public class Bill extends QuanLyBanHang{
    String name,custom,date;
    double tongTienBill;
    static double Sum=0;

    Scanner sc=new Scanner(System.in);

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        System.out.println("Moi nhap vao ngay: ");
        date=sc.nextLine();
        this.date = date;
    }
    
    public String getNameid() {
        return name;
    }

    public void setNameid(String name) {
        System.out.println("Moi nhap vao ho ten nhan vien: ");
        name=sc.nextLine();
        this.name = name;
    }

    public String getCustom() {
        return custom;
    }
    
    public void setCustom(String custom) {
        System.out.println("Moi nhap vao ten khach hang: ");
        custom=sc.nextLine();
        this.custom = custom;
    }

    @Override
    public void NhapThongTin(){
        DanhSachMonAn dsma= new DanhSachMonAn();
        setDate(date);
        setNameid(name);
        setCustom(custom);
        tongTienBill=0;     //Khoi tao gia tri
        tongTienBill=dsma.Order();
        if(tongTienBill!=-1){
            System.out.println("-----------------------Tong so tien phai thanh toan: "+tongTienBill+"--------------------");
            System.out.println(toString());
            System.out.println("=======================================================================================");
            Sum+=tongTienBill;
        }
        else{
            System.out.println("Loi he thong order !");
        }
    }

    @Override
    public String toString() {
        return "Ngay order: "+ getDate()+ "-------Ten nhan vien thu tien: "+getNameid()+"------Ten khach hang thanh toan: "+getCustom();
    }

    @Override
    public double SumOfBill(){
        return Sum;
    }
}