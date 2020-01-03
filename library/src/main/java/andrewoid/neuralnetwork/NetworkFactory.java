package andrewoid.neuralnetwork;

import com.google.gson.Gson;

import java.io.*;

public class NetworkFactory
{
    public Network loadFromJSON(String fileName) throws FileNotFoundException
    {
        return loadFromJSON(new FileReader(fileName));
    }

    public Network loadFromJSON(Reader reader)
    {
        Gson gson = new Gson();
        Network network = gson.fromJson(reader, Network.class);
        network.connectNeurons();
        return network;
    }
}
