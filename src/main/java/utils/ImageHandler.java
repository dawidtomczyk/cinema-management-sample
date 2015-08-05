package utils;

import javax.swing.*;
import java.net.URL;

/**
 * Created by Dawid on 2015-07-16.
 */
public class ImageHandler {

    public static ImageIcon uploadIcon(String path)
    {
        URL url = System.class.getResource(path);

        if(url!=null)
        {
           return new ImageIcon(url);
        }
        return null;
    }
}
