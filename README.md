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

### [Elias Richard Næss] — GitHub: [@daDevBoat]

Elias was reponsible for the implementation of **LIC 1, 7, and 13**. These methods use amongst other methods the **Point.distance()** and **Point.circleRadius** which he also was responsible for implementing. He also implemented the **Matrix class** and all its methods except for the **evalLine** method, and he also implemented the **Cond enum**. For all methods he created he was also responsible for the corresponding unit tests. He also was resposible (with collaberation with the rest of the group) for setting up the folder structure and the gradle wrapper build and the build file. 

### [Arnau Pelechano García] — GitHub: [@arpega75]

Arnau was responsible for the implementation of **LICs 3, 9 and 12**, including the implementation of their corresponding Unit Tests. He created the **Parameters class**. In addition, he implemented the **CMV function and the PUM function**, together with their respective Unit Tests. For the development of the LICs, he implemented in the Point class the **triangleArea and directedAngle methods**, as well as their respective Unit Tests.

### [Jannis Name] — GitHub: [@JannisHaeffner]

Jannis was responsible for the implementation of **LIC 0, 6 and 10**, and the setup of the **Point.distancePointToLine** function together with the corresponding Unit Tests. He also implemented the **PUV function** and the **matrix line evaluation**, together with the corresponding tests. In addition, he set up the auto build and test for GitHub, that allowed the team to work in a continious integration way-of-working, where new changes are merged into main, as soon as they are completed. He was also responsible for parts of the documentation.

### [Jonte Name] — GitHub: [@username]

## License: MIT (see LICENSE)
