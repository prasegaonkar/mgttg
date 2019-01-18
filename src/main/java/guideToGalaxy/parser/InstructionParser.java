package guideToGalaxy.parser;

import guideToGalaxy.instructions.Instruction;

public interface InstructionParser {
	public Instruction parse(String input);
}
