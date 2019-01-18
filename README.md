# mgttg (Merchant's Guide To The Galaxy)

## Application build and execute instructions

 - mgttg is a maven project, unzip the uploaded zip file that would create folder named `mgttg`
 - To build the application, execute `mvn clean package`. The executable jar `mgttg-0.0.1-SNAPSHOT.jar` is created under `target` folder.
 - To run the application, execute `java -jar target\mgttg-0.0.1-SNAPSHOT.jar <sample file path>`

## Approach

3 components are conceptualized in proposed design - InstructionsProcessor, InstructionParser and RomanNumberConverter.
 
 - `Instruction` represents a line of input. For this proposed solution, 5 types of instructions are considered - `LiteralDeclaration`, `CreditsDeclaration`, `LiteralQuestion`, `CreditsQuestion` and `InvalidInstruction`.
 - `InstructionParser` parses a line of input, and creates and populates corresponding instruction instance.
 - `RomanNumberConverter` converts the Roman number to Arabic number.
 - `InstructionsProcessor` processes the input lines and converts it to expected lines of output.
 - `ProcessingState` - InstructionsProcessor is driver component for processing the lines of input. While processing, processor coordinates with parser and converter to process each instruction. It maintains the intermediate state of execution and results in a object called ProcessingState.
 - `ExecutionContext` maintains the instances of the components, to be used for execution of the actual application, as well as for execution of application tests.

## Considerations

 - There could be cases or boundary conditions which might hae got missed or not are covered in the implemented solution. For such cases, this solution can be updated for such improvements, starting by adding failing tests. 
 - The implemented behavior of this solution is defined entirely by written tests - nothing more, nothing less.
 