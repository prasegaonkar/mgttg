package guideToGalaxy.parser;

public abstract class Instruction {

	private InstructionType type;

	Instruction(InstructionType type) {
		this.type = type;
	}

	public InstructionType getType() {
		return type;
	}

	public abstract void parse(String input);

}
