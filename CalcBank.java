public class CalcBank {
	public static double sigmoid(double value) {
		return 1.0 / (1.0 + Math.exp(0 - value));
	}
	
	public static double [] sigmoid(double [] values) {
		double [] results = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			results[i] = sigmoid(values[i]);
		}
		return results;
	}
}
