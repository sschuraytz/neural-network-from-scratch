package andrewoid.neuralnetwork.MNISTReader;

import java.io.File;

public class MNISTTrainingFile
{
    private int[] imgPixels;
    private int label;


    public MNISTTrainingFile(int[] imgPixels, int label)
    {
      this.imgPixels = imgPixels;
      this.label = label;
    }

    public int[] getImgPixels()
    {
        return imgPixels;
    }

    public int getLabel()
    {
        return label;
    }
}
