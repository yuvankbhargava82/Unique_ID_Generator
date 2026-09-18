# Problem Statement
Different applications require unique IDs for records such as users, orders, transactions, tickets, and logs. A single ID-generation method is not suitable for every situation. There is a need for a simple and reliable system that demonstrates multiple ID-generation techniques and allows users to generate and compare them easily.

# Scope of the Project
This project is a command-line based **Unique ID Generator** developed in Java. The scope covers three major functional areas: **ID Generation** using six different methods, **Input Validation and Request Handling**, and **Result Analysis** for checking uniqueness and basic generation statistics. The system is designed to run directly from the Command Prompt without requiring a graphical interface or database.

# Target Users
* **Students:** To learn and practice different ID-generation techniques.
* **Developers:** To generate IDs for applications, records, and testing.
* **System Designers:** To choose a suitable ID-generation method for different requirements.

# High-Level Features
1. **Command-Line Execution:** Runs directly through Command Prompt using simple commands.
2. **Multiple ID Generation Methods:** Supports UUID, Timestamp, Sequential, Hybrid, Alphanumeric, and Snowflake-style IDs.
3. **Input Validation:** Checks the number of IDs, alphanumeric length, and Snowflake worker ID before generation.
4. **Result Analysis:** Displays unique IDs, duplicates, average length, and generation time.
5. **Comparison Mode:** Allows the six generation methods to be compared using the same batch size.
6. **Basic Testing:** Includes validation tests for generation, uniqueness, formatting, and invalid input.
