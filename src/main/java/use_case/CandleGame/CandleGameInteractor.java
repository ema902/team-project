package use_case.CandleGame;

import entities.Game.CandleGame;
import entities.Game.CandleGameSession;
import entities.Game.GameCondition;
import use_case.SecretCodeGenerator.SecretCodeGenerator;

/**
 * Implements the Candle Game use case.
 */

public class CandleGameInteractor implements CandleGameInputBoundary {
    private final CandleGameSession candleGameSession;
//    private CandleGame game;
    private final SecretCodeGenerator secretCodeGenerator;
    private final CandleGameOutputBoundary presenter;

    public CandleGameInteractor(CandleGameSession candleGameSession, SecretCodeGenerator secretCodeGenerator,
                                CandleGameOutputBoundary presenter) {
        this.candleGameSession = candleGameSession;
        this.secretCodeGenerator = secretCodeGenerator;
        this.presenter = presenter;
    }

    @Override
    public void initGame() {
        String secretCode =  secretCodeGenerator.generate();
        CandleGame game = new CandleGame(secretCode, 10);
        candleGameSession.setGame(game);

        CandleGameOutputData outputData = new CandleGameOutputData(
                game.getMaxTries(),
                game.getGameCondition(),
                game.getAttemptedTries());

        presenter.prepareGameView(outputData );
    }

    @Override
    public void quitGame() {
        CandleGame game = candleGameSession.getGame();
        game.setGameCondition(GameCondition.QUIT);
        presenter.prepareBreakView();
    }

    @Override
    public void showInstructions() {
        presenter.prepareInstructionsView();

    }

    @Override
    public void timerExpired(){
        CandleGame game = candleGameSession.getGame();
        game.setGameCondition(GameCondition.TIME_ENDED);
        presenter.prepareFocusView();
    }

}
