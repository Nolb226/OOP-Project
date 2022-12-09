package NHANVIEN;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

import CONNGUOI.*;
import INTERFACE.*;
import EXCEPTION.*;;

public class DanhSachNhanVien implements DocGhiFile{
    public NhanVien[] dsnv; 
    public int stt;

    public static String fileNameNV = "DOAN/NHANVIEN/NhanVien.txt";

    public DanhSachNhanVien(){
        stt = 0 ;
        dsnv = new NhanVien[100];
        DocFile();
    }

    public Scanner sc = new Scanner(System.in);

    // TINH LUONG TAT CA NHAN VIEN 
    public double calSalary() {     
        double sumSalary = 0;       
        for (int i = 0; i < stt; i++){
            sumSalary += dsnv[i].calSalary();
        }
        return sumSalary;
    }
    
    // SUA NHAN VIEN
    public void suaNhanVien(Integer k) {
        int choose;  
        if(dsnv[k] instanceof Manager) {
            Manager temp = (Manager)dsnv[k];
            do{  
                System.out.println("|------------------------SUA THONG TIN MANAGER---------------------|");
                System.out.println("|\t1.Sua ho ten                     |");
                System.out.println("|\t2.Sua dia chi                     |");
                System.out.println("|\t3.Ngay thang nam sinh                      |");
                System.out.println("|\t4.So dien thoai                                                   |");
                System.out.println("|\t5.Sua cap bac quan ly                                                   |");
                System.out.println("|\t6.Sua cong viec                                                   |");
                System.out.println("|\t0.Thoat                                                  |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("Nhap lua chon: ");
                
                choose = checkLoi.checkSo(sc.next());
                switch (choose) {
                    case 1:{
                        temp.sethoten("");
                        GhiFile();
                    }
                    break;
                        
                    case 2:{
                        temp.setdiachi("");
                        GhiFile();
                    }
                    break;
                    
                    case 3:{
                        Date date = new Date();
                        temp.setNgaySinh(date);
                        GhiFile();
                    }
                    break;
    
                    case 4: {
                        temp.setSdt("");
                        GhiFile();
                    };
                    break;

                    case 5: {
                        temp.setCapbac(0);
                        GhiFile();
                    };
                    break;

                    case 6: {
                        temp.setCongviec("");
                        GhiFile();
                    };
                    break;
    
                    default:System.out.println("Nhap sai moi nhap lai!");   
                    break;          
                }
            } while(choose != 0);
        }

        if(dsnv[k] instanceof PartTime) {
            PartTime temp = (PartTime)dsnv[k];
            do{  
                System.out.println("|-----------------------SUA THONG TIN PART-TIME--------------------|");
                System.out.println("|\t1.Sua ho ten                     |");
                System.out.println("|\t2.Sua dia chi                     |");
                System.out.println("|\t3.Ngay thang nam sinh                      |");
                System.out.println("|\t4.So dien thoai                                                   |");
                System.out.println("|\t5.Sua cong viec                                                  |");
                System.out.println("|\t6.Sua gio cong                                                   |");
                System.out.println("|\t0.Thoat                                                  |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("Nhap lua chon: ");
                
                choose = checkLoi.checkSo(sc.next());
                switch (choose) {
                    case 1:{
                        temp.sethoten("");
                        GhiFile();
                    }
                    break;
                        
                    case 2:{
                        temp.setdiachi("");
                        GhiFile();
                    }
                    break;
                    
                    case 3:{
                        Date date = new Date();
                        temp.setNgaySinh(date);
                        GhiFile();
                    }
                    break;
    
                    case 4: {
                        temp.setSdt("");
                        GhiFile();
                    };

                    case 5: {
                        temp.setcongviec("");;
                        GhiFile();
                    };

                    case 6: {
                        temp.setgiocong(0);
                        GhiFile();
                    };
    
                    default:System.out.println("Nhap sai moi nhap lai!");   
                    break;          
                }
            } while(choose != 0);

        }

        if(dsnv[k] instanceof FullTime) {
            FullTime temp = (FullTime)dsnv[k];
            do{  
                System.out.println("|------------------------SUA THONG TIN FULL-TIME---------------------|");
                System.out.println("|\t1.Sua ho ten                     |");
                System.out.println("|\t2.Sua dia chi                     |");
                System.out.println("|\t3.Ngay thang nam sinh                      |");
                System.out.println("|\t4.So dien thoai                                                   |");
                System.out.println("|\t5.Sua cong viec                                                  |");
                System.out.println("|\t6.Sua ngay cong                                                    |");
                System.out.println("|\t0.Thoat                                                  |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("Nhap lua chon: ");
                
                choose = checkLoi.checkSo(sc.next());
                switch (choose) {
                    case 0: break;

                    case 1:{
                        temp.sethoten("");
                        GhiFile();
                    }
                    break;
                        
                    case 2:{
                        temp.setdiachi("");
                        GhiFile();
                    }
                    break;
                    
                    case 3:{
                        Date date = new Date();
                        temp.setNgaySinh(date);
                        GhiFile();
                    }
                    break;
    
                    case 4: {
                        temp.setSdt("");
                        GhiFile();
                    };
                    break;

                    case 5: {
                        temp.setcongviec("");;
                        GhiFile();
                    };
                    break;

                    case 6: {
                        temp.setngaycong(0);
                        GhiFile();
                    };
                    break;
    
                    default:System.out.println("Nhap sai moi nhap lai!");   
                    break;          
                }
            } while(choose != 0);
        }
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
            mnv = "FT" + ran.nextInt(999);
            if(checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVPT(){
        Random rd = new Random();
        String mnv;
        while (true) {
            mnv = "PT" + rd.nextInt(999);
            if(checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public String taoMNVMN(){
        Random rd = new Random();
        String mnv;
        while (true) {
            mnv = "MN" + rd.nextInt(999);
            if(checkMaNV(mnv))
                break;
        }
        return mnv;
    }

    public void DocFile(){
        try{
            String line;
            FileReader readerFile = new FileReader(fileNameNV);
            BufferedReader bufferedReader = new BufferedReader(readerFile);
            stt = 0; 

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
                
                // System.out.println(stt);
                
                if(maNV.contains("PT")){
                    String congviec;
                    int giocong;
                    congviec = temp[5];
                    giocong = Integer.parseInt(temp[6]);
                    dsnv[stt] = new PartTime(maNV, ten, diaChi, birthDate, sdt, congviec, giocong);
                    dsnv[stt].set_Username(maNV);
                    stt++;
                }

                else if(maNV.contains("FT")){
                    String congviec;
                    int ngaycong;
                    congviec = temp[5];
                    ngaycong = Integer.parseInt(temp[6]);
                    dsnv[stt] = new FullTime(maNV, ten, diaChi, birthDate, sdt, congviec, ngaycong);
                    dsnv[stt].set_Username(maNV);
                    stt++;
                }

                else if(maNV.contains("MN")) {
                    int capbac;
                    String congviec;
                    capbac = Integer.parseInt(temp[6]);
                    congviec = temp[5];
                    dsnv[stt] = new Manager(maNV, ten, diaChi, birthDate, sdt, congviec, capbac);
                    dsnv[stt].set_Username(maNV);
                    stt++;
                }
            }

            

            dsnv = Arrays.copyOf(dsnv, stt);

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
            
            for(int i = 0; i < stt; i++) {
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
        int choose;  
        do{  
            System.out.println("|------------------------------MENU--------------------------------|");
            System.out.println("|\t1.Them vao he thong nhan vien Parttime                     |");
            System.out.println("|\t2.Them vao he thong nhan vien Fulltime                     |");
            System.out.println("|\t3.Them vao he thong nhan vien Manager                      |");
            System.out.println("|\t4.Thoat                                                    |");
            System.out.println("|*Note: Ma nhan vien Parttime(PTxx), Fulltime(FTxx), Manager(MNxx) |");
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("Nhap lua chon: ");
            
            choose = checkLoi.checkSo(sc.next());
            switch (choose) {
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

                        // Cap nhat danh sach nhan vien
                        dsnv = Arrays.copyOf(dsnv, stt + 1);
                        dsnv[dsnv.length - 1] = newPT[i];
                        stt++;
                        GhiFile();
                    }
                    XuatDanhSach();
                }
                break;
                    
                case 2:{
                    int n;
                    System.out.println("Nhap vao so luong nhan vien Full-time can them: ");
                    n = sc.nextInt();
                    NhanVien[] newFT = new NhanVien[n];
                    for(int i = 0; i < n; i++){
                        newFT[i] = new FullTime();
                        System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                        newFT[i].Nhap();
                        newFT[i].setMaNV(taoMNVFT());
                        System.out.println("hello");

                        // Cap nhat danh sach nhan vien
                        dsnv = Arrays.copyOf(dsnv, stt + 1);
                        dsnv[dsnv.length - 1] = newFT[i];
                        stt++;
                        GhiFile();
                    }
                    XuatDanhSach();
                }
                break;
                
                case 3:{
                    int n;
                    System.out.println("Nhap vao so luong nhan vien Manager can them: ");
                    n = sc.nextInt();
                    NhanVien[] newMN = new NhanVien[n];
                    for(int i = 0; i < n; i++) {
                        newMN[i]=new Manager();
                        System.out.printf("----------------Nhap vao nhan vien thu %d--------------\n",i+1);
                        newMN[i].Nhap();
                        newMN[i].setMaNV(taoMNVMN());

                        // Cap nhat danh sach nhan vien
                        dsnv = Arrays.copyOf(dsnv, stt + 1);
                        dsnv[dsnv.length - 1] = newMN[i];
                        stt++;
                        GhiFile();
                    }
                    XuatDanhSach();
                }
                break;

                case 4: continue;

                default:System.out.println("Nhap sai moi nhap lai!");   
                break;          
            }
        } while(choose != 4);
    }

    public void XoaNhanVien(int k){    
        for(int i = k; i < stt - 1; i++){
            dsnv[i] = dsnv[i+1];
        }
        stt--;
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
        
        if(dsnv[0] == null){
            System.out.println("Khong co du lieu tren he thong!");
        }
        for(int i = 0; i < dsnv.length; i++){
            System.out.printf("\n---------Nhan vien thu %d----------\n", i + 1);
            dsnv[i].Xuat();
        }
    }

    public void TimKiemNhanVien(String input){
        int count = 0;

        for(int i = 0; i < stt; i++){
            if(dsnv[i].getMaNV().equalsIgnoreCase(input)){
                dsnv[i].Xuat();
                System.out.println("Luong: " + dsnv[i].calSalary());
                count++;
            }
        }

        if(count == 0) {
            System.out.println("Khong tim thay nhan vien.");
        }
    }

    public int TimKiemNhanVienReturnIndex(String input){
        for(int i = 0; i < stt; i++){
            if(dsnv[i].getMaNV().equalsIgnoreCase(input)){
                return i;
            }
        }
        return -1;
    }

    

    public static void main(String[] args) {
        DanhSachNhanVien ds = new DanhSachNhanVien();
        // ds.DocFile();
        // ds.QuanLyNhanVien();
    }
}