package KHACHHANG;

import java.util.Scanner;

public class connguoi {
    protected String Hoten;
    protected Date ngaysinh=new Date();
    protected String diachi;
    protected String Sdt;
    Scanner scanner=new Scanner(System.in);
    public connguoi()
    {
        Hoten=null;
        Sdt=null;
        diachi=null;
    }

    public connguoi(connguoi temp)
    {
        this.Hoten=temp.Hoten;
        this.ngaysinh=temp.ngaysinh;
        this.diachi=temp.diachi;
        this.Sdt=temp.Sdt;
    }



    public connguoi(String Hoten,String diachi,int ngay,int thang,int nam,String Sdt)
    {
        this.Hoten=Hoten;
        // this.diachi=new Diachi(sonha, duong, ap, quan, thanhpho);
        this.diachi=diachi;
        this.ngaysinh=new Date(ngay, thang, nam);
        this.Sdt=Sdt;
    }

    public connguoi(String Hoten,String diachi,Date ngaysinh,String Sdt)
    {
        this.Hoten=Hoten;
        // this.diachi=new Diachi(sonha, duong, ap, quan, thanhpho);
        this.diachi=diachi;
        this.ngaysinh=ngaysinh;
        this.Sdt=Sdt;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }
    public void setSdt(String sdt) {
        Sdt = sdt;
        kt_Sdt();
    }
    public void setSdt() {
        System.out.print("nhap so dien thoai: ");
        Sdt=scanner.nextLine();
        kt_Sdt();
    }
    public String getSdt() {
        return Sdt;
    }
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    public String getHoten() {
        return Hoten;
    }
    public String getDiachi() {
        return diachi;
    }
    public Date getNgaysinh() {
        return ngaysinh;
    }
    public void setHoten() {
        System.out.print("nhap ho ten: ");
        Hoten=scanner.nextLine();
    }
    public void setDiachi() {
        System.out.print("nhap dia chi: ");
        diachi=scanner.nextLine();
    }
    public void setNgaysinh() {
        ngaysinh.nhap();
    }
    public void xuatten()
    {
        System.out.print("ho va ten la: "+Hoten);
    }
    // public void xuatdiachi()
    // {
    //     diachi.xuat();
    // }
    public void xuatdiachi()
    {
        System.out.print("dia chi la: "+diachi);
    }
    public void xuatngaysinh()
    {
        ngaysinh.xuat();
    }
    public void kt_Sdt()
    {
        while(Sdt.matches("^0[3|5|7|8|9]\\d{8}$") == false )
        {
            System.out.println("Sai mau '0xxx xxx xxx' moi nhap lai ");
            setSdt(scanner.nextLine());
        }
    }
    public void nhap()
    {
        System.out.print("nhap ho ten: ");
        Hoten=scanner.nextLine();
        setSdt();
        System.out.println("nhap nam sinh:");
        ngaysinh.nhap();
        // System.out.println("nhap dia chi: ");
        // diachi.nhap();
        setDiachi();
    }

    public String toString(){
        return Hoten+','+ngaysinh.toString() +','+Sdt+','+diachi+'\n';
    }

}
