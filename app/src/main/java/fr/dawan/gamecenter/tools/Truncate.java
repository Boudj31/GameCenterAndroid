package fr.dawan.gamecenter.tools;

public class Truncate {

    public static String usingSubstringMethod(String text, int length) {
        if (text.length() <= length) {
            return text;
        } else {
            return text.substring(0, length) + "...";
        }
    }
}
