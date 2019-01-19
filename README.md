# mgttg (Merchant's Guide To The Galaxy)

## Application build and execute instructions

 - mgttg is a maven project.
 - To setup, unzip the uploaded zip that would create folder named `mgttg`.
 - To build the application, execute `mvn clean package`. The executable jar `mgttg-0.0.1-SNAPSHOT.jar` will be created under `target` folder.
 - To run the application, execute `java -jar target\mgttg-0.0.1-SNAPSHOT.jar <sample file path>`, where `<sample file path>` is the absolute path of input file containing instructions.

## Approach

3 components are conceptualized in this solution - InstructionsProcessor, InstructionParser and RomanNumberConverter.
 
 - `Instruction` represents a line of input. This solution identifies 5 types of instructions - `LiteralDeclaration`, `CreditsDeclaration`, `LiteralQuestion`, `CreditsQuestion` and `InvalidInstruction`.
 - `InstructionParser` parses a line of input, and creates and populates corresponding instruction instance.
 - `RomanNumberConverter` converts a Roman number to its Arabic number equivalent.
 - `InstructionsProcessor` drives the application's execution. When invoked, processor coordinates with parser and converter to generate the expected results. 
 - `ProcessingState` - InstructionsProcessor maintains the intermediate state of execution and results in a object called ProcessingState.
 - `ExecutionContext` instantiates and maintains the 3 components' instances. These instances are utilized for execution of the actual application, as well as for execution of application tests.

## Considerations

 - There could be cases or boundary conditions which might have got missed or are not covered in the implemented solution. For such cases, this solution can be improved upon; starting by adding failing tests for such cases. 
 - Current supported behavior of this application is defined "entirely" by written tests.
 