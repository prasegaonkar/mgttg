package guideToGalaxy;

public class Query {

	private QueryType queryType;

	Query(QueryType queryType) {
		this.queryType = queryType;
	}

	public QueryType getQueryType() {
		return queryType;
	}
}
