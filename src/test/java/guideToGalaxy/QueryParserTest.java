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
	public void testLiteralDeclarationForInvalidLength() {
		validateQueryType("glob is I J", QueryType.INVALID);
		validateQueryType("glob is ", QueryType.INVALID);
	}

	@Test
	public void testLiteralDeclarationWithValidLiterals() {
		validateQueryType("glob is I", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is V", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is X", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is L", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is C", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is D", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob is M", QueryType.LITERAL_DECLARATION);
	}

	@Test
	public void testLiteralDeclarationWithInvalidLiterals() {
		validateQueryType("glob is A", QueryType.INVALID);
	}

	@Test
	public void testCreditsDeclarationForValidPattern() {
		validateQueryType("glob glob Silver is 34 Credits", QueryType.CREDITS_DECLARATION);
	}

	@Test
	public void testCreditsDeclarationForInvalidPatterns() {
		validateQueryType("glob glob Silver is xx Credits", QueryType.INVALID);
		validateQueryType("glob glob Silver is 34 Credit", QueryType.INVALID);
		validateQueryType("glob glob Titanium is 34 Credits", QueryType.INVALID);
	}
}
