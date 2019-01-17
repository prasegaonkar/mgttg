package guideToGalaxy.parser;

public class QueryFactory {
	public static Query create(QueryType type) {
		switch (type) {
		case CREDITS_DECLARATION:
			return new CreditsDeclarationQuery();
		case CREDITS_QUESTION:
			return new CreditsQuestionQuery();
		case LITERAL_DECLARATION:
			return new LiteralDeclarationQuery();
		case LITERAL_QUESTION:
			return new LiteralQuestionQuery();
		default:
			return new InvalidQuery();
		}
	}
}
