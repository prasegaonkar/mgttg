package mgttg.instructions;

import mgttg.processor.ProcessingState;

public class InvalidInstruction implements Instruction {

	@Override
	public void updateProcessingState(ProcessingState state) {
		state.addResponse("I have no idea what you are talking about");
	}

	@Override
	public InstructionType getType() {
		return InstructionType.INVALID;
	}

}
