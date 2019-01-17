package guideToGalaxy.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

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

	@Test
	public void testLiteralsQuestionForValidPattern() {
		validateQueryType("how much is pish tegj glob glob ?", QueryType.LITERAL_QUESTION);
	}

	@Test
	public void testLiteralsQuestionForInvalidPattern() {
		validateQueryType("how much is pish tegj glob glob?", QueryType.INVALID);
		validateQueryType("much is pish tegj glob glob?", QueryType.INVALID);
		validateQueryType("how much is ?", QueryType.INVALID);
		validateQueryType("how much is pish tegj glob glob ??", QueryType.INVALID);
	}

	@Test
	public void testCreditsQuestionForValidPattern() {
		validateQueryType("how many Credits is glob prok Silver ?", QueryType.CREDITS_QUESTION);
	}

	@Test
	public void testCreditsQuestionForInvalidPattern() {
		validateQueryType("how many Credits is glob prok Silver?", QueryType.INVALID);
		validateQueryType("many Credits is glob prok Silver ?", QueryType.INVALID);
		validateQueryType("how many Credits is Silver ?", QueryType.INVALID);
		validateQueryType("how many Credits is glob prok Silver ??", QueryType.INVALID);
	}

	@Test
	public void validateSampleInputForQueryType() {
		validateQueryType("glob is I", QueryType.LITERAL_DECLARATION);
		validateQueryType("prok is V", QueryType.LITERAL_DECLARATION);
		validateQueryType("pish is X", QueryType.LITERAL_DECLARATION);
		validateQueryType("tegj is L", QueryType.LITERAL_DECLARATION);
		validateQueryType("glob glob Silver is 34 Credits", QueryType.CREDITS_DECLARATION);
		validateQueryType("glob prok Gold is 57800 Credits", QueryType.CREDITS_DECLARATION);
		validateQueryType("pish pish Iron is 3910 Credits", QueryType.CREDITS_DECLARATION);
		validateQueryType("how much is pish tegj glob glob ?", QueryType.LITERAL_QUESTION);
		validateQueryType("how many Credits is glob prok Silver ?", QueryType.CREDITS_QUESTION);
		validateQueryType("how many Credits is glob prok Gold ?", QueryType.CREDITS_QUESTION);
		validateQueryType("how many Credits is glob prok Iron ?", QueryType.CREDITS_QUESTION);
		validateQueryType("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?", QueryType.INVALID);
	}

	@Test
	public void validateSampleInputForParsedQuery() {
		validateLiteralDeclarationQuery("glob is I", "glob", "I");
		validateLiteralDeclarationQuery("prok is V", "prok", "V");
		validateLiteralDeclarationQuery("pish is X", "pish", "X");
		validateLiteralDeclarationQuery("tegj is L", "tegj", "L");
		validateCreditsDeclarationQuery("glob glob Silver is 34 Credits", "Silver", 34, "glob", "glob");
		validateCreditsDeclarationQuery("glob prok Gold is 57800 Credits", "Gold", 57800, "glob", "prok");
		validateCreditsDeclarationQuery("pish pish Iron is 3910 Credits", "Iron", 3910, "pish", "pish");
		validateLiteralQuestionQuery("how much is pish tegj glob glob ?", "pish", "tegj", "glob", "glob");
		validateCreditsQuestionQuery("how many Credits is glob prok Silver ?", "Silver", "glob", "prok");
		validateCreditsQuestionQuery("how many Credits is glob prok Gold ?", "Gold", "glob", "prok");
		validateCreditsQuestionQuery("how many Credits is glob prok Iron ?", "Iron", "glob", "prok");
	}

	private void validateCreditsQuestionQuery(String input, String metalType, String... aliases) {
		Query query = parser.parse(input);
		assertThat(query).isInstanceOf(CreditsQuestionQuery.class);
		CreditsQuestionQuery q = ((CreditsQuestionQuery) query);
		assertThat(q.getMetalType()).isEqualTo(metalType);
		assertThat(q.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateLiteralQuestionQuery(String input, String... aliases) {
		Query query = parser.parse(input);
		assertThat(query).isInstanceOf(LiteralQuestionQuery.class);
		LiteralQuestionQuery q = ((LiteralQuestionQuery) query);
		assertThat(q.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateCreditsDeclarationQuery(String input, String metalType, int creditValue, String... aliases) {
		Query query = parser.parse(input);
		assertThat(query).isInstanceOf(CreditsDeclarationQuery.class);
		CreditsDeclarationQuery q = ((CreditsDeclarationQuery) query);
		assertThat(q.getMetalType()).isEqualTo(metalType);
		assertThat(q.getCreditValue()).isEqualTo(creditValue);
		assertThat(q.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateLiteralDeclarationQuery(String input, String alias, String literal) {
		Query query = parser.parse(input);
		assertThat(query).isInstanceOf(LiteralDeclarationQuery.class);
		LiteralDeclarationQuery q = ((LiteralDeclarationQuery) query);
		assertThat(q.getLiteral()).isEqualTo(literal);
		assertThat(q.getLiteralAlias()).isEqualTo(alias);
	}
}
