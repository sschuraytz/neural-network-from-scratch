package andrewoid.neuralnetwork.display;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

import javax.swing.*;
import java.awt.*;


public class NeuralNetworkComponent extends JComponent {
    private static final int COLOR_RANGE = 255;//16777215;
    int start = 25;
    int size = 10;
    int space = 200;
    int space2 = 50;
    double weightMin = 0;
    double weightMax = 1;
    Network network;

    public NeuralNetworkComponent(Network network) {
        this.network = network;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color neuronColor;
        int neuronColorValue;
        int pathWeight;
        Color pathColor;
        Neuron[][] layers = network.getLayers();
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

    private void drawNeuron(int row, int column, Color color, Graphics g) {
        g.setColor(color);
        g.fillOval(start + (row * space),
                start + (column * space2),
                size,
                size);
    }

    private void drawConnection(int row, int column, int number, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(start + ((row - 1) * space) + size,
                start + (number * space2) + (int) (size / 2),
                start + ((row) * space),
                start + (column * space2) + (int) (size / 2));
    }
}
