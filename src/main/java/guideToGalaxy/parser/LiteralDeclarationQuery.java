package guideToGalaxy.parser;

public class LiteralDeclarationQuery extends Query {
	private String literalAlias;
	private String literal;

	LiteralDeclarationQuery() {
		super(QueryType.LITERAL_DECLARATION);
	}

	public String getLiteralAlias() {
		return literalAlias;
	}

	public String getLiteral() {
		return literal;
	}

	@Override
	public void parse(String instruction) {
		String[] splits = instruction.split(" is ");
		literalAlias = splits[0].trim();
		literal = splits[1].trim();
	}

}
