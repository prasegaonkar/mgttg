package mgttg.parser;

import mgttg.instructions.Instruction;
import mgttg.instructions.InstructionType;
import mgttg.instructions.ParseableInstruction;

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
