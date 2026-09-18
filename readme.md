# Unique ID Generator
## Project Title
**Unique ID Generator**
## Overview
This project is a simple Java application that demonstrates six different ways of generating identifiers:
1. UUID
2. Timestamp-based ID
3. Sequential ID
4. Hybrid ID
5. Alphanumeric ID
6. Snowflake-style ID

The project also validates input,checks uniqueness,calculates basic statistics,and provides a comparison mode.

The main design choice is to keep the user interface simple.The program is started directly from Command Prompt with command-line arguments.
---
## Features
- Direct Command Prompt execution
- Six ID-generation methods
- Input validation
- Duplicate and uniqueness checking
- Generation-time measurement
- Comparison of all six methods
- Basic validation tests
---
## Technologies Used
- Java
- JDK 8 or newer
- Standard Java libraries
- Command Prompt / PowerShell / Terminal
- Git and GitHub
No external Java library is required.
---
## Project Structure
```
Unique_ID_Generator/
|
├── src/
|   ├── Main.java
|   ├── IDGenerator.java
|   ├── IDMethod.java
|   ├── IDRequest.java
|   ├── GenerationService.java
|   ├── ValidationUtils.java
|   └── ResultAnalyzer.java
|   └── ResultPrinter.java
|
├── tests/
|   └── IDGeneratorTest.java
|
├── README.md
├── statement.md
└── .gitignore
```
---
## Requirements
Install JDK 8 or newer.
Check Java:
```cmd
java -version
```
Check the compiler:
```cmd
javac -version
```
---
## Compile the Project
Open Command Prompt in the project root.
```cmd
javac -d out src/*.java
```
---
## Run the Project
### UUID
```cmd
java -cp out Main uuid 5
```
### Timestamp
```cmd
java -cp out Main timestamp 5
```
### Sequential
```cmd
java -cp out Main sequential 5
```
### Hybrid
```cmd
java -cp out Main hybrid 5
```
### Alphanumeric
Generate 5 IDs, each 8 characters:
```cmd
java -cp out Main alphanumeric 5 8
```
### Snowflake-style
Generate 5 IDs using worker ID 1:
```cmd
java -cp out Main snowflake 5 1
```
---
## Comparison Mode
The project has one small extra feature for evaluation and demonstration.
```cmd
java -cp out Main compare 100
```
This generates the same number of IDs using all six methods and displays:
- method
- number generated
- unique count
- generation time
The timing is dependent on the computer and is intended for simple demonstration rather than scientific benchmarking.
---
## Testing
Compile:
```cmd
javac -d out src/*.java
```
Compile the test:
```cmd
javac -cp out -d out tests/IDGeneratorTest.java
```
Run:
```cmd
java -cp out IDGeneratorTest
```
Expected result:
```text
All validation tests passed.
```
The tests cover:
- UUID format and uniqueness
- sequential generation
- timestamp generation
- alphanumeric length and format
- Snowflake uniqueness
- invalid input handling
---
## Functional Modules
### Module 1-ID Generation
Implements the six generation techniques.
### Module 2-Validation and Request Handling
Validates count, alphanumeric length, and Snowflake worker ID.
### Module 3-Result Analysis
Checks uniqueness, duplicates, average length, and generation time.
### Module 4-Comparison
Provides a simple side-by-side run of all six methods.
---
## Non-Functional Requirements
### Performance
The application should generate normal batches quickly.
### Usability
Commands are short and can be run directly from Command Prompt.
### Reliability
Invalid input is rejected with a readable error message.
### Maintainability
Each class has a clear responsibility.
### Resource Efficiency
Only standard Java libraries are used and generated IDs are held in memory for the current run.
---
## Method Summary
| Method | Main idea | Typical use |
|---|---|---|
| UUID | Random identifier | Distributed references |
| Timestamp | Time + local sequence | Logs and tracking |
| Sequential | Incrementing counter | Local records |
| Hybrid | Time + random + local sequence | Tracking/reference IDs |
| Alphanumeric | Random short code | Coupons and codes |
| Snowflake-style | Time + worker + sequence | Distributed systems |
## Future Enhancements
Possible future improvements include:
- JUnit tests
- GUI interface
- database integration
- JSON output
- configurable prefixes
These are intentionally left as future work so that the current project remains simple.
---
## Author
Yuvank Bhargava 🔗 GitHub: https://github.com/yuvankbhargava82
