public class InitNet {
	public static void main(String [] args) {
		int [] test = {3, 10, 1};
		double [] ins = {1.0, 1.0, 1.0};
		NeuralNet annabelle = new NeuralNet(3, test);
		annabelle.setInputs(ins);
		System.out.println(annabelle);
		System.out.println(annabelle.getOutput());
		annabelle.readData("fake_features.x", "fake_labels.y");
		annabelle.printData();
	}
}
