package DOANHTHU;

import NHANVIEN.*;
import java.util.Scanner;

public class ChiPhiTonThat extends QuanLyDoanhThu{
    int cpsuachua;
    int cpvatlieu;
    int tong;
    Scanner input=new Scanner(System.in);
    public void nhapchiphi(){
        int choose;
        do{
            tong=0;
            System.out.println("|----------------------Menu--------------------| ");
            System.out.println("|1.tien sua chua:                              |");
            System.out.println("|2.tien vat lieu:                              |");
            System.out.println("|3.Thoat                                       |");
            System.out.println("|----------------------------------------------| ");
            System.out.println("Nhap yeu cau");
            choose=input.nextInt();
            switch(choose){
                case 1: System.out.println("Nhap tien sua chua: ");
                        cpsuachua=input.nextInt();
                        tong+=cpsuachua;
                        break;
                case 2: System.out.println("Nhap tien vat lieu: ");
                        cpvatlieu=input.nextInt();
                        tong+=cpvatlieu;
                        break;
                case 3: break;
                default: System.out.println("nhap khong dung yeu cau");
            }
        }while(choose!=3);
    }
    public int getCpsuachua() {
        return cpsuachua;
    }
    public int getCpvatlieu() {
        return cpvatlieu;
    }
    public void setCpsuachua(int cpsuachua) {
        this.cpsuachua = cpsuachua;
    }
    public void setCpvatlieu(int cpvatlieu) {
        this.cpvatlieu = cpvatlieu;
    }
    @Override
    public int usage(){
        return tong;
    }
}
