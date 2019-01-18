package guideToGalaxy;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import guideToGalaxy.config.ExecutionContext;
import guideToGalaxy.processor.ProcessingState;

public class App {
	static {
		ExecutionContext.setup();
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Specify input file path as command line argument");
		} else {
			new App().run(args);
		}
	}

	private void run(String... args) throws Exception {
		final String inputFilePath = args[0];
		final List<String> output = guideMe(inputFilePath);
		output.forEach(System.out::println);
	}

	private List<String> guideMe(final String inputFilePath) throws Exception {
		final List<String> inputs = Files.lines(Paths.get(inputFilePath)).collect(Collectors.toList());
		ProcessingState process = ExecutionContext.processor.process(inputs);
		return process.getResponses();
	}

}
