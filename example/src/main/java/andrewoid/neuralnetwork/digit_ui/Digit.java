package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Digit extends JPanel {

    private final int DIGIT_LINES = 7;
    private double[] linesStatus;
    private final int lineLength = DigitFrame.WIDTH / 4;
    private final int lineThickness = 10;
    private final int sideMargin = (DigitFrame.WIDTH - lineLength) / 2;
    private final int topMargin = (DigitFrame.HEIGHT - (lineLength * 2)) / 2;
    private Network network;
    private ResultScreen resultScreen;

    public Digit(Network network, ResultScreen resultScreen) {
        linesStatus = new double[DIGIT_LINES];
        setSize(DigitFrame.WIDTH, DigitFrame.HEIGHT);
        setLayout(null);
        createDigit();
        this.network = network;
        this.resultScreen = resultScreen;
    }

    private void createDigit() {
        int xPosition, yPosition, width, height;
        for (int line = 0; line < DIGIT_LINES; line++) {
            switch (line) {
                case 0:
                    xPosition = sideMargin;
                    yPosition = topMargin - (2 * lineThickness);
                    width = lineLength;
                    height = lineThickness;
                    break;
                case 1:
                    xPosition = sideMargin - lineThickness;
                    yPosition = topMargin - lineThickness;
                    width = lineThickness;
                    height = lineLength;
                    break;
                case 2:
                    xPosition = sideMargin + lineLength;
                    yPosition = topMargin - lineThickness;
                    width = lineThickness;
                    height = lineLength;
                    break;
                case 3:
                    xPosition = sideMargin;
                    yPosition = topMargin + lineLength - lineThickness;
                    width = lineLength;
                    height = lineThickness;
                    break;
                case 4:
                    xPosition = sideMargin - lineThickness;
                    yPosition = topMargin + lineLength;
                    width = lineThickness;
                    height = lineLength;
                    break;
                case 5:
                    xPosition = sideMargin + lineLength;
                    yPosition = topMargin + lineLength;
                    width = lineThickness;
                    height = lineLength;
                    break;
                default:
                    xPosition = sideMargin;
                    yPosition = topMargin + (2 * lineLength);
                    width = lineLength;
                    height = lineThickness;
                    break;
            }
            DigitLine digitLine = new DigitLine(line, width, height);
            digitLine.setLocation(xPosition, yPosition);
            digitLine.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    digitLine.changeStatus();
                    linesStatus[digitLine.getId()] = digitLine.getStatus();
                    evaluate(linesStatus);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            add(digitLine);
        }
    }

    public void evaluate(double[] input) {
        // test the network on the input sets, printing out everything that evaluates to greater than 10%
        StringBuilder result = new StringBuilder();
        Neuron[] outputs = network.evaluate(input);
        for (int i = 0; i < outputs.length; i++) {
            double value = outputs[i].getValue();
            if (value > 0.90) {
                result.append(i);
                result.append(" ");
            }
        }
        resultScreen.setText(result.toString());
    }

/*    *//**
     *
     * @return deep copy of the array that represents which parts of the digit are selected. The selected parts of the
     * digit should be used by the neural network to determine which number is represented
     *//*
    public double[] getLinesStatus() {
        return Arrays.copyOf(linesStatus, linesStatus.length);
    }*/
}
