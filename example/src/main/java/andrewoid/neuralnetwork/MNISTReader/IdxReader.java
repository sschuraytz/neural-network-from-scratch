package andrewoid.neuralnetwork.MNISTReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class IdxReader
{

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

    public void resetInputImageAndFilePath(String inputImagePath, String inputLabelPath)
    {
        this.inputImagePath = inputImagePath;
        this.inputLabelPath = inputLabelPath;
    }

    /*
    * Resource I utilized to decompress the files:
    * https://stackoverflow.com/questions/17279049/reading-a-idx-file-type-in-java
    * */
    public void loadFromCompressedFilesToOutputDir() {


        if(!pathExist(outputPath)) { createOutputDirectories(outputPath); }
        else {
            /* Simplistic check -- If anything is in here, don't want to reload*/
//            if(new File(outputPath + "0").list().length > 0) { return; }
        }

        int[] hashMap = new int[10];

        try
        {
            initializeVariables();

            for(int i = 0; i < numberOfImages; i++) {

                if(i % 100 == 0) {System.out.println("Number of images extracted: " + i);}

                for(int p = 0; p < numberOfPixels; p++) {
                    int gray = 255 - inImage.read();
                    imgPixels[p] = 0xFF000000 | (gray<<16) | (gray<<8) | gray;
                }

                image.setRGB(0, 0, numberOfColumns, numberOfRows, imgPixels, 0, numberOfColumns);

                int label = inLabel.read();

                hashMap[label]++;
                File outputfile = new File(outputPath + "/" + label + "/" + hashMap[label] + ".png");

                ImageIO.write(image, "png", outputfile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFileStreams();
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
