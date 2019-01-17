package guideToGalaxy.processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import guideToGalaxy.parser.MGTTGInstructionParser;

public class InstructionsProcessorTest {
	private InstructionsProcessor processor;

	@Before
	public void setup() {
		processor = new InstructionsProcessor(new MGTTGInstructionParser());
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
		List<String> lines = readSampleFile(sampleFile);
		assertThat(lines.size()).isEqualTo(12);
		ProcessingState state = processor.process(lines);
		assertThat(state).isNotNull();
		assertThat(state.getAliasOfLiteral_C()).isNull();
		assertThat(state.getAliasOfLiteral_D()).isNull();
		assertThat(state.getAliasOfLiteral_I()).isEqualTo("glob");
		assertThat(state.getAliasOfLiteral_L()).isEqualTo("tegj");
		assertThat(state.getAliasOfLiteral_M()).isNull();
		assertThat(state.getAliasOfLiteral_V()).isEqualTo("prok");
		assertThat(state.getAliasOfLiteral_X()).isEqualTo("pish");
		final Map<String, BigDecimal> metalPerUnitCreditValue = state.getMetalPerUnitCreditValue();
		assertThat(metalPerUnitCreditValue.size()).isEqualTo(3);
		assertThat(metalPerUnitCreditValue.get("Silver")).isEqualTo(17);
		assertThat(metalPerUnitCreditValue.get("Gold")).isEqualTo(57800 / 4);
		assertThat(metalPerUnitCreditValue.get("Iron")).isEqualTo(3910 / 20);
		final List<String> responses = state.getResponses();
		assertThat(responses).isNotNull();
		assertThat(responses.size()).isEqualTo(5);
		assertThat(responses.get(0)).isEqualTo("pish tegj glob glob is 42");
		assertThat(responses.get(1)).isEqualTo("glob prok Silver is 68 Credits");
		assertThat(responses.get(2)).isEqualTo("glob prok Gold is 57800 Credits");
		assertThat(responses.get(3)).isEqualTo("glob prok Iron is 782 Credits");
		assertThat(responses.get(4)).isEqualTo("I have no idea what you are talking about");
	}

	private List<String> readSampleFile(final String sampleFile) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(sampleFile).toURI());
		List<String> lines = Files.lines(path).collect(Collectors.toList());
		return lines;
	}
}
