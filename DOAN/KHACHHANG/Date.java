package KHACHHANG;

import java.util.Scanner;

public class Date {
    protected int ngay;
    protected int thang;
    protected int nam;
    Scanner scanner=new Scanner(System.in);
    Scanner input=new Scanner(System.in);
    public Date()
    {
        ngay=1;
        thang=1;
        nam=1;
    }
    public Date(int ngay,int thang,int nam)
    {
        this.ngay=ngay;
        this.thang=thang;
        this.nam=nam;
    }
    public Date(Date temp)
    {
        this.ngay=temp.ngay;
        this.thang=temp.thang;
        this.nam=temp.nam;
    }
    public void setNam(int nam) {
        this.nam = nam;
    }
    public void setNgay(int ngay) {
        this.ngay = ngay;
    }
    public void setThang(int thang) {
        this.thang = thang;
    }
    public int getNam() {
        return nam;
    }
    public int getNgay() {
        return ngay;
    }public int getThang() {
        return thang;
    }
    public void setNam() {
        System.out.print("nhap nam:");
        nam=scanner.nextInt();

    }
    public void setNgay() {
        System.out.print("nhap thang:");
        thang=scanner.nextInt();
    }
    public void setThang() {
        System.out.print("nhap thang:");
        thang=scanner.nextInt();
    }
    public void nhap()
    {
        boolean check = true;
        do {
            System.out.print("Nhap ngay thang nam (dd/mm/yy): ");
            String s = input.nextLine();
            String a[] = s.split("/");
            setNam(Integer.parseInt(a[2]));
            if (checkMonth(Integer.parseInt(a[1]))) {
                check = true;
                setThang(Integer.parseInt(a[1]));
                if (checkDay(Integer.parseInt(a[0]))) {
                    check = true;
                    setNgay(Integer.parseInt(a[0]));
                } else {
                    check = false;
                    System.out.println("Nhap sai thong tin, moi nhap lai!");
                }
            } else {
                check = false;
                System.out.println("Nhap sai thong tin, moi nhap lai!");
            }
        } while (check == false);
    }
    public void xuat()
    {
        System.out.print(ngay+"/"+thang+"/"+nam);
    }
    public String toString()
    {
        return ngay+"/"+thang+"/"+nam;
    }

    public boolean checkYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    public boolean checkMonth(int month) {
        if (month > 12 || month < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void xulyngay(String temp)
    {
        String Birthday[] = temp.split("/");
        this.ngay = Integer.parseInt(Birthday[0]);
        this.thang = Integer.parseInt(Birthday[1]);
        this.nam = Integer.parseInt(Birthday[2]);
    }

    public boolean checkDay(int d) {
        boolean day = true;
        int DayinT2;
        if (checkYear(nam)) {
            DayinT2 = 29;
        } else {
            DayinT2 = 28;
        }
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                if (d > 30 || d < 1) {
                    day = false;
                }
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {

                if (d > 31 || d < 1) {
                    day = false;
                }
                break;
            }
            case 2: {
                if (d > DayinT2 || d < 1) {
                    day = false;
                }
            }
        }
        return day;
    }
}
