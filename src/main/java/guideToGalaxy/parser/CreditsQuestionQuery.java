package guideToGalaxy.parser;

import java.util.List;

public class CreditsQuestionQuery extends Query {
	private List<String> aliases;
	private String metalType;

	CreditsQuestionQuery() {
		super(QueryType.CREDITS_QUESTION);
	}

	@Override
	public void parse(String instruction) {

	}

	public List<String> getAliases() {
		return aliases;
	}

	public String getMetalType() {
		return metalType;
	}

}
