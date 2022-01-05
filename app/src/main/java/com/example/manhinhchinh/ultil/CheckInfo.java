package com.example.manhinhchinh.ultil;

import android.text.TextUtils;
import android.util.Patterns;

public class CheckInfo {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean checkPassword(String s){
        String pattern = "(?=.*[a-z])(?=\\S+$).{6,}";
        return s.matches(pattern);
    }
}
