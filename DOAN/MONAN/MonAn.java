package MONAN;

import java.util.Scanner;

public class MonAn {
    protected String maMon;
    protected String tenMon;
    // protected Double giaTien;
    protected Integer soLuong;
    protected Double giaBan;
    protected Double giaNhap;

    public Scanner sc = new Scanner(System.in);

    public MonAn() {
        maMon = "";
        tenMon = "";
        giaBan = 0.0;
        giaNhap = 0.0;
    }

    public MonAn(MonAn a) {
        this.maMon = a.maMon;
        this.tenMon = a.tenMon;
        this.soLuong = a.soLuong;
        this.giaBan = a.giaBan;
        this.giaNhap = a.giaNhap;

    }

    public MonAn(String maMon, String tenMon, Integer soLuong, Double giaBan, Double giaNhap) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
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

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void Nhap() {
        // setMaMon(maMon);
        setTenMon(tenMon);

    }

    public MonAn clone() {
        return new MonAn(this);
    }

    @Override
    public String toString() {
        return getMaMon() + ";" + getTenMon() + ";" + getSoLuong() + ";" + getGiaBan() + ";" + getGiaNhap() + ";"+ "\n";
    }

    public void Xuat() {
        System.out.println("|" + getMaMon() + "|\t|" + getTenMon() + "|\t|" + getSoLuong() + "|");
    }
}