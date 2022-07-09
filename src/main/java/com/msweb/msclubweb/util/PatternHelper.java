package com.msweb.msclubweb.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternHelper {
    private final static String PATTERN_QQ = "[1-9]([0-9]{5,11})";
    private final static String PATTERN_MAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    public static boolean checkQq(String str){
        return Pattern.matches(PATTERN_QQ, str);
    }

    public static boolean checkMail(String str){
        return Pattern.matches(PATTERN_MAIL,str);
    }
}
