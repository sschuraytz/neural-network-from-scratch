package andrewoid.neuralnetwork;

import andrewoid.neutralnetwork.Network;
import andrewoid.neutralnetwork.Neuron;

import java.util.Arrays;
import java.util.Random;

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

    private static final double expectedOutputs[][] = new double[][]{
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 3
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
    };

    public static void main(String args[]) {

        Network network = new Network(7, 15, 15, 10);
        Neuron outputs[];
        Random random = new Random();

        // train the network on random input and output sets.
        for (int i = 0; i < 40000; i++) {
            int set = random.nextInt(inputs.length);
            network.train(inputs[set], expectedOutputs[set], 0.3);
        }

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
