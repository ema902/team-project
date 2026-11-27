package interface_adapters.candle_game;

import java.util.ArrayList;
import java.util.List;

/**
 * The ViewModel for the Candle Game View.
 */

public class CandleGameViewModel {

    private List<String> candleFeedback;
    private int attemptsRemaining;
    private String gameCondition;
    private List<List<String>> pastGuesses;
    private List<List<String>> pastFeedback;
    private final List<CandleGameViewModelListener> listeners = new ArrayList<>();

    public List<String> getCandleFeedback() {
        return candleFeedback;
    }

    public void setCandleFeedback(List<String> candleFeedback) {
        this.candleFeedback = candleFeedback;
    }

    public List<List<String>> getPastGuesses() {
        return pastGuesses;
    }
    public void setPastGuesses(List<List<String>> pastGuesses) {
        this.pastGuesses = pastGuesses;
    }

    public List<List<String>> getPastFeedback() {
        return pastFeedback;
    }
    public void setPastFeedback(List<List<String>> pastFeedback) {
        this.pastFeedback = pastFeedback;
    }

    public int getAttemptsRemaining(){
        return attemptsRemaining;
    }
    public void setAttemptsRemaining(int attemptsRemaining) {
        this.attemptsRemaining = attemptsRemaining;
    }

    public String getGameCondition() {
        return gameCondition;
    }

    public void setGameCondition(String gameCondition) {
        this.gameCondition = gameCondition;
    }

    /**
     * Registers a listener to be notified when the Evaluate Guess View Model changes.
     * @param listener a View
     */

    public void addListener(CandleGameViewModelListener listener) {
        listeners.add(listener);
    }

    void notifyStateChanged() {
        for (CandleGameViewModelListener listener : listeners) {
            listener.candleGameStateChanged();
        }
    }

}
