package NHANVIEN;

import java.util.Scanner;
import NHANVIEN.*;
import EXCEPTION.*;
public class Account {
    public String userName;
    public String password;
    Scanner scanner = new Scanner(System.in);

    public Account(String userName  ){
        this.userName=userName;
        this.password="123456";
    } 
    public Account( ){
        this.userName=null;
        this.password="123456";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int Kt_account(DanhSachNhanVien a)
    {   
        do{
            System.out.print("Ten dang nhap: ");
            String us=scanner.nextLine();
            System.out.print("Mat khau: ");
            String pass=scanner.nextLine();
            int kt = a.TimKiemNhanVienReturnIndex(us);
            if(kt != -1)
            {
                do{
                    if(a.dsnv[kt].getpassword().equals(pass))
                    {
                        return kt;
                    }
                    else
                    {
                        System.out.print("Mat khau sai moi nhap lai: ");
                        pass=scanner.nextLine();
                    }
                }while(true);
            }else{
                System.out.println("Ten dang nhap sai!");
            }
        }while(true);
    }

    public void doi_pass()
    {  
        int choose;
        do{
            System.out.print(   "|---------------Doi mat khau---------------|\n"+
                                "|1.Doi mat khau                            |\n"+
                                "|2.Thoat                                   |\n"+
                                "|------------------------------------------|\n");
            choose=checkLoi.checkSo(scanner.nextLine());
            switch (choose) {
                case 1:
                    System.out.print("Nhap lai mat khau cu: ");
                    String oldPass=scanner.nextLine();
                    if(password.equals(oldPass)){
                        do{
                            System.out.print("Nhap mat khau moi:");
                            String newPass=scanner.nextLine();
                            System.out.print("Nhap lai mat khau moi: ");
                            String rePass=scanner.nextLine();
                            if(newPass.equals(rePass))
                            {
                                setPassword(newPass);
                                return ;
                            }else
                            {
                                System.out.println("Mat khau khong khop!");
                            }
                        }while(true);
                    }
                    break;
                case 2:
                    break;    
                default:
                    break;
            }
        }while(choose != 2);   
    }
}
