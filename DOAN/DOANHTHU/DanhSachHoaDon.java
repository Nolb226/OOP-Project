package DOANHTHU;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;
import EXCEPTION.*;
import INTERFACE.*;
import KHACHHANG.DanhSachKhachHang;
import NHANVIEN.DanhSachNhanVien;
import NHANVIEN.NhanVien;
import MONAN.DanhSachMonAn;
import MONAN.MonAn;

public class DanhSachHoaDon implements DocGhiFile {
    private HoaDon[] HDlist;
    private int n;
    private DanhSachMonAn DSSP = new DanhSachMonAn();
    private DanhSachNhanVien DSNV = new DanhSachNhanVien();
    private DanhSachKhachHang DSKH = new DanhSachKhachHang();
    static Scanner input = new Scanner(System.in);
    private String tenFile = "DOAN/DOANHTHU/DSHD.txt";

    public DanhSachHoaDon() {
        DSKH = new DanhSachKhachHang();
        DSSP = new DanhSachMonAn();
        DSNV = new DanhSachNhanVien();
        HDlist = new HoaDon[100];
        n = 0;
    }

    public DanhSachHoaDon(DanhSachKhachHang a, DanhSachMonAn b, DanhSachNhanVien c) {
        DSKH = new DanhSachKhachHang(a.getKHList());
        DSSP = new DanhSachMonAn(b.getSpList());
        DSNV = new DanhSachNhanVien(c.getNVList());
        HDlist = new HoaDon[100];
        n = 0;
    }

    public DanhSachHoaDon(DanhSachHoaDon orther) {
        this.DSKH = orther.DSKH;
        this.DSNV = orther.DSNV;
        this.DSSP = orther.DSSP;
        this.HDlist = orther.HDlist;
        this.n = orther.n;
    }

    public void setHDlist(HoaDon[] hdlist) {
        this.HDlist = hdlist;
    }

    public void setN(int n) {
        this.n = n;
    }

    public HoaDon[] getHDlist() {
        return HDlist;
    }

    public int getN() {
        return n;
    }

    public void DocFile() {

        File fhd = new File(tenFile);
        try {
            BufferedReader brhd = Files.newBufferedReader(fhd.toPath());
            String s = null;
            while (true) {
                s = brhd.readLine();
                if (s == null) {
                    break;
                } else {
                    HoaDon a = new HoaDon();
                    a.TachTT(s, DSNV, DSSP, DSKH);
                    add(a);
                }
            }
            brhd.close();
        } catch (IOException e) {

            System.out.println(e.toString());
            System.out.println("Loi doc file hoa don");

        }
        // System.out.println(n);
    }

    public void GhiFile() {
        try {
            PrintWriter pw = new PrintWriter(tenFile);
            for (int i = 0; i < n; i++) {
                pw.println(HDlist[i].toString());
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Loi ghi file hoa don");
            System.out.println(e.toString());
        }
    }

    public void add(HoaDon a) {
        if (n == 100) {
            remotePN();
            HDlist[n - 1] = new HoaDon();
            HDlist[n - 1].copyHD(a);
        } else {
            n++;
            HDlist[n - 1] = new HoaDon();
            HDlist[n - 1].copyHD(a);
        }
    }

    public void remotePN() {
        for (int i = 0; i < 99; i++) {
            HDlist[i].copyHD(HDlist[i + 1]);
        }
    }

    public void themHoaDon(NhanVien a, DanhSachKhachHang dskh, String mkh, DanhSachMonAn dssp, DoanhThu DT) {
        HoaDon hd = new HoaDon();
        Random rd = new Random();
        String mhd;
        while (true) {
            mhd = "HD" + rd.nextInt(100000);
            if (checkMaHD(mhd)) {
                break;
            }
        }
        hd = new HoaDon(mhd);
        if (mkh == null) {
            hd.taoPhieu(a, null);
        } else {
            if (dskh.SearchKH(mkh) == null) {
                System.out.print("Khong tim thay khach hang nhap 1 de tao khach hang moi! :");
                if (checkLoi.checkSo(input.nextLine()) == 1) {
                    dskh.NhapThemKh();
                    mkh = dskh.layMaKHCuoi();
                }
            }
            hd.taoPhieu(a, dskh.SearchKH(mkh));
        }
        do {
            dssp.xuatDanhSach();
            String m;
            MonAn temp;
            while (true) {
                System.out.print("Ma san pham: ");

                m = input.nextLine();
                if ((temp = dssp.timKiemMaMon(m)).getMaMon() != null) {
                    break;
                } else {
                    System.out.println("Khong tim thay san pham, moi nhap lai");

                }
            }
            if (dssp.timKiemMaMon(m).getMaMon() == null) {
                System.out.println("Khong tim thay san pham.");
            } else {
                dssp.timKiemMaMon(m).Xuat();
                int sl;
                while (true) {
                    System.out.print("So luong: ");
                    sl = checkLoi.inputIntNumberError(input.next());
                    if (sl < 0) {
                        System.out.println("Khong hop le, moi nhap lai");
                    } else {
                        break;
                    }
                }
                if (sl > dssp.timKiemMaMon(m).getSoLuong()) {
                    System.out.println("So san pham con lai khong du, xin thong cam.");
                } else {
                    hd.addSP(dssp.timKiemMaMon(m), sl);
                }
            }
            System.out.print("Nhan bat ki de tiep tuc, nhan 't' de thoat: ");
        } while (checkLoi.continueString(input.next()) != 't');
        hd.inPhieu();
        System.out.print("Nhan bat ki de luu, 'h' de huy hoa don: ");
        if (checkLoi.continueString(input.next()) != 'h') {
            for (int i = 0; i < hd.sp.length; i++) {

                // Food Attr define
                String food_id = dssp.timKiemMaMon(hd.getSp()[i].getMaMon()).getMaMon();
                Integer food_number = Integer.parseInt(food_id.split("MH")[1]) - 1;

                // System.out.println(food_id);
                // System.out.println(food_number);
                // Decrease the number of food in store
                dssp.foodList[food_number].removeSoLuong(hd.getSoLuong()[i]);

            }
            DT.moreIN(hd.price());
            add(hd);
            GhiFile();
            dssp.GhiFile();
            System.out.println("Da luu");
        }
        System.out.print("Nhan phim bat ki de tiep tuc.");
        input.next();
    }

    public boolean checkMaHD(String m) {
        boolean diff = true;
        for (int i = 0; i < n; i++) {
            if (HDlist[i].getId().equalsIgnoreCase(m)) {
                diff = false;
                break;
            }
        }
        return diff;
    }

    public HoaDon searchHoaDon(String mpn) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (HDlist[i].getId().equals(mpn)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        } else {
            return HDlist[index];
        }
    }

