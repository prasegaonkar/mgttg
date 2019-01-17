package guideToGalaxy.processor;

import java.util.List;

import guideToGalaxy.parser.Instruction;
import guideToGalaxy.parser.InstructionParser;

public class InstructionsProcessor {
	private final InstructionParser parser;

	public InstructionsProcessor(InstructionParser parser) {
		this.parser = parser;
	}

	public ProcessingState process(List<String> inputs) {
		if (inputs != null && inputs.size() > 0) {
			ProcessingState state = new ProcessingState();
			inputs.forEach(input -> {
				Instruction instruction = parser.parse(input);
				instruction.updateProcessingState(state);
			});
			return state;
		}
		return null;
	}
}
