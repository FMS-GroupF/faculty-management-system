package com.faculty.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import com.faculty.dao.UserDAO;
import com.faculty.controller.LoginController;

public class StudentDashboardView extends JFrame {
    private JLabel lblName, lblRegId, lblDegree, lblEmail, lblMobile;
    private JTable timetableTable, coursesTable;
    private DefaultTableModel timetableModel, coursesModel;
    private JTabbedPane tabbedPane;

    public StudentDashboardView(String username) {
        setTitle("Faculty Management System - Student Dashboard");
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

        JLabel lblWelcome = new JLabel("Welcome, " + username);
        lblWelcome.setFont(AppTheme.bold(20));
        lblWelcome.setForeground(Color.WHITE);
        topPanel.add(lblWelcome, BorderLayout.WEST);

        JButton btnLogout = AppTheme.dangerButton("Logout");
        btnLogout.setPreferredSize(new Dimension(110, 36));
        JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 16));
        topRight.setOpaque(false);
        topRight.add(btnLogout);
        topPanel.add(topRight, BorderLayout.EAST);

        btnLogout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView, new UserDAO());
            loginView.setVisible(true);
        });
        add(topPanel, BorderLayout.NORTH);

        // ── Tabbed Pane ───────────────────────────────────────────────
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(AppTheme.bold(13));
        tabbedPane.setBackground(AppTheme.BG_MAIN);

        tabbedPane.addTab("Profile",          createProfilePanel());
        tabbedPane.addTab("Timetable",         createTimetablePanel());
        tabbedPane.addTab("Courses & Grades",  createCoursesPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(12, 16, 12, 16);
        gbc.anchor  = GridBagConstraints.WEST;

        String[] labels = {"Full Name:", "Student ID:", "Degree:", "Email:", "Mobile:"};
        JLabel[] values = new JLabel[labels.length];
        lblName   = new JLabel(); values[0] = lblName;
        lblRegId  = new JLabel(); values[1] = lblRegId;
        lblDegree = new JLabel(); values[2] = lblDegree;
        lblEmail  = new JLabel(); values[3] = lblEmail;
        lblMobile = new JLabel(); values[4] = lblMobile;

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
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
        return buildTablePanel(
            new String[]{"Day", "Time Slot", "Course"},
            timetableModel = new DefaultTableModel(new String[]{"Day", "Time Slot", "Course"}, 0)
        );
    }

    private JPanel createCoursesPanel() {
        return buildTablePanel(
            new String[]{"Course Code", "Course Name", "Credits", "Grade"},
            coursesModel = new DefaultTableModel(new String[]{"Course Code", "Course Name", "Credits", "Grade"}, 0)
        );
    }

    private JPanel buildTablePanel(String[] columns, DefaultTableModel model) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JTable table = new JTable(model);
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

        // Store the correct table reference
        if (model == timetableModel) timetableTable = table;
        else                          coursesTable   = table;

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(AppTheme.BORDER_COLOR, 1, true));
        panel.add(sp, BorderLayout.CENTER);
        return panel;
    }

    // ── Getters ──────────────────────────────────────────────────────
    public JLabel getLblName()   { return lblName; }
    public JLabel getLblRegId()  { return lblRegId; }
    public JLabel getLblDegree() { return lblDegree; }
    public JLabel getLblEmail()  { return lblEmail; }
    public JLabel getLblMobile() { return lblMobile; }
    public DefaultTableModel getTimetableModel() { return timetableModel; }
    public DefaultTableModel getCoursesModel()   { return coursesModel; }
}