public class NeuralNet {	
	private int layers;
	private int [] layersizes;
	private Perceptron [][] network;

	public NeuralNet (int [] numunits) {
		this(numunits.length, numunits);
	}

	public NeuralNet (int numlay, int [] numunits) {
		layers = numlay;
		layersizes = numunits;
		network = new Perceptron[numlay][];
		for (int i = 0; i < layers; i++) {
			network[i] = new Perceptron[layersizes[i]];
		}
		for (int i = 0; i < layers - 1; i++) {
			for (int j = 0; j < layersizes[i]; j++) {
				network[i][j] = new Perceptron(layersizes[i + 1]);
			}
		}
		for (int j = 0; j < layersizes[layers - 1]; j++) {
			double[] unitweight = {1};
			network[layers - 1][j] = new Perceptron(unitweight);
		}
	}
}
