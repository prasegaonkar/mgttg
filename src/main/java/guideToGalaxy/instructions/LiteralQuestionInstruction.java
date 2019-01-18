package guideToGalaxy.instructions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import guideToGalaxy.processor.ProcessingState;

public class LiteralQuestionInstruction implements ParseableInstruction {
	private List<String> aliases;

	@Override
	public void parse(String input) {
		int startIndex = input.indexOf("how much is ") + "how much is ".length();
		String[] splits = input.substring(startIndex, input.lastIndexOf("?")).split("\\s+");
		aliases = Stream.of(splits).collect(Collectors.toList());
	}

	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void updateProcessingState(ProcessingState state) {
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append(aliases.stream().collect(Collectors.joining(" ")));
		responseBuilder.append(" is " + state.getArabicConversion(aliases));
		state.addResponse(responseBuilder.toString());
	}

	@Override
	public InstructionType getType() {
		return InstructionType.LITERAL_QUESTION;
	}

}
