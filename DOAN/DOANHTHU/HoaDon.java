package DOANHTHU;

import KHACHHANG.*;
import NHANVIEN.*;
import MONAN.*;
import java.util.Arrays;

import CONNGUOI.Date;

public class HoaDon extends Phieu {

    private KhachHang kh;

    public HoaDon() {
        id = null;
        n = 0;
        Date = new Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public HoaDon(String mhd) {
        id = mhd;
        n = 0;
        Date = new Date();
        sp = new MonAn[n];
        soLuong = new int[n];
    }

    public void copyHD(HoaDon orther) {
        setId(orther.id);
        setNv(orther.nv);
        setKh(orther.kh);
        setDate(orther.Date);
        for (int i = 0; i < orther.n; i++) {
            addSP(orther.sp[i], orther.soLuong[i]);
        }
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public KhachHang getKh() {
        return kh;
    }

    @Override
    public void taoPhieu(NhanVien a, KhachHang b) {
        if (a instanceof Manager) {
            nv = new Manager((Manager) a);
        } else if (a instanceof FullTime) {
            nv = new FullTime((FullTime) a);
        } else if (a instanceof PartTime) {
            nv = new PartTime((PartTime) a);
        }
        Date.Today();
        if (b != null) {
            kh = new KhachHang(b);
        } else {
            kh = null;
        }
    }

    @Override
    public void addSP(MonAn a, int sl) {
        sp = Arrays.copyOf(sp, n + 1);
        soLuong = Arrays.copyOf(soLuong, n + 1);
        setN(n + 1);
        sp[n - 1] = new MonAn((MonAn) a);
        soLuong[n - 1] = sl;
    }

    @Override
    public double price() {
        double price = 0;
        for (int i = 0; i < n; i++) {
            price = price + sp[i].getGiaBan() * soLuong[i];
        }
        // Neu co thong tin khach hang se duoc giam gia 5% - Thai
        if (kh == null) {
            return price;
        } else
            return price - price * 0.05;
    }

    @Override
    public void inPhieu() {
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|                                 HOA DON                                 |");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.printf("%-73s", "Ngay: " + Date.toString());
        System.out.println("|");
        System.out.print("|");
        System.out.printf("%-73s", "Ma phieu: " + id);
        System.out.println("|");
        System.out.println("|Nhan vien thuc hien:                                           |");
        if (nv == null) {
            System.out.print("|");
            System.out.printf("%-73s", "Ma nhan vien: ");
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "Ten nhan vien: ");
            System.out.println("|");
        } else {
            System.out.print("|");
            System.out.printf("%-73s", "Ma nhan vien: " + nv.getMaNV());
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "Ten nhan vien: " + nv.gethoten());
            System.out.println("|");
        }
        if (kh != null) {
            System.out.println("+-------------------------------------------------------------------------+");
            System.out.println("|Thong tin khach hang:                                                    |");
            System.out.print("|");
            System.out.printf("%-73s", "Ma khach hang: " + kh.getMSKH());
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "Ten khach hang: " + kh.getHoten());
            System.out.println("|");
            System.out.print("|");
            System.out.printf("%-73s", "So dien thoai: " + kh.getSdt());
            System.out.println("|");
        }
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|Chi tiet hoa don:                                                        |");
        System.out.println("+----------+------------------------------+----------+--------------------+");
        System.out.print("|");
        System.out.printf("%-10s", "Ma SP");
        System.out.print("|");
        System.out.printf("%-30s", "Ten san pham");
        System.out.print("|");
        System.out.printf("%-10s", "So luong");
        System.out.print("|");
        System.out.printf("%-20s", "Gia tien");
        System.out.println("|");
        for (int i = 0; i < n; i++) {
            System.out.print("|");
            System.out.printf("%-10s", sp[i].getMaMon());
            System.out.print("|");
            System.out.printf("%-30s", sp[i].getTenMon());
            System.out.print("|");
            System.out.printf("%-10s", soLuong[i]);
            System.out.print("|");
            System.out.printf("%-20s", sp[i].getGiaBan() * soLuong[i]);
            System.out.println("|");
        }
        System.out.println("+----------+------------------------------+----------+--------------------+");
        System.out.print("|");
        System.out.printf("%-52s", "Tong tien");
        System.out.print("|");
        System.out.printf("%-20.2f", price());
        System.out.println("|");
        System.out.println("+----------------------------------------------------+--------------------+");

    }

    @Override
    public void TachTT(String pn, DanhSachNhanVien a, DanhSachMonAn b, DanhSachKhachHang c) {

        String[] word = pn.split(",");
        Date.xulyngay(word[0]);
        setId(word[1]);
        if (a.TimKiemNhanVienReturnNV(word[2]) != null) {
            if (word[2].indexOf("MN") == 0) {
                nv = new Manager((Manager) a.TimKiemNhanVienReturnNV(word[2]));
            } else if (word[2].indexOf("FT") == 0) {
                nv = new FullTime((FullTime) a.TimKiemNhanVienReturnNV(word[2]));
            } else if (word[2].indexOf("PT") == 0) {
                nv = new PartTime((PartTime) a.TimKiemNhanVienReturnNV(word[2]));
            }
        } else {
            nv = null;
        }
        if (c.SearchKH(word[3]) == null) {
            kh = null;
        } else {
            kh = new KhachHang(c.SearchKH(word[3]));
        }
        setN(word.length - 4);
        sp = new MonAn[n];
        soLuong = new int[n];
        int j = 4;
        int i = 0;
        while (j < word.length) {
            String[] k = word[j].split("#");
            MonAn temp_MonAn = new MonAn();
            if ((temp_MonAn = (b.timKiemMaMon(k[0])).clone()).getMaMon().equals("null")) {
                System.out.println(temp_MonAn);
                sp[i] = temp_MonAn.clone();
                soLuong[i] = Integer.parseInt(k[1]);
                i++;
            }
            j++;
        }
        setN(i);
    }

    @Override
    public String toString() {
        String s;
        if (nv != null) {
            s = Date.toString() + "," + id + "," + nv.getMaNV();
        } else {
            s = Date.toString() + "," + id + "," + "null";
        }
        if (kh != null) {
            s = s + "," + kh.getMSKH();
        } else {
            s = s + "," + "null";
        }
        for (int i = 0; i < n; i++) {
            s = s + "," + sp[i].getMaMon() + "#" + soLuong[i];
        }
        return s + "\n";
    }

    @Override
    public void xuat() {
        System.out.print("|");
        System.out.printf("%-12s", Date.toString());
        System.out.print("|");
        System.out.printf("%-12s", id);
        System.out.print("|");
        if (nv == null) {
            System.out.printf("%-15s", "null");
        } else {
            System.out.printf("%-15s", nv.getMaNV());
        }
        System.out.print("|");
        if (kh == null) {
            System.out.printf("%-15s", "null");
            System.out.print("|");
        } else {
            System.out.printf("%-15s", kh.getMSKH());
            System.out.print("|");
        }
        System.out.printf("%-20.2f", price());
        System.out.println("|");
    }
}