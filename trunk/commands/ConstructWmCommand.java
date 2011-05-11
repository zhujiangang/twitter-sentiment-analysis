package commands;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import classifier.ClassifierBuilder;
import classifier.IClassifier;
import classifier.Item;
import classifier.WeightedMajority;
import classifier.WekaClassifier;



public class ConstructWmCommand implements Command {

	private ClassifierBuilder clb;
	
	public ConstructWmCommand(ClassifierBuilder clb) {
		this.clb = clb;
	}

	@Override
	public void execute() {
		try {
			this.clb.constructWm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
