package guideToGalaxy.parser;

import guideToGalaxy.processor.ProcessingState;

public abstract class Instruction {

	private InstructionType type;

	Instruction(InstructionType type) {
		this.type = type;
	}

	public InstructionType getType() {
		return type;
	}

	public abstract void parse(String input);

	public abstract void updateProcessingState(ProcessingState state);

}
