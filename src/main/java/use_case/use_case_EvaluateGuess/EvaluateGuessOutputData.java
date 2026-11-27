package use_case.use_case_EvaluateGuess;

import java.util.List;

public class EvaluateGuessOutputData {
    private List<String> feedback;
    private int attemptsRemaining;
    private String gameCondition;
    private List<List<String>> pastGuesses;
    private List<List<String>> pastFeedback;

    public EvaluateGuessOutputData(List<String> feedback, int attemptsRemaining, String gameCondition, List<List<String>> pastGuesses, List<List<String>> pastFeedback) {
        this.feedback = feedback;
        this.attemptsRemaining = attemptsRemaining;
        this.gameCondition = gameCondition;
        this.pastGuesses = pastGuesses;
        this.pastFeedback = pastFeedback;
    }
    public List<String> getFeedback() {
        return feedback;
    }
    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }
    public String getGameCondition() {
        return gameCondition;
    }
    public List<List<String>> getPastGuesses() {
        return pastGuesses;
    }
    public List<List<String>> getPastFeedback() {
        return pastFeedback;
    }
}
