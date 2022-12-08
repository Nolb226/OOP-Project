package MONAN;

import EXCEPTION.*;
import INTERFACE.*;
import java.io.*;
import java.util.Arrays;

public class DanhSachMonAn extends MonAn implements DocGhiFile {
    private MonAn[] foodList = new MonAn[0];
    private MonAn[] maOrder;
    private static int length;
    public static String fileName = "DOAN/MONAN/MonAn";

    public DanhSachMonAn() {
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
                System.out.println(foodList[length]);
                length++;
            }
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Loi doc du lieu tu file!");
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
        System.out.println("Nhập số món ăn thêm vào một lúc ");
        n = checkLoi.checkSo(sc.next());
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
        do {
            // while (true) {
                System.out.println("\n1.Thêm n Món Ăn\n\n2.Thêm Món ăn ở vị trí k\n\n0.Quay lại");
                System.out.println("Nhập lựa chọn :");
                String selection = sc.next();
                Integer c = checkLoi.checkSo(selection);
                if(c==0) {
                    break;
                }

                switch (c) {
                    case 1: {
                        System.out.println("1.Thêm n Món Ăn");
                        // themMonAnCuoiDanhSach();
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

                    default: {
                        break;
                    }
                }
                if (c < 1 || c > 2) {
                    System.out.println("Vui lòng nhập lại");

                } else {
                    System.out.println("Nhập T hoặc t để dừng chương trình hoặc nhấn bất kỳ để tiếp tục");

                    
                }
            // }
        } while (!sc.next().equalsIgnoreCase("T"));
    }
    //
    // Xoá
    public void xoaMonAn() { // Xoa n mon an tren he thong
        int n;
        System.out.println("Moi nhap vao so luong mon an can xoa tren he thong: ");
        n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("-----------------------------------------\n");
            String input;
            input = sc.nextLine();
            for (int j = 0; j < length; j++) {
                if (foodList[j].getMaMon().equalsIgnoreCase(input)) {
                    for (int u = j; u < length - 1; u++) {
                        foodList[u] = foodList[u + 1];
                    }
                    length--;
                }
            }
        }
        GhiFile();
    }

    //
    // Tim kiem
    public MonAn timKiemMaMon(String input) {
        MonAn a = new MonAn();
        for (int i = 0; i < length; i++) {
            if (foodList[i].getMaMon().equals(input.trim())) {
                a = foodList[i].clone();
                System.out.println(a);
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
        double input = Double.parseDouble(sc.next());

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
        do {
            while (true) {
                System.out.println("\n1.Tìm kiếm theo mã Món Ăn\n\n2.Tìm kiếm theo tên Món Ăn\n\n3.Tìm kiếm theo giá");
                System.out.println("Nhập lựa chọn :");
                String selection = sc.next();
                Integer c = checkLoi.checkSo(selection);

                switch (c) {
                    case 1: {
                        System.out.println("1.Tìm kiếm theo mã Món Ăn");
                        String id = sc.next();
                        if (!xuatMotMonAn(timKiemMaMon(id))) {
                            System.out.println("Không tìm thấy Món Ăn");
                        }
                        break;
                    }

                    ///
                    case 2: {
                        System.out.println("2.Tìm kiếm theo tên Món Ăn");
                        if (!xuatMotMonAn((tiemKiemTenMonAn()))) {
                            System.out.println("Không tìm thấy Món Ăn");
                        }
                        break;
                    }

                    ///
                    case 3: {
                        System.out.println("3.Tìm kiếm theo Giá Tiền");
                        timKiemGiaMonAn();

                    }
                    default: {
                        break;
                    }
                }
                if (c < 1 || c > 3) {
                    System.out.println("Vui lòng nhập lại");

                } else {
                    System.out.println("Nhập T hoặc t để dừng chương trình hoặc nhấn bất kỳ để tiếp tục");

                    
                }
            }
        } while (!sc.next().equalsIgnoreCase("T"));

        // } while (sc.next().trim().charAt(0) != 'T');
    }

    //
    // Xuat
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
            int space = (spaceName + 40);
            ///
            System.out.printf("%" + spaceName + "s   Món ăn \n", " ");
            keVienTren(space);
            System.out.printf("  %" + space + "s\r| %-10s| %-" + spaceName + "s | %" + spaceName + "s\n", "|",
                    " Mã Món",
                    "Tên Món", "Giá Tiền");
            System.out.printf("| %" + space + "s\n\r", "|");
            System.out.printf("  %" + space + "s\r-----------------------------------\r| Món ăn số "
                    + i.getMaMon().split("MH")[1] + "\n", "|");
            System.out.printf("| %" + space + "s\n\r", "|");
            System.out.printf("  %" + space + "s\r| %-10s %-" + spaceName + "s %" + spaceName + ".1fVNĐ \n", "|",
                    i.getMaMon(), i.getTenMon(), i.getGiaBan());
            System.out.printf("| %" + space + "s\n\r", "|");
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
            // keVienDuoi(space);
            System.out.printf("|  %" + space + "s\n", "|");

        }
        keVienDuoi(space);
        ///

        System.out.print("\n");
    }

    public double Order() {
        maOrder = new MonAn[10];
        int[] sl = new int[10];
        double tongTien = 0;
        int u = 0;
        int number;
        do {
            System.out.println("-------------------------");
            System.out.println("1.Chon mon\n2.Thanh toan"); // Chon thanh toan se tu dong xuat ra danh sach mon kem tong
                                                            // tien
            System.out.println("-------------------------");
            System.out.println("Moi nhap lua chon: ");
            String input;
            number = Integer.parseInt(sc.nextLine());
            switch (number) {
                case 1: {
                    int count = 0;
                    do {
                        System.out.println("Moi nhap vao ma mon an: ");
                        input = sc.nextLine();
                        for (int i = 0; i < length; i++) {
                            if (foodList[i].getMaMon().equalsIgnoreCase(input)) {
                                maOrder[u] = foodList[i];
                                System.out.println("Moi nhap vao so luong"); // Nhap so luong cua mon an khi duoc tim
                                // thay tren he thong
                                sl[u] = Integer.parseInt(sc.nextLine());
                                u++;
                                count++;
                            }
                        }
                        if (count == 0) {
                            System.out.println("Khong tim thay mon an tren he thong, moi nhap lai !");
                        }
                    } while (count == 0);
                }
                    break;

                case 2: {
                    System.out.println(
                            "=================================Hoa Don Thanh Toan=====================================");
                    for (int i = 0; i < u; i++) {
                        System.out.println("Ten mon: " + maOrder[i].getTenMon() + "------Don gia: "
                                + maOrder[i].getGiaBan() + "------So luong: " + sl[i] + "------");
                        tongTien += maOrder[i].getGiaBan() * sl[i];
                    }
                    return tongTien;
                }

                default:
                    System.out.println("Nhap sai lua chon, moi nhap lai");
                    break;
            }
        } while (number != 2);
        return -1; // Tra ve gia tri bao loi neu he thong thanh toan loi
    }

    public static void main(String[] args) {
        DanhSachMonAn testdrive = new DanhSachMonAn();
        testdrive.themMonAn();
        // testdrive.timKiemMonAn();
        // testdrive.xuatDanhSach();
        // testdrive.sortDanhSach();
        // testdrive.themMonAnCuoiDanhSach();
        // testdrive.themMonAnOViTriK(6);
        // testdrive.xuatMotMonAn(testdrive.tiemKiemTenMonAn());

    }
}