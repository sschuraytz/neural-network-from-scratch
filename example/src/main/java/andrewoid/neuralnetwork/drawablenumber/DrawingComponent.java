package andrewoid.neuralnetwork.drawablenumber;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class DrawingComponent extends JComponent {
    private BufferedImage buffImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    private Point startPoint;
    private Point endPoint;
    private Shape line;

    public DrawingComponent() {
        MouseInputAdapter mouseInputAdapter = new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                line = new Line2D.Double(startPoint, endPoint);
                startPoint = endPoint;
                drawLines();
            }
        };

        addMouseListener(mouseInputAdapter);
        addMouseMotionListener(mouseInputAdapter);
    }

    public void drawLines() {
        Graphics2D g = (Graphics2D) buffImage.getGraphics();
        g.setColor(Color.BLACK);
        g.draw(line);
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (buffImage != null) {
            graphics.drawImage(buffImage, 0, 0, null);
        }
    }
}
