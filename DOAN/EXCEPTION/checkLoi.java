package EXCEPTION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkLoi {

    public static int checkSo (String s) {

        try {
            int k=Integer.parseInt(s);
            return k;

        } catch (Exception e) {
            return -1;
        }
        
    }

    public static Boolean checkNgay(String s) {
        Pattern rule = Pattern.compile("\\d{2}[/]\\d{2}[/]\\d{4}");
        Matcher check = rule.matcher(s);
        if(check.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
}

