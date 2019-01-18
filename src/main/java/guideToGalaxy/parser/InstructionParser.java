package guideToGalaxy.parser;

import guideToGalaxy.instructions.Instruction;
import guideToGalaxy.instructions.InstructionType;
import guideToGalaxy.instructions.ParseableInstruction;

public interface InstructionParser {
	default Instruction parse(String input) {
		final InstructionType type = determineInstructionType(input);
		final Instruction instruction = InstructionFactory.create(type);
		if (InstructionType.INVALID.equals(type) == false) {
			((ParseableInstruction) instruction).parse(input);
		}
		return instruction;
	}

	InstructionType determineInstructionType(String input);
}
