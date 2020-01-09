package andrewoid.neuralnetwork.MNISTReader;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.NetworkFactory;
import andrewoid.neuralnetwork.Neuron;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class MNISTTraining
{
    private static final String saveFilename = "mnist.json";
    private static final String trainingInputImagePath = "train-images-idx3-ubyte";
    private static final String trainingInputLabelPath = "train-labels-idx1-ubyte";

    private static final String trainingOutputPath = "TrainingMNISTOutputFiles/";

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

        Network network;
        try {
            network = new NetworkFactory().loadFromJSON(saveFilename);
        } catch (FileNotFoundException e) {
            network =new Network(784, 15, 15, 10);
        }

        Iterator<MNISTTrainingFile> iterator = reader.iterator();
        while(iterator.hasNext())
        {
            MNISTTrainingFile next = iterator.next();
            network.train(next.getImgPixelsAsDoubles(), expectedOutputs[next.getLabel()], 0.3);
        }

        network.saveToJSON(saveFilename);
    }

}
