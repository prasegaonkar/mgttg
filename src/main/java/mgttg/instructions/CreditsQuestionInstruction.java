package mgttg.instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mgttg.processor.ProcessingState;

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

	@Override
	public void updateProcessingState(ProcessingState state) {
		String aliasSequence = aliases.stream().collect(Collectors.joining(" "));
		Integer credits = state.calculateCredits(metalType, aliases);
		state.addResponse(String.format("%s %s is %d Credits", aliasSequence, metalType, credits));
	}

	@Override
	public InstructionType getType() {
		return InstructionType.CREDITS_QUESTION;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public String getMetalType() {
		return metalType;
	}
}
