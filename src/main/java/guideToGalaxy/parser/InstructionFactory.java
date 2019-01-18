package guideToGalaxy.parser;

import guideToGalaxy.instructions.CreditsDeclarationInstruction;
import guideToGalaxy.instructions.CreditsQuestionInstruction;
import guideToGalaxy.instructions.Instruction;
import guideToGalaxy.instructions.InstructionType;
import guideToGalaxy.instructions.InvalidInstruction;
import guideToGalaxy.instructions.LiteralDeclarationInstruction;
import guideToGalaxy.instructions.LiteralQuestionInstruction;

public class InstructionFactory {
	public static Instruction create(InstructionType type) {
		switch (type) {
		case CREDITS_DECLARATION:
			return new CreditsDeclarationInstruction();
		case CREDITS_QUESTION:
			return new CreditsQuestionInstruction();
		case LITERAL_DECLARATION:
			return new LiteralDeclarationInstruction();
		case LITERAL_QUESTION:
			return new LiteralQuestionInstruction();
		default:
			return new InvalidInstruction();
		}
	}
}
