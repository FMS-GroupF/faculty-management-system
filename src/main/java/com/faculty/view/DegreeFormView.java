package com.faculty.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DegreeFormView extends JDialog {
    private JTextField txtName;
    private JComboBox<String> cbDepartments;
    private JButton btnSave, btnCancel;
    private Map<String, Integer> departmentMap;

    public DegreeFormView(JFrame parent, String title, Map<String, Integer> departments) {
        super(parent, title, true);
        this.departmentMap = departments;

        setSize(420, 260);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(AppTheme.BG_MAIN);
        content.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Degree Name
        JLabel lblName = new JLabel("Degree Name:");
        lblName.setFont(AppTheme.bold(12));
        lblName.setForeground(AppTheme.TEXT_DARK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(8, 0, 2, 0);
        content.add(lblName, gbc);

        txtName = new JTextField();
        AppTheme.styleField(txtName);
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 4, 0);
        content.add(txtName, gbc);

        // Department
        JLabel lblDept = new JLabel("Department:");
        lblDept.setFont(AppTheme.bold(12));
        lblDept.setForeground(AppTheme.TEXT_DARK);
        gbc.gridy = 2; gbc.insets = new Insets(8, 0, 2, 0);
        content.add(lblDept, gbc);

        cbDepartments = new JComboBox<>(departments.keySet().toArray(new String[0]));
        cbDepartments.setFont(AppTheme.regular(13));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 4, 0);
        content.add(cbDepartments, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setOpaque(false);
        btnSave   = AppTheme.primaryButton("Save");
        btnCancel = AppTheme.secondaryButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        gbc.gridy = 4; gbc.insets = new Insets(14, 0, 0, 0);
        content.add(btnPanel, gbc);
    }

    // Getters
    public String getName()                  { return txtName.getText(); }
    public int getSelectedDepartmentId()     { return departmentMap.get((String) cbDepartments.getSelectedItem()); }
    public JButton getBtnSave()              { return btnSave; }

    public void setDegreeData(String name, String departmentName) {
        txtName.setText(name);
        if (departmentName != null) cbDepartments.setSelectedItem(departmentName);
    }
}