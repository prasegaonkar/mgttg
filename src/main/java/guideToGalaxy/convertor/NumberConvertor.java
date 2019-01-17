package guideToGalaxy.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberConvertor {
	private final static Map<Character, Integer> valueMap = new HashMap<>();
	private final static List<Character> validLiterals = Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M');
	private final static List<String> invalidPatterns = new ArrayList<>();
	private final static List<String> IXCM_MORE_THAN_3_TIMES_IN_SUCCESSION = Arrays.asList("IIII", "XXXX", "CCCC",
			"MMMM");

	private final static List<String> REPEATING_DLV = Arrays.asList("DD", "LL", "VV");

	private final static List<String> I_BEFORE_ANYTHING_BUT_VX = Arrays.asList("IL", "IC", "ID", "IM");
	private final static List<String> X_BEFORE_ANYTHING_BUT_LC = Arrays.asList("XD", "XM");
	private final static List<String> V_IS_SUBTRACTABLE = Arrays.asList("VX", "VL", "VC", "VD", "VM");
	private final static List<String> L_IS_SUBTRACTABLE = Arrays.asList("LC", "LD", "LM");
	private final static List<String> D_IS_SUBTRACTABLE = Arrays.asList("DM");

	static {
		invalidPatterns.addAll(IXCM_MORE_THAN_3_TIMES_IN_SUCCESSION);
		invalidPatterns.addAll(REPEATING_DLV);
		invalidPatterns.addAll(I_BEFORE_ANYTHING_BUT_VX);
		invalidPatterns.addAll(X_BEFORE_ANYTHING_BUT_LC);
		invalidPatterns.addAll(V_IS_SUBTRACTABLE);
		invalidPatterns.addAll(L_IS_SUBTRACTABLE);
		invalidPatterns.addAll(D_IS_SUBTRACTABLE);
	}

	static {
		valueMap.put('I', 1);
		valueMap.put('V', 5);
		valueMap.put('X', 10);
		valueMap.put('L', 50);
		valueMap.put('C', 100);
		valueMap.put('D', 500);
		valueMap.put('M', 1000);
	}

	public Integer convert(String romanNumber) {
		if (romanNumber != null && !romanNumber.trim().equals("")) {
			if (containsInvalidCharacter(romanNumber)) {
				return null;
			}
			boolean hasInvalidPattern = invalidPatterns.stream().anyMatch(x -> romanNumber.contains(x));
			if (hasInvalidPattern) {
				return null;
			}

			List<Character> chars = romanNumber.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
			return calcualate(chars);
		}
		return null;
	}

	private Integer calcualate(List<Character> chars) {
		Character prev = null;
		Character curr = null;
		Iterator<Character> itr = chars.iterator();
		Integer sum = 0;
		while (itr.hasNext()) {
			prev = curr;
			curr = itr.next();
			if (valueOf(prev) > valueOf(curr)) {
				sum = sum + valueOf(curr);
			} else {
				sum = sum + (valueOf(curr) - valueOf(prev));
			}
		}
		return sum;
	}

	private Integer valueOf(Character key) {
		if (key == null) {
			return Integer.MAX_VALUE;
		}
		return valueMap.get(key);
	}

	private boolean containsInvalidCharacter(String romanNumber) {
		boolean hasInvalidCharacter = romanNumber.chars().mapToObj(c -> (char) c)
				.anyMatch(x -> !validLiterals.contains(x));
		return hasInvalidCharacter;
	}

}
