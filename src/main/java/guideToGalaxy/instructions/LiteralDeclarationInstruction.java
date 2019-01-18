package guideToGalaxy.instructions;

import guideToGalaxy.processor.ProcessingState;

public class LiteralDeclarationInstruction implements ParseableInstruction {
	private String literalAlias;
	private Character literal;

	@Override
	public void parse(String input) {
		String[] splits = input.split(" is ");
		literalAlias = splits[0].trim();
		literal = splits[1].trim().charAt(0);
	}

	@Override
	public void updateProcessingState(ProcessingState state) {
		state.registerAliasLiteralMapping(literalAlias, literal);
	}

	@Override
	public InstructionType getType() {
		return InstructionType.LITERAL_DECLARATION;
	}

	public String getLiteralAlias() {
		return literalAlias;
	}

	public Character getLiteral() {
		return literal;
	}

}
