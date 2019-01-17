package guideToGalaxy.parser;

public class InvalidInstruction extends Instruction {

	InvalidInstruction() {
		super(InstructionType.INVALID);
	}

	@Override
	public void parse(String instruction) {

	}

}
