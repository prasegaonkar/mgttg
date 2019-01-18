package guideToGalaxy.config;

import guideToGalaxy.convertor.RomanNumberConvertor;
import guideToGalaxy.convertor.ToArabicConvertor;
import guideToGalaxy.parser.InstructionParser;
import guideToGalaxy.parser.MGTTGInstructionParser;
import guideToGalaxy.processor.InstructionsProcessor;
import guideToGalaxy.processor.MGTTGInstructionsProcessor;

public class ExecutionContext {

	public static InstructionsProcessor processor;
	public static InstructionParser parser;
	public static RomanNumberConvertor convertor;

	public static void setup() {
		parser = new MGTTGInstructionParser();
		convertor = new ToArabicConvertor();
		processor = new MGTTGInstructionsProcessor(parser, convertor);
	}

}
