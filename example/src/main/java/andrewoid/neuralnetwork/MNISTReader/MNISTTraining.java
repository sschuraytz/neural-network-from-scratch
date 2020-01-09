package andrewoid.neuralnetwork.MNISTReader;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class MNISTTraining
{
    private static final String trainingInputImagePath = "train-images-idx3-ubyte";
    private static final String trainingInputLabelPath = "train-labels-idx1-ubyte";
    private static final String testingInputImagePath = "t10k-images-idx3-ubyte";
    private static final String testingInputLabelPath = "t10k-labels-idx1-ubyte";

    private static final String trainingOutputPath = "TrainingMNISTOutputFiles/";
    private static final String testingOutputPath = "TestingMNISTOutputFiles/";

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

    public static void main(String args[])
    {
        IdxReader reader = new IdxReader(trainingInputImagePath,trainingInputLabelPath,trainingOutputPath);

        Network network = new Network(784, 1600, 1600, 10);
        Neuron outputs[];

        Iterator<MNISTTrainingFile> iterator = reader.iterator();
        while(iterator.hasNext())
        {
            MNISTTrainingFile next = iterator.next();
            network.train(next.getImgPixelsAsDoubles(), expectedOutputs[next.getLabel()], 0.3);
        }


        reader.resetInputImageAndFilePath(testingInputImagePath, testingInputLabelPath, testingOutputPath);
        Iterator<MNISTTrainingFile> iterator2 = reader.iterator();

        while(iterator2.hasNext())
        {
            MNISTTrainingFile next = iterator2.next();
            double[] imgInput = next.getImgPixelsAsDoubles();
            outputs = network.evaluate(imgInput);
            System.out.print(Arrays.toString(imgInput));
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

        network.saveToJSON("network.json");
    }

}
