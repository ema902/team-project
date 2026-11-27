package use_case.use_case_EvaluateGuess;

public class EvaluateGuessInputData {
    private String guess;

    public EvaluateGuessInputData(String guess) {
        this.guess = guess;
    }

    /**
     * Returns the guessed code.
     * @return the guessed code .
     */
    public String getGuess() {
        return guess;
    }

}
