import java.io.*;

public class NeuralNet {	
	private int layers;
	private int [] layersizes;
	private Perceptron [][] network;
	private double [][] features;
	private int [] labels;

	public NeuralNet(int [] numunits) {
		this(numunits.length, numunits);
	}

	public NeuralNet(int numlay, int [] numunits) {
		layers = numlay;
		layersizes = numunits;
		network = new Perceptron[layers][];
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

	public void readData(String featfile, String labelfile) {
		File f1 = new File(featfile);
		File f2 = new File(labelfile);
		try {
			BufferedReader brf = new BufferedReader(new FileReader(f1));
			BufferedReader brl = new BufferedReader(new FileReader(f2));
			int counter = 0;
			String line;
			line = brf.readLine();
			if (line != null) {
				features = new double[Integer.parseInt(line)][layersizes[0]];
				labels = new int[Integer.parseInt(line)];
			}
			while(true) {
				line = brf.readLine();
				if (line == null)
					break;
				String [] curfeats = line.split(",");
				for (int i = 0; i < curfeats.length; i++) {
					features[counter][i] = Double.parseDouble(curfeats[i]);
				}
				line = brl.readLine();
				if (line == null)
					break;
				labels[counter] = Integer.parseInt(line);
				counter++;
			}
		}
		catch (IOException e) {
			System.out.println("ERROR: Cannot read file");
			return;
		}
	}

	public void setInputs(double [] invals) {
		if (invals == null || invals.length < layersizes[0]) {
			System.out.println("ERROR: invalid input array");
			return;
		}
		for (int j = 0; j < layersizes[0]; j++) {
			network[0][j].setValue(invals[j]);
		}
		for (int i = 1; i < layers; i++) {
			for (int j = 0; j < layersizes[i]; j++) {
				double [] prevals = new double[layersizes[i - 1]];
				for (int k = 0; k < layersizes[i - 1]; k++) {
					prevals[k] = network[i - 1][k].getValue(j);
				}
				network[i][j].updateValue(0, prevals);
			}
		}
	}

	public int getOutput() {
		int max = 0;
		if (layersizes[layers - 1] == 1) {
			if (network[layers - 1][0].getValue(0) > 0.5) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			for (int i = 0; i < layersizes[layers - 1]; i++) {
				if (network[layers - 1][i].getValue(0) > network[layers - 1][max].getValue(0)) {
					max = i;
				}
			}
			return max;
		}
	}

	public void printData() {
		for (int i = 0; i < features.length; i++) {
			for (int j = 0; j < features[i].length; j++) {
				System.out.print(features[i][j] + ",");
			}
			System.out.print(labels[i] + "\n");
		}
	}

	public String toString() {
		String out = "";
		for (int i = 0; i < layers - 1; i++) {
			out += "Layer " + i + ": \n";
			for (int j = 0; j < layersizes[i]; j++) {
				out += "\tNode " + j + ": \n";
				for (int k = 0; k < layersizes[i+1]; k++) {
					out += "\t\tOut" + k + ": " + network[i][j].getValue(k) + "\n";
				}
			}
		}
		out += "Layer " + (layers - 1) + ": \n";
		for (int j = 0; j < layersizes[layers - 1]; j++) {
			out += "\tNode " + j + ": \n";
			out += "\t\tOut" + 0 + ": " + network[layers - 1][j].getValue(0) + "\n";
		}
		return out;
	}
}
