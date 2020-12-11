package utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * @author yuzhihai
 */
public class SwingUtil {
    public static void setUIFont()
    {
        InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 18));
    }
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static void setUITheme(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
