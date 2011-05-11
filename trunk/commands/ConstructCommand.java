package commands;

import classifier.ClassifierBuilder;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;

public class ConstructCommand implements Command {

	private ClassifierBuilder clb;
	
	public ConstructCommand(ClassifierBuilder clb) {
		this.clb = clb;
	}

	@Override
	public void execute() {
		try {
			this.clb.constructClassifierByName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
