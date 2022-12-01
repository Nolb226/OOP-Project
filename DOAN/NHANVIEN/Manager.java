package NHANVIEN;

import java.util.Scanner;
import KHACHHANG.Date;

public class Manager extends NhanVien{
    private int capbac;
    private String congviec;

    public Scanner sc = new Scanner(System.in);
    
    public  Manager(){
    }

    public Manager(String maNV, String hoten, String diachi, Date namsinh, String sdt, String congviec,int capbac){
        super(maNV, hoten, diachi, namsinh, sdt);
        this.capbac=capbac;
        this.congviec=congviec;
    }

    public void setCapbac(int capbac) {
        System.out.println("Moi nhap vao cap bac: ");
        capbac=Integer.parseInt(sc.nextLine());
        this.capbac = capbac;
    }

    public int getCapbac() {
        return capbac;
    }

    public void setCongviec(String congviec) {
        System.out.println("Moi nhap vao cong viec: ");
        congviec=sc.nextLine();
        this.congviec = congviec;
    }

    public String getCongviec() {
        return congviec;
    }

    public double calSalary(){
        if(getCapbac()==1){
            return getluongcoban()+2000000;
        }
        if(getCapbac()==2){
            return getluongcoban()+3000000;
        }
        if(getCapbac()==3){
            return getluongcoban()+4000000;
        }
        else{
            return -1;
        }
    }

    @Override
    public void Nhap(){
        super.Nhap();
        setCapbac(capbac);
        setCongviec(congviec);
    }

    @Override
    public String toString() {
        return super.toString()+getCongviec()+";"+getCapbac()+"\n";
    }

    @Override
    public void Xuat(){
        super.Xuat();
        System.out.println("||"+"\tCap bac quan ly: "+getCapbac()+"||"+"\tCong viec: "+getCongviec()+"||"+"\n");
    }
}
