package DOANHTHU;

import java.util.Scanner;

public class ChiPhiTonThat extends QuanLyDoanhThu{
    int chiphi;
    int tong;
    Scanner input=new Scanner(System.in);
    public void nhapchiphi(){
        int flag;
        do{
            tong=0;
            System.out.println("|----------------------Menu--------------------|");
            System.out.println("|1.Chi phi                                     |");
            System.out.println("|0.Thoat                                       |");
            System.out.println("|----------------------------------------------|");
            System.out.println("Nhap yeu cau");
            flag=input.nextInt();
            switch(flag){
                case 1: System.out.println("Nhap tien sua chua: ");
                        chiphi=input.nextInt();
                        tong+=chiphi;
                        break;
                case 0: break;
                default: System.out.println("Nhap khong dung yeu cau");
            }
        }while(flag!=0);
    }
    public int getchiphi() {
        return chiphi;
    }
    public void setchiphi(int chiphi) {
        this.chiphi = chiphi;
    }
    @Override
    public int usage(){
        return tong;
    }
}
