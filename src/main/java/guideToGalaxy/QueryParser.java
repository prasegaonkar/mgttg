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
		return queryType;
	}

	private QueryType literalDeclarationCheck(String query) {
		String validQueryPattern = ".* is [I,V,X,L,C,D,M]";

		if (query.matches(validQueryPattern) && query.split("\\s").length == 3) {
			return QueryType.LITERAL_DECLARATION;
		}
		return QueryType.INVALID;
	}

}
