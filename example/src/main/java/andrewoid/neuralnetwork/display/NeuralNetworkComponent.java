package andrewoid.neuralnetwork.display;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

import javax.swing.*;
import java.awt.*;


public class NeuralNetworkComponent extends JComponent {
    private static final int COLOR_RANGE = 255;//16777215;
    int start = 25;
    int neuronSize;
    double weightMin = 0;
    double weightMax = 1;
    Network network;
    int horizontalSpace;
    int verticalSpace;

    public NeuralNetworkComponent(Network network) {
        this.network = network;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
            for (int j = 0; j < layers[i].length; j++) {
                neuronColorValue = (int) (layers[i][j].getValue() * COLOR_RANGE);
                if (neuronColorValue < 0) {
                    neuronColorValue = 0;
                }
                else if (neuronColorValue > 255) {
                    neuronColorValue = 255;
                }
                neuronColor = new Color(0, neuronColorValue, 0);
                drawNeuron(i, j, neuronColor, g);
                for (int k = 0; k < layers[i][j].getNumWeights(); k++) {
                    pathWeight = (int) ((layers[i][j].getWeight(k) - weightMin) / (weightMax - weightMin) * COLOR_RANGE);
                    pathColor = new Color(0, pathWeight, 0);
                    drawConnection(i, j, k, pathColor, g);
                }
            }
        }
    }

    private void setSizing(Neuron[][] layers) {
        int maxLength = calculateLargestLayer(layers);
        horizontalSpace = this.getWidth() / layers.length;
        double potentialVerticalSpace = (double)this.getHeight() / (double)maxLength;
        verticalSpace = potentialVerticalSpace > 1 ? (int)potentialVerticalSpace : 15;
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
        g.setColor(color);
        g.fillOval(start + (row * horizontalSpace),
                start + (column * verticalSpace),
                neuronSize,
                neuronSize);
    }

    private void drawConnection(int row, int column, int number, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(start + ((row - 1) * horizontalSpace) + neuronSize,
                start + (number * verticalSpace) + (int) (neuronSize / 2),
                start + ((row) * horizontalSpace),
                start + (column * verticalSpace) + (int) (neuronSize / 2));
    }
}
