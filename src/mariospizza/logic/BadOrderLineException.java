package mariospizza.logic;

public class BadOrderLineException extends Exception{
    public enum Problem { AMOUNT, PIZZA }
    private Problem problem;

    public BadOrderLineException(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }
    
    
}
