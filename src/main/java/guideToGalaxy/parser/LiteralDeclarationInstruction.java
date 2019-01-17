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
		switch (literal) {
		case 'I':
			state.setAliasOfLiteral_I(literalAlias);
			break;
		case 'V':
			state.setAliasOfLiteral_V(literalAlias);
			break;
		case 'X':
			state.setAliasOfLiteral_X(literalAlias);
			break;
		case 'L':
			state.setAliasOfLiteral_L(literalAlias);
			break;
		case 'C':
			state.setAliasOfLiteral_C(literalAlias);
			break;
		case 'D':
			state.setAliasOfLiteral_D(literalAlias);
			break;
		case 'M':
			state.setAliasOfLiteral_M(literalAlias);
			break;
		default:
			break;
		}
	}

}
