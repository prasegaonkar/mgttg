package guideToGalaxy.parser;

import java.util.ArrayList;
import java.util.List;

public class CreditsDeclarationInstruction extends Instruction {
	private List<String> aliases;
	private Integer creditValue;
	private String metalType;

	CreditsDeclarationInstruction() {
		super(InstructionType.CREDITS_DECLARATION);
	}

	@Override
	public void parse(String instruction) {
		final int indexOfIs = instruction.indexOf(" is ");
		String part1 = instruction.substring(0, indexOfIs);
		String part2 = instruction.substring(indexOfIs + " is ".length());
		String[] splits = part1.split("\\s+");
		metalType = splits[splits.length - 1];
		aliases = new ArrayList<String>();
		for (int i = 0; i < splits.length - 1; i++) {
			aliases.add(splits[i]);
		}
		String creditValueAsString = part2.substring(0, part2.indexOf(" Credits"));
		creditValue = Integer.parseInt(creditValueAsString.trim());

	}

	public List<String> getAliases() {
		return aliases;
	}

	public Integer getCreditValue() {
		return creditValue;
	}

	public String getMetalType() {
		return metalType;
	}

}
