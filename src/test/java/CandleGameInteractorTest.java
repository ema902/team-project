import entities.Game.GameCondition;
import entities.Game.CandleGameSession;

import org.junit.jupiter.api.Test;
import use_case.CandleGame.CandleGameInputBoundary;
import use_case.CandleGame.CandleGameInteractor;
import use_case.CandleGame.CandleGameOutputBoundary;
import use_case.CandleGame.CandleGameOutputData;
import use_case.CandleGame.FixedSecretCodeGen;


import static org.junit.jupiter.api.Assertions.*;


public class CandleGameInteractorTest {

    @Test
    void successTest() {
        CandleGameSession candleGameSession = new CandleGameSession(); // initialize the session with no game in it yet
        CandleGameOutputBoundary presenter = //fake presenter for testing purposes
                new CandleGameOutputBoundary() {
                    @Override
                    public void prepareGameView(CandleGameOutputData outputData) {
                        assertEquals(10,outputData.getMaxTries()); // check if new game allow 10 max tries
                        assertEquals(GameCondition.ON_GOING, outputData.getGameCondition()); // game start on going
                    }

                    @Override
                    public void prepareBreakView() {
                        assertEquals(GameCondition.QUIT, candleGameSession.getGame().getGameCondition());
                        // check game condition in game session because it lives there

                    }

                    @Override
                    public void prepareFocusView() {
                        assertEquals(GameCondition.TIME_ENDED, candleGameSession.getGame().getGameCondition());

                    }

                    @Override
                    public void prepareInstructionsView() {


                    }


                };
        CandleGameInputBoundary interactor = new CandleGameInteractor(
                candleGameSession, new FixedSecretCodeGen("12345"), presenter);
        interactor.initGame();
        assertEquals("12345", candleGameSession.getGame().getSecretCode()); // check if game is started and has the correct secret code.


    }
}
