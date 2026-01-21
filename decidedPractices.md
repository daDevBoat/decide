# Decided practice conventions in the group

- We use **java 25** as our java version
- We make a new branch for each issue - it should be name `issue/x`, where x is the issue number
- All commits are linked to an issue describing the feature / commit. Use `#x` to reference or `close #x` to reference and close the issue in github through your commit message. **Include the reference in the end** of the commit message.
- Every commit is an *atomic bug fix or feature*, with a clear commit message. We use the prefix convention where the commit messages starts with for example **"feat", "fix", "doc", "refactor"**). A link describing the used prefix convention is given by: https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13
- The commit contents reflect the commit message, **every bug fix or feature commit contains a test** (i.e., adds at least one test or modifies at least one existing test).
- The commits are well balanced among group members
- We use JUnit for testing
