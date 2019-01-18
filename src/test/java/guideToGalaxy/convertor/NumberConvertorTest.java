package guideToGalaxy.convertor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import guideToGalaxy.config.ExecutionContext;

public class NumberConvertorTest {
	private RomanNumberConvertor convertor = ExecutionContext.convertor;

	@BeforeClass
	public static void setupContext() {
		ExecutionContext.setup();
	}

	@Test
	public void nullConvertsToNull() {
		validateConversionIsNull(null);
	}

	@Test
	public void blankConvertsToNull() {
		validateConversionIsNull("  ");
	}

	@Test
	public void invalidConvertsToNull() {
		validateConversionIsNull("invalid");
	}

	@Test
	public void test_IXCM_MoreThan3TimesInSuccessionConvertsToNull() {
		validateConversionIsNull("XIIII");
		validateConversionIsNull("XXXXI");
		validateConversionIsNull("CCCCX");
		validateConversionIsNull("MMMMI");
	}

	@Test
	public void test_DLV_MoreThan1TimesInSuccessionConvertsToNull() {
		validateConversionIsNull("DDC");
		validateConversionIsNull("LLX");
		validateConversionIsNull("VVI");
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

	private void validateConversionIsNull(final String romanNumber) {
		assertThat(convertor.convert(romanNumber)).isNull();
	}

}
