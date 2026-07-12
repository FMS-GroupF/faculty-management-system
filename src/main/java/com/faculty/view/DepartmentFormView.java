package com.faculty.view;

import javax.swing.*;
import java.awt.*;

public class DepartmentFormView extends JDialog {
    private JTextField txtName, txtHodName, txtStaffCount;
    private JButton btnSave, btnCancel;

    public DepartmentFormView(JFrame parent, String title) {
        super(parent, title, true);

        setSize(420, 320);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(AppTheme.BG_MAIN);
        content.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[]   labels = {"Department Name:", "Head of Department:", "Staff Count:"};
        txtName       = new JTextField();
        txtHodName    = new JTextField();
        txtStaffCount = new JTextField();
        JTextField[] fields = {txtName, txtHodName, txtStaffCount};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(AppTheme.bold(12));
            lbl.setForeground(AppTheme.TEXT_DARK);
            gbc.gridx = 0; gbc.gridy = i * 2; gbc.insets = new Insets(8, 0, 2, 0);
            content.add(lbl, gbc);

            gbc.gridy = i * 2 + 1; gbc.insets = new Insets(0, 0, 4, 0);
            AppTheme.styleField(fields[i]);
            content.add(fields[i], gbc);
        }

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setOpaque(false);
        btnSave   = AppTheme.primaryButton("Save");
        btnCancel = AppTheme.secondaryButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        gbc.gridy = labels.length * 2; gbc.insets = new Insets(14, 0, 0, 0);
        content.add(btnPanel, gbc);
    }

    // Getters
    public String getName()    { return txtName.getText(); }
    public String getHodName() { return txtHodName.getText(); }
    public int getStaffCount() {
        try { return Integer.parseInt(txtStaffCount.getText().trim()); }
        catch (NumberFormatException e) { return 0; }
    }
    public JButton getBtnSave() { return btnSave; }

    public void setDepartmentData(String name, String hodName, int staffCount) {
        txtName.setText(name);
        txtHodName.setText(hodName);
        txtStaffCount.setText(String.valueOf(staffCount));
    }
}