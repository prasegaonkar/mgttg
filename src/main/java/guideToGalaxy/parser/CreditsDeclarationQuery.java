package guideToGalaxy.parser;

import java.util.List;

public class CreditsDeclarationQuery extends Query {

	private List<String> aliases;
	private Integer creditValue;
	private String metalType;

	CreditsDeclarationQuery() {
		super(QueryType.CREDITS_DECLARATION);
	}

	@Override
	public void parse(String instruction) {
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
