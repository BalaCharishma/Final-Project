import javax.swing.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class EventHandler implements ActionListener {
    private final UIComponents ui;
    private final PasswordGenerator generator;
    private final PasswordUtils utils;
    private final ThemeManager theme;

    public EventHandler(UIComponents ui, PasswordGenerator generator, PasswordUtils utils, ThemeManager theme) {
        this.ui = ui;
        this.generator = generator;
        this.utils = utils;
        this.theme = theme;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == ui.themeToggleButton) {
            boolean dark = theme.toggleTheme();
            theme.applyTheme(ui.mainPanel, dark);
        } else if (source == ui.generateButton) {
            int length = (Integer) ui.lengthSpinner.getValue();
            String base = ui.baseWordField.getText().trim();
            boolean upper = ui.upperCaseCheck.isSelected();
            boolean lower = ui.lowerCaseCheck.isSelected();
            boolean numbers = ui.numberCheck.isSelected();
            boolean symbols = ui.symbolCheck.isSelected();

            String pwd = generator.generate(length, upper, lower, numbers, symbols, base);
            ui.passwordField.setText(pwd);
            String strength = utils.checkStrength(pwd);
            ui.strengthLabel.setText("Strength: " + strength);
        } else if (source == ui.copyButton) {
            String pwd = ui.passwordField.getText();
            StringSelection selection = new StringSelection(pwd);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            JOptionPane.showMessageDialog(ui.mainPanel, "Password copied to clipboard!");
        } else if (source == ui.historyButton) {
            JOptionPane.showMessageDialog(ui.mainPanel, utils.getHistory());
        }
    }
}