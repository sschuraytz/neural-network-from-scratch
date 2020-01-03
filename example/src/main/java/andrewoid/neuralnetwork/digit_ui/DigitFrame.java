package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neutralnetwork.Network;
import javax.swing.*;
import java.awt.*;

public class DigitFrame extends JFrame {

    final static int WIDTH = 825;
    final static int HEIGHT = 800;

    public DigitFrame(Network network) {
        setTitle("Neural Network");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ResultScreen resultScreen = new ResultScreen();
        Digit digit = new Digit(network, resultScreen);

        JPanel root = new JPanel(new BorderLayout());
        root.add(digit, BorderLayout.CENTER);
        root.add(resultScreen, BorderLayout.EAST);

        setContentPane(root);
    }
}
