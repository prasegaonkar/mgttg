package mgttg.instructions;

import mgttg.processor.ProcessingState;

public interface Instruction {

	InstructionType getType();

	void updateProcessingState(ProcessingState state);

}
