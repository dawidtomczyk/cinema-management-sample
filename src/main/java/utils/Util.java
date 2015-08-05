package utils;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dawid on 2015-07-16.
 */
public class Util {

    public static boolean checkInput(String text, String patternn) {

        Pattern pattern = Pattern.compile(patternn);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches())
            return false;

        return true;
    }
    public static void showConfirmDialog(Container parent,String message,String title){
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);

    }
}
