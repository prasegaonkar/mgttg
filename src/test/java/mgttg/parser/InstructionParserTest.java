package mgttg.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import mgttg.config.ExecutionContext;
import mgttg.instructions.CreditsDeclarationInstruction;
import mgttg.instructions.CreditsQuestionInstruction;
import mgttg.instructions.Instruction;
import mgttg.instructions.InstructionType;
import mgttg.instructions.LiteralDeclarationInstruction;
import mgttg.instructions.LiteralQuestionInstruction;
import mgttg.parser.InstructionParser;

public class InstructionParserTest {
	private InstructionParser parser = ExecutionContext.parser;

	@BeforeClass
	public static void setupContext() {
		ExecutionContext.setup();
	}

	@Test
	public void nullInputIsInvalidInstruction() {
		validateType(null, InstructionType.INVALID);
	}

	@Test
	public void blankInputIsInvalidInstruction() {
		validateType("", InstructionType.INVALID);
	}

	@Test
	public void gibberishInputIsInvalidInstruction() {
		validateType("gibberish input", InstructionType.INVALID);
	}

	@Test
	public void testLiteralDeclarationForInvalidLength() {
		validateType("glob is I J", InstructionType.INVALID);
		validateType("glob is ", InstructionType.INVALID);
	}

	@Test
	public void testLiteralDeclarationWithValidLiterals() {
		validateType("glob is I", InstructionType.LITERAL_DECLARATION);
		validateType("glob is V", InstructionType.LITERAL_DECLARATION);
		validateType("glob is X", InstructionType.LITERAL_DECLARATION);
		validateType("glob is L", InstructionType.LITERAL_DECLARATION);
		validateType("glob is C", InstructionType.LITERAL_DECLARATION);
		validateType("glob is D", InstructionType.LITERAL_DECLARATION);
		validateType("glob is M", InstructionType.LITERAL_DECLARATION);
	}

	@Test
	public void testLiteralDeclarationWithInvalidLiterals() {
		validateType("glob is A", InstructionType.INVALID);
	}

	@Test
	public void testCreditsDeclarationForValidPattern() {
		validateType("glob glob Silver is 34 Credits", InstructionType.CREDITS_DECLARATION);
	}

	@Test
	public void testCreditsDeclarationForInvalidPatterns() {
		validateType("glob glob Silver is xx Credits", InstructionType.INVALID);
		validateType("glob glob Silver is 34 Credit", InstructionType.INVALID);
		validateType("glob glob Titanium is 34 Credits", InstructionType.INVALID);
	}

	@Test
	public void testLiteralsQuestionForValidPattern() {
		validateType("how much is pish tegj glob glob ?", InstructionType.LITERAL_QUESTION);
	}

	@Test
	public void testLiteralsQuestionForInvalidPattern() {
		validateType("how much is pish tegj glob glob?", InstructionType.INVALID);
		validateType("much is pish tegj glob glob?", InstructionType.INVALID);
		validateType("how much is ?", InstructionType.INVALID);
		validateType("how much is pish tegj glob glob ??", InstructionType.INVALID);
	}

	@Test
	public void testCreditsQuestionForValidPattern() {
		validateType("how many Credits is glob prok Silver ?", InstructionType.CREDITS_QUESTION);
	}

	@Test
	public void testCreditsQuestionForInvalidPattern() {
		validateType("how many Credits is glob prok Silver?", InstructionType.INVALID);
		validateType("many Credits is glob prok Silver ?", InstructionType.INVALID);
		validateType("how many Credits is Silver ?", InstructionType.INVALID);
		validateType("how many Credits is glob prok Silver ??", InstructionType.INVALID);
	}

