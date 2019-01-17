package guideToGalaxy.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiteralQuestionQuery extends Query {
	private List<String> aliases;

	LiteralQuestionQuery() {
		super(QueryType.LITERAL_QUESTION);
	}

	@Override
	public void parse(String instruction) {
		int startIndex = instruction.indexOf("how much is ") + "how much is ".length();
		String[] splits = instruction.substring(startIndex, instruction.lastIndexOf("?")).split("\\s+");
		aliases = Stream.of(splits).collect(Collectors.toList());
	}

	public List<String> getAliases() {
		return aliases;
	}

}
