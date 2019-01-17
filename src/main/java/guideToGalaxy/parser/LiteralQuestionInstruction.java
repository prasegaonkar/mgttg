package guideToGalaxy.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiteralQuestionInstruction extends Instruction {
	private List<String> aliases;

	LiteralQuestionInstruction() {
		super(InstructionType.LITERAL_QUESTION);
	}

	@Override
	public void parse(String input) {
		int startIndex = input.indexOf("how much is ") + "how much is ".length();
		String[] splits = input.substring(startIndex, input.lastIndexOf("?")).split("\\s+");
		aliases = Stream.of(splits).collect(Collectors.toList());
	}

	public List<String> getAliases() {
		return aliases;
	}

}
