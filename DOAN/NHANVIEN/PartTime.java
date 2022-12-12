package NHANVIEN;

import CONNGUOI.*;
import EXCEPTION.checkLoi;

import java.util.Scanner;

public class PartTime extends NhanVien{
    private String congviec;
    private int giocong;

    public Scanner sc = new Scanner(System.in);

    public  PartTime(){
    }

    public PartTime(PartTime a){
        super(a.getMaNV(),a.getHoten(),a.getdiachi(),a.getNgaySinh(),a.getSdt());
        this.giocong=a.giocong;
        this.congviec=a.congviec;
    }

    public  PartTime(String maNV, String hoten, String diachi, Date namsinh, String sdt, String congviec, int giocong){
        super(maNV, hoten, diachi, namsinh, sdt);
        this.giocong=giocong;
        this.congviec=congviec;
    }
    
    public void setcongviec(String congviec){
        System.out.println("Moi nhap vao cong viec:");
        congviec=sc.nextLine();
        this.congviec=congviec;
    }

    public String getcongviec(){
        return congviec;
    }

    public void chamCong() {
        this.giocong += 5;
    }

    public void setgiocong(int giocong){
        System.out.println("Moi nhap vao gio cong:");
        do{
            giocong = checkLoi.checkSo((sc.nextLine()));
            if(giocong == -1)
                System.out.println("Du lieu nhap khong hop le, moi nhap lai !");

        }while(giocong == -1);

        this.giocong=giocong;
    }

    public int getgiocong(){
        return giocong;
    }

    public double calSalary(){
        return (getgiocong()*getLuongCoBanTheoGio())+150000;
    }

    @Override
    public void Nhap(){
        super.Nhap();
        setcongviec(congviec);
        setgiocong(giocong);
    }

    @Override
    public String toString() {
        return super.toString()+";"+getcongviec()+";"+getgiocong()+"\n";     //tinh luong khong ghi len file
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.print("||"+"\tCong viec: "+getcongviec()+"||"+"\tGio Cong: "+getgiocong()+"||"+"\n");
    }
}