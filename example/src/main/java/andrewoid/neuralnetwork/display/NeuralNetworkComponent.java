package andrewoid.neuralnetwork.display;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


public class NeuralNetworkComponent extends JComponent {
    private static final int COLOR_RANGE = 255;//16777215;
    private int start = 25;
    private int neuronSize;
    private double weightMin = 0;
    private double weightMax = 1;
    private Network network;
    private int horizontalSpace;
    private double verticalSpaceLeft, verticalSpaceRight;
    int counter = 0;

    public NeuralNetworkComponent(Network network) {
        this.network = network;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Neuron[][] layers = network.getLayers();
        setSizing(layers);
        Color neuronColor;
        int neuronColorValue;
        int pathWeight;
        Color pathColor;

        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].length; j++) {
                for (int k = 0; k < layers[i][j].getNumWeights(); k++) {
                    if (weightMin > layers[i][j].getWeight(k)) {
                        weightMin = layers[i][j].getWeight(k);
                    }
                    if (weightMax < layers[i][j].getWeight(k)) {
                        weightMax = layers[i][j].getWeight(k);
                    }
                }
            }
        }
        for (int i = 0; i < layers.length; i++) {
            int layerLength = layers[i].length;

            double potentialVerticalSpace = (double)getHeight() / (double)layerLength;
            if (i > 0) {
                verticalSpaceLeft = verticalSpaceRight;
            }
            verticalSpaceRight = potentialVerticalSpace > 1 ? potentialVerticalSpace : 15;
            for (int j = 0; j < layerLength; j++) {
                neuronColorValue = (int) (layers[i][j].getValue() * COLOR_RANGE);
                if (neuronColorValue < 0) {
                    neuronColorValue = 0;
                }
                else if (neuronColorValue > 255) {
                    neuronColorValue = 255;
                }
                neuronColor = new Color(0, neuronColorValue, 0);
                drawNeuron(i, j, neuronColor, graphics);

                for (int k = 0; k < layers[i][j].getNumWeights(); k++) {
                    pathWeight = (int) ((layers[i][j].getWeight(k) - weightMin) / (weightMax - weightMin) * COLOR_RANGE);
                    pathColor = new Color(0, pathWeight, 0);
                    drawConnection(i, j, k, pathColor, graphics);
                }
            }
        }
    }

    private void setSizing(Neuron[][] layers) {
        int maxLength = calculateLargestLayer(layers);
        horizontalSpace = getWidth() / (layers.length - 1) - start;
        neuronSize = maxLength > 30 ? 4 : 10;
    }

    private int calculateLargestLayer(Neuron[][] layers) {
        int maxLength = 0;
        for (Neuron[] layer : layers) {
            if (layer.length > maxLength) {
                maxLength = layer.length;
            }
        }
        return maxLength;
    }

    private void drawNeuron(int row, int column, Color color, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Double(start + (row * horizontalSpace),
                start + (column * verticalSpaceRight),
                neuronSize,
                neuronSize));
    }

    private void drawConnection(int row, int column, int number, Color color, Graphics g) {
        System.out.println(counter++);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.draw(new Line2D.Double(start + ((row - 1) * horizontalSpace) + neuronSize,
                start + (number * verticalSpaceLeft) + (neuronSize / 2.0),
                start + ((row) * horizontalSpace),
                start + (column * verticalSpaceRight) + (neuronSize / 2.0)));
    }
}
