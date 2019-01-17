package guideToGalaxy.parser;

import java.util.List;

public class LiteralQuestionQuery extends Query {
	private List<String> aliases;

	LiteralQuestionQuery() {
		super(QueryType.LITERAL_QUESTION);
	}

	@Override
	public void parse(String instruction) {

	}

	public List<String> getAliases() {
		return aliases;
	}

}
