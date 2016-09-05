public class InitNet {
	public static void main(String [] args) {
		double [] test = {1.0, 0.5, 0.5};
		double [] test2 = {5, -2};
		Perceptron tester = new Perceptron(test);
		tester.updateValue(0.0, test2);
		System.out.println(tester.getValue(0));
	}
}
