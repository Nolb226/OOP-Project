package NHANVIEN;

import java.util.Scanner;
import CONNGUOI.*;

public class Manager extends NhanVien{
    private int capbac;
    private String congviec;

    public Scanner sc = new Scanner(System.in);
    
    public  Manager(){
        this.capbac = 0;
        this.congviec = null;
    }

    public Manager(String maNV, String hoten, String diachi, Date ngaySinh, String sdt, String congviec,  int capbac){
        super(maNV, hoten, diachi, ngaySinh, sdt);
        this.capbac=capbac;
        this.congviec=congviec;
    }

    public void setCapbac(int capbac) {
        this.capbac = capbac;
    }

    public int getCapbac() {
        return capbac;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public String getCongviec() {
        return congviec;
    }

    public double calSalary(){
        if(getCapbac()==1){
            return getLuongCoBan()+2000000;
        }
        if(getCapbac()==2){
            return getLuongCoBan()+3000000;
        }
        if(getCapbac()==3){
            return getLuongCoBan()+4000000;
        }
        else{
            return -1;
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
        System.out.print("Nhap cap bac quan ly: ");
        setCapbac(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nhap cong viec cu the: ");
        setCongviec(scanner.nextLine());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + getCongviec() + ";" + getCapbac() + "\n";
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.print(" || " + "Cap bac quan ly: " + getCapbac() + " || " + "Cong viec: " + getCongviec() + "\n");
    }
}
