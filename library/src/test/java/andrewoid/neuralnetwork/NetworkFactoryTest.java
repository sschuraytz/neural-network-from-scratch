package andrewoid.neuralnetwork;

import andrewoid.neutralnetwork.Network;
import andrewoid.neutralnetwork.Neuron;
import andrewoid.neutralnetwork.NetworkFactory;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.*;

public class NetworkFactoryTest
{
    private String fileName = "testingNetwork.json";

    @Test
    public void loadFromJSON_correctNumberLayers()
    {
        //given
        NetworkFactory networkFactory = new NetworkFactory();
        InputStream in = NetworkFactory.class.getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(in);

        //when
        Network network = networkFactory.loadFromJSON(reader);

        //then
        assertEquals(3, network.getLayers().length);
    }

    @Test
    public void loadFromJSON_correctContentsFirstNeuron()
    {
        //given
        NetworkFactory networkFactory = new NetworkFactory();
        InputStream in = NetworkFactory.class.getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(in);

        //when
        Network network = networkFactory.loadFromJSON(reader);
        Neuron neuron = network.getLayers()[1][0];

        //then
        assertEquals(.7, neuron.getBias(), .001);
        assertEquals(.33, neuron.getDerivative(), .001);
        assertEquals(.709, neuron.getValue(), .001);
        assertEquals(-7.811767127875814E-6, neuron.getError(), .001);
        assertEquals(1, neuron.getNumWeights());
    }
}
