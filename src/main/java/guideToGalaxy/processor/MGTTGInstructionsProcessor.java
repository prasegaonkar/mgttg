package guideToGalaxy.processor;

import java.util.List;

import guideToGalaxy.converter.RomanNumberConverter;
import guideToGalaxy.instructions.Instruction;
import guideToGalaxy.parser.InstructionParser;

public class MGTTGInstructionsProcessor implements InstructionsProcessor {
	private final InstructionParser parser;
	private final RomanNumberConverter converter;

	public MGTTGInstructionsProcessor(InstructionParser parser, RomanNumberConverter converter) {
		this.parser = parser;
		this.converter = converter;
	}

	@Override
	public ProcessingState process(List<String> inputs) {
		if (inputs != null && inputs.size() > 0) {
			ProcessingState state = new ProcessingState(converter);
			inputs.forEach(input -> {
				try {
					Instruction instruction = parser.parse(input);
					instruction.updateProcessingState(state);
				} catch (Exception ex) {
					state.addResponse("I have no idea what you are talking about");
				}
			});
			return state;
		}
		return null;
	}
}
