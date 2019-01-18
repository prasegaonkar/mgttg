package guideToGalaxy.instructions;

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
