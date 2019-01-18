package guideToGalaxy.convertor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NumberConvertorTest {
	private NumberConvertor convertor;

	@Before
	public void setup() {
		convertor = new NumberConvertor();
	}

	@Test
	public void nullConvertsToNull() {
		assertThat(convertor.convert(null)).isNull();
	}

	@Test
	public void blankConvertsToNull() {
		assertThat(convertor.convert("  ")).isNull();
	}

	@Test
	public void invalidConvertsToNull() {
		assertThat(convertor.convert("invalid")).isNull();
	}

	@Test
	public void test_IXCM_MoreThan3TimesInSuccessionConvertsToNull() {
		assertThat(convertor.convert("XIIII")).isNull();
		assertThat(convertor.convert("XXXXI")).isNull();
		assertThat(convertor.convert("CCCCX")).isNull();
		assertThat(convertor.convert("MMMMI")).isNull();
	}

	@Test
	public void test_DLV_MoreThan1TimesInSuccessionConvertsToNull() {
		assertThat(convertor.convert("DDC")).isNull();
		assertThat(convertor.convert("LLX")).isNull();
		assertThat(convertor.convert("VVI")).isNull();
	}

	@Test
	public void conversionForOneValidLiteral() {
		validateConversion("I", 1);
		validateConversion("V", 5);
		validateConversion("X", 10);
		validateConversion("L", 50);
		validateConversion("C", 100);
		validateConversion("D", 500);
		validateConversion("M", 1000);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithI() {
		validateConversion("II", 2);
		validateConversion("IV", 4);
		validateConversion("IX", 9);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithV() {
		validateConversion("VI", 6);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithX() {
		validateConversion("XI", 11);
		validateConversion("XV", 15);
		validateConversion("XX", 20);
		validateConversion("XL", 40);
		validateConversion("XC", 90);
	}

	@Test
	public void testNumberConversion() {
		validateConversion("MCMXLIV", 1944);
		validateConversion("MCMIII", 1903);
	}

	private void validateConversion(final String romanNumber, final int expected) {
		assertThat(convertor.convert(romanNumber)).isEqualTo(expected);
	}

}
