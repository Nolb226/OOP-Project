package MONAN;

import java.util.*;
import EXCEPTION.*;
import INTERFACE.*;
import KHACHHANG.DanhSachKhachHang;
import KHACHHANG.KhachHang;
import NHANVIEN.DanhSachNhanVien;
import NHANVIEN.Manager;
import NHANVIEN.NhanVien;

import java.io.*;
import java.util.Arrays;

import DOANHTHU.DanhSachHoaDon;
import DOANHTHU.DanhSachPhieuNhap;
import DOANHTHU.DoanhThu;
import DOANHTHU.DoanhThuThang;
import DOANHTHU.HoaDon;

public class DanhSachMonAn implements DocGhiFile {

    public MonAn[] foodList = new MonAn[0];
    public int length = 0;
    private static Scanner sc = new Scanner(System.in);
    public static String fileName = "DOAN/MONAN/MonAn";

    private String[] backupData = { "MH01;Banh bao;3;15000.0;14000.0", "MH02;Banh Mi Thit;1;20000.0;15000.0",
            "MH03;Com ga;4;45000.0;30000.0",
            "MH04;Burger;5;35000.0;25000.0",
            "MH05;Khoai tay chien;12;13000.0;5000.0",
            "MH06;Coca Cola;12;12000.0;8000.0",
            "MH07;Tra dao;5;25000.0;15000.0",
            "MH08;Sprite;12;12000.0;8000.0",
            "MH09;Kho muc;24;25000.0;15000.0",
            "MH10;Ga ran;7;50000.0;25000.0",
            "MH11;Fanta;12;12000.0;8000.0",
            "MH12;Pho mai que;12;20000.0;12000.0",
            "MH13;Com ga;6;50000.0;30000.0",
            "MH14;Coca Zero;12;15000.0;8000.0",
            "MH15;Banh mi ga;5;20000.0;18000.0",
            "MH16;Tra vai;12;25000.0;15000.0" };

    private void backupFile() {
        System.out.print("Running backup file....");
        for (String s : backupData) {
            System.out.print(".");
            String temp[] = s.split(";");
            String mm = temp[0];
            String tm = temp[1];
            Integer sl = Integer.parseInt(temp[2]);
            Double gb = Double.parseDouble(temp[3]);
            Double gn = Double.parseDouble(temp[4]);
            foodList = Arrays.copyOf(foodList, length + 1);
            foodList[length] = new MonAn(mm, tm, sl, gb, gn);
            length++;
        }
        System.out.println("\n..Done");
    }

    public DanhSachMonAn() {
        length = 0;
        DocFile();
    }

    public DanhSachMonAn(MonAn[] foodList) {
        this.foodList = foodList;
    }

    public DanhSachMonAn(DanhSachMonAn orther) {
        this.foodList = orther.foodList;
    }

    public MonAn[] getSpList() {
        return foodList;
    }

    @Override
    public void DocFile() {
        try {
            String s;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                String temp[] = s.split(";");
                String mm = temp[0];
                String tm = temp[1];
                Integer sl = Integer.parseInt(temp[2]);
                Double gb = Double.parseDouble(temp[3]);
                Double gn = Double.parseDouble(temp[4]);
                foodList = Arrays.copyOf(foodList, length + 1);
                foodList[length] = new MonAn(mm, tm, sl, gb, gn);
                length++;
            }
            br.close();
            fr.close();
            if (foodList.length == 0) {
                throw new FileNotFoundException("File rỗng");
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Không tìm thấy file, FILE NOT FOUND! Hoặc File Rỗng, Empty File!");
                backupFile();
            } else {
                System.out.println("Loi doc du lieu tu file!");
                backupFile();
            }

        }

    }

    @Override
    public void GhiFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < length; i++) {

                bw.write(foodList[i].toString());
            }
            bw.close();
            fw.close();
        } catch (Exception e) {

        }

    }

    private Integer findLongestName() {
        Integer max = foodList[0].getTenMon().length();
        for (int i = 1; i < length; i++) {

            max = max > foodList[i].getTenMon().length() ? max : foodList[i].getTenMon().length();

        }
        return max;
    }

    private void sortDanhSach() {
        MonAn[] copy = Arrays.copyOf(foodList, length);
        for (MonAn i : copy) {
            foodList[Integer.parseInt(i.getMaMon().split("MH")[1]) - 1] = i;
        }
        GhiFile();
    }

    //
    // Thêm
    public void themMonAnCuoiDanhSach() {
        int n;
        System.out.println("So mon an them vao cung mot luc:");
        System.out.print("                      Mon\r");

        n = checkLoi.checkSo(sc.next());
        while (n == -1) {
            System.out.println("Vui long nhap so");
            n = checkLoi.checkSo(sc.next());

        }
        n += length;
        for (int i = length; i < n; i++) {
            foodList = Arrays.copyOf(foodList, i + 1);
            System.out.printf("-----------Mon an thu %d trong danh sach------------\n", i + 1);
            foodList[i] = new MonAn();
            foodList[i].setMaMon(String.format("MH%02d", i + 1));
            foodList[i].Nhap();
        }
        length = n;
        xuatDanhSach();
        GhiFile();
    }

    public void themMonAnOViTriK(Integer vitri) {
        foodList = Arrays.copyOf(foodList, length + 1);

        for (int i = length; i > vitri - 1; i--) {

            foodList[i] = foodList[i - 1].clone();
            foodList[i].setMaMon(String.format("MH%02d", i + 1));
        }

        foodList[vitri - 1].Nhap();
        foodList[vitri - 1].setMaMon(String.format("MH%02d", vitri));

        System.out.println(vitri - 1);
        length++;
        xuatDanhSach();
        // GhiFile();
    }

    public void themMonAn() {
        Integer c;
        do {
            System.out.println(" ________________Them________________");
            System.out.println(
                    "|  1.Them n mon an                  |" +
                            "\n|                                   |" +
                            "\n|  2.Them mon an o vi tri k         |" +
                            "\n|                                   |" +
                            "\n|  0.Quay lai                       |");
            System.out.println(" -----------------------------------");
            System.out.println("Nhap lua chon:");

            c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Vui long nhap so");
                c = checkLoi.checkSo(sc.next());
            }
            while (c < 1 || c > 2) {
                if (c == 0) {
                    break;
                }
                System.out.println("\r\rVui lòng nhập lại");
                c = checkLoi.checkSo(sc.next());
            }
            if (c == 0) {
                break;
            }
            switch (c) {
                case 1: {

                    themMonAnCuoiDanhSach();
                    break;
                }

                ///
                case 2: {
                    System.out.println("2.Thêm Món ăn ở một vị trí\n\n0.Quay lại");
                    Integer input = checkLoi.checkSo(sc.next());
                    break;
                }
                ///

            }
            System.out.println("Nhập 0 để dừng chương trình hoặc nhấn bất kỳ để tiếp tục");

        } while ((c = checkLoi.checkSo(sc.next())) != 0);
    }

    //
    // Xoá
    public void xoaMotMonAn(MonAn a) {
        if (a.getMaMon() != null) {
            int n = length;
            for (MonAn i : foodList) {
                if (i.getMaMon().equals(a.getMaMon())) {
                    Integer vitri = Integer.parseInt(a.getMaMon().split("MH")[1]) - 1;
                    for (int j = vitri; j < n - 1; j++) {
                        foodList[j] = foodList[j + 1];
                        foodList[j].setMaMon(String.format("MH%02d", j + 1));
                    }
                }
            }

            foodList = Arrays.copyOf(foodList, length - 1);
            length = length - 1;
            GhiFile();
            xuatDanhSach();
        } else {
            System.out.println("Khong tim thay Mon An");
        }
    }

    public void xoaMonAn() {
        do {
            System.out.println("________________Xoa_________________");
            System.out.println(
                    "|  1.Xoa mon an theo ma        |" +
                            "\n|                             |" +
                            "\n|  2.Xoa mon an theo ten      |" +
                            "\n|                             |" +
                            "\n|  0.Quay lai                 |");
            System.out.println(" -----------------------------------------");
            System.out.println("Nhap lua chon:");

            Integer c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Khong hop le, vui long nhap lai");
                c = checkLoi.checkSo(sc.next());
            }
            while (c < 1 || c > 2) {
                if (c == 0) {
                    break;
                }
                System.out.println("Vui long nhap lai");
                c = checkLoi.checkSo(sc.next());
            }
            if (c == 0) {
                break;
            }
            switch (c) {
                case 1: {
                    System.out.println("Nhap ma mon an can xoa: ");
                    xoaMotMonAn(timKiemMaMon(sc.next()));
                    break;
                }

                ///
                case 2: {
                    System.out.println("Nhap ten mon an can xoa:");
                    xoaMotMonAn(tiemKiemTenMonAn());
                    break;
                }
                ///

            }
            System.out.println("Nhap 't' de thoat hoac nhap bat ky de tiep tuc");

        } while (!sc.next().equalsIgnoreCase("T"));
        // GhiFile();
    }

    //
    // Tim kiem
    public MonAn timKiemMaMon(String input) {
        MonAn a = new MonAn();
        for (int i = 0; i < length; i++) {
            if (foodList[i].getMaMon().equals(input.trim())) {
                a = foodList[i].clone();
            }
        }

        return a;
    }

    public MonAn tiemKiemTenMonAn() {
        String input = sc.next();
        input = input + sc.nextLine();
        MonAn b = new MonAn();
        for (int i = 0; i < length; i++) {
            if (foodList[i].getTenMon().equalsIgnoreCase(input.trim())) {
                b = foodList[i].clone();
                System.out.println(1);
            }
        }
        return b;
    }

    public MonAn[] timKiemGiaMonAn() {
        System.out.println("Vui long nhap gia tien ");
        System.out.print("                      VNĐ\r");
        double input = checkLoi.checkSoThuc(sc.next());
        while (input == -1) {
            System.out.println("Khong hop le, vui long nhap lai");
            System.out.print("                      VNĐ\r");
            input = checkLoi.checkSoThuc(sc.next());
        }

        MonAn[] temp = new MonAn[0];
        int j = 0;
        for (int i = 0; i < length; i++) {
            // System.out.println(foodList[i].getGiaBan()==input);
            if (foodList[i].getGiaBan() == input) {
                temp = Arrays.copyOf(temp, j + 1);
                temp[j++] = foodList[i].clone();
            }
        }
        if (temp.length == 0) {
            System.out.println("Khong tim thay!");
        } else {
            Integer spaceName = findLongestName();
            int space = (spaceName * 2 + 36);
            ///
            System.out.printf("%" + spaceName + "s   Danh sach mon an co gia = " + input + "\n", " ");
            keVienTren(space);
            System.out
                    .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                            + spaceName + "s \n", "Ma mon", "Ten mon", "So luong", "  Gia ban", "Gia nhap|");
            System.out
                    .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                            + spaceName + "s \n", "", "", "(Cai/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");

            ///
            for (int i = 0; i < j; i++) {
                System.out.printf("|  %" + space + "s\n", "|");
                System.out.printf(
                        "| %-10s %-" + spaceName + "s   %-" + spaceName + "d %-" + spaceName + ".0f %-5.0f|\n",
                        temp[i].getMaMon(), temp[i].getTenMon(), temp[i].getSoLuong(),
                        temp[i].getGiaBan(), temp[i].getGiaNhap());
                // keVienDuoi(space);
                System.out.printf("|  %" + space + "s\n", "|");

            }
            keVienDuoi(space);
            ///

            System.out.print("\n");
        }
        return temp;
    }

    public void timKiemMonAn() {
        MonAn a = new MonAn();
        do {
            System.out.println(" __________Tim kiem__________________");
            System.out.println(
                    "|  1.Sua va tim kiem mon an theo ma |" +
                            "\n|                                   |" +
                            "\n|  2.Sua va tim kiem mon an theo ten|" +
                            "\n|                                   |" +
                            "\n|  3.TIm kiem theo gia              |" +
                            "\n|                                   |" +
                            "\n|  0.Quay lai                       |");
            System.out.println(" -----------------------------------------");
            System.out.println("Nhap lua chon :");

            Integer c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Khong hop le, vui long nhap lai");
                c = checkLoi.checkSo(sc.next());

            }
            while (c < 1 || c > 3) {
                if (c == 0) {
                    break;
                }
                System.out.println("\r\rKhong hop le, vui long nhap lai");
                c = checkLoi.checkSo(sc.next());
            }
            if (c == 0) {
                break;
            }

            switch (c) {
                case 1: {
                    System.out.println("Nhap ma mon an: ");
                    String id = sc.next();
                    if ((a = timKiemMaMon(id).clone()).getTenMon() == null) {
                        System.out.println("Khong tim thay!");
                    } else {
                        xuatMotMonAn(a);
                    }
                    break;

                }

                ///
                case 2: {
                    System.out.println("Nhap ten mon an: ");
                    if ((a = tiemKiemTenMonAn().clone()).getTenMon() == null) {
                        System.out.println("Khong tim thay!");
                    } else {
                        xuatMotMonAn(a);
                    }
                    break;
                }

                ///
                case 3: {
                    System.out.println("Nhap gia: ");
                    timKiemGiaMonAn();
                    break;
                }
            }

            if (a.getTenMon() != null) {
                suaMonAn(a);
            }
            System.out.println("Nhap 't' de thoat hoac nhap bat ky de tiep tuc");

        } while (!sc.next().equalsIgnoreCase("T"));

        // } while (sc.next().trim().charAt(0) != 'T');
    }

    ///
    /// *Sửa
    public void suaMonAn(MonAn a) {

        do {
            System.out.println(" _____________Sửa_____________");
            System.out.println(
                    "|  1.Sua Ten                  |" +
                            "\n|                             |" +
                            "\n|  2.Sua so luong             |" +
                            "\n|                             |" +
                            "\n|  3.Sua gia ban              |" +
                            "\n|                             |" +
                            "\n|  4.Sua gia nhap             |" +
                            "\n|                             |" +
                            "\n|  0.Quay lai                 |");
            System.out.println(" -----------------------------------");
            System.out.println("Nhap lua chon :");

            Integer c = checkLoi.checkSo(sc.next());

            while (c == -1) {
                System.out.println("Khong hop le, vui long nhap lai");
                c = checkLoi.checkSo(sc.next());

            }
            while (c < 1 || c > 3) {
                if (c == 0) {
                    break;
                }
                System.out.println("\r\rKhong hop le, vui long nhap lai");
                c = checkLoi.checkSo(sc.next());
            }
            if (c == 0) {
                break;
            }

            switch (c) {
                case 1: {
                    System.out.println("Nhap ten moi: ");
                    String tenMoi = sc.next();
                    tenMoi = tenMoi + sc.nextLine();
                    System.out.println(tenMoi + " thay cho " + a.getTenMon());
                    a.tenMon = tenMoi;
                    break;

                }

                ///
                case 2: {
                    System.out.println("Sua so luong: ");
                    Integer temp = a.getSoLuong();
                    a.setSoLuong(temp);
                    break;
                }

                ///
                case 3: {
                    System.out.println("Sua gia ban: ");
                    Double temp = a.getGiaBan();
                    a.setGiaBan(temp);
                    break;
                }
                case 4: {
                    System.out.println("Sua gia nhap: ");
                    Double temp = a.getGiaNhap();
                    a.setGiaNhap(temp);
                    break;
                }
            }
            xuatMotMonAn(a);
            foodList[Integer.parseInt(a.getMaMon().split("MH")[1]) - 1] = a.clone();
            GhiFile();
            break;
        } while (true);
    }

    //
    /// * Xuat
    public void keVienTren(int space) {
        for (int i = 0; i <= space + 1; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public void keVienDuoi(int space) {
        for (int i = 0; i <= space + 1; i++) {
            System.out.print("‾");
        }
        System.out.println();
    }

    public boolean xuatMotMonAn(MonAn i) {
        if (i.getMaMon() != null) {
            Integer spaceName = findLongestName();
            int space = (spaceName + 51);
            ///
            System.out.printf("%" + spaceName + "s   Mon an \n", " ");
            keVienTren(space);
            System.out.printf(
                    "|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s \n",
                    "Ma mon", "Ten mon", "So luong", "  Gia ban", "Gia nhap|");
            System.out.printf(
                    "|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s \n", "",
                    "", "(Cai/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");
            System.out.printf("|  %" + space + "s\n", "|");
            System.out.printf(
                    "| %-10s %-" + spaceName + "s   %-" + spaceName + "d %-" + spaceName + ".0f %-5.0f|\n",
                    i.getMaMon(), i.getTenMon(), i.getSoLuong(),
                    i.getGiaBan(), i.getGiaNhap());
            System.out.printf("|  %" + space + "s\n", "|");
            keVienDuoi(space);
            return true;
        } else {
            return false;
        }
    }

    public void xuatDanhSach() {
        Integer spaceName = findLongestName();
        int space = (spaceName + 51);

        ///
        System.out.printf("%-" + spaceName + "s   Danh sach mon an \n", " ");
        keVienTren(space);
        System.out
                .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                        + spaceName + "s \n", "Ma mon", "Ten mon", "So luong", "  Gia ban", "Gia nhap|");

        System.out
                .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                        + spaceName + "s \n", "", "", "(Cai/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");

        ///
        for (int i = 0; i < length; i++) {
            System.out.printf("|  %" + space + "s\n", "|");
            System.out.printf(
                    "| %-10s %-" + spaceName + "s   %-" + spaceName + "d %-" + spaceName + ".0f %-5.0f|\n",
                    foodList[i].getMaMon(), foodList[i].getTenMon(), foodList[i].getSoLuong(),
                    foodList[i].getGiaBan(), foodList[i].getGiaNhap());
            System.out.printf("|  %" + space + "s\n", "|");

        }
        keVienDuoi(space);
        ///

        System.out.print("\n");
    }

    public void order(NhanVien nv, KhachHang kh) {
        int sl;
        int number;
        boolean check = false;
        Integer rd = (int) (Math.random() * 10000);
        HoaDon temp = new HoaDon("HD" + rd.toString());
        temp.setKh(kh);
        temp.setNv(nv);
        do {
            do {

                System.out.println(" ___________Dat hang___________");
                System.out.println(
                        "|  1.Chon mon                |" +
                                "\n|                             |" +
                                "\n|  2.Thanh toan               |" +
                                "\n|                             |" +
                                "\n|  0.Quay lai                 |");
                System.out.println(" -----------------------------------------");
                System.out.println("Nhap lua chon: ");
                number = checkLoi.checkSo(sc.next());
                while (number == -1) {
                    System.out.println("Khong hop le, vui long nhap lai");
                    number = checkLoi.checkSo(sc.next());

                }
                while (number < 1 || number > 2) {
                    if (number == 0) {
                        break;
                    }
                    System.out.println("Khong hop le, vui long nhap lai");
                    number = checkLoi.checkSo(sc.next());
                }
                if (number == 0) {
                    break;
                }

                switch (number) {
                    case 1: {
                        check = true;
                        do {
                            System.out.println("Nhập mã món ăn thêm vào");
                            MonAn b = timKiemMaMon(sc.next());
                            System.out.println("Nhập số lượng muốn mua");
                            sl = checkLoi.checkSo(sc.next());
                            while (sl > b.getSoLuong()) {
                                System.out.println("Trong kho chi con " + b.getSoLuong());
                                sl = checkLoi.checkSo(sc.next());
                            }
                            while (sl == -1) {
                                System.out.println("Sai lỗi định dạng");
                                sl = checkLoi.checkSo(sc.next());

                            }

                            temp.addSP(b, sl);
                            System.out.println("Nhap 0 de thoat hoac nhap bat ky de tiep tuc");
                        } while ((number = checkLoi.checkSo(sc.next())) != 0);
                        break;
                    }

                    case 2: {
                        if (!check) {
                            System.out.println("Bạn chưa  thêm món");
                            break;
                        }
                        try {
                            FileWriter fw = new FileWriter("DOAN/DOANHTHU/DSHD.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            temp.inPhieu();
                            bw.write(temp.toString());
                            bw.close();
                            fw.close();
                            System.out.println("done");
                        } catch (Exception e) {
                            System.out.println("Loi");
                        }
                        break;
                    }

                }
            } while (number != 2);
            if (number == 0) {
                break;
            }
            System.out.println("Nhap 0 de dung hoac bat ky de tiep tuc");
        } while (checkLoi.checkSo(sc.next()) != 0);
        // Tra ve gia tri bao loi neu he thong thanh toan loi
    }

    public void menu() {
        Integer c;
        do {
            do {

                System.out.println(" ___________= Menu Mon An =_________");
                System.out.println(
                        "|  1.Them mon an                    |" +
                                "\n|                                   |" +
                                "\n|  2.Xoa mon an                     |" +
                                "\n|                                   |" +
                                "\n|  3.Sua & Tim kiem                 |" +
                                "\n|                                   |" +
                                "\n|  4.Xuat danh sach                 |" +
                                "\n|                                   |" +
                                "\n|  0.Quay lai                       |");
                System.out.println(" ----------------------------------- ");
                System.out.println("Nhap lua chon:");

                c = checkLoi.checkSo(sc.next());
                while (c == -1) {
                    System.out.println("Khong hop le, vui long nhap lai");
                    c = checkLoi.checkSo(sc.next());

                }
                while (c < 1 || c > 4) {
                    if (c == 0) {
                        break;
                    }
                    System.out.println("Khong hop le, vui long nhap lai");
                    c = checkLoi.checkSo(sc.next());
                }
                if (c == 0) {
                    break;
                }

                switch (c) {
                    case 1: {
                        themMonAn();
                        break;
                    }

                    ///
                    case 2: {
                        xoaMonAn();
                        break;
                    }

                    ///
                    case 3: {
                        timKiemMonAn();
                        break;
                    }
                    case 4: {
                        xuatDanhSach();
                        break;
                    }
                }
            } while ((c != 0));
            if (c == 0) {
                break;
            }
            System.out.println("Nhap '0' de thoat hoac nhap bat ky de tiep tuc");

        } while ((c = checkLoi.checkSo(sc.next())) != 0);
    }

    public void nhapHang(DanhSachMonAn dsma, DanhSachPhieuNhap dspn, NhanVien a, DoanhThu DT) {
        dspn.nhapHang(a, dsma, DT);
    }

    public void banHang(DanhSachMonAn dsma, DanhSachHoaDon dshd, String mkh, DanhSachKhachHang dskh, NhanVien a,
            DoanhThu DT) {
        dsma.xuatDanhSach();
        dshd.themHoaDon(a, dskh, mkh, dsma, DT);
    }

    public static void main(String[] args) {
        DanhSachMonAn testdrive = new DanhSachMonAn();
        // testdrive.menu();
        // testdrive.DocFile();
        testdrive.menu();
        // testdrive.order();
        // testdrive.xuatDanhSach();
        // testdrive.timKiemMonAn();
        // testdrive.xuatDanhSach();
        // testdrive.sortDanhSach();
        // testdrive.themMonAnCuoiDanhSach();
        // testdrive.themMonAnOViTriK(6);
        // testdrive.xuatMotMonAn(testdrive.tiemKiemTenMonAn());
        // testdrive.themMonAn();
        testdrive.timKiemMaMon("MH08");
    }
}