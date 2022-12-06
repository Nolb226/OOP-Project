package NHANVIEN;

import CONNGUOI.*;
import java.util.Scanner;

public class FullTime extends NhanVien{  
    private String congviec;
    private int ngaycong;

    public Scanner sc = new Scanner(System.in);

    public  FullTime(){
    }

    public FullTime(String maNV, String hoten, String diachi, Date namsinh, String sdt, String congviec,int ngaycong){
        super(maNV, hoten, diachi, namsinh, sdt);
        this.ngaycong=ngaycong;
        this.congviec=congviec;
    }

    public void setngaycong(int ngaycong){
        System.out.println("Moi nhap vao so ngay cong: ");
        ngaycong=Integer.parseInt(sc.nextLine());
        this.ngaycong=ngaycong;
    }

    public int getngaycong(){
        return ngaycong;
    }
    
    public void setcongviec(String congviec){
        System.out.println("Moi nhap vao cong viec: ");
        congviec=sc.nextLine();
        this.congviec=congviec;
    }

    public String getcongviec(){
        return congviec;
    }
    
    public double calSalary(){
        if(getngaycong()<20){
            return getluongcoban()-getluongcoban()*0.15;
        }
        else{
            return getluongcoban();
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
        setcongviec(congviec);
        setngaycong(ngaycong);
    }

    @Override
    public String toString() {
        return super.toString()+";"+getcongviec()+";"+getngaycong()+"\n";
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.print("||"+"\tCong viec: "+getcongviec()+"||"+"\tNgay Cong: "+getngaycong()+"||"+"\n");
    }

}