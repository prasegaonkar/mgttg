package mgttg.processor;

import java.util.List;

public interface InstructionsProcessor {
	ProcessingState process(List<String> inputs);
}
