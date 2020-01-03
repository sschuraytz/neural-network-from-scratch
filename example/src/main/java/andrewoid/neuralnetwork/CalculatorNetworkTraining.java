package andrewoid.neuralnetwork;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Demonstration to train a Network to recognize calculator digits. Calculator digits have 7 possible inputs and 10 possible outputs</br>
 * _</br>
 * |_|</br>
 * |_|</br>
 * <p>
 * The indexes of these inputs are read from top to bottom, left to right.</br>
 * <p>
 * The number 1 has the following inputs</br>
 * 0, 0, 1, 0, 0, 1, 0</br>
 * <p>
 * The number 6 has the following inputs</br>
 * 1, 1, 0, 1, 1, 1, 1</br>
 * <p>
 * For the purpose of this demonstration we are
 */
public class CalculatorNetworkTraining {

    private static final double inputs[][] = new double[][]{
            {1, 1, 1, 0, 1, 1, 1}, // 0
            {0, 0, 1, 0, 0, 1, 0}, // 1
            {1, 0, 1, 1, 1, 0, 1}, // 2
            {1, 0, 1, 1, 0, 1, 1}, // 3
            {0, 1, 1, 1, 0, 1, 0}, // 4
            {1, 1, 0, 1, 0, 1, 1}, // 5
            {1, 1, 0, 1, 1, 1, 1}, // 6
            {1, 0, 1, 0, 0, 1, 0}, // 7
            {1, 1, 1, 1, 1, 1, 1}, // 8
            {1, 1, 1, 1, 0, 1, 0}, // 9
    };

    public static void main(String args[]) throws FileNotFoundException {
        Network network = new NetworkFactory().loadFromJSON("network.json");
        Neuron outputs[];

        // test the network on the input sets, printing out everything that evaluates to greater than 10%
        for (double input[] : inputs) {
            outputs = network.evaluate(input);
            System.out.print(Arrays.toString(input));
            System.out.print(" = ");
            for (int i = 0; i < outputs.length; i++) {
                double value = outputs[i].getValue();
                if (value > 0.10) {
                    System.out.print(i);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
