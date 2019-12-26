package andrewoid.neuralnetwork.digit_ui;

import javax.swing.*;
import java.awt.*;

public class DigitLine extends JLabel {

    private double status;
    private final double OFF = 0.0;
    private final double ON = 1.0;
    private final Color OFF_COLOR = Color.BLACK;
    private final Color ON_COLOR = Color.GREEN;
    private int id;

    public DigitLine(int id, int width, int height) {
        this.id = id;
        status = OFF;
        setBackground(OFF_COLOR);
        setSize(width,height);
        setOpaque(true);
    }

    public void changeStatus() {
        if (status == OFF) {
            status = ON;
            setBackground(ON_COLOR);
        } else {
            status = OFF;
            setBackground(OFF_COLOR);
        }
    }

    public int getId() {
        return id;
    }

    public double getStatus() {
        return status;
    }
}
