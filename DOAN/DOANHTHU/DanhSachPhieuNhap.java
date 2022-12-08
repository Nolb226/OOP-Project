package DOANHTHU;


import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

import INTERFACE.*;
import NHANVIEN.DanhSachNhanVien;
import NHANVIEN.NhanVien;
import MONAN.DanhSachMonAn;

public class DanhSachPhieuNhap implements DocGhiFile {
    private PhieuNhap[] PNlist;
    private int n;
    private DanhSachMonAn DSSP;
    private DanhSachNhanVien DSNV;
    static Scanner input = new Scanner(System.in);
    private String tenFile = "DOANHTHU/DSPN.txt";

    public DanhSachPhieuNhap() {
        DSSP = new DanhSachMonAn();
        DSNV = new DanhSachNhanVien();
        PNlist = new PhieuNhap[100];
        n = 0;
    }
    public DanhSachPhieuNhap(DanhSachMonAn b, DanhSachNhanVien c) {
        DSSP = new DanhSachMonAn(b.getSpList());
        DSNV = new DanhSachNhanVien(c.getNVList());
        PNlist = new PhieuNhap[100];
        n = 0;
    }

    public DanhSachPhieuNhap(DanhSachPhieuNhap orther) {
        this.DSSP = orther.DSSP;
        this.DSNV = orther.DSNV;
        this.PNlist = orther.PNlist;
        this.n = orther.n;
    }

    public void setPNlist(PhieuNhap[] pnlist) {
        this.PNlist = pnlist;
    }

    public void setN(int n) {
        this.n = n;
    }

    public PhieuNhap[] getPNlist() {
        return PNlist;
    }

    public int getN() {
        return n;
    }

    public void DocFile() {
        File fpn = new File(tenFile);
        try {
            BufferedReader brpn = Files.newBufferedReader(fpn.toPath());
            String s = null;
            while (true) {
                s = brpn.readLine();
                if (s == null) {
                    break;
                } else {
                    PhieuNhap a = new PhieuNhap();
                    a.TachTT(s, DSNV, DSSP, null);
                    add(a);
                }
            }
            brpn.close();
        } catch (Exception e) {
            System.out.println("Loi doc file phieu nhap");
            System.out.println(e.toString());
        }
    }

    public void GhiFile() {
        try {
            PrintWriter pw = new PrintWriter(tenFile);
            for (int i=0;i<n;i++) {
                pw.println(PNlist[i].toString());
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Loi ghi file phieu nhap");
            System.out.println(e.toString());
        }
    }

    public void add(PhieuNhap a) {
        if (n == 100) {
            remotePN();
            PNlist[n - 1] = new PhieuNhap();
            PNlist[n - 1].copyPN(a);
        } else {
            n++;
            PNlist[n - 1] = new PhieuNhap();
            PNlist[n - 1].copyPN(a);
        }
    }

    public void remotePN() {
        for (int i = 0; i < 99; i++) {
            PNlist[i].copyPN(PNlist[i + 1]);
        }
    }

    public void nhapHang(NhanVien a, DanhSachMonAn dssp_Main, DoanhThu DT) {
        PhieuNhap pn = new PhieuNhap();
        Random rd = new Random();
        String mpn;
        while (true) {
            mpn = "PN" + rd.nextInt(10000);
            if (checkMaPN(mpn)) {
                break;
            }
        }
        pn = new PhieuNhap(mpn);
        pn.taoPhieu(a,null);
        do {
            System.out.print("Ma san pham muon nhap them: ");
            String m = input.nextLine();
            if(dssp_Main.SearchByMaSP(m)==null) {
                System.out.println("Khong tim thay san pham.");
            } else {
                dssp_Main.SearchByMaSP(m).toTable();
                int sl;
                while (true) {
                    System.out.print("So luong muon nhap them la: ");
                    sl = error.inputIntNumberError(input.nextLine());
                    if (sl < 0) {
                        System.out.println("Khong hop le, moi nhap lai.");
                    } else {
                        break;
                    }
                }
                pn.addSP(dssp_Main.SearchByMaSP(m), sl);
            }
            System.out.print("Nhan bat ki de tiep tuc, nhan 't' de thoat: ");
        } while (error.continueString(input.nextLine()) != 't');
        pn.inPhieu();
        System.out.print("Nhan bat ki de luu, 'h' de huy phieu nhap: ");
        if (error.continueString(input.nextLine()) != 'h') {
            for(int i=0;i<pn.sp.length;i++) {
                dssp_Main.SearchByMaSP(pn.getSp()[i].getMaSP()).setSoLuong2(pn.getSoLuong()[i]);
            }
            DT.moreOut(pn.price());
            add(pn);
            GhiFile();
            System.out.println("Da luu");
        }
        System.out.print("Nhan phim bat ki de tiep tuc.");
        input.nextLine();
    }

    public void NhapToanBo(NhanVien a, DanhSachMonAn dssp_Main, DoanhThu DT) {
        PhieuNhap pn = new PhieuNhap();
        Random rd = new Random();
        String mpn;
        while (true) {
            mpn = "PN" + rd.nextInt(10000);
            if (checkMaPN(mpn)) {
                break;
            }
        }
        pn = new PhieuNhap(mpn);
        pn.taoPhieu(a,null);
        int sl;
        while (true) {
            System.out.print("So luong muon nhap them cho toan bo san pham la: ");
            sl = error.inputIntNumberError(input.nextLine());
            if (sl < 0) {
                System.out.println("Khong hop le, moi nhap lai.");
            } else {
                break;
            }
        }
        for (int i = 0; i < dssp_Main.getSp().size(); i++) {
            pn.addSP(dssp_Main.getSp().get(i), sl);
        }
        pn.inPhieu();
        System.out.print("Nhan bat ki de luu, 'h' de huy phieu nhap: ");
        if (error.continueString(input.nextLine()) != 'h') {
            for(int i = 0; i<dssp_Main.getSp().size();i++) {
                dssp_Main.getSp().get(i).setSoLuong2(sl);
            }
            DT.moreOut(pn.price());
            add(pn);
            GhiFile();
            System.out.println("Da luu");
        }
        System.out.print("Nhan phim bat ki de tiep tuc.");
        input.nextLine();
    }

    public boolean checkMaPN(String m) {
        boolean diff = true;
        for (int i = 0; i < n; i++) {
            if (PNlist[i].getId().equalsIgnoreCase(m)) {
                diff = false;
                break;
            }
        }
        return diff;
    }

    public PhieuNhap searchPhieuNhap(String mpn) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (PNlist[i].getId().equals(mpn)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        } else {
            return PNlist[index];
        }
    }

