package com.faculty.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StudentFormView extends JDialog {
    private JTextField txtFullName, txtRegId, txtEmail, txtMobile;
    private JComboBox<String> cbDegrees;
    private JButton btnSave, btnCancel;
    private Map<String, Integer> degreeMap;

    public StudentFormView(JFrame parent, String title, Map<String, Integer> degrees) {
        super(parent, title, true);
        this.degreeMap = degrees;

        setSize(440, 400);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(AppTheme.BG_MAIN);
        content.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(6, 0, 6, 0);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[]  labels = {"Full Name:", "Student ID (Reg No):", "Email:", "Mobile Number:", "Degree:"};
        txtFullName  = new JTextField();
        txtRegId     = new JTextField();
        txtEmail     = new JTextField();
        txtMobile    = new JTextField();
        cbDegrees    = new JComboBox<>(degrees.keySet().toArray(new String[0]));
        Component[] fields = {txtFullName, txtRegId, txtEmail, txtMobile, cbDegrees};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(AppTheme.bold(12));
            lbl.setForeground(AppTheme.TEXT_DARK);
            gbc.gridx = 0; gbc.gridy = i * 2; gbc.insets = new Insets(8, 0, 2, 0);
            content.add(lbl, gbc);

            gbc.gridy = i * 2 + 1; gbc.insets = new Insets(0, 0, 6, 0);
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
    public String getFullName()       { return txtFullName.getText(); }
    public String getRegId()          { return txtRegId.getText(); }
    public String getEmail()          { return txtEmail.getText(); }
    public String getMobile()         { return txtMobile.getText(); }
    public int getSelectedDegreeId()  { return degreeMap.get((String) cbDegrees.getSelectedItem()); }
    public JButton getBtnSave()       { return btnSave; }

    public void setStudentData(String name, String regId, String email, String mobile, String degreeName) {
        txtFullName.setText(name);
        txtRegId.setText(regId);
        txtRegId.setEditable(false);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        if (degreeName != null) cbDegrees.setSelectedItem(degreeName);
    }
}