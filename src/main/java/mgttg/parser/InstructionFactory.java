package mgttg.parser;

import mgttg.instructions.CreditsDeclarationInstruction;
import mgttg.instructions.CreditsQuestionInstruction;
import mgttg.instructions.Instruction;
import mgttg.instructions.InstructionType;
import mgttg.instructions.InvalidInstruction;
import mgttg.instructions.LiteralDeclarationInstruction;
import mgttg.instructions.LiteralQuestionInstruction;

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
