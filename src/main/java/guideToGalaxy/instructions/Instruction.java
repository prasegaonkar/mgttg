package guideToGalaxy.instructions;

import guideToGalaxy.processor.ProcessingState;

public interface Instruction {

	InstructionType getType();

	void updateProcessingState(ProcessingState state);

}
