import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    private boolean darkTheme = true;
    private final Color bgDark = new Color(33, 33, 55);
    private final Color bgLight = new Color(245, 245, 245);
    private final Color fgDark = Color.WHITE;
    private final Color fgLight = Color.BLACK;
    private final Color accentDark = new Color(102, 204, 255);
    private final Color accentLight = new Color(0, 102, 204);

    public void applyTheme(JPanel panel, boolean dark) {
        Color bg = dark ? bgDark : bgLight;
        Color fg = dark ? fgDark : fgLight;
        Color accent = dark ? accentDark : accentLight;

        panel.setBackground(bg);
        for (Component comp : panel.getComponents()) {
            comp.setBackground(bg);
            comp.setForeground(fg);
            if (comp instanceof JButton btn) {
                btn.setBackground(accent);
                btn.setForeground(Color.WHITE);
            }
            if (comp instanceof JCheckBox cb) {
                cb.setBackground(bg);
                cb.setForeground(fg);
            }
            if (comp instanceof JTextField tf) {
                tf.setBackground(Color.WHITE);
                tf.setForeground(Color.BLACK);
            }
        }
    }

    public boolean toggleTheme() {
        darkTheme = !darkTheme;
        return darkTheme;
    }
}2