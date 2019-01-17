package guideToGalaxy.parser;

import static guideToGalaxy.parser.QueryType.CREDITS_DECLARATION;
import static guideToGalaxy.parser.QueryType.CREDITS_QUESTION;
import static guideToGalaxy.parser.QueryType.INVALID;
import static guideToGalaxy.parser.QueryType.LITERAL_DECLARATION;
import static guideToGalaxy.parser.QueryType.LITERAL_QUESTION;

public class QueryParser {

	public Query parse(String instruction) {
		final QueryType queryType = determineQueryType(instruction);
		final Query query = QueryFactory.create(queryType);
		if (QueryType.INVALID.equals(queryType) == false) {
			query.parse(instruction);
		}
		return query;
	}

	private QueryType determineQueryType(String instruction) {
		QueryType queryType = INVALID;
		final boolean isInstructionBlank = instruction == null || "".equals(instruction.trim());
		if (isInstructionBlank) {
			return queryType;
		}
		queryType = instruction.matches(LITERAL_DECLARATION.getMatchingPattern()) ? LITERAL_DECLARATION : INVALID;
		if (INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = instruction.matches(CREDITS_DECLARATION.getMatchingPattern()) ? CREDITS_DECLARATION : INVALID;
		if (INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = instruction.matches(LITERAL_QUESTION.getMatchingPattern()) ? LITERAL_QUESTION : INVALID;
		if (INVALID.equals(queryType) == false) {
			return queryType;
		}
		queryType = instruction.matches(CREDITS_QUESTION.getMatchingPattern()) ? CREDITS_QUESTION : INVALID;
		return queryType;
	}

}
