package guideToGalaxy.parser;

public abstract class Query {

	private QueryType queryType;

	Query(QueryType queryType) {
		this.queryType = queryType;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public abstract void parse(String instruction);

}
