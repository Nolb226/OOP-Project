package MONAN;
import java.util.Scanner;

public class MonAn {
    String maMon;
    String tenMon;
    double giaTien;

    public Scanner sc = new Scanner(System.in);

    public MonAn(){}

    public MonAn(String maMon, String tenMon, double giaTien){
        this.maMon=maMon;
        this.tenMon=tenMon;
        this.giaTien=giaTien;
    }

    public void setMaMon(String maMon) {
        System.out.println("Moi nhap vao ma mon an:");
        maMon=sc.nextLine();
        this.maMon = maMon;
    }

    public String getMaMon() {
        return maMon;
    }
    public void setTenMon(String tenMon) {
        System.out.println("Moi nhap vao ten mon: ");
        tenMon=sc.nextLine();
        this.tenMon = tenMon;
    }
    
    public String getTenMon() {
        return tenMon;
    }

    public void setGiaTien(double giaTien) {
        System.out.println("Moi nhap vao gia tien cua mon an:");
        giaTien = sc.nextDouble();
        this.giaTien = giaTien;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void Nhap () {
        setMaMon(maMon);
        setTenMon(tenMon);
        setGiaTien(giaTien);
    }

    @Override
    public String toString (){
        return getMaMon()+";"+ getTenMon()+";"+ getGiaTien()+"\n";
    }
    
    public void Xuat(){
        System.out.println("|"+getMaMon()+"|\t|"+ getTenMon()+"|\t|"+ getGiaTien()+"|");
    }
}