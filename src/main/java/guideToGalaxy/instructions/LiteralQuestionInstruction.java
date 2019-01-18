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

	@Override
	public void updateProcessingState(ProcessingState state) {
		String aliasSequence = aliases.stream().collect(Collectors.joining(" "));
		final Integer arabicConversion = state.getArabicConversion(aliases);
		state.addResponse(String.format("%s is %d", aliasSequence, arabicConversion));
	}

	@Override
	public InstructionType getType() {
		return InstructionType.LITERAL_QUESTION;
	}

	public List<String> getAliases() {
		return aliases;
	}

}
