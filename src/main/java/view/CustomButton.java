package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * Creates a custom JButton.
 */
public class CustomButton extends JButton {

    /**
     * Constructor that receives the JButton and applies the custom button UI.
     * @param text String that the button displays
     */
    public CustomButton(String text) {
        super(text);
        setUI(new BasicButtonUI()); // Allows the button to be painted a custom color
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        setForeground(new Color(59, 38, 24));
        setBackground(new Color(249, 239, 230));
    }

    /**
     * A custom background for the JButton.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background fill
        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);

        g2.dispose();
        super.paintComponent(g);
    }

    /**
     * A custom border painting method that rounds out the button corners.
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rounded border
        g2.setColor(new Color(194, 122, 86));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 16, 16);

        g2.dispose();
    }
}
