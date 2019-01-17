package guideToGalaxy.parser;

import guideToGalaxy.processor.ProcessingState;

public class InvalidInstruction extends Instruction {

	InvalidInstruction() {
		super(InstructionType.INVALID);
	}

	@Override
	public void parse(String instruction) {

	}

	@Override
	public void updateProcessingState(ProcessingState state) {
		state.addResponse("I have no idea what you are talking about");
	}

}
