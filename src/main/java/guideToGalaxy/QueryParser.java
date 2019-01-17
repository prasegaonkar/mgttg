package guideToGalaxy;

public class QueryParser {

	public Query parse(String query) {
		return new Query(determineQueryType(query));
	}

	private QueryType determineQueryType(String query) {
		QueryType queryType = QueryType.INVALID;
		final boolean isQueryBlank = query == null || "".equals(query.trim());
		if (isQueryBlank) {
			return queryType;
		}
		queryType = literalDeclarationCheck(query);
		if (QueryType.INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = creditsDeclarationCheck(query);
		if (QueryType.INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = literalQuestionCheck(query);
		if (QueryType.INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = creditsQuestionCheck(query);
		return queryType;
	}

	private QueryType creditsQuestionCheck(String query) {
		String validQueryPattern = "(how many Credits is .* )(?:Silver|Gold|Iron) \\?";
		if (query.matches(validQueryPattern)) {
			return QueryType.CREDITS_QUESTION;
		}
		return QueryType.INVALID;
	}

	private QueryType literalQuestionCheck(String query) {
		String validQueryPattern = "(how much is .*) \\?";
		if (query.matches(validQueryPattern)) {
			return QueryType.LITERAL_QUESTION;
		}
		return QueryType.INVALID;
	}

	private QueryType creditsDeclarationCheck(String query) {
		String validQueryPattern = ".* (?:Silver|Gold|Iron) is \\d+ Credits";
		if (query.matches(validQueryPattern)) {
			return QueryType.CREDITS_DECLARATION;
		}
		return QueryType.INVALID;
	}

	private QueryType literalDeclarationCheck(String query) {
		String validQueryPattern = "\\w+{1} is (?:I|V|X|L|C|D|M)";

		if (query.matches(validQueryPattern)) {
			return QueryType.LITERAL_DECLARATION;
		}
		return QueryType.INVALID;
	}

}
