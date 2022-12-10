package CONNGUOI;

import java.util.Scanner;

public abstract class ConNguoi {

    public String hoTen;
    protected Date ngaySinh;
    protected String diaChi;
    protected String sdt;

    Scanner scanner = new Scanner(System.in);

    public ConNguoi()
    {
        this.hoTen = null;
        this.sdt = null;
        this.diaChi = null;
        this.ngaySinh = new Date();
    }

    public ConNguoi(ConNguoi temp)
    {
        this.hoTen = temp.hoTen;
        this.ngaySinh = temp.ngaySinh;
        this.diaChi = temp.diaChi;
        this.sdt = temp.sdt;
    }

    public ConNguoi(String hoTen, String diaChi, Date ngaySinh, String sdt)
    {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
    }

    public void setHoten(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getHoten() {
        return hoTen;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
        kt_sdt();
    }

    public String getSdt() {
        return sdt;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setNgaySinh(Date ngaySinh) {
        
        this.ngaySinh = ngaySinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setHoten() {
        System.out.print("nhap ho ten: ");
        hoTen=scanner.nextLine();
    }

    public void setDiaChi() {
        System.out.print("nhap dia chi: ");
        diaChi=scanner.nextLine();
    }
    
    public void setNgaySinh() {
        ngaySinh.nhap();
    }

    public void setSdt() {
        System.out.print("nhap sdt: ");
        this.sdt = scanner.nextLine();
        kt_sdt();
    }

    // public void xuatTen()
    // {
    //     System.out.print("ho va ten la: " + hoTen);
    // }

    // public void xuatDiaChi()
    // {
    //     System.out.print("dia chi la: "+ diaChi);
    // }

    // public void xuatNgaySinh()
    // {
    //     ngaySinh.xuat();
    // }

    public void kt_sdt()
    {
        while(sdt.matches("^0[3|5|7|8|9]\\d{8}$") == false )
        {
            System.out.println("Sai mau '0xxx xxx xxx' moi nhap lai ");
            setSdt(scanner.nextLine());
        }
    }

    public void Nhap() {
        System.out.print("Nhap ho ten: ");
        setHoten(scanner.nextLine());

        ngaySinh.nhap();

        System.out.print("Nhap so dien thoai: ");
        setSdt(scanner.nextLine());

        System.out.print("Nhap dia chi: ");
        setDiaChi(scanner.nextLine());
    }

    public String toString(){
        return hoTen + ',' + ngaySinh.toString() + ',' + sdt + ',' + diaChi;
    }

}
