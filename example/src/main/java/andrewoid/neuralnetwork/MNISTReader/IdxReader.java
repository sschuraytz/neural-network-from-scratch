package andrewoid.neuralnetwork.MNISTReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class IdxReader
{

    class MnistIterator implements Iterator<MNISTTrainingFile>
    {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < numberOfImages;
        }

        @Override
        public MNISTTrainingFile next() {
            if (hasNext()) {
                for (int p = 0; p < numberOfPixels; p++) {
                    int gray = 0;
                    try {
                        gray = 255 - inImage.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgPixels[p] = 0xFF000000 | (gray << 16) | (gray << 8) | gray;
                }

                int label = 0;
                try {
                    label = inLabel.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++position;
                return new MNISTTrainingFile(imgPixels, label);
            }
            else {
                return null;
            }
        }
    }

    private String inputImagePath;
    private String inputLabelPath;
    private String outputPath;
    private InputStream inImage = null;
    private InputStream inLabel = null;
    private int magicNumberImages, numberOfImages, numberOfRows,
            numberOfColumns, magicNumberLabels,
            numberOfLabels, numberOfPixels;
    private BufferedImage image;
    private int[] imgPixels;

    public IdxReader(String inputImagePath, String inputLabelPath, String outputPath)
    {
        this.inputImagePath = inputImagePath;
        this.inputLabelPath = inputLabelPath;
        this.outputPath = outputPath;
    }

    public void resetInputImageAndFilePath(String inputImagePath, String inputLabelPath, String outputPath)
    {
        this.inputImagePath = inputImagePath;
        this.inputLabelPath = inputLabelPath;
        this.outputPath = outputPath;
    }

    public Iterator<MNISTTrainingFile> iterator() {
        initializeVariables();
        return new MnistIterator();
    }

    /*
    * Resource I utilized to decompress the files:
    * https://stackoverflow.com/questions/17279049/reading-a-idx-file-type-in-java
    * */
    public void loadFromCompressedFilesToOutputDir(boolean saveUncompressedToFile)
    {


        if(!pathExist(outputPath)) { createOutputDirectories(outputPath); }
        else {
            /* Simplistic check -- If anything is in here, don't want to reload*/
//            if(new File(outputPath + "0").list().length > 0) { return; }
        }

        int[] hashMap = new int[10];

        try
        {
            Iterator<MNISTTrainingFile> iterator = iterator();
            while(iterator.hasNext())
            {
                MNISTTrainingFile next = iterator.next();
                if(saveUncompressedToFile) {
                    writeIndividualFile(hashMap, next.getLabel());
                }
            }

        }  finally {
            closeFileStreams();
        }
    }

    public void writeIndividualFile(int[] hashMap, int label)
    {

            image.setRGB(0, 0, numberOfColumns, numberOfRows, imgPixels, 0, numberOfColumns);

            hashMap[label]++;
            File outputFile = new File(outputPath + "/" + label + "/" + hashMap[label] + ".png");

            try {
                boolean wrote = ImageIO.write(image, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public boolean areFilesLoaded()
    {
        File file = new File(outputPath + "0");
        if(file.list() !=  null) {
            return file.list().length > 0;
        }
        else {
            return false;
        }
    }

    private void closeFileStreams() {
        if (inImage != null) {
            try {
                inImage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inLabel != null) {
            try {
                inLabel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeVariables() {
        try {

            inImage = new BufferedInputStream(new FileInputStream(inputImagePath));
            inLabel = new BufferedInputStream(new FileInputStream(inputLabelPath));

            magicNumberImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
            numberOfImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
            numberOfRows = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
            numberOfColumns = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());

            magicNumberLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8) | (inLabel.read());
            numberOfLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8) | (inLabel.read());

            image = new BufferedImage(numberOfColumns, numberOfRows, BufferedImage.TYPE_INT_RGB);
            numberOfPixels = numberOfRows * numberOfColumns;
            imgPixels = new int[numberOfPixels];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createOutputDirectories(String outputPath)
    {
        File outputDir = new File(outputPath);
        outputDir.mkdir();
        for(int ix = 0; ix < 10; ++ix)
        {
            new File(outputPath + "" + ix).mkdir();
        }
    }

    private boolean pathExist(String outpath)
    {
        return new File(outputPath).exists();
    }

}
