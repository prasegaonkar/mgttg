package guideToGalaxy.processor;

import java.util.List;

import guideToGalaxy.convertor.RomanNumberConvertor;
import guideToGalaxy.parser.Instruction;
import guideToGalaxy.parser.InstructionParser;

public class InstructionsProcessor {
	private final InstructionParser parser;
	private final RomanNumberConvertor convertor;

	public InstructionsProcessor(InstructionParser parser, RomanNumberConvertor convertor) {
		this.parser = parser;
		this.convertor = convertor;
	}

	public ProcessingState process(List<String> inputs) {
		if (inputs != null && inputs.size() > 0) {
			ProcessingState state = new ProcessingState(convertor);
			inputs.forEach(input -> {
				Instruction instruction = parser.parse(input);
				instruction.updateProcessingState(state);
			});
			return state;
		}
		return null;
	}
}
