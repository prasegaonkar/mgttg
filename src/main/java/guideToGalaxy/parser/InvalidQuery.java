package guideToGalaxy.parser;

public class InvalidQuery extends Query {

	InvalidQuery() {
		super(QueryType.INVALID);
	}

	@Override
	public void parse(String instruction) {

	}

}
