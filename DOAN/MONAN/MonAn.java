package MONAN;

import java.util.Scanner;

import EXCEPTION.checkLoi;

public class MonAn {
    protected String maMon;
    protected String tenMon;
    // protected Double giaTien;
    protected Integer soLuong;
    protected Double giaBan;
    protected Double giaNhap;

    public Scanner sc = new Scanner(System.in);

    public MonAn() {
        maMon = null;
        tenMon = null;
        soLuong = 0;
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
        System.out.println("Nhập giá bán của " + this.getTenMon());
        System.out.print("                      VNĐ\r");
        giaBan = checkLoi.checkSoThuc(sc.next());
        while (giaBan == -1) {
            System.out.println("Sai định dạng, vui lòng nhập lại");
            System.out.print("                      VNĐ\r");
            giaBan = checkLoi.checkSoThuc(sc.next());
        }
        this.giaBan = giaBan;

    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaNhap(Double giaNhap) {
        System.out.println("Nhập giá nhập hàng của " + this.getTenMon());
        System.out.print("                      VNĐ\r");
        giaNhap = checkLoi.checkSoThuc(sc.next());
        while (giaNhap == -1) {
            System.out.println("Sai định dạng, vui lòng nhập lại");
            System.out.print("                      VNĐ\r");
            giaNhap = checkLoi.checkSoThuc(sc.next());

        }
        this.giaNhap = giaNhap;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setSoLuong(int sl) {
        soLuong = sl;
    }

    public void setSoLuong() {
        System.out.println("Nhập số lượng " + this.getTenMon());
        System.out.print("                        Cái/Ly/Lon\r");
        soLuong = checkLoi.checkSo(sc.next());
        while (soLuong == -1) {
            System.out.println("Sai định dạng, vui lòng nhập lại");
            System.out.print("                      VNĐ\r");
            soLuong = checkLoi.checkSo(sc.next());

        }
        this.soLuong = soLuong;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void Nhap() {
        // setMaMon(maMon);
        setTenMon(tenMon);
        setSoLuong();
        setGiaBan(giaBan);
        setGiaNhap(giaNhap);

    }

    public MonAn clone() {
        return new MonAn(this);
    }

    @Override
    public String toString() {
        return getMaMon() + ";" + getTenMon() + ";" + getSoLuong() + ";" + getGiaBan() + ";" + getGiaNhap() + ";"
                + "\n";
    }

    public void Xuat() {
        System.out.println("|" + getMaMon() + "|\t|" + getTenMon() + "|\t|" + getSoLuong() + "|");
    }
}