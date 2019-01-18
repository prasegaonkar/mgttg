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
		assertThat(convertor.convert("I")).isEqualTo(1);
		assertThat(convertor.convert("V")).isEqualTo(5);
		assertThat(convertor.convert("X")).isEqualTo(10);
		assertThat(convertor.convert("L")).isEqualTo(50);
		assertThat(convertor.convert("C")).isEqualTo(100);
		assertThat(convertor.convert("D")).isEqualTo(500);
		assertThat(convertor.convert("M")).isEqualTo(1000);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithI() {
		assertThat(convertor.convert("II")).isEqualTo(2);
		assertThat(convertor.convert("IV")).isEqualTo(4);
		assertThat(convertor.convert("IX")).isEqualTo(9);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithV() {
		assertThat(convertor.convert("VI")).isEqualTo(6);
	}

	@Test
	public void conversionForTwoValidLiteralsStartingWithX() {
		assertThat(convertor.convert("XI")).isEqualTo(11);
		assertThat(convertor.convert("XV")).isEqualTo(15);
		assertThat(convertor.convert("XX")).isEqualTo(20);
		assertThat(convertor.convert("XL")).isEqualTo(40);
		assertThat(convertor.convert("XC")).isEqualTo(90);
	}

}
