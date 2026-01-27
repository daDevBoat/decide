# Decided work conventions in the group:

## Versions:
- We use **java 25**
- We use **Junit 5.10.1** as our test framework

## Way of working:
0) Create an issue if a task is identified
    - Every issue should have a useful description

1) Make a new branch for every issue 
    - The branch should be name `issue/x`, where x is the issue number

2) Commit all work regarding issue `x` to branch `issue/x`
    - **Every** bug fix or feature commit contains a test
    - **Every** commit reflects the commit message
    - **Every** commit message should link to the issue by having `#x` in the end.
    - **Every** commit message should start with a prefix like **"feat", "fix", "doc", "refactor"**, find more [here](https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13).

3) When the work is done, **rebase** the issue branch on main 
    - **squash** all commits into one
    - Have a commit message summarizing all the commits, include the *close trigger* for the issue `closes #x`

4) Push the branch to GitHub

5) If all tests pass on GitHub create a pull request

