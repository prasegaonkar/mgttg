package guideToGalaxy.parser;

import static guideToGalaxy.instructions.InstructionType.CREDITS_DECLARATION;
import static guideToGalaxy.instructions.InstructionType.CREDITS_QUESTION;
import static guideToGalaxy.instructions.InstructionType.INVALID;
import static guideToGalaxy.instructions.InstructionType.LITERAL_DECLARATION;
import static guideToGalaxy.instructions.InstructionType.LITERAL_QUESTION;

import guideToGalaxy.instructions.InstructionType;

public class MGTTGInstructionParser implements InstructionParser {

	@Override
	public InstructionType determineInstructionType(String instruction) {
		InstructionType type = INVALID;
		final boolean isInstructionBlank = instruction == null || "".equals(instruction.trim());
		if (isInstructionBlank) {
			return type;
		}
		type = instruction.matches(LITERAL_DECLARATION.getMatchingPattern()) ? LITERAL_DECLARATION : INVALID;
		if (INVALID.equals(type) == false) {
			return type;
		}
		type = instruction.matches(CREDITS_DECLARATION.getMatchingPattern()) ? CREDITS_DECLARATION : INVALID;
		if (INVALID.equals(type) == false) {
			return type;
		}
		type = instruction.matches(LITERAL_QUESTION.getMatchingPattern()) ? LITERAL_QUESTION : INVALID;
		if (INVALID.equals(type) == false) {
			return type;
		}
		type = instruction.matches(CREDITS_QUESTION.getMatchingPattern()) ? CREDITS_QUESTION : INVALID;
		return type;
	}

}
