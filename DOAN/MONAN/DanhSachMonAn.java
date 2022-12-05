package MONAN;

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

    public void DocFile() {
        try {
            String s;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                String temp[] = s.split(";");
                String mm = temp[0];
                String tm = temp[1];
                Double gt = Double.parseDouble(temp[2]);
                foodList = Arrays.copyOf(foodList, length + 1);
                foodList[length] = new MonAn(mm, tm, gt);
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
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MonAn i : foodList) {
                bw.write(i.toString());
                bw.close();

            }
        } catch (Exception e) {

        }

    }
    private Integer findLongestName() {
        Integer max = foodList[0].getTenMon().length();
        for (int i = 1; i < length; i++) {
            max = max > foodList[0].getTenMon().length() ? max : foodList[0].getTenMon().length();
        }
        return max;
    }

    public void ThemMonAn() { // Them n mon an vao cuoi file
        int n;
        System.out.println("Nhap so luong mon an can them vao he thong: ");
        n = Integer.parseInt(sc.nextLine());
        MonAn[] maNew = new MonAn[n];
        for (int i = 0; i < n; i++) {
            maNew[i] = new MonAn();
            System.out.printf("-----------Moi nhap thong tin mon an thu %d------------\n", i + 1);
            maNew[i].Nhap();
            GhiFile();
        }
    }

    public void XoaMonAn() { // Xoa n mon an tren he thong
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
        File fp = new File(fileName);
        fp.delete(); // Xoa file cu
        for (int k = 0; k < length; k++) {
            GhiFile();
        }
    }

    public void TimKiemMonAn(String input) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (foodList[i].getMaMon().equalsIgnoreCase(input)) {
                foodList[i].Xuat();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Khong tim thay mon tren he thong. Vui long nhap lai");
        }
    }

    public void XuatDanhSach() {
        Integer longestName = findLongestName();
        System.out.println("=================Mon An====================");
        System.out.printf(" %-10s| %-" + longestName + "s | %10s\n\n", "Mã Món", "Tên Món", "Giá Tiền");
        for (int i = 0; i < length; i++) {
            System.out.printf("-----------------------------------\rMón ăn số " + (i + 1) + "\n\n");
            System.out.printf(" %-10s %-" + longestName + "s %10.3fVNĐ  \n\n", foodList[i].getMaMon(),foodList[i].getTenMon(), foodList[i].getGiaTien());
        }
        System.out.println("===========================================");
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
                                + maOrder[i].getGiaTien() + "------So luong: " + sl[i] + "------");
                        tongTien += maOrder[i].getGiaTien() * sl[i];
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

        testdrive.XuatDanhSach();
    }
}