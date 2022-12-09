package MONAN;

import java.util.*;
import EXCEPTION.*;
import INTERFACE.*;
import KHACHHANG.KhachHang;
import NHANVIEN.NhanVien;

import java.io.*;
import java.util.Arrays;

import DOANHTHU.HoaDon;

public class DanhSachMonAn implements DocGhiFile {

    private MonAn[] foodList = new MonAn[0];
    private int length=0;
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
        length=0;
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
        System.out.println("Vui lòng nhập số Món Ăn muốn thêm vào cùng một lúc :");
        System.out.print("                      Món\r");

        n = checkLoi.checkSo(sc.next());
        while (n == -1) {
            System.out.println("Lỗi định dạng, vui lòng nhập lại");
            n = checkLoi.checkSo(sc.next());

        }
        n += length;
        for (int i = length; i < n; i++) {
            foodList = Arrays.copyOf(foodList, i + 1);
            System.out.printf("-----------Mời nhập thông tin món ăn thứ %d trong danh sách------------\n", i + 1);
            foodList[i] = new MonAn();
            foodList[i].setMaMon("MH" + (i + 1));
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
            System.out.println("\n1.Thêm n Món Ăn\n\n2.Thêm Món ăn ở vị trí k\n\n0.Quay lại");
            System.out.println("Nhập lựa chọn :");

            c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Sai định dạng, vui lòng nhập SỐ");
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
                    System.out.println("Chọn 1");
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
        for (MonAn i : foodList) {
            if (i.getMaMon() == a.getMaMon()) {
                System.out.println(a.getMaMon());
                Integer vitri = Integer.parseInt(a.getMaMon().split("MH")[1]) - 1;
                for (int j = vitri; j < length; j++) {
                    foodList[j] = foodList[j + 1];
                    length--;
                }
            }
        }

        foodList = Arrays.copyOf(foodList, length);
        GhiFile();
        xuatDanhSach();
    }

