package andrewoid.neuralnetwork.MNISTReader;

public class ReaderLoading {
    private static final String trainingInputImagePath = "train-images-idx3-ubyte";
    private static final String trainingInputLabelPath = "train-labels-idx1-ubyte";
    private static final String testingInputImagePath = "t10k-images-idx3-ubyte";
    private static final String testingInputLabelPath = "t10k-labels-idx1-ubyte";

    private static final String outputPath = "MNISTOutputFiles/";

    public static void main(String[] args)
    {
        IdxReader reader = new IdxReader(trainingInputImagePath,trainingInputLabelPath,outputPath);
        reader.loadFromCompressedFilesToOutputDir();
        reader.resetInputImageAndFilePath(testingInputImagePath, testingInputLabelPath);
        reader.loadFromCompressedFilesToOutputDir();
    }
}
