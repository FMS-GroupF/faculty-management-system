package com.faculty.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rbAdmin, rbStudent, rbLecturer;
    private JButton btnLogin;

    public LoginView() {
        AppTheme.applyGlobalDefaults();

        setTitle("Faculty Management System - Login");
        setSize(820, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AppTheme.BG_MAIN);

        // ── Left Panel ───────────────────────────────────────────────
        JPanel leftPanel = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, AppTheme.PRIMARY,
                        getWidth(), getHeight(), new Color(139, 92, 246));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(340, 520));
        leftPanel.setOpaque(false);

        JLabel lblTitle1 = new JLabel("Faculty");
        JLabel lblTitle2 = new JLabel("Management");
        JLabel lblTitle3 = new JLabel("System");
        JLabel lblSub    = new JLabel("Manage. Organize. Succeed.");

        for (JLabel lbl : new JLabel[]{lblTitle1, lblTitle2, lblTitle3}) {
            lbl.setForeground(Color.WHITE);
            lbl.setFont(AppTheme.bold(28));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        lblSub.setForeground(new Color(221, 214, 254));
        lblSub.setFont(AppTheme.regular(13));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(lblTitle1);
        leftPanel.add(lblTitle2);
        leftPanel.add(lblTitle3);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(lblSub);
        leftPanel.add(Box.createVerticalGlue());

        // ── Right Panel ──────────────────────────────────────────────
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(AppTheme.BG_MAIN);

        // Card container with rounded look
        JPanel card = new JPanel(null);
        card.setBackground(AppTheme.BG_PANEL);
        card.setBorder(BorderFactory.createEmptyBorder());
        card.setBounds(40, 40, 360, 400);
        // Give it a subtle shadow by putting it inside a container with padding
        rightPanel.add(card);

        JLabel lblSignIn = new JLabel("Sign In");
        lblSignIn.setFont(AppTheme.bold(24));
        lblSignIn.setForeground(AppTheme.PRIMARY);
        lblSignIn.setBounds(130, 20, 160, 35);
        card.add(lblSignIn);

        JLabel lblSubtitle = new JLabel("Welcome Back !!!");
        lblSubtitle.setFont(AppTheme.regular(13));
        lblSubtitle.setForeground(AppTheme.TEXT_MUTED);
        lblSubtitle.setBounds(120, 55, 160, 20);
        card.add(lblSubtitle);

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(AppTheme.bold(12));
        lblUsername.setForeground(AppTheme.TEXT_DARK);
        lblUsername.setBounds(30, 95, 150, 18);
        card.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(30, 116, 300, 38);
        AppTheme.styleField(txtUsername);
        card.add(txtUsername);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(AppTheme.bold(12));
        lblPassword.setForeground(AppTheme.TEXT_DARK);
        lblPassword.setBounds(30, 168, 150, 18);
        card.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 189, 300, 38);
        AppTheme.styleField(txtPassword);
        card.add(txtPassword);

        // Role
        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(AppTheme.bold(12));
        lblRole.setForeground(AppTheme.TEXT_DARK);
        lblRole.setBounds(30, 242, 80, 18);
        card.add(lblRole);

        rbAdmin    = styledRadio("Admin");
        rbStudent  = styledRadio("Student");
        rbLecturer = styledRadio("Lecturer");

        rbAdmin   .setBounds(30,  264, 85, 28);
        rbStudent .setBounds(120, 264, 85, 28);
        rbLecturer.setBounds(210, 264, 95, 28);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(rbAdmin);
        roleGroup.add(rbStudent);
        roleGroup.add(rbLecturer);
        rbAdmin.setSelected(true);

        card.add(rbAdmin);
        card.add(rbStudent);
        card.add(rbLecturer);

        btnLogin = AppTheme.primaryButton("Sign In");
        btnLogin.setBounds(30, 318, 300, 44);
        card.add(btnLogin);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JRadioButton styledRadio(String text) {
        JRadioButton rb = new JRadioButton(text);
        rb.setFont(AppTheme.regular(13));
        rb.setBackground(AppTheme.BG_PANEL);
        rb.setForeground(AppTheme.TEXT_DARK);
        rb.setFocusPainted(false);
        return rb;
    }

    // ── Getters ──────────────────────────────────────────────────────
    public String getUsername()  { return txtUsername.getText(); }
    public String getPassword()  { return new String(txtPassword.getPassword()); }
    public String getSelectedRole() {
        if (rbAdmin.isSelected())    return "Admin";
        if (rbStudent.isSelected())  return "Student";
        if (rbLecturer.isSelected()) return "Lecturer";
        return "";
    }

    public void addLoginListener(ActionListener listener) { btnLogin.addActionListener(listener); }
    public void showMessage(String message) { JOptionPane.showMessageDialog(this, message); }
}