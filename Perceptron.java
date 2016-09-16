public class Perceptron {
	private int outputs;		// number of outgoing branches
	private double value;		// current stored value
	private double [] outweights;	// array of outgoing weights

	/** Constructor: initializes Perceptron with given number of outgoing branches, sets
	*   all weights to random doubles between 0 and 1.
	*   
	*   @args:
	*   -numout: number of outgoing branches
	*/
	public Perceptron(int numout) {
		outputs = numout;
		outweights = new double[outputs];
		for (int i = 0; i < outputs; i++) {
			outweights[i] = (Math.random() * 2 - 1);
		}
		value = 0.0;
	}

	/** Constructor: initializes Perceptron with a given array of weights
	*   
	*   @args:
	*   -defweights: array of weights
	*/
	public Perceptron(double [] defweights) {
		outputs = defweights.length;
		outweights = defweights;
		value = 0.0;
	}

	/** getValue: stored value multiplied by the weight at the given branch
	*   
	*   @args:
	*   -branch: index of outweights to use
	*
	*   @return: weighted value at given branch
	*/
	public double getValue(int branch) {
		return value * outweights[branch]; 
	}

	/** setValue: directly sets the value of the perceptron, useful for input nodes
	*   
	*   @args:
	*   -newval: value to be set;
	*/
	public void setValue(double newval) {
		value = newval;
	}

	/** updateValue: updates the internally stored value of the perceptron given an
	*   intercept and an array of incoming values. Adds all of the values together,
	*   then pipes it through a sigmoid function before returning a value between 0
	*   and 1.
	*
	*   @args:
	*   -intercept: bias value added to other values
	*   -prevalues: array of values obtained from incoming branches
	*/
	public void updateValue(double intercept, double [] prevalues) {
		value = intercept;
		for (int i = 0; i < prevalues.length; i++) {
			value += prevalues[i];
		}
		value = CalcBank.sigmoid(value); 
	}
}
