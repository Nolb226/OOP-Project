package MONAN;

import java.util.Scanner;

public class MonAn {
    String maMon;
    String tenMon;
    double giaTien;

    public Scanner sc = new Scanner(System.in);

    public MonAn() {
    }

    public MonAn(MonAn a) {
        this.maMon = a.maMon;
        this.tenMon = a.tenMon;
        this.giaTien = a.giaTien;
    }

    public MonAn(String maMon, String tenMon, double giaTien) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaTien = giaTien;
    }

    public void setMaMon(String maMon) {

        this.maMon = maMon;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setTenMon(String tenMon) {
        System.out.println("Mời nhập vào tên món: ");
        tenMon = sc.next();
        this.tenMon = tenMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setGiaTien(double giaTien) {
        System.out.println("Mời nhập vào giá tiền của món ăn:");
        System.out.printf("%15s \r", "VNĐ");
        giaTien = sc.nextDouble();
        this.giaTien = giaTien;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void Nhap() {
        // setMaMon(maMon);
        setTenMon(tenMon);
        setGiaTien(giaTien);
    }

    public MonAn clone() {
        return new MonAn(this);
    }

    @Override
    public String toString() {
        return getMaMon() + ";" + getTenMon() + ";" + getGiaTien() + "\n";
    }

    public void Xuat() {
        System.out.println("|" + getMaMon() + "|\t|" + getTenMon() + "|\t|" + getGiaTien() + "|");
    }
}