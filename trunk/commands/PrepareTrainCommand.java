package commands;

import java.io.IOException;

import classifier.ClassifierBuilder;

public class PrepareTrainCommand implements Command{
	
	private ClassifierBuilder clb;
	
	public PrepareTrainCommand(ClassifierBuilder clb) {
		this.clb = clb;
	}
	
	@Override
	public void execute() {
		try {
			clb.prepareTrain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
