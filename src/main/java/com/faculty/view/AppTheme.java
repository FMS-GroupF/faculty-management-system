package com.faculty.view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Centralized UI theme for the Faculty Management System.
 * Provides shared colors, fonts, and factory methods for styled components.
 */
public class AppTheme {

    // --- Color Palette ---
    public static final Color PRIMARY        = new Color(109, 40, 217);   // Deep violet
    public static final Color PRIMARY_LIGHT  = new Color(139, 92, 246);   // Lighter violet
    public static final Color ACCENT         = new Color(236, 72, 153);   // Pink accent
    public static final Color DANGER         = new Color(220, 38,  38);   // Red for logout/delete
    public static final Color BG_MAIN        = new Color(245, 243, 255);  // Soft lavender white
    public static final Color BG_PANEL       = Color.WHITE;
    public static final Color TEXT_DARK      = new Color(30,  27,  75);   // Deep indigo text
    public static final Color TEXT_MUTED     = new Color(109, 99, 155);
    public static final Color BORDER_COLOR   = new Color(196, 181, 253);  // Light violet border
    public static final Color SIDEBAR_BG     = new Color(109, 40, 217);
    public static final Color SIDEBAR_ACTIVE_BG = new Color(139, 92, 246);

    // --- Font Family ---
    private static final String[] FONT_PRIORITY = {"Segoe UI", "Inter", "SansSerif"};

    public static Font getFont(int style, int size) {
        for (String name : FONT_PRIORITY) {
            Font f = new Font(name, style, size);
            if (!f.getFamily().equals("Dialog")) return f;
        }
        return new Font("SansSerif", style, size);
    }

    public static Font regular(int size)  { return getFont(Font.PLAIN,  size); }
    public static Font bold(int size)     { return getFont(Font.BOLD,   size); }

    // --- Corner Radius ---
    public static final int RADIUS_BTN    = 10;
    public static final int RADIUS_FIELD  = 8;
    public static final int RADIUS_PANEL  = 14;

    // ---------------------------------------------------------------
    //  Factory: Rounded Primary Button
    // ---------------------------------------------------------------
    public static JButton primaryButton(String text) {
        JButton btn = new RoundedButton(text, PRIMARY, Color.WHITE, RADIUS_BTN);
        btn.setFont(bold(13));
        return btn;
    }

    // ---------------------------------------------------------------
    //  Factory: Rounded Secondary Button (gray / neutral)
    // ---------------------------------------------------------------
    public static JButton secondaryButton(String text) {
        JButton btn = new RoundedButton(text, new Color(229, 222, 255), TEXT_DARK, RADIUS_BTN);
        btn.setFont(bold(13));
        return btn;
    }

    // ---------------------------------------------------------------
    //  Factory: Rounded Danger Button (logout, delete)
    // ---------------------------------------------------------------
    public static JButton dangerButton(String text) {
        JButton btn = new RoundedButton(text, DANGER, Color.WHITE, RADIUS_BTN);
        btn.setFont(bold(13));
        return btn;
    }

    // ---------------------------------------------------------------
    //  Factory: Sidebar Menu Button
    // ---------------------------------------------------------------
    public static JButton sidebarButton(String text) {
        JButton btn = new RoundedButton(text, Color.WHITE, PRIMARY, RADIUS_BTN);
        btn.setFont(bold(13));
        btn.setMaximumSize(new Dimension(185, 42));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    // ---------------------------------------------------------------
    //  Style: apply rounded border + background to text field
    // ---------------------------------------------------------------
    public static void styleField(JComponent field) {
        field.setFont(regular(13));
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT_DARK);
        field.setBorder(new RoundedBorder(RADIUS_FIELD, BORDER_COLOR));
        if (field instanceof JTextField tf) tf.setColumns(20);
    }

    // ---------------------------------------------------------------
    //  Style: apply global LAF overrides (call once at startup)
    // ---------------------------------------------------------------
    public static void applyGlobalDefaults() {
        UIManager.put("Label.font",          regular(13));
        UIManager.put("Button.font",         bold(13));
        UIManager.put("TextField.font",      regular(13));
        UIManager.put("PasswordField.font",  regular(13));
        UIManager.put("ComboBox.font",       regular(13));
        UIManager.put("Table.font",          regular(13));
        UIManager.put("TableHeader.font",    bold(13));
        UIManager.put("TabbedPane.font",     bold(13));
        UIManager.put("RadioButton.font",    regular(13));
        UIManager.put("OptionPane.messageFont", regular(13));
    }

    // ================================================================
    //  Inner class: Rounded Button
    // ================================================================
    public static class RoundedButton extends JButton {
        private Color bg;
        private final Color fg;
        private final int radius;
        private Color hoverBg;

        public RoundedButton(String text, Color bg, Color fg, int radius) {
            super(text);
            this.bg = bg;
            this.fg = fg;
            this.radius = radius;
            // Slightly lighter / darker hover colour
            this.hoverBg = bg.brighter();
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setForeground(fg);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) { repaint(); }
                @Override public void mouseExited (java.awt.event.MouseEvent e) { repaint(); }
            });
        }

        public void setHoverBackground(Color c) { this.hoverBg = c; }

        @Override
        public void setBackground(Color c) {
            this.bg     = c;
            this.hoverBg = c.brighter();
            super.setBackground(c);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean hovered = getModel().isRollover();
            g2.setColor(hovered ? hoverBg : bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) { /* no default border */ }
    }

    // ================================================================
    //  Inner class: Rounded Border for text fields / panels
    // ================================================================
    public static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color  = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.draw(new RoundRectangle2D.Float(x + 1, y + 1, w - 2, h - 2, radius, radius));
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c)           { return new Insets(6, 10, 6, 10); }
        @Override
        public Insets getBorderInsets(Component c, Insets i) {
            i.set(6, 10, 6, 10); return i;
        }
    }
}
