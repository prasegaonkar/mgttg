package guideToGalaxy.parser;

public enum QueryType {
	INVALID("X"),

	LITERAL_DECLARATION("\\w+{1} is (?:I|V|X|L|C|D|M)"),

	CREDITS_DECLARATION(".* (?:Silver|Gold|Iron) is \\d+ Credits"),

	LITERAL_QUESTION("(how much is .*) \\?"),

	CREDITS_QUESTION("(how many Credits is .* )(?:Silver|Gold|Iron) \\?");

	private String matchingPattern;

	QueryType(String matchingPattern) {
		this.matchingPattern = matchingPattern;
	}

	public String getMatchingPattern() {
		return matchingPattern;
	}

}
