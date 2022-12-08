package DOANHTHU;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

import INTERFACE.DocGhiFile;
import KHACHHANG.DanhSachKhachHang;
import NHANVIEN.DanhSachNhanVien;
import NHANVIEN.NhanVien;
import MONAN.DanhSachMonAn;

public class DanhSachBill implements DocGhiFile {
    private Bill_ChiTiet[] List;
    private int n;
    private DanhSachMonAn DSSP;
    private DanhSachNhanVien DSNV;
    private DanhSachKhachHang DSKH;
    static Scanner input = new Scanner(System.in);
    private String tenFile = "DOANHTHU/DSHD.txt";

    public DanhSachBill() {
        DSKH = new DanhSachKhachHang();
        DSSP = new DanhSachMonAn();
        DSNV = new DanhSachNhanVien();
        List = new Bill_ChiTiet[100];
        n = 0;
    }

    public DanhSachBill(DanhSachBill orther) {
        this.DSKH = orther.DSKH;
        this.DSNV = orther.DSNV;
        this.DSSP = orther.DSSP;
        this.List = orther.List;
        this.n = orther.n;
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
                    Bill a = new Bill();
                    a.TachTT(s, DSNV, DSSP, DSKH);
                    add(a);
                }
            }
            brhd.close();
        } 
        catch (Exception e) {
            System.out.println("Loi doc file hoa don");
            System.out.println(e.toString());
        }
    }

    public void GhiFile() {
        try {
            PrintWriter pw = new PrintWriter(tenFile);
            for (int i=0;i<n;i++) {
                pw.println(List[i].toString());
            }
            pw.flush();
            pw.close();
        } 
        catch (Exception e) {
            System.out.println("Loi ghi file hoa don");
            System.out.println(e.toString());
        }
    }

    public void add(Bill_ChiTiet a) {
        if (n == 100) {
            remotePN();
            List[n - 1] = new Bill_ChiTiet();
            List[n - 1].copyHD(a);
        } 
        else {
            n++;
            List[n - 1] = new Bill_ChiTiet();
            List[n - 1].copyHD(a);
        }
    }

    public void remotePN() {
        for (int i = 0; i < 99; i++) {
            List[i].copyHD(List[i + 1]);
        }
    }

    public void themBill(NhanVien a, DanhSachKhachHang dskh, String mkh, DanhSachMonAn dssp, DoanhThu DT) {
        Bill hd = new Bill();
        Random rd = new Random();
        String mhd;
        while (true) {
            mhd = "HD" + rd.nextInt(100000);
            if (checkMaHD(mhd)) {
                break;
            }
        }
        hd = new Bill(mhd);
        if(mkh == null) {
            hd.taoPhieu(a, null);
        } else {
            hd.taoPhieu(a, dskh.timMskhKhachHang());
        }
        do {
            System.out.print("Ma san pham: ");
            String m = input.nextLine();
            if(dssp.timKiemMonAn()==null) {
                System.out.println("Khong tim thay san pham.");
            } else {
                dssp.SearchByMaSP(m).toTable();
                int sl;
                while (true) {
                    System.out.print("So luong: ");
                    sl = error.inputIntNumberError(input.nextLine());
                    if (sl < 0) {
                        System.out.println("Khong hop le, moi nhap lai");
                    } else {
                        break;
                    }
                }
                if (sl > dssp.SearchByMaSP(m).getSoLuong()) {
                    System.out.println("So san pham con lai khong du, xin thong cam.");
                } else {
                    hd.addSP(dssp.SearchByMaSP(m), sl);
                }
            }
            System.out.print("Nhap bat ki de tiep tuc, nhan 't' de thoat: ");
        } while (error.continueString(input.nextLine()) != 't');
        hd.inPhieu();
        System.out.print("Nhan bat ki de luu, 'h' de huy hoa don: ");
        if (error.continueString(input.nextLine()) != 'h') {
            for(int i=0;i<hd.sp.length;i++) {
                dssp.SearchByMaSP(hd.getSp()[i].getMaSP()).setSoLuong2(0 - hd.getSoLuong()[i]);
            }
            DT.moreIN(hd.price());
            if(mkh != null) {
                dskh.SearchKH(mkh).setDtinhluy(DSKH.SearchKH(mkh).getDtinhluy() + 1);
            }
            add(hd);
            GhiFile();
            System.out.println("Da luu");
        }
        System.out.print("Nhan phim bat ki de tiep tuc.");
        input.nextLine();
    }

    public boolean checkMaHD(String m) {
        boolean diff = true;
        for (int i = 0; i < n; i++) {
            if (List[i].getId().equalsIgnoreCase(m)) {
                diff = false;
                break;
            }
        }
        return diff;
    }

    public Bill searchBill(String mpn) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (List[i].getId().equals(mpn)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        } else {
            return List[index];
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
            for (int i=0;i<n;i++) {
                List[i].xuat();
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        }
    }

    public void xemBill() {
        xuatHD();
        String mhd;
        do {
            System.out.print("Nhap ma hoa don de xem chi tiet, ma nhan vien, ma khach hang, ngay de loc, 't' de thoat: ");
            mhd = input.nextLine();
            if (error.continueString(mhd) != 't') {
                if(mhd.contains("HD")) {
                    if (searchBill(mhd) != null) {
                        System.out.println("Ket qua: ");
                        searchBill(mhd).inPhieu();
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
        } while (error.continueString(input.nextLine()) != 't');
    }

    public void locHD(String dk) {
        boolean k = false;
        if (error.checkNgay(dk)) {
            title();
            for (int i=0;i<n;i++) {
                if (List[i].getDate().toString().equals(dk)) {
                    List[i].xuat();
                    k = true;
                }
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        } else if (dk.indexOf("NV") == 0) {
            title();
            for (int i=0;i<n;i++) {
                if (List[i].getNv().getMaNV().equalsIgnoreCase(dk)) {
                    List[i].xuat();
                    k = true;
                }
            }
            System.out.println("+------------+------------+---------------+---------------+--------------------+");
        } else if(dk.indexOf("KH")==0) {
            title();
            for (int i=0;i<n;i++) {
                if(List[i].getKh() != null) {
                    if (List[i].getKh().getMaKH().equalsIgnoreCase(dk)) {
                        List[i].xuat();
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
