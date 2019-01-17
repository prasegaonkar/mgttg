package guideToGalaxy.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import guideToGalaxy.convertor.NumberConvertor;

public class ProcessingState {
	private NumberConvertor convertor;
	private Map<String, Character> aliasLiteralMappings;
	private Map<String, BigDecimal> metalPerUnitCreditValue;
	private List<String> responses;

	public ProcessingState(NumberConvertor convertor) {
		this.convertor = convertor;
	}

	public Map<String, BigDecimal> getMetalPerUnitCreditValue() {
		return metalPerUnitCreditValue;
	}

	public List<String> getResponses() {
		return responses;
	}

	public void registerAliasLiteralMapping(String alias, Character literal) {
		if (aliasLiteralMappings == null) {
			aliasLiteralMappings = new HashMap<>();
		}
		aliasLiteralMappings.put(alias, literal);
	}

	public Map<String, Character> getAliasLiteralMappings() {
		return aliasLiteralMappings;
	}

	public void addResponse(String response) {
		if (responses == null) {
			responses = new ArrayList<>();
		}
		responses.add(response);
	}

	public void calculatePerUnitCreditValue(String metalType, List<String> aliases, Integer creditValue) {
		if (metalPerUnitCreditValue == null) {
			metalPerUnitCreditValue = new HashMap<>();
		}
		Integer arabicNumber = getArabicConversion(aliases);
		BigDecimal perUnitCreditValue = new BigDecimal(creditValue).divide(new BigDecimal(arabicNumber), 2,
				RoundingMode.HALF_UP);
		metalPerUnitCreditValue.put(metalType, perUnitCreditValue);
	}

	public Integer calculateCredits(String metalType, List<String> aliases) {
		Integer arabicNumber = getArabicConversion(aliases);
		BigDecimal perUnitCreditValue = metalPerUnitCreditValue.get(metalType);
		return perUnitCreditValue.multiply(new BigDecimal(arabicNumber)).intValue();
	}

	public Integer getArabicConversion(List<String> aliases) {
		String romanNumber = aliases.stream().map(a -> String.valueOf(aliasLiteralMappings.get(a)))
				.collect(Collectors.joining());
		return convertor.convert(romanNumber);
	}
}
