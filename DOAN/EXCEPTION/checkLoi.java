package EXCEPTION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkLoi {

    public static int checkSo(String s) {

        try {
            int k = Integer.parseInt(s);
            return k;

        } catch (Exception e) {
            return -1;
        }

    }

    public static Boolean checkNgay(String s) {
        Pattern rule = Pattern.compile("\\d{2}[/]\\d{2}[/]\\d{4}");
        Matcher check = rule.matcher(s);
        if (check.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static int inputIntNumberError(String s) {
        try {
            int k = Integer.parseInt(s);
            return k;
        } catch (Exception e) {
            return -1;
        }

    }

    public static double inputDoubleNumberError(String s) {
        try {
            double k = Double.parseDouble(s);
            return k;
        } catch (Exception e) {
            return -1;
        }

    }

    public static long inputLongNumberError(String s) {
        try {
            long k = Long.parseLong(s);
            return k;
        } catch (Exception e) {
            return -1;
        }
    }

    public static char continueString(String s) {
        if (s.length() > 1) {
            return 'a';
        } else {
            try {
                char a = s.charAt(0);
                return a;
            } catch (Exception e) {
                return 'a';
            }
        }

    }

    public static Boolean checkKiTu(String s) {
        if (s == "") {
            return false;
        } else {
            Pattern rule = Pattern.compile("^[a-zA-Z0-9 ]*$");
            Matcher check = rule.matcher(s);
            if (check.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static Double checkSoThuc(String s) {
        try {
            double k=Double.parseDouble(s);
            return k;

        } catch (Exception e) {
            return -1.0;
        }
    }
    
    public static Boolean checkAddress(String s) {
        if (s == "") {
            return false;
        } else {
            Pattern rule = Pattern.compile("^[a-zA-Z0-9 /]*$");
            Matcher check = rule.matcher(s);
            if (check.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

}