	@Test
	public void validateSampleInputForInstructionType() {
		validateType("glob is I", InstructionType.LITERAL_DECLARATION);
		validateType("prok is V", InstructionType.LITERAL_DECLARATION);
		validateType("pish is X", InstructionType.LITERAL_DECLARATION);
		validateType("tegj is L", InstructionType.LITERAL_DECLARATION);
		validateType("glob glob Silver is 34 Credits", InstructionType.CREDITS_DECLARATION);
		validateType("glob prok Gold is 57800 Credits", InstructionType.CREDITS_DECLARATION);
		validateType("pish pish Iron is 3910 Credits", InstructionType.CREDITS_DECLARATION);
		validateType("how much is pish tegj glob glob ?", InstructionType.LITERAL_QUESTION);
		validateType("how many Credits is glob prok Silver ?", InstructionType.CREDITS_QUESTION);
		validateType("how many Credits is glob prok Gold ?", InstructionType.CREDITS_QUESTION);
		validateType("how many Credits is glob prok Iron ?", InstructionType.CREDITS_QUESTION);
		validateType("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?",
				InstructionType.INVALID);
	}

	@Test
	public void validateSampleInputForParsedInstruction() {
		validateLiteralDeclarationInstruction("glob is I", "glob", 'I');
		validateLiteralDeclarationInstruction("prok is V", "prok", 'V');
		validateLiteralDeclarationInstruction("pish is X", "pish", 'X');
		validateLiteralDeclarationInstruction("tegj is L", "tegj", 'L');
		validateCreditsDeclarationInstruction("glob glob Silver is 34 Credits", "Silver", 34, "glob", "glob");
		validateCreditsDeclarationInstruction("glob prok Gold is 57800 Credits", "Gold", 57800, "glob", "prok");
		validateCreditsDeclarationInstruction("pish pish Iron is 3910 Credits", "Iron", 3910, "pish", "pish");
		validateLiteralQuestionInstruction("how much is pish tegj glob glob ?", "pish", "tegj", "glob", "glob");
		validateCreditsQuestionInstruction("how many Credits is glob prok Silver ?", "Silver", "glob", "prok");
		validateCreditsQuestionInstruction("how many Credits is glob prok Gold ?", "Gold", "glob", "prok");
		validateCreditsQuestionInstruction("how many Credits is glob prok Iron ?", "Iron", "glob", "prok");
	}

	private void validateType(String input, InstructionType expectedType) {
		assertThat(parser.parse(input).getType()).isEqualTo(expectedType);
	}

	private void validateCreditsQuestionInstruction(String input, String metalType, String... aliases) {
		Instruction instruction = parser.parse(input);
		assertThat(instruction).isInstanceOf(CreditsQuestionInstruction.class);
		CreditsQuestionInstruction cqi = ((CreditsQuestionInstruction) instruction);
		assertThat(cqi.getMetalType()).isEqualTo(metalType);
		assertThat(cqi.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateLiteralQuestionInstruction(String input, String... aliases) {
		Instruction instruction = parser.parse(input);
		assertThat(instruction).isInstanceOf(LiteralQuestionInstruction.class);
		LiteralQuestionInstruction lqi = ((LiteralQuestionInstruction) instruction);
		assertThat(lqi.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateCreditsDeclarationInstruction(String input, String metalType, int creditValue,
			String... aliases) {
		Instruction instruction = parser.parse(input);
		assertThat(instruction).isInstanceOf(CreditsDeclarationInstruction.class);
		CreditsDeclarationInstruction cdi = ((CreditsDeclarationInstruction) instruction);
		assertThat(cdi.getMetalType()).isEqualTo(metalType);
		assertThat(cdi.getCreditValue()).isEqualTo(creditValue);
		assertThat(cdi.getAliases()).isEqualTo(Arrays.asList(aliases));
	}

	private void validateLiteralDeclarationInstruction(String input, String alias, Character literal) {
		Instruction instruction = parser.parse(input);
		assertThat(instruction).isInstanceOf(LiteralDeclarationInstruction.class);
		LiteralDeclarationInstruction ldi = ((LiteralDeclarationInstruction) instruction);
		assertThat(ldi.getLiteral()).isEqualTo(literal);
		assertThat(ldi.getLiteralAlias()).isEqualTo(alias);
	}
}
