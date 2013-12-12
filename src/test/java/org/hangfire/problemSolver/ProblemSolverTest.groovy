package org.hangfire.problemSolver

import org.hangfire.RunAwayRobotTest
import org.hangfire.attempt.AttemptUtils
import org.hangfire.problem.ProblemFactory
import org.hangfire.source.HttpSource

import static org.hangfire.attempt.Instruction.LEFT
import static org.hangfire.attempt.Instruction.UP

class ProblemSolverTest extends RunAwayRobotTest {

    def ProblemSolver uut

    def setup() {
        uut = new ProblemSolver()
    }

    def test() {
        given: "Problems"
        def problem = theProblem
        println problem
        when: "I solve"
        def attempt = uut.solve(problem)
        println AttemptUtils.parseAttempt(attempt)
        then:
        attempt.instructions == theSolution
        where:
        theProblem                              | theSolution
        /*PROBLEM_FACTORY.fetchProblemForLevel(1) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(2) | [LEFT, UP, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(3) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(4) | [UP, UP, LEFT]
        PROBLEM_FACTORY.fetchProblemForLevel(5) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(6) | [LEFT, UP, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(7) | [UP, UP, UP, LEFT]*/
        //PROBLEM_FACTORY.fetchProblemForLevel(91) | [UP, UP, LEFT, UP, LEFT, UP, UP, UP, LEFT, UP, UP, LEFT, UP, UP, LEFT, LEFT, UP, UP, UP, LEFT, UP, UP, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(96) | [UP, UP, LEFT, UP, LEFT, UP, UP, UP, LEFT, UP, UP, LEFT, UP, UP, LEFT, LEFT, UP, UP, UP, LEFT, UP, UP, UP]
    }

    def "Live test"() {
        given: "A real problem"
        def liveProblemFactory = new ProblemFactory(new HttpSource())
        def problem = liveProblemFactory.fetchProblemForLevel(104)
        println problem
        when: "I solve"
        def attempt = uut.solve(problem)
        println attempt
        println HttpSource.sendSolution(AttemptUtils.parseAttempt(attempt))
        then:
        true
        //http://www.hacker.org/runaway/index.php?name=hangfire&password=empire12&path=RRDRRRRRRRRRRRRRRRRRRRRRRRRRRR
    }


}
