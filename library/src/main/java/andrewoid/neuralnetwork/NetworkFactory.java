package andrewoid.neuralnetwork;

import com.google.gson.Gson;

import java.io.FileReader;

public class NetworkFactory
{
    public Network loadFromJSON(String fileName)
    {
        Network network = null;
        try(FileReader fileReader = new FileReader(fileName))
        {
            Gson gson = new Gson();
            network = gson.fromJson(fileReader, Network.class);
            network.connectNeurons();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        return network;
    }
}
