package EXCEPTION;

public class checkLoi {

    public static int checkSo (String s) {

        try {
            int k=Integer.parseInt(s);
            return k;

        } catch (Exception e) {
            return -1;
        }
        
    }
    
}

