package interface_adapters.candle_game;

import use_case.use_case_EvaluateGuess.EvaluateGuessOutputBoundary;
import use_case.use_case_EvaluateGuess.EvaluateGuessOutputData;

import java.util.List;

public class EvaluateGuessPresenter implements EvaluateGuessOutputBoundary {
    private CandleGameViewModel candleGameViewModel;

    public EvaluateGuessPresenter(CandleGameViewModel candleGameViewModel) {
        this.candleGameViewModel = candleGameViewModel;
    }


    @Override
    public void prepareSuccessView(EvaluateGuessOutputData outputData) {
        List<String> feedback = outputData.getFeedback();
        int attemptsRemaining = outputData.getAttemptsRemaining();
        String gameCondition = outputData.getGameCondition();
        List<List<String>> pastGuesses = outputData.getPastGuesses();
        List<List<String>> pastFeedback = outputData.getPastFeedback();
        candleGameViewModel.setCandleFeedback(feedback);
        candleGameViewModel.setPastGuesses(pastGuesses);
        candleGameViewModel.setPastFeedback(pastFeedback);
        candleGameViewModel.setAttemptsRemaining(attemptsRemaining);
        candleGameViewModel.setGameCondition(gameCondition);
        candleGameViewModel.notifyStateChanged();
    }

//    @Override
//    public void prepareFailView(String errorMessage) {
//
//    }
}
