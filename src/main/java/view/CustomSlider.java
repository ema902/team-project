package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * Creates a custom JSlider.
 */
public class CustomSlider extends BasicSliderUI {

    /**
     * Constructor that receives the JSlider and applies the custom slider UI.
     * @param slider a JSlider
     */
    public CustomSlider(JSlider slider) {
        super(slider);
    }

    /**
     * Creates a custom rounded track.
     * @param g the graphics
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int trackHeight = 4;
        int trackY = trackRect.y + (trackRect.height - trackHeight) / 2;

        // Unfilled track
        g2d.setColor(new Color(196, 155, 130));
        g2d.fillRoundRect(trackRect.x, trackY, trackRect.width, trackHeight, 4, 4);

        // Filled track (left of thumb)
        int fillWidth = thumbRect.x + thumbRect.width / 2 - trackRect.x;
        g2d.setColor(new Color(194, 122, 86));
        g2d.fillRoundRect(trackRect.x, trackY, Math.max(0, fillWidth), trackHeight, 4, 4);

        g2d.dispose();
    }

    /**
     * Creates a custom circular slider thumb.
     * @param g the graphics
     */
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = 14;
        int x = thumbRect.x + (thumbRect.width - size) / 2;
        int y = thumbRect.y + (thumbRect.height - size) / 2;

        g2d.setColor(new Color(250, 247, 242));
        g2d.fillOval(x, y, size, size);

        g2d.setColor(new Color(194, 122, 86));
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawOval(x, y, size, size);

        g2d.dispose();
    }

    /**
     * Paints the major tick marks on the horizontal slider.
     * @param g the graphics
     * @param tickBounds the tick bounds
     * @param x the x coordinate
     */
    @Override
    protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
        g.setColor(new Color(194, 122, 86));
        g.drawLine(x, 0, x, tickBounds.height - 2);
    }

    /**
     * Removes the default dotted focus outline.
     * @param g the graphics
     */
    @Override
    public void paintFocus(Graphics g) {
        // Intentionally left empty so that the dotted focus box is removed when the slider is being manipulated.
    }

    /**
     * Resizes the circular thumb size.
     * @return Dimension of the thumb size
     */
    @Override
    protected Dimension getThumbSize() {
        // Gives the thumb enough room so it doesn't smear
        return new Dimension(18, 18);
    }
}
