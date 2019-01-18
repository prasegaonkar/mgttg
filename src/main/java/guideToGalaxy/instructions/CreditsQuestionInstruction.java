package guideToGalaxy.instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import guideToGalaxy.processor.ProcessingState;

public class CreditsQuestionInstruction implements ParseableInstruction {
	private List<String> aliases;
	private String metalType;

	@Override
	public void parse(String input) {
		int startIndex = input.indexOf("how many Credits is ") + "how many Credits is ".length();
		String[] splits = input.substring(startIndex, input.lastIndexOf("?")).split("\\s+");
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

	@Override
	public void updateProcessingState(ProcessingState state) {
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append(aliases.stream().collect(Collectors.joining(" ")));
		responseBuilder.append(" " + metalType);
		responseBuilder.append(" is " + state.calculateCredits(metalType, aliases) + " Credits");
		state.addResponse(responseBuilder.toString());
	}

	@Override
	public InstructionType getType() {
		return InstructionType.CREDITS_QUESTION;
	}

}
