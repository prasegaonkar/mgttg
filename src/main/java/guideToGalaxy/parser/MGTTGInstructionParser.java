package guideToGalaxy.parser;

import static guideToGalaxy.instructions.InstructionType.CREDITS_DECLARATION;
import static guideToGalaxy.instructions.InstructionType.CREDITS_QUESTION;
import static guideToGalaxy.instructions.InstructionType.INVALID;
import static guideToGalaxy.instructions.InstructionType.LITERAL_DECLARATION;
import static guideToGalaxy.instructions.InstructionType.LITERAL_QUESTION;

import guideToGalaxy.instructions.Instruction;
import guideToGalaxy.instructions.InstructionFactory;
import guideToGalaxy.instructions.InstructionType;
import guideToGalaxy.instructions.ParseableInstruction;

public class MGTTGInstructionParser implements InstructionParser {

	@Override
	public Instruction parse(String input) {
		final InstructionType type = determineType(input);
		final Instruction instruction = InstructionFactory.create(type);
		if (InstructionType.INVALID.equals(type) == false) {
			((ParseableInstruction) instruction).parse(input);
		}
		return instruction;
	}

	private InstructionType determineType(String instruction) {
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
