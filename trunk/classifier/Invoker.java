package classifier;

import commands.CalculateWmPrecisionCommand;
import commands.Command;
import commands.ConstructCommand;
import commands.ConstructWmCommand;
import commands.PrepareTrainCommand;


public class Invoker {
	
	private Command prepareTrainCommand;
	private Command constructCommand;
	private Command constructWmCommand;
	private Command calculateWmPrecisionCommand;
	
	public Invoker(PrepareTrainCommand ptc, ConstructCommand cc, ConstructWmCommand cwmc, CalculateWmPrecisionCommand calcPrec) {
		this.prepareTrainCommand = ptc;
		this.constructCommand = cc;
		this.constructWmCommand = cwmc;
		this.calculateWmPrecisionCommand = calcPrec;
	}

	public void prepareTrain() {
		this.prepareTrainCommand.execute();
	}

	public void construct() {
		this.constructCommand.execute();
	}

	public void constructWm() {
		this.constructWmCommand.execute();
	}

	public void calculateWmPrecision() {
		this.calculateWmPrecisionCommand.execute();
	}
}
