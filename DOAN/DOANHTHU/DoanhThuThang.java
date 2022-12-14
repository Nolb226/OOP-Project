package DOANHTHU;

import INTERFACE.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import CONNGUOI.Date;

public class DoanhThuThang implements DocGhiFile {
    // private DoanhThu DTList[] = new DoanhThu[0];
    private DoanhThu DTList[];
    private int n;
    public static Scanner input = new Scanner(System.in);
    private String fileName = "DOAN/DOANHTHU/Doanhthu.txt";

    public DoanhThuThang() {
        DTList = new DoanhThu[30];
        n = 0;
        DocFile();
        // System.out.println(n);
    }

    public DoanhThuThang(DoanhThu[] dt, int n) {
        this.DTList = dt;
        this.n = n;
    }

    public DoanhThuThang(DoanhThuThang orther) {
        this.DTList = orther.DTList;
        this.n = orther.n;
    }

    public void setDTList(DoanhThu[] dTList) {
        DTList = dTList;
    }

    public DoanhThu[] getDTList() {
        return DTList;
    }

    public void DocFile() {
        // System.out.println(n);
        try {
            String s;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                String temp[] = s.split(",");
                Date date = new Date();
                date.xulyngay(temp[0]);
                Double cash_in = Double.parseDouble(temp[1]);
                Double cash_out = Double.parseDouble(temp[2]);
                // DTList = Arrays.copyOf(DTList, n + 1);
                // DTList[n] = new DoanhThu(date, cash_in, cash_out);
                // n++;
                addDT(new DoanhThu(date, cash_in, cash_out));
                
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi doc file doanh thu!");
        }
    }

    public void GhiFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < n; i++) {
                bw.write(DTList[i].toString()+"\n");
            }
            bw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println("Loi ghi file doanh thu.");
            System.out.println(e.toString());
        }
    }

    public void addDT(DoanhThu a) {
        if (n == 0) {
            n++;
            DTList[n - 1] = a;
        } else {
            if ( DTList[n - 1].getNgay().equals(a.getNgay())) {
                DTList[n - 1].moreIN(a.getIn());
                DTList[n - 1].moreOut(a.getOut());
                a.setIn(DTList[n - 1].getIn());
                a.setOut(DTList[n - 1].getOut());
            } else {
                if (n == 30) {
                    removeDT();
                    DTList[n - 1] = new DoanhThu(a);
                } else {
                    n++;
                    DTList[n - 1] = new DoanhThu(a);
                }
            }
        }
    }

    public void removeDT() {
        for (int i = 0; i < 29; i++) {
            DTList[i].copyDT(DTList[i + 1]);
        }
    }

    public void thongKe() {
        System.out.println("");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                                 THONG KE                                  |");
        System.out.println("+------------+--------------------+--------------------+--------------------+");
        System.out.print("|");
        System.out.printf("%-12s", "Ngay thang");
        System.out.print("|");
        System.out.printf("%-20s", "Tien nhap");
        System.out.print("|");
        System.out.printf("%-20s", "Tien ban");
        System.out.print("|");
        System.out.printf("%-20s", "Lai suat");
        System.out.println("|");
        System.out.println("+------------+--------------------+--------------------+--------------------+");
        for (int i = 0; i < n; i++) {
            DTList[i].xuatDT();
        }
        System.out.println("+---------------------------------------------------------------------------+");

    }

    public void updateDT(DoanhThu DT) {
        DTList[n - 1].setIn(DT.getIn());
        DTList[n - 1].setOut(DT.getOut());
    }

}