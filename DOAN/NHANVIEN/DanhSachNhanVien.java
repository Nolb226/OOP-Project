package NHANVIEN;

import java.util.Random;
import java.util.Scanner;
import java.io.*;
import KHACHHANG.*;
import INTERFACE.*;

public class DanhSachNhanVien extends NhanVien implements DocGhiFile{
    NhanVien[] dsnv; 
    int stt;

    public DanhSachNhanVien(){
        dsnv = null;
        stt = 0 ;
        dsnv= new NhanVien[100];
        DocFile();
    }

    public Scanner sc = new Scanner(System.in);

    @Override
    public double calSalary() {     //Ham tinh tong luong cua tat ca nhan vien tren he thong
        double sumSalary=0;       
        for (int i=0;i<stt;i++){
            sumSalary +=dsnv[i].calSalary();
        }
        return sumSalary;
    }

    public boolean checkmaNV(String mnv) {
        boolean diff = true;
        for (int i=0;i<stt;i++) {
            if (dsnv[i].getmaNV().equalsIgnoreCase(mnv)) {
                diff = true;
                break;
            }
        }
        return diff;
    }

    public String taoMNVF(){
        Random rd=new Random();
        String mnv;
        while (true) {
            mnv="FT"+rd.nextInt(1000,9999);
            if(checkmaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVP(){
        Random rd=new Random();
        String mnv;
        while (true) {
            mnv="PT"+rd.nextInt(1000,9999);
            if(checkmaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVM(){
        Random rd=new Random();
        String mnv;
        while (true) {
            mnv="MN"+rd.nextInt(1000,9999);
            if(checkmaNV(mnv))
                break;
        }
        return mnv;
    }

    public void DocFile(){
            try{
                String line;
                FileReader fr=new FileReader("NHANVIEN/NhanVien");
                BufferedReader br=new BufferedReader(fr); 
                while(true){
                    line=br.readLine();
                    if(line==null) {
                        break;
                    }
                    String []st=line.split(";");
                    String ma,ten,diachi,sdt; Date birthDate=new Date();
                    ma=st[0]; ten=st[1]; diachi=st[2]; birthDate.xulyngay(st[3]); sdt = st[4];
                    if(ma.contains("PT")){
                        String congviec;
                        int giocong;
                        congviec = st[5];
                        giocong = Integer.parseInt(st[6]);
                        dsnv[stt]=new PartTime(ma,ten,diachi,birthDate,sdt,congviec,giocong);
                        stt++;
                    }
                    if(ma.contains("FT")){
                        String congviec;
                        int ngaycong;
                        congviec = st[5];
                        ngaycong=Integer.parseInt(st[6]);
                        dsnv[stt]=new FullTime(ma,ten,diachi,birthDate,sdt,congviec,ngaycong);
                        stt++;
                    }
                    if(ma.contains("MN")){
                        int capbac;
                        String congviec;
                        capbac = Integer.parseInt(st[5]);
                        congviec = st[6];
                        dsnv[stt]= new Manager(ma,ten,diachi,birthDate,sdt,congviec,capbac);
                        stt++;
                    }
                }
                br.close();
                fr.close();
            }catch(IOException ex){
                System.out.println("Loi doc du lieu len file");
            }
    }

    // public void GhiFile(String input){
    //     try{
    //         FileWriter fw = new FileWriter("NHANVIEN/NhanVien",true);
    //         BufferedWriter bw = new BufferedWriter(fw);
    //         bw.write(input);
    //         bw.close();
    //         fw.close();
    //     } catch (IOException e ){
    //         System.out.println("Loi ghi du lieu len file!");
    //     }
    // }
    public void GhiFile()
    {
        try {
            // FileWriter fw = new FileWriter("KHACHHANG/text");
            PrintWriter pw = new PrintWriter("NHANVIEN/text");
            // BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<stt;i++){
                // s=s+List.get(i).toString();
                pw.println(dsnv[i].toString());
            }
        // bw.write(temp);
        // bw.close();
        pw.flush();
        pw.close();
        } catch (Exception e) {
            System.out.println("Loi ghhi File");
        }
        System.out.println("Success...");
    }
    public void ThemNhanVien(){    
        int chose;  
        do{  
        System.out.println("|-----------------------------------MENU-----------------------------------|");
        System.out.println("|1.Them vao he thong nhan vien Parttime                                    |");
        System.out.println("|2.Them vao he thong nhan vien Fulltime                                    |");
        System.out.println("|3.Them vao he thong nhan vien Manager                                     |");
        System.out.println("|Chu y ma nhan vien cua Parttime(PTxx),cua Fulltime(FTxx),cua Manager(MNxx)|");
        System.out.println("|4.Thoat                                                                   |");
        System.out.println("|--------------------------------------------------------------------------|");
        System.out.println("Nhap lua chon: ");
        chose = Integer.parseInt(sc.nextLine());
        switch (chose) {
            case 1:{
                int n;
                System.out.println("Nhap vao so luong nhan vien Parttime can them: ");
                n=Integer.parseInt(sc.nextLine());
                NhanVien[] newPT = new NhanVien[n];
                for(int i = 0 ;i<n;i++){
                    newPT[i]=new PartTime();
                    System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                    newPT[i].Nhap();
                    newPT[i].setMaNV(taoMNVP());
                }
                GhiFile();
            }
            break;
                
            case 2:{
                int n;
                System.out.println("Nhap vao so luong nhan vien Fulltime can them: ");
                n=Integer.parseInt(sc.nextLine());
                NhanVien[] newFT = new NhanVien[n];
                for(int i = 0 ;i<n;i++){
                    newFT[i]=new FullTime();
                    System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                    newFT[i].Nhap();
                    newFT[i].setMaNV(taoMNVF());
                }
                GhiFile();
            }
            break;
              
            case 3:{
                int n;
                System.out.println("Nhap vao so luong nhan vien Manager can them: ");
                n=Integer.parseInt(sc.nextLine());
                NhanVien[] newMN = new NhanVien[n];
                for(int i = 0 ;i<n;i++){
                    newMN[i]=new Manager();
                    System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                    newMN[i].Nhap();
                    newMN[i].setMaNV(taoMNVM());
                }
                GhiFile();
            }
            break;

            case 4:continue;

            default:System.out.println("Nhap sai moi nhap lai!");   
            break;          
        }
        }while(chose !=4);
    }

    public void XoaNhanVien(){     
        int n;
        System.out.println("Moi nhap vao so luong nhan vien can xoa tren he thong: ");
        n=Integer.parseInt(sc.nextLine());
        for(int i = 0;i<n;i++){
            System.out.printf("-------Moi nhap vao ma nhan vien thu %d can xoa-------\n",i+1);
            String input;
            input=sc.nextLine();
            for(int j=0;j<stt;j++){
                if(dsnv[j].getmaNV().equalsIgnoreCase(input)){
                    for(int u = j ;u<stt-1;u++){
                        dsnv[u]=dsnv[u+1];
                    }
                    stt--;
                }
            }
        }
        GhiFile();
    }

    public void TinhLuong(){       //Tinh luong cua tat ca nhan vien
        for(int i=0;i<stt;i++){
            System.out.printf("-------------Nhan vien thu %d-------------\n",i+1);
            dsnv[i].Xuat();
            System.out.println("\n"+dsnv[i].calSalary());
        }
    }

    public void XuatDanhSach(){     //Xuat ra tat ca nhan vien tren he thong
        if(dsnv[0]==null){
            System.out.println("Khong co du lieu tren he thong!");
        }
        for(int i=0;i<stt;i++){
            System.out.printf("---------Nhan vien thu %d----------\n",i+1);
            dsnv[i].Xuat();
        }
    }

    public void TinKiemNhanVien(String input){
        int count=0;
        for(int i=0;i<stt;i++){
            if(dsnv[i].getmaNV().equalsIgnoreCase(input)){
                dsnv[i].Xuat();
                System.out.println("Luong: "+dsnv[i].calSalary());
                count++;
            }
        }
        if(count==0)
        System.out.println("Khong tim thay nhan vien: ");
    }
}