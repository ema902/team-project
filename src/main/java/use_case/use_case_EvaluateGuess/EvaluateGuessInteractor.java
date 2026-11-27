package use_case.use_case_EvaluateGuess;

import entities.Game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EvaluateGuessInteractor implements EvaluateGuessInputBoundary{
    private final EvaluateGuessOutputBoundary presenter;
    private final CandleGameSession candleGameSession;


    public EvaluateGuessInteractor(EvaluateGuessOutputBoundary presenter, CandleGameSession candleGameSession) {
        this.presenter = presenter;
        this.candleGameSession = candleGameSession;
    }

    @Override
    public void execute(EvaluateGuessInputData inputData) {
        CandleGame game = candleGameSession.getGame();
        String guess = inputData.getGuess();
        String secretCode  = game.getSecretCode();
        Guess newGuess = new Guess(guess, secretCode);
        game.addGuess(newGuess);
        List<String> feedback = newGuess.getFeedback().stream()
                .map(Enum::name)
                .collect(Collectors.toList());

        int attemptsRemaining = game.getAttemptsRemaining();
        String gameCondition = game.getGameCondition().name();
        List<List<String>> pastGuesses = new ArrayList<>();
        List<List<String>> pastFeedback = new ArrayList<>();
        for (Guess pastGuess : game.getGuesses()) {
            List<String> digits = new ArrayList<>();
            for (int i=0; i < pastGuess.getGuessedCode().length(); i++) {
                digits.add(String.valueOf(pastGuess.getGuessedCode().charAt(i)));
            }
            List<String> candleTypes = pastGuess.getFeedback().stream().map(Enum::name).collect(Collectors.toList());
            pastGuesses.add(digits);
            pastFeedback.add(candleTypes);
        }

        EvaluateGuessOutputData outputData = new EvaluateGuessOutputData(feedback, attemptsRemaining, gameCondition, pastGuesses, pastFeedback);
        presenter.prepareSuccessView(outputData);
    }
}
