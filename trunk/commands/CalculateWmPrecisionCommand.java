package commands;
import classifier.ClassifierBuilder;


public class CalculateWmPrecisionCommand implements Command {

	private ClassifierBuilder clb;
	
	public CalculateWmPrecisionCommand(ClassifierBuilder clb) {
		this.clb = clb;
	}

	@Override
	public void execute() {
		try {
			this.clb.calculateWmPrecision();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
