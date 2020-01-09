package andrewoid.neuralnetwork.MNISTReader;

import java.util.ArrayList;

public class ReaderLoading {
    private static final String trainingInputImagePath = "train-images-idx3-ubyte";
    private static final String trainingInputLabelPath = "train-labels-idx1-ubyte";
    private static final String testingInputImagePath = "t10k-images-idx3-ubyte";
    private static final String testingInputLabelPath = "t10k-labels-idx1-ubyte";

    private static final String trainingOutputPath = "TrainingMNISTOutputFiles/";
    private static final String testingOutputPath = "TestingMNISTOutputFiles/";

    public static void main(String[] args)
    {
        boolean saveUncompressedToFile = true;
        IdxReader reader = new IdxReader(trainingInputImagePath,trainingInputLabelPath,trainingOutputPath);
        reader.loadFromCompressedFilesToOutputDir(saveUncompressedToFile);
        System.out.println("Adding the testing files now");
        reader.resetInputImageAndFilePath(testingInputImagePath, testingInputLabelPath, testingOutputPath);
        reader.loadFromCompressedFilesToOutputDir(saveUncompressedToFile);
//        ArrayList<MNISTTrainingFile> mnistTrainingFiles = reader.getMnistTrainingFiles();
    }
}