    public void title() {
        System.out.println("");
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("|                            LICH SU NHAP HANG                           |");
        System.out.println("+------------+------------+-------------------------+--------------------+");
        System.out.print("|");
        System.out.printf("%-12s", "Ngay thang");
        System.out.print("|");
        System.out.printf("%-12s", "Ma phieu");
        System.out.print("|");
        System.out.printf("%-25s", "Nhan vien thuc hien");
        System.out.print("|");
        System.out.printf("%-20s", "Gia tri");
        System.out.println("|");
        System.out.println("+------------+------------+-------------------------+--------------------+");
    }

    public void xuatDSPN() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
        } else {
            title();
            for (int i = 0; i < n; i++) {
                PNlist[i].xuat();
            }
            System.out.println("+------------+------------+-------------------------+--------------------+");
        }
    }

    public void xemPhieuNhap() {
        xuatDSPN();
        String mpn;
        do {
            System.out.print("Nhap ma phieu de xem chi tiet, ma nhan vien, ngay de loc, 't' de thoat: ");
            mpn = input.nextLine();
            if (error.continueString(mpn) != 't') {
                if (mpn.contains("PN")) {
                    if (searchPhieuNhap(mpn) != null) {
                        System.out.println("Ket qua: ");
                        searchPhieuNhap(mpn).inPhieu();
                    } else {
                        System.out.println("Khong tim thay ket qua.");
                    }
                } else {
                    locPN(mpn);
                }
            } else {
                break;
            }
            System.out.print("Nhan phim bat ki de tiep tuc, 't' de thoat: ");
        } while (error.continueString(input.nextLine()) != 't');
    }

    public void locPN(String dk) {
        boolean k = false;
        if (error.checkNgay(dk)) {
            title();
            for (int i=0;i<n;i++) {
                if (PNlist[i].getDate().toString().equals(dk)) {
                    PNlist[i].xuat();
                    k = true;
                }
            }
            System.out.println("+------------+------------+-------------------------+--------------------+");
        } else if (dk.indexOf("NV") == 0) {
            title();
            for (int i=0;i<n;i++) {
                if (PNlist[i].getNv().getMaNV().equalsIgnoreCase(dk)) {
                    PNlist[i].xuat();
                    k = true;
                }
            }
            System.out.println("+------------+------------+-------------------------+--------------------+");
        }
        if (k == false) {
            System.out.println("Khong co ket qua.");
        }
    }

}