package guideToGalaxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class QueryParserTest {
	private QueryParser parser = null;

	@Before
	public void setup() {
		parser = new QueryParser();
	}

	private void validateQueryType(String input, QueryType expectedType) {
		assertThat(parser.parse(input).getQueryType()).isEqualTo(expectedType);
	}

	@Test
	public void nullInputIsInvalidQuery() {
		validateQueryType(null, QueryType.INVALID);
	}

	@Test
	public void blankInputIsInvalidQuery() {
		validateQueryType("", QueryType.INVALID);
	}

	@Test
	public void gibberishInputIsInvalidQuery() {
		validateQueryType("gibberish input", QueryType.INVALID);
	}

	@Test
	public void testDeclarationForInvalidLength() {
		validateQueryType("glob is I J", QueryType.INVALID);
		validateQueryType("glob is ", QueryType.INVALID);
	}

	@Test
	public void testDeclarationWithValidLiterals() {
		validateQueryType("glob is I", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is V", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is X", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is L", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is C", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is D", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is M", QueryType.LITERAL_DECLARATION);
	}

	@Test
	public void testDeclarationWithInvalidLiterals() {
		validateQueryType("glob is A", QueryType.INVALID);
	}
}
