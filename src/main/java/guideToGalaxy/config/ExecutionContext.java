package guideToGalaxy.config;

import guideToGalaxy.converter.RomanNumberConverter;
import guideToGalaxy.converter.RomanToArabicNumberConverter;
import guideToGalaxy.parser.InstructionParser;
import guideToGalaxy.parser.MGTTGInstructionParser;
import guideToGalaxy.processor.InstructionsProcessor;
import guideToGalaxy.processor.MGTTGInstructionsProcessor;

public class ExecutionContext {

	public static InstructionsProcessor processor;
	public static InstructionParser parser;
	public static RomanNumberConverter converter;

	public static void setup() {
		parser = new MGTTGInstructionParser();
		converter = new RomanToArabicNumberConverter();
		processor = new MGTTGInstructionsProcessor(parser, converter);
	}

}
