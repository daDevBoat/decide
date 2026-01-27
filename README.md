# Decide - Launch Interceptor Program

## Overview
This project implements a program that decides whether an interceptor should be launched for a hypothetical anti-ballistic missile system based on 2-dimensional radar tracking information. 

The decision is made by calculating whether the relevant Launch Interceptor Conditions (LICs) for the particular launch determination are satisified, if they are the interceptor is launched. The program is implemented according to the "Launch Interceptor Program: Requirements Specification" by authors J. C. Knight and N. G. Leveson
(adapted by John Regehr and Martin Monperrus), June 16, 2016, version 5.

The repository includes:
- Implementation of all LICs and final decision function
- Unit tests for each LIC contract and final decision function
- Gradle build configuration and wrapper
- Documentation (README and decided practices)

## How to Run
### Prerequisites 
- Java 25

### Run the program

**On Windows:**
.\gradlew run

**On macOS/Linux:**
./gradlew run

## Way-of-working:
We agreed to follow a continuous integration workstyle, where for every feature or fix identified, we create an issue. Every issue is its own branch. After the work on an issue is completed, the issue branch will be rebased, the commits will be squashed, and then merged with main. This allows the main branch to have an easily understood commit history, with each commit representing one issue, while also allowing for tracing errors or bugs to a specific commit. When comparing our way-of-working to the Essence checklist, we fullfill most criterias of the state In Place and one of Working well. Our practices and tools are available to all team members through our ReadMe, the decidedPractices file, and the gradle-wrapper, and are used by the whole team. The progress has been mostly as planned, while all team members follow our agreed way-of-working. We could still improve a little in the inspection and accurate adaptation of the way-of-working, but with every working day, the accuracy with which we follow the way-of-working increases. To reach the next step, we all need to become more accustomed to the agreed-upon practices, such as creating an issue for every bug or feature, so that we follow them naturally without thinking about it. We should also have regular meetings where we discuss if the current way-of-working is still optimal or if we should tune the practices and tools. 

## Statement of Contributions

### [Alexander Mannertorn] — GitHub: [@knasssss]

### [Elias Lastname] — GitHub: [@username]

### [Arnau Name] — GitHub: [@username]

### [Jannis Name] — GitHub: [@username] 

### [Jonte Name] — GitHub: [@username]

## LICENSE

The license used in this project is the MIT license.

Copyright (c) 2026 The Project Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.