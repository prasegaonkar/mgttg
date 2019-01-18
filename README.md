# Approach

3 components are conceptualized in proposed design - InstructionsProcessor, InstructionParser and RomanNumberConverter.

 - `Instruction` represents a line of input. For this proposed solution, 5 types of instructions are considered - `LiteralDeclaration`, `CreditsDeclaration`, `LiteralQuestion`, `CreditsQuestion` and `InvalidInstruction`.
 - `InstructionParser` parses a line of input, and creates and populates corresponding instruction instance.
 - `RomanNumberConverter` converts the Roman number to Arabic number.
 - `InstructionsProcessor` processes the input lines and converts it to expected lines of output.
 - `ProcessingState` - InstructionsProcessor is driver component for processing the lines of input. While processing, processor coordinates with parser and converter to process each instruction. It maintains the intermediate state of execution and results in a object called ProcessingState.
