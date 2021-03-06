package mgttg.processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import mgttg.config.ExecutionContext;
import mgttg.processor.InstructionsProcessor;
import mgttg.processor.ProcessingState;

public class InstructionsProcessorTest {
	private InstructionsProcessor processor = ExecutionContext.processor;

	@BeforeClass
	public static void setupContext() {
		ExecutionContext.setup();
	}

	@Test
	public void testNoInstructions() {
		ProcessingState state = processor.process(null);
		assertThat(state).isNull();
	}

	@Test
	public void testEmptyInstructions() {
		ProcessingState state = processor.process(new ArrayList<>());
		assertThat(state).isNull();
	}

	@Test
	public void validateSampleInput() throws Exception {
		final String sampleFile = "sample.txt";
		List<String> lines = readFile(sampleFile);
		assertThat(lines.size()).isEqualTo(12);
		ProcessingState state = processor.process(lines);
		assertThat(state).isNotNull();
		final Map<String, Character> aliasLiteralMappings = state.getAliasLiteralMappings();
		assertThat(aliasLiteralMappings).isNotNull();
		assertThat(aliasLiteralMappings.get("glob")).isEqualTo('I');
		assertThat(aliasLiteralMappings.get("tegj")).isEqualTo('L');
		assertThat(aliasLiteralMappings.get("prok")).isEqualTo('V');
		assertThat(aliasLiteralMappings.get("pish")).isEqualTo('X');
		assertThat(aliasLiteralMappings.values()).contains('I', 'L', 'V', 'X');
		assertThat(aliasLiteralMappings.keySet()).contains("glob", "tegj", "prok", "pish");
		final Map<String, BigDecimal> metalPerUnitCreditValue = state.getMetalPerUnitCreditValue();
		assertThat(metalPerUnitCreditValue).isNotNull();
		assertThat(metalPerUnitCreditValue.size()).isEqualTo(3);
		assertThat(metalPerUnitCreditValue.get("Silver")).isEqualByComparingTo(new BigDecimal(17));
		assertThat(metalPerUnitCreditValue.get("Gold")).isEqualByComparingTo(new BigDecimal(14450));
		assertThat(metalPerUnitCreditValue.get("Iron")).isEqualByComparingTo(new BigDecimal(195.5));
		final List<String> responses = state.getResponses();
		assertThat(responses).isNotNull();
		assertThat(responses.size()).isEqualTo(5);
		assertThat(responses.get(0)).isEqualTo("pish tegj glob glob is 42");
		assertThat(responses.get(1)).isEqualTo("glob prok Silver is 68 Credits");
		assertThat(responses.get(2)).isEqualTo("glob prok Gold is 57800 Credits");
		assertThat(responses.get(3)).isEqualTo("glob prok Iron is 782 Credits");
		assertThat(responses.get(4)).isEqualTo("I have no idea what you are talking about");
	}

	@Test
	public void validateNegativeSampleInput() throws Exception {
		final String sampleFile = "negativeSample.txt";
		List<String> lines = readFile(sampleFile);
		ProcessingState state = processor.process(lines);
		assertThat(state).isNotNull();
		final List<String> responses = state.getResponses();
		assertThat(responses).isNotNull();
		assertThat(responses.size()).isEqualTo(7);
		assertThat(responses.get(0)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(1)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(2)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(3)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(4)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(5)).isEqualTo("I have no idea what you are talking about");
		assertThat(responses.get(6)).isEqualTo("I have no idea what you are talking about");
	}

	private List<String> readFile(final String file) throws Exception {
		Path path = Paths.get(getClass().getClassLoader().getResource(file).toURI());
		List<String> lines = Files.lines(path).collect(Collectors.toList());
		return lines;
	}
}
