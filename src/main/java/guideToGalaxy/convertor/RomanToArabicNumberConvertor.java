package guideToGalaxy.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanToArabicNumberConvertor implements RomanNumberConvertor {
	private final static Map<Character, Integer> literalValues = new HashMap<>();

	static {
		literalValues.put('I', 1);
		literalValues.put('V', 5);
		literalValues.put('X', 10);
		literalValues.put('L', 50);
		literalValues.put('C', 100);
		literalValues.put('D', 500);
		literalValues.put('M', 1000);
	}

	@Override
	public Integer convert(String romanNumber) {
		if (isNotValid(romanNumber)) {
			throw new InvalidRomanNumberException();
		}

		return calcualate(toListOfChars(romanNumber));
	}

	private Integer calcualate(List<Character> chars) {
		int cursor = 0;
		Character charAtCursor = null;
		Character charAtCursorNext = null;
		Integer sum = 0;

		while (cursor < chars.size()) {
			charAtCursor = chars.get(cursor);
			if (cursor + 1 <= chars.size() - 1) {
				charAtCursorNext = chars.get(cursor + 1);
				if (literalValues.get(charAtCursor) >= literalValues.get(charAtCursorNext)) {
					sum += literalValues.get(charAtCursor);
				} else {
					sum += (literalValues.get(charAtCursorNext) - literalValues.get(charAtCursor));
					cursor++;
				}
			} else {
				sum += literalValues.get(charAtCursor);
			}
			cursor++;
		}

		return sum;
	}

	private boolean isNotValid(String romanNumber) {
		return isBlank(romanNumber) || containsInvalidCharacter(romanNumber) || hasInvalidPattern(romanNumber);
	}

	private boolean isBlank(String romanNumber) {
		return romanNumber == null || romanNumber.trim().equals("");
	}

	private List<Character> toListOfChars(String romanNumber) {
		return romanNumber.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}

	private boolean hasInvalidPattern(String romanNumber) {
		return getInvalidPatterns().stream().anyMatch(x -> romanNumber.contains(x));
	}

	private boolean containsInvalidCharacter(String romanNumber) {
		return romanNumber.chars().mapToObj(c -> (char) c).anyMatch(x -> !literalValues.containsKey(x));
	}

	private static List<String> getInvalidPatterns() {
		final List<String> IXCM_MORE_THAN_3_TIMES_IN_SUCCESSION = Arrays.asList("IIII", "XXXX", "CCCC", "MMMM");
		final List<String> REPEATING_DLV = Arrays.asList("DD", "LL", "VV");
		final List<String> I_BEFORE_ANYTHING_BUT_VX = Arrays.asList("IL", "IC", "ID", "IM");
		final List<String> X_BEFORE_ANYTHING_BUT_LC = Arrays.asList("XD", "XM");
		final List<String> V_IS_SUBTRACTABLE = Arrays.asList("VX", "VL", "VC", "VD", "VM");
		final List<String> L_IS_SUBTRACTABLE = Arrays.asList("LC", "LD", "LM");
		final List<String> D_IS_SUBTRACTABLE = Arrays.asList("DM");

		final List<String> invalidPatterns = new ArrayList<>();
		invalidPatterns.addAll(IXCM_MORE_THAN_3_TIMES_IN_SUCCESSION);
		invalidPatterns.addAll(REPEATING_DLV);
		invalidPatterns.addAll(I_BEFORE_ANYTHING_BUT_VX);
		invalidPatterns.addAll(X_BEFORE_ANYTHING_BUT_LC);
		invalidPatterns.addAll(V_IS_SUBTRACTABLE);
		invalidPatterns.addAll(L_IS_SUBTRACTABLE);
		invalidPatterns.addAll(D_IS_SUBTRACTABLE);
		return invalidPatterns;
	}

}
