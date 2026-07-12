package com.faculty.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class LecturerFormView extends JDialog {
    private JTextField txtUsername, txtFullName, txtEmail, txtMobile;
    private JPasswordField txtPassword;
    private JComboBox<String> cbDepartments;
    private JButton btnSave, btnCancel;
    private Map<String, Integer> deptMap;

    public LecturerFormView(JFrame parent, String title, Map<String, Integer> departments) {
        super(parent, title, true);
        this.deptMap = departments;

        setSize(440, 470);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(AppTheme.BG_MAIN);
        content.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[]   labels = {"Username:", "Password:", "Full Name:", "Email:", "Mobile Number:", "Department:"};
        txtUsername    = new JTextField();
        txtPassword    = new JPasswordField();
        txtFullName    = new JTextField();
        txtEmail       = new JTextField();
        txtMobile      = new JTextField();
        cbDepartments  = new JComboBox<>(departments.keySet().toArray(new String[0]));
        Component[] fields = {txtUsername, txtPassword, txtFullName, txtEmail, txtMobile, cbDepartments};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(AppTheme.bold(12));
            lbl.setForeground(AppTheme.TEXT_DARK);
            gbc.gridx = 0; gbc.gridy = i * 2; gbc.insets = new Insets(8, 0, 2, 0);
            content.add(lbl, gbc);

            gbc.gridy = i * 2 + 1; gbc.insets = new Insets(0, 0, 4, 0);
            if (fields[i] instanceof JTextField tf) AppTheme.styleField(tf);
            else if (fields[i] instanceof JComboBox<?> cb) cb.setFont(AppTheme.regular(13));
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
    public String getUsername()           { return txtUsername.getText(); }
    public String getPassword()           { return new String(txtPassword.getPassword()); }
    public String getFullName()           { return txtFullName.getText(); }
    public String getEmail()              { return txtEmail.getText(); }
    public String getMobile()             { return txtMobile.getText(); }
    public int getSelectedDepartmentId()  { return deptMap.get((String) cbDepartments.getSelectedItem()); }
    public JButton getBtnSave()           { return btnSave; }

    public void setEditMode(boolean isEdit) {
        if (isEdit) {
            txtUsername.setEnabled(false);
            txtPassword.setEnabled(false);
            txtPassword.setText("********");
        }
    }

    public void setLecturerData(String fullName, String email, String mobile, String deptName) {
        txtFullName.setText(fullName);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        if (deptName != null) cbDepartments.setSelectedItem(deptName);
    }
}