    public void title() {
        System.out.println("");
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("|                               LICH SU HOA DON                                |");
        System.out.println("+------------+------------+---------------+---------------+--------------------+");
        System.out.print("|");
        System.out.printf("%-12s", "Ngay thang");
        System.out.print("|");
        System.out.printf("%-12s", "Ma phieu");
        System.out.print("|");
        System.out.printf("%-15s", "Ma nhan vien");
        System.out.print("|");
        System.out.printf("%-15s", "Ma khach hang");
        System.out.print("|");
        System.out.printf("%-20s", "Gia tri");
        System.out.println("|");
        System.out.println("+------------+------------+---------------+---------------+--------------------+");
    }

    public void xuatHD() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
        } else {
            title();
            for (int i = 0; i < n; i++) {
                HDlist[i].xuat();
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        }
    }

    public void xemHoaDon() {
        xuatHD();
        String mhd;
        do {
            System.out
                    .print("Nhap ma hoa don de xem chi tiet, ma nhan vien, ma khach hang, ngay de loc, 't' de thoat: ");
            mhd = input.nextLine();
            if (checkLoi.continueString(mhd) != 't') {
                if (mhd.contains("HD")) {
                    if (searchHoaDon(mhd) != null) {
                        System.out.println("Ket qua: ");
                        searchHoaDon(mhd).inPhieu();
                    } else {
                        System.out.println("Khong tim thay ket qua.");
                    }
                } else {
                    locHD(mhd);
                }
            } else {
                break;
            }
            System.out.print("Nhan phim bat ki de tiep tuc, 't' de thoat: ");
        } while (checkLoi.continueString(input.nextLine()) != 't');
    }

    public void locHD(String dk) {
        boolean k = false;
        if (checkLoi.checkNgay(dk)) {
            title();
            for (int i = 0; i < n; i++) {
                if (HDlist[i].getDate().toString().equals(dk)) {
                    HDlist[i].xuat();
                    k = true;
                }
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        } else if (dk.indexOf("MN") == 0 || dk.indexOf("PT") == 0 || dk.indexOf("FT") == 0) {
            title();
            for (int i = 0; i < n; i++) {
                if (HDlist[i].getNv() != null) {
                    if (HDlist[i].getNv().getMaNV().equalsIgnoreCase(dk)) {
                        HDlist[i].xuat();
                        k = true;
                    }
                }
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        } else if (dk.indexOf("KH") == 0) {
            title();
            for (int i = 0; i < n; i++) {
                if (HDlist[i].getKh() != null) {
                    if (HDlist[i].getKh().getMSKH().equalsIgnoreCase(dk)) {
                        HDlist[i].xuat();
                        k = true;
                    }
                }
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        }
        if (k == false) {
            System.out.println("Khong co ket qua.");
        }
    }

}