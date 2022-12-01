package MONAN;

import INTERFACE.*;
import java.io.*;

public class DanhSachMonAn extends MonAn  implements DocGhiFile{
    private MonAn[] monAn;
    private MonAn[] maOrder;            
    private static int stt;

    public DanhSachMonAn(){
        monAn=null;
        stt =0;
        monAn= new MonAn[100];
        DocFile();
    }

    public void DocFile() {
        try{ 
            String s;
            String []tmp;
            FileReader fr = new FileReader("File_Text/MonAn.txt");
            BufferedReader br = new BufferedReader(fr);
            while(true){
                s=br.readLine();
                if(s==null){
                    break;
                }
                tmp=s.split(";");
                String mm = tmp[0]; String tm=tmp[1];
                double gt = Double.parseDouble(tmp[2]);
                monAn[stt]=new MonAn(mm,tm,gt);
                stt++;
            }
            br.close();
            fr.close();
        }catch (IOException e){
            System.out.println("Loi doc du lieu tu file!");
        }
    }
    
    public void GhiFile(String input){
        try{
            FileWriter fw = new FileWriter("MonAn.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(input);
            bw.close();
            fw.close();
        } catch (IOException e ){
            System.out.println("Loi ghi du lieu len file!");
        }
    }

    public void ThemMonAn(){        //Them n mon an vao cuoi file
        int n;
        System.out.println("Nhap so luong mon an can them vao he thong: ");
        n=Integer.parseInt(sc.nextLine());
        MonAn[] maNew = new MonAn[n];
        for(int i=0;i<n;i++){
            maNew[i]=new MonAn();
            System.out.printf("-----------Moi nhap thong tin mon an thu %d------------\n",i+1);
            maNew[i].Nhap();
            GhiFile(maNew[i].toString());
        }
    }

    public void XoaMonAn(){     //Xoa n mon an tren he thong 
        int n;
        System.out.println("Moi nhap vao so luong mon an can xoa tren he thong: ");
        n=Integer.parseInt(sc.nextLine());
        for(int i = 0;i<n;i++){
            System.out.printf("-----------------------------------------\n");
            String input;
            input=sc.nextLine();
            for(int j=0;j<stt;j++){
                if(monAn[j].getMaMon().equalsIgnoreCase(input)){
                    for(int u = j ;u<stt-1;u++){
                        monAn[u]=monAn[u+1];
                    }
                    stt--;
                }
            }
        }
        File fp = new File("MonAn.txt");
        fp.delete();                            //Xoa file cu
        for(int k=0;k<stt;k++){
            GhiFile(monAn[k].toString());
        }
    }

    public void TimKiemMonAn(String input){   
        int count=0;   
        for(int i=0;i<stt;i++){
            if(monAn[i].getMaMon().equalsIgnoreCase(input)){
                monAn[i].Xuat();count++;
            }
        }
        if(count==0){
        System.out.println("Khong tim thay mon tren he thong. Vui long nhap lai");}
    }

    public void XuatDanhSach(){
        System.out.println("=================Mon An====================");
        System.out.println("|ma mon|    |ten mon|   |giatien|");
        for(int i=0;i<stt;i++){
            System.out.printf("-----------------------------------\n");
            monAn[i].Xuat();
        }
        System.out.println("===========================================");
        System.out.print("\n");
    }
    
    public double Order(){
        maOrder=new MonAn[10];
        int[] sl = new int[10];
        double tongTien=0;
        int u = 0;
        int number;
        do{
        System.out.println("-------------------------");
        System.out.println("1.Chon mon\n2.Thanh toan");     //Chon thanh toan se tu dong xuat ra danh sach mon kem tong tien
        System.out.println("-------------------------");
        System.out.println("Moi nhap lua chon: ");
        String input;
        number=Integer.parseInt(sc.nextLine());
        switch (number) {
            case 1:{
                    int count=0;
                    do{
                    System.out.println("Moi nhap vao ma mon an: ");
                    input= sc.nextLine();
                    for(int i=0;i<stt;i++){
                        if(monAn[i].getMaMon().equalsIgnoreCase(input)){
                            maOrder[u]=monAn[i];
                            System.out.println("Moi nhap vao so luong");        //Nhap so luong cua mon an khi duoc tim thay tren he thong
                            sl[u]=Integer.parseInt(sc.nextLine());
                            u++;
                            count++;
                        }
                    }
                    if(count==0){
                        System.out.println("Khong tim thay mon an tren he thong, moi nhap lai !");
                    }
                    }while(count==0);
            }
            break;

            case 2: {
                System.out.println("=================================Hoa Don Thanh Toan=====================================");
                for(int i = 0;i<u;i++){
                    System.out.println("Ten mon: "+maOrder[i].getTenMon()+"------Don gia: "+maOrder[i].getGiaTien()+"------So luong: "+sl[i]+"------");
                    tongTien+=maOrder[i].getGiaTien()*sl[i];
                }
                return tongTien;
            }

            default: System.out.println("Nhap sai lua chon, moi nhap lai");
            break;
        }
        }while(number != 2);
        return -1;     //Tra ve gia tri bao loi neu he thong thanh toan loi
    }
}