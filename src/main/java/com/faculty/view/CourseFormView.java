package com.faculty.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CourseFormView extends JDialog {
    private JTextField txtCourseCode, txtCourseName, txtCredits;
    private JComboBox<String> cbLecturers;
    private JButton btnSave, btnCancel;
    private Map<String, Integer> lecturerMap;

    public CourseFormView(JFrame parent, String title, Map<String, Integer> lecturers) {
        super(parent, title, true);
        this.lecturerMap = lecturers;

        setSize(440, 360);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(AppTheme.BG_MAIN);
        content.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[]   labels = {"Course Code:", "Course Name:", "Credits:", "Lecturer:"};
        txtCourseCode = new JTextField();
        txtCourseName = new JTextField();
        txtCredits    = new JTextField();
        cbLecturers   = new JComboBox<>(lecturers.keySet().toArray(new String[0]));
        Component[] fields = {txtCourseCode, txtCourseName, txtCredits, cbLecturers};

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
    public String getCourseCode()      { return txtCourseCode.getText(); }
    public String getCourseName()      { return txtCourseName.getText(); }
    public int getCredits() {
        try { return Integer.parseInt(txtCredits.getText().trim()); }
        catch (NumberFormatException e) { return 0; }
    }
    public int getSelectedLecturerId() { return lecturerMap.get((String) cbLecturers.getSelectedItem()); }
    public JButton getBtnSave()        { return btnSave; }

    public void setCourseData(String courseCode, String courseName, int credits, String lecturerName) {
        txtCourseCode.setText(courseCode);
        txtCourseName.setText(courseName);
        txtCredits.setText(String.valueOf(credits));
        if (lecturerName != null) cbLecturers.setSelectedItem(lecturerName);
    }
}