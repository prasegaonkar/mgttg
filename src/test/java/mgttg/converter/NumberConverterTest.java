package mgttg.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import mgttg.config.ExecutionContext;
import mgttg.converter.InvalidRomanNumberException;
import mgttg.converter.RomanNumberConverter;

public class NumberConverterTest {
	private RomanNumberConverter converter = ExecutionContext.converter;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setupContext() {
		ExecutionContext.setup();
	}

	@Test
	public void nullNumberThrowsInvalidException() {
		convert(null);
	}

	@Test
	public void blankThrowsInvalidException() {
		convert("  ");
	}

	@Test
	public void invalidThrowsInvalidException() {
		convert("invalid");
	}

	@Test
	public void test_IXCM_MoreThan3TimesInSuccessionThrowsInvalidException() {
		convert("XIIII");
		convert("XXXXI");
		convert("CCCCX");
		convert("MMMMI");
	}

	@Test
	public void test_DLV_MoreThan1TimesInSuccessionThrowsInvalidException() {
		convert("DDC");
		convert("LLX");
		convert("VVI");
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
		validateConversion("MMVI", 2006);
		validateConversion("MCMXLIV", 1944);
		validateConversion("MCMIII", 1903);
	}

	private void validateConversion(final String romanNumber, final int expected) {
		assertThat(converter.convert(romanNumber)).isEqualTo(expected);
	}

	private void convert(final String romanNumber) {
		exception.expect(InvalidRomanNumberException.class);
		converter.convert(romanNumber);
	}

}
