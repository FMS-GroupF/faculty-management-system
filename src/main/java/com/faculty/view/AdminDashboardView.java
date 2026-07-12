package com.faculty.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class AdminDashboardView extends JFrame {

    // --- Sidebar Buttons ---
    private JButton btnStudents;
    private JButton btnLecturers;
    private JButton btnCourses;
    private JButton btnDepartments;
    private JButton btnDegrees;
    private JButton btnLogout;

    // --- CRUD Action Buttons ---
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    // --- Table ---
    private JTable dataTable;
    private DefaultTableModel tableModel;

    // --- Sidebar reference ---
    private JPanel sidebar;

    public AdminDashboardView(String username) {
        setTitle("Faculty Management System - Admin Dashboard");
        setSize(1060, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AppTheme.BG_MAIN);

        // ── LEFT SIDEBAR ──────────────────────────────────────────────
        sidebar = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, AppTheme.PRIMARY,
                        0, getHeight(), new Color(79, 21, 172));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        sidebar.setOpaque(false);
        sidebar.setPreferredSize(new Dimension(230, 680));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel lblAppName = new JLabel("FMS");
        lblAppName.setFont(AppTheme.bold(30));
        lblAppName.setForeground(Color.WHITE);
        lblAppName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblWelcome = new JLabel("Welcome, " + username);
        lblWelcome.setFont(AppTheme.bold(13));
        lblWelcome.setForeground(new Color(221, 214, 254));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(36));
        sidebar.add(lblAppName);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(lblWelcome);
        sidebar.add(Box.createVerticalStrut(36));

        // Separator line
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(139, 92, 246));
        sep.setMaximumSize(new Dimension(185, 1));
        sidebar.add(sep);
        sidebar.add(Box.createVerticalStrut(18));

        // Sidebar menu buttons
        btnStudents    = AppTheme.sidebarButton("Students");
        btnLecturers   = AppTheme.sidebarButton("Lecturers");
        btnCourses     = AppTheme.sidebarButton("Courses");
        btnDepartments = AppTheme.sidebarButton("Departments");
        btnDegrees     = AppTheme.sidebarButton("Degrees");

        for (JButton btn : new JButton[]{btnStudents, btnLecturers, btnCourses, btnDepartments, btnDegrees}) {
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(8));
        }

        sidebar.add(Box.createVerticalGlue());

        btnLogout = AppTheme.dangerButton("Logout");
        btnLogout.setMaximumSize(new Dimension(185, 42));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(24));

        // ── RIGHT MAIN PANEL ──────────────────────────────────────────
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(AppTheme.BG_MAIN);

        // Top toolbar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 16));
        topPanel.setBackground(AppTheme.BG_PANEL);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppTheme.BORDER_COLOR));

        btnAdd    = AppTheme.primaryButton("+ Add New");
        btnEdit   = AppTheme.secondaryButton("Edit");
        btnDelete = AppTheme.dangerButton("Delete");

        for (JButton btn : new JButton[]{btnAdd, btnEdit, btnDelete}) {
            btn.setPreferredSize(new Dimension(130, 38));
            topPanel.add(btn);
        }

        // Table
        tableModel = new DefaultTableModel();
        dataTable  = new JTable(tableModel);
        dataTable.setRowHeight(36);
        dataTable.setFont(AppTheme.regular(13));
        dataTable.setGridColor(new Color(229, 222, 255));
        dataTable.setShowGrid(true);
        dataTable.setBackground(Color.WHITE);
        dataTable.setSelectionBackground(new Color(221, 214, 254));
        dataTable.setSelectionForeground(AppTheme.TEXT_DARK);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.setFocusable(false);

        JTableHeader header = dataTable.getTableHeader();
        header.setFont(AppTheme.bold(13));
        header.setBackground(new Color(237, 233, 254));
        header.setForeground(AppTheme.PRIMARY);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(AppTheme.BG_MAIN);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(AppTheme.BG_MAIN);
        tableWrapper.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel,      BorderLayout.NORTH);
        mainPanel.add(tableWrapper,  BorderLayout.CENTER);

        add(sidebar,    BorderLayout.WEST);
        add(mainPanel,  BorderLayout.CENTER);

        highlightButton(btnStudents);
    }

    // Helper to highlight the active sidebar button
    public void highlightButton(JButton activeBtn) {
        JButton[] buttons = {btnStudents, btnLecturers, btnCourses, btnDepartments, btnDegrees};
        for (JButton btn : buttons) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(AppTheme.PRIMARY);
        }
        activeBtn.setBackground(AppTheme.SIDEBAR_ACTIVE_BG);
        activeBtn.setForeground(new Color(255, 100, 100)); // vivid red — clearly visible on violet
    }

    // ---------- Getters ----------
    public JButton getBtnStudents()    { return btnStudents; }
    public JButton getBtnLecturers()   { return btnLecturers; }
    public JButton getBtnCourses()     { return btnCourses; }
    public JButton getBtnDepartments() { return btnDepartments; }
    public JButton getBtnDegrees()     { return btnDegrees; }
    public JButton getBtnLogout()      { return btnLogout; }

    public JButton getBtnAdd()    { return btnAdd; }
    public JButton getBtnEdit()   { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }

    public JTable getDataTable()             { return dataTable; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void setTableColumns(String[] columns) { tableModel.setColumnIdentifiers(columns); }
    public void clearTable()                       { tableModel.setRowCount(0); }
}