package guideToGalaxy.parser;

import guideToGalaxy.processor.ProcessingState;

public class LiteralDeclarationInstruction extends Instruction {
	private String literalAlias;
	private Character literal;

	LiteralDeclarationInstruction() {
		super(InstructionType.LITERAL_DECLARATION);
	}

	public String getLiteralAlias() {
		return literalAlias;
	}

	public Character getLiteral() {
		return literal;
	}

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

}
