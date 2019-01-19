package mgttg.config;

import mgttg.converter.RomanNumberConverter;
import mgttg.converter.RomanToArabicNumberConverter;
import mgttg.parser.InstructionParser;
import mgttg.parser.MGTTGInstructionParser;
import mgttg.processor.InstructionsProcessor;
import mgttg.processor.MGTTGInstructionsProcessor;

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
