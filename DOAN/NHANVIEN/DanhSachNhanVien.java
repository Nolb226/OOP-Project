package NHANVIEN;

import java.util.Random;
import java.util.Scanner;
import java.io.*;
import CONNGUOI.*;
import INTERFACE.*;
import KHACHHANG.checkLoi;

public class DanhSachNhanVien extends NhanVien implements DocGhiFile{
    NhanVien[] dsnv; 
    int stt;

    public static String fileNameNV = "DOAN/NHANVIEN/NhanVien.txt";

    public DanhSachNhanVien(){
        stt = 0 ;
        dsnv = new NhanVien[100];
        DocFile();
    }

    public Scanner sc = new Scanner(System.in);

    @Override
    // TINH LUONG TAT CA NHAN VIEN 
    public double calSalary() {     
        double sumSalary = 0;       
        for (int i = 0; i < stt; i++){
            sumSalary += dsnv[i].calSalary();
        }
        return sumSalary;
    }
    

    public boolean checkMaNV(String mnv) {
        boolean diff = true;
        for (int i = 0; i < stt; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(mnv)) {
                diff = false;
                break;
            }
        }
        return diff;
    }

    public String taoMNVFT(){
        Random ran = new Random();
        String mnv;
        while (true) {
            mnv = "FT" + ran.nextInt(1000);
            if(!checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVPT(){
        Random rd = new Random();
        String mnv;
        while (true) {
            mnv = "PT" + rd.nextInt(1000);
            if(checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVMN(){
        Random rd = new Random();
        String mnv;
        while (true) {
            mnv = "MN" + rd.nextInt(1000);
            if(!checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public void DocFile(){
        try{
            String line;
            FileReader readerFile = new FileReader(fileNameNV);
            BufferedReader bufferedReader = new BufferedReader(readerFile); 

            while(true){
                line = bufferedReader.readLine();
                if(line == null) {
                    break;
                }

                String temp[] = line.split(";");

                String maNV, ten, diaChi, sdt; 

                Date birthDate = new Date();

                maNV = temp[0]; 
                // System.out.println(temp.length);
                ten = temp[1]; 
                diaChi = temp[2]; 
                birthDate.xulyngay(temp[3]); 
                sdt = temp[4];

                // Check loai nhan vien

                if(maNV.contains("PT")){
                    String congviec;
                    int giocong;
                    congviec = temp[5];
                    giocong = Integer.parseInt(temp[6]);
                    dsnv[stt] = new PartTime(maNV, ten, diaChi, birthDate, sdt, congviec, giocong);
                    stt++;
                }

                if(maNV.contains("FT")){
                    String congviec;
                    int ngaycong;
                    congviec = temp[5];
                    ngaycong = Integer.parseInt(temp[6]);
                    dsnv[stt] = new FullTime(maNV, ten, diaChi, birthDate, sdt, congviec, ngaycong);
                    stt++;
                }

                if(maNV.contains("MN")) {
                    int capbac;
                    String congviec;
                    capbac = Integer.parseInt(temp[6]);
                    congviec = temp[5];
                    dsnv[stt] = new Manager(maNV, ten, diaChi, birthDate, sdt, congviec, capbac);
                    stt++;
                }
            }

            bufferedReader.close();
            readerFile.close();
        }

        catch(IOException ex) {
            System.out.println("Loi doc du lieu len file");
        }
    }

    // public void GhiFile(String input){
    //     try{
    //         FileWriter fw = new FileWriter("NHANVIEN/NhanVien",true);
    //         BufferedWriter bufferedWriter = new BufferedWriter(fw);
    //         bufferedWriter.write(input);
    //         bufferedWriter.close();
    //         fw.close();
    //     } catch (IOException e ){
    //         System.out.println("Loi ghi du lieu len file!");
    //     }
    // }

    public void GhiFile()
    {
        try {
            PrintWriter writerFile = new PrintWriter(fileNameNV);
            for(int i = 0; i < stt; i++){
                writerFile.print(dsnv[i].toString());
            }

            writerFile.flush();
            writerFile.close();

        } catch (Exception e) {
            System.out.println("Lỗi ghi File");
        }

        System.out.println("Ghi file thành công !");
    }

    public void ThemNhanVien(){    
        int chose;  
        do{  
            System.out.println("|------------------------------MENU--------------------------------|");
            System.out.println("|\t1.Them vao he thong nhan vien Parttime                     |");
            System.out.println("|\t2.Them vao he thong nhan vien Fulltime                     |");
            System.out.println("|\t3.Them vao he thong nhan vien Manager                      |");
            System.out.println("|\t4.Thoat                                                    |");
            System.out.println("|*Note: Ma nhan vien Parttime(PTxx), Fulltime(FTxx), Manager(MNxx) |");
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("Nhap lua chon: ");
            
            chose = checkLoi.checkSo(sc.next());
            switch (chose) {
                case 1:{
                    int n;
                    System.out.println("Nhap vao so luong nhan vien Part-time can them: ");
                    n = sc.nextInt();
                    NhanVien[] newPT = new NhanVien[n];
                    for(int i = 0; i < n; i++){
                        newPT[i] = new PartTime();
                        System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                        newPT[i].Nhap();
                        newPT[i].setMaNV(taoMNVPT());
                    }
                    GhiFile();
                }
                break;
                    
                case 2:{
                    int n;
                    System.out.println("Nhap vao so luong nhan vien Full-time can them: ");
                    n=Integer.parseInt(sc.nextLine());
                    NhanVien[] newFT = new NhanVien[n];
                    for(int i = 0 ;i<n;i++){
                        newFT[i]=new FullTime();
                        System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                        newFT[i].Nhap();
                        newFT[i].setMaNV(taoMNVFT());
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
                        newMN[i].setMaNV(taoMNVMN());
                    }
                    GhiFile();
                }
                break;

                case 4: continue;

                default:System.out.println("Nhap sai moi nhap lai!");   
                break;          
            }
        } while(chose != 4);
    }

    public void XoaNhanVien(){     
        int n;
        System.out.println("Ma nhan vien can xoa tren he thong: ");
        n=Integer.parseInt(sc.nextLine());
        for(int i = 0;i<n;i++){
            System.out.printf("-------Moi nhap vao ma nhan vien thu %d can xoa-------\n",i+1);
            String input;
            input=sc.nextLine();
            for(int j=0;j<stt;j++){
                if(dsnv[j].getMaNV().equalsIgnoreCase(input)){
                    for(int u = j; u < stt - 1; u++){
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
            System.out.printf("\n---------Nhan vien thu %d----------\n",i+1);
            dsnv[i].Xuat();
        }
    }

    public void TimKiemNhanVien(String input){
        int count = 0;
        System.out.println(input);

        for(int i = 0; i < stt; i++){
            if(dsnv[i].getMaNV().equalsIgnoreCase(input)){
                dsnv[i].Xuat();
                System.out.println("Luong: " + dsnv[i].calSalary());
                count++;
            }
        }
        if(count == 0)
        System.out.println("Khong tim thay nhan vien.");
    }

    public void QuanLyNhanVien() {
        int selection;
        while(true)
        {
            // system("cls");
            System.out.print("\n\n\t\t========== MENU ==========");
            System.out.print("\n\t1. Xuat danh sach nhan vien.");
            System.out.print("\n\t2. Them nhan vien vao danh sach.");
            System.out.print("\n\t3. Tim kiem nhan vien.");
            System.out.print("\n\t4. Tinh tong cac phan tu trong danh sach.");
            System.out.print("\n\t5. Dem so nguyen to trong danh sach.");
            System.out.print("\n\t6. Dem so am, duong, 0 trong danh sach.");
            System.out.print("\n\t7. Dem so phan tu = x.");
            System.out.print("\n\t8. Tim min.");
            System.out.print("\n\t0. Exit.");
            System.out.print("\n\n\t\t========== END ==========");

            System.out.print("\nNhap lua chon: ");
            selection = sc.nextInt() ;

            if(selection < 0 || selection > 8)
            {
                System.out.print("\nLua chon khong hop le !");
            }

            else if(selection == 1) {
                XuatDanhSach();
            }
                
            else if(selection == 2)
            {
                ThemNhanVien();
            }
            else if(selection == 3)
            {
                System.out.print("\nNhap ma nhan vien can tim: ");
                String maNV = sc.next();
                System.out.println(maNV);
                TimKiemNhanVien(maNV);
            }
            else if(selection == 4)
            {
                
            }
            else if(selection == 5)
            {
                
            }
            else if(selection == 6)
            {
               
            }
            
            else if(selection == 7)
            {
                
            }

            else if(selection == 8)
            {
                
            }

            else
                break;
        }
    }

    public static void main(String[] args) {
        DanhSachNhanVien ds = new DanhSachNhanVien();
        // ds.ThemNhanVien();
        ds.QuanLyNhanVien();
    }
}