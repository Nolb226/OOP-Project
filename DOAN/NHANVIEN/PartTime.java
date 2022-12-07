package NHANVIEN;

import CONNGUOI.*;
import java.util.Scanner;

public class PartTime extends NhanVien{
    private String congviec;
    private int giocong;

    public Scanner sc = new Scanner(System.in);

    public  PartTime(){
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

    public void setgiocong(int giocong){
        System.out.println("Moi nhap vao gio cong:");
        giocong=Integer.parseInt(sc.nextLine());
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