    public void xoaMonAn() {
        do {
            System.out.println(" ___________Xoá___________");
            System.out.println(
                    "|  1.Xoá món ăn theo mã       |" +
                            "\n|                             |" +
                            "\n|  2.Xoá món ăn theo tên      |" +
                            "\n|                             |" +
                            "\n|  0.Quay lại                 |");
            System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
            System.out.println("Nhập lựa chọn :");

            Integer c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Sai định dạng, vui lòng nhập SỐ");
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

                    System.out.println("Nhập mã món cần xoá");
                    xoaMotMonAn(timKiemMaMon(sc.next()));
                    break;
                }

                ///
                case 2: {
                    System.out.println("Nhập tên món cần xoá ");
                    xoaMotMonAn(tiemKiemTenMonAn());
                    break;
                }
                ///

            }
            System.out.println("Nhập T hoặc t để dừng chương trình hoặc nhấn bất kỳ để tiếp tục");

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
        System.out.println("Vui lòng nhập giá tiền ");
        System.out.print("                      VNĐ\r");
        double input = checkLoi.checkSoThuc(sc.next());
        while (input == -1) {
            System.out.println("Vui lòng nhập lại");
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
            System.out.println("Không tìm thấy Món Ăn");
        } else {
            Integer spaceName = findLongestName();
            int space = (spaceName * 2 + 36);
            ///
            System.out.printf("%" + spaceName + "s   Danh sách món ăn có giá tiền = " + input + "\n", " ");
            keVienTren(space);
            System.out
                    .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                            + spaceName + "s \n", "Mã Món", "Tên Món", "Số Lượng", "  Giá Bán", "Giá Nhập|");
            System.out
                    .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                            + spaceName + "s \n", "", "", "(Cái/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");

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
            System.out.println(" __________Tìm kiếm__________________");
            System.out.println(
                    "|  1.Sửa & Tìm kiếm theo mã Món Ăn  |" +
                            "\n|                                   |" +
                            "\n|  2.Sửa & Tìm kiếm theo tên Món Ăn |" +
                            "\n|                                   |" +
                            "\n|  3.Tìm kiếm theo giá              |" +
                            "\n|                                   |" +
                            "\n|  0.Quay lại                       |");
            System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
            System.out.println("Nhập lựa chọn :");

            Integer c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Sai định dạng, vui lòng nhập SỐ");
                c = checkLoi.checkSo(sc.next());

            }
            while (c < 1 || c > 3) {
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
                    System.out.println("Nhập vào mã cần tìm");
                    String id = sc.next();
                    if ((a = timKiemMaMon(id).clone()).getTenMon() == null) {
                        System.out.println("Không tìm thấy Món Ăn");
                    } else {
                        xuatMotMonAn(a);
                    }
                    break;

                }

                ///
                case 2: {
                    System.out.println("Nhập tên món ăn cần tìm");
                    if ((a = tiemKiemTenMonAn().clone()).getTenMon() == null) {
                        System.out.println("Không tìm thấy Món Ăn");
                    } else {
                        xuatMotMonAn(a);
                    }
                    break;
                }

                ///
                case 3: {
                    System.out.println("Nhập giá cần tìm");
                    timKiemGiaMonAn();
                    break;
                }
            }
            if (a.getTenMon() != null) {
                suaMonAn(a);
            }
            System.out.println("Nhập T hoặc t để dừng tìm kiếm hoặc nhấn bất kỳ để tiếp tục");

        } while (!sc.next().equalsIgnoreCase("T"));

        // } while (sc.next().trim().charAt(0) != 'T');
    }

    ///
    /// *Sửa
    public void suaMonAn(MonAn a) {
        do {
            System.out.println(" _____________Sửa___________");
            System.out.println(
                    "|  1.Sửa Tên                  |" +
                            "\n|                             |" +
                            "\n|  2.Sửa Số Lượng             |" +
                            "\n|                             |" +
                            "\n|  3.Sửa Giá Bán              |" +
                            "\n|                             |" +
                            "\n|  4.Sửa Giá Nhập             |" +
                            "\n|                             |" +
                            "\n|  0.Quay lại                 |");
            System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
            System.out.println("Nhập lựa chọn :");

            Integer c = checkLoi.checkSo(sc.next());
            while (c == -1) {
                System.out.println("Sai định dạng, vui lòng nhập SỐ");
                c = checkLoi.checkSo(sc.next());

            }
            while (c < 1 || c > 3) {
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
                    System.out.println("Tên mới : ");
                    String tenMoi = sc.next();
                    tenMoi = tenMoi + sc.nextLine();
                    System.out.println(tenMoi + " thay cho " + a.getTenMon());
                    a.tenMon = tenMoi;
                    break;

                }

                ///
                case 2: {
                    System.out.println("Sửa số lượng  :");
                    Integer temp = a.getSoLuong();
                    a.setSoLuong(temp);
                    break;
                }

                ///
                case 3: {
                    System.out.println("Sửa giá bán");
                    Double temp = a.getGiaBan();
                    a.setGiaBan(temp);
                    break;
                }
                case 4: {
                    System.out.println("Sửa giá bán");
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
            System.out.printf("%" + spaceName + "s   Món ăn \n", " ");
            keVienTren(space);
            System.out.printf(
                    "|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s \n",
                    "Mã Món", "Tên Món", "Số Lượng", "  Giá Bán", "Giá Nhập|");
            System.out.printf(
                    "|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s \n", "",
                    "", "(Cái/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");
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
        System.out.printf("%-" + spaceName + "s   Danh sách món ăn \n", " ");
        keVienTren(space);
        System.out
                .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                        + spaceName + "s \n", "Mã Món", "Tên Món", "Số Lượng", "  Giá Bán", "Giá Nhập|");
        System.out
                .printf("|%-10s %-" + spaceName + "s %-" + spaceName + "s %-" + spaceName + "s %-"
                        + spaceName + "s \n", "", "", "(Cái/Lon/Ly)", "   (VNĐ)", "  (VNĐ) |");

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

                System.out.println(" ___________Đặt hàng___________");
                System.out.println(
                        "|  1.Chọn món                 |" +
                                "\n|                             |" +
                                "\n|  2.Thanh Toán               |" +
                                "\n|                             |" +
                                "\n|  0.Quay lại                 |");
                System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
                System.out.println("Nhập lựa chọn :");
                number = checkLoi.checkSo(sc.next());
                while (number == -1) {
                    System.out.println("Sai định dạng, vui lòng nhập SỐ");
                    number = checkLoi.checkSo(sc.next());

                }
                while (number < 1 || number > 2) {
                    if (number == 0) {
                        break;
                    }
                    System.out.println("\r\rVui lòng nhập lại");
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
                                System.out.println("Trong kho chỉ còn " + b.getSoLuong());
                                sl = checkLoi.checkSo(sc.next());
                            }
                            while (sl == -1) {
                                System.out.println("Sai lỗi định dạng");
                                sl = checkLoi.checkSo(sc.next());

                            }
                            temp.addSP(b, sl);
                            System.out.println("Nhập 0 để thoát hoặc bấm bất kỳ để tiếp tục thêm món");
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
                            System.out.println(temp.toString());
                            bw.write(temp.toString());
                            bw.close();
                            fw.close();
                            System.out.println("done");
                        } catch (Exception e) {
                            System.out.println("Lỗi");
                        }
                        break;
                    }

                }
            } while (number != 2);
            if (number == 0) {
                break;
            }
            System.out.println("Nhập 0 để dừng hoặc bất kỳ để tiếp tục");
        } while (checkLoi.checkSo(sc.next()) != 0);
        // Tra ve gia tri bao loi neu he thong thanh toan loi
    }

    public void menu() {
        Integer c;
        do {
            do {

                System.out.println(" ____________= Menu Món Ăn =_______________");
                System.out.println(
                        "|  1.Thêm Món ăn                    |" +
                                "\n|                                   |" +
                                "\n|  2.Xoá Món Ăn                     |" +
                                "\n|                                   |" +
                                "\n|  3.Sửa & Tìm kiếm                 |" +
                                "\n|                                   |" +
                                "\n|  4.Xuất danh sách                 |" +
                                "\n|                                   |" +
                                "\n|  0.Quay lại                       |");
                System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
                System.out.println("Nhập lựa chọn :");

                c = checkLoi.checkSo(sc.next());
                while (c == -1) {
                    System.out.println("Sai định dạng, vui lòng nhập SỐ");
                    c = checkLoi.checkSo(sc.next());

                }
                while (c < 1 || c > 4) {
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
            System.out.println("Nhập T hoặc t để dừng tìm kiếm hoặc nhấn bất kỳ để tiếp tục");

        } while (!sc.next().equalsIgnoreCase("T"));
    }

    public static void main(String[] args) {
        DanhSachMonAn testdrive = new DanhSachMonAn();
        // testdrive.menu();
        // testdrive.DocFile();
        // testdrive.order();
        // testdrive.xuatDanhSach();
        // testdrive.timKiemMonAn();
        // testdrive.xuatDanhSach();
        // testdrive.sortDanhSach();
        // testdrive.themMonAnCuoiDanhSach();
        // testdrive.themMonAnOViTriK(6);
        // testdrive.xuatMotMonAn(testdrive.tiemKiemTenMonAn());
        // testdrive.themMonAn();
testdrive.timKiemMaMon("MH08")  ;
}
}