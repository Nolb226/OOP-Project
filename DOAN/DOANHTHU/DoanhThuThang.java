package DOANHTHU;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import INTERFACE.DocGhiFile;

public class DoanhThuThang implements DocGhiFile {
    private DoanhThu DTList[];
    private int n;
    private String tenFILE = "DOANHTHU/DT.txt";

    public DoanhThuThang() {
        DTList = new DoanhThu[30];
        n = 0;
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
        File fdt = new File(tenFILE);
        try {
            BufferedReader brdt = Files.newBufferedReader(fdt.toPath());
            while (true) {
                String s = brdt.readLine();
                if (s == null) {
                    break;
                } else {
                    String[] word = s.split(",");
                    DoanhThu a = new DoanhThu();
                    a.tachTT(word);
                    addDT(a);
                }
            }
            brdt.close();
        } catch (Exception e) {
            System.out.println("Loi doc file doanh thu.");
            System.out.println(e.toString());
        }
    }

    public void GhiFile() {
        try {
            PrintWriter pw = new PrintWriter(tenFILE);
            for (int i = 0; i < n; i++) {
                pw.println(DTList[i].toString());
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Loi ghi file doanh thu.");
            System.out.println(e.toString());
        }
    }

    public void addDT(DoanhThu a) {
        if (n == 0) {
            n++;
            DTList[n - 1] = new DoanhThu(a);
        } else {
            if (DTList[n - 1].getNgay().equals(a.getNgay())) {
                DTList[n - 1].moreIN(a.getIn());
                DTList[n - 1].moreOut(a.getOut());
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
        System.out.printf("%-12s", "Ngay");
        System.out.print("|");
        System.out.printf("%-20s", "Tien nhap");
        System.out.print("|");
        System.out.printf("%-20s", "Tien ban");
        System.out.print("|");
        System.out.printf("%-20s", "Lai suat");
        System.out.println("|");
        System.out.println("+------------+--------------------+--------------------+--------------------+");
        for(int i=0;i<n;i++) {
            DTList[i].xuatDT();
        }
        System.out.println("+---------------------------------------------------------------------------+");
        
    }

    public void updateDT(DoanhThu DT) {
        DTList[n-1].moreIN(DT.getIn());
        DTList[n-1].moreOut(DT.getOut());
    }
}
