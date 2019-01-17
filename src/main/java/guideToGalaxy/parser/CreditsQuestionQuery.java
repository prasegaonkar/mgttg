package guideToGalaxy.parser;

import java.util.ArrayList;
import java.util.List;

public class CreditsQuestionQuery extends Query {
	private List<String> aliases;
	private String metalType;

	CreditsQuestionQuery() {
		super(QueryType.CREDITS_QUESTION);
	}

	@Override
	public void parse(String instruction) {
		int startIndex = instruction.indexOf("how many Credits is ") + "how many Credits is ".length();
		String[] splits = instruction.substring(startIndex, instruction.lastIndexOf("?")).split("\\s+");
		metalType = splits[splits.length - 1];
		aliases = new ArrayList<String>();
		for (int i = 0; i < splits.length - 1; i++) {
			aliases.add(splits[i]);
		}
	}

	public List<String> getAliases() {
		return aliases;
	}

	public String getMetalType() {
		return metalType;
	}

}
