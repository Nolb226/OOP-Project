package NHANVIEN;

import java.util.Scanner;
import NHANVIEN.*;
public class Account {
    public String userName;
    public String password;
    Scanner scanner = new Scanner(System.in);

    public Account(String userName  ){
        this.userName=userName;
        this.password="123456";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void Kt_account(DanhSachNhanVien a)
    {   
        do{
            System.out.print("Ten dang nhap: ");
            String us=scanner.nextLine();
            System.out.print("Mat khau: ");
            String pass=scanner.nextLine();
            
        }
    }
}
