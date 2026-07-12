package com.faculty.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class LecturerDashboardView extends JFrame {

    private JTabbedPane tabbedPane;

    // Profile fields
    private JLabel lblName, lblId, lblEmail, lblMobile, lblDepartment;

    // Tables
    private JTable timetableTable;
    private DefaultTableModel timetableModel;
    private JTable coursesTable;
    private DefaultTableModel coursesModel;

    private JButton btnLogout;

    public LecturerDashboardView(String username) {
        setTitle("Faculty Management System - Lecturer Dashboard");
        setSize(960, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AppTheme.BG_MAIN);

        // ── Top Header ───────────────────────────────────────────────
        JPanel topPanel = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, AppTheme.PRIMARY,
                        getWidth(), 0, AppTheme.PRIMARY_LIGHT);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(960, 68));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 24));

        JLabel lblWelcome = new JLabel("Welcome, " + username + " (Lecturer)");
        lblWelcome.setFont(AppTheme.bold(20));
        lblWelcome.setForeground(Color.WHITE);
        topPanel.add(lblWelcome, BorderLayout.WEST);

        btnLogout = AppTheme.dangerButton("Logout");
        btnLogout.setPreferredSize(new Dimension(110, 36));
        JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 16));
        topRight.setOpaque(false);
        topRight.add(btnLogout);
        topPanel.add(topRight, BorderLayout.EAST);

        // ── Tabbed Pane ───────────────────────────────────────────────
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(AppTheme.bold(13));
        tabbedPane.setBackground(AppTheme.BG_MAIN);

        tabbedPane.addTab("My Profile",   createProfilePanel());
        tabbedPane.addTab("My Timetable",  createTimetablePanel());
        tabbedPane.addTab("My Courses",    createCoursesPanel());

        add(topPanel,   BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(12, 16, 12, 16);
        gbc.anchor  = GridBagConstraints.WEST;

        String[] fieldLabels = {"Full Name:", "Lecturer ID:", "Email:", "Mobile:", "Department:"};
        lblName       = new JLabel("-");
        lblId         = new JLabel("-");
        lblEmail      = new JLabel("-");
        lblMobile     = new JLabel("-");
        lblDepartment = new JLabel("-");
        JLabel[] values = {lblName, lblId, lblEmail, lblMobile, lblDepartment};

        for (int i = 0; i < fieldLabels.length; i++) {
            JLabel lbl = new JLabel(fieldLabels[i]);
            lbl.setFont(AppTheme.bold(13));
            lbl.setForeground(AppTheme.TEXT_DARK);
            gbc.gridx = 0; gbc.gridy = i;
            panel.add(lbl, gbc);

            values[i].setFont(AppTheme.regular(13));
            values[i].setForeground(AppTheme.TEXT_MUTED);
            gbc.gridx = 1;
            panel.add(values[i], gbc);
        }
        return panel;
    }

    private JPanel createTimetablePanel() {
        timetableModel = new DefaultTableModel(new String[]{"Day", "Time Slot", "Course"}, 0);
        timetableTable = new JTable(timetableModel);
        return buildTablePanel(timetableTable);
    }

    private JPanel createCoursesPanel() {
        coursesModel = new DefaultTableModel(new String[]{"Course Code", "Course Name", "Credits"}, 0);
        coursesTable = new JTable(coursesModel);
        return buildTablePanel(coursesTable);
    }

    private JPanel buildTablePanel(JTable table) {
        table.setRowHeight(34);
        table.setFont(AppTheme.regular(13));
        table.setGridColor(new Color(229, 222, 255));
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(221, 214, 254));
        table.setSelectionForeground(AppTheme.TEXT_DARK);
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(AppTheme.bold(13));
        header.setBackground(new Color(237, 233, 254));
        header.setForeground(AppTheme.PRIMARY);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(AppTheme.BORDER_COLOR, 1, true));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panel.add(sp, BorderLayout.CENTER);
        return panel;
    }

    // ---------- Getters ----------
    public JButton getBtnLogout()              { return btnLogout; }
    public JLabel getLblName()                 { return lblName; }
    public JLabel getLblId()                   { return lblId; }
    public JLabel getLblEmail()                { return lblEmail; }
    public JLabel getLblMobile()               { return lblMobile; }
    public JLabel getLblDepartment()           { return lblDepartment; }
    public DefaultTableModel getTimetableModel() { return timetableModel; }
    public DefaultTableModel getCoursesModel()   { return coursesModel; }
}