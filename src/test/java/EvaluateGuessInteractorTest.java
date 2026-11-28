import entities.Game.CandleGame;
import entities.Game.CandleGameSession;
import org.junit.Test;
import use_case.use_case_EvaluateGuess.EvaluateGuessInputData;
import use_case.use_case_EvaluateGuess.EvaluateGuessInteractor;
import use_case.use_case_EvaluateGuess.EvaluateGuessOutputBoundary;
import use_case.use_case_EvaluateGuess.EvaluateGuessOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EvaluateGuessInteractorTest {

    @Test
    public void testExecuteWON(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("12345");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(9, output[0].getAttemptsRemaining());
        assertEquals("WON", output[0].getGameCondition());
        assertEquals(1, output[0].getPastGuesses().size());
        assertEquals(1, output[0].getPastFeedback().size());

    }

    @Test
    public void testExecuteONGOING(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("67890");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(9, output[0].getAttemptsRemaining());
        assertEquals("ON_GOING", output[0].getGameCondition());
        assertEquals(1, output[0].getPastGuesses().size());
        assertEquals(1, output[0].getPastFeedback().size());

    }

    @Test
    public void testExecuteLOSS(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 1);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("67890");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(0, output[0].getAttemptsRemaining());
        assertEquals("LOSS", output[0].getGameCondition());
        assertEquals(1, output[0].getPastGuesses().size());
        assertEquals(1, output[0].getPastFeedback().size());

    }

    @Test
    public void testExecuteFEEDBACKSNUFF(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("67890");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(Arrays.asList("SNUFF", "SNUFF", "SNUFF", "SNUFF", "SNUFF"), output[0].getFeedback());

    }

    @Test
    public void testExecuteFEEDBACKDIM(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("54231");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(Arrays.asList("DIM", "DIM", "DIM", "DIM", "DIM"), output[0].getFeedback());

    }

    @Test
    public void testExecuteFEEDBACKMIX(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);
        EvaluateGuessInputData inputData = new EvaluateGuessInputData("01329");

        interactor.execute(inputData);
        assertNotNull(output[0]);
        assertEquals(Arrays.asList("SNUFF", "DIM", "BRIGHT", "DIM", "SNUFF"), output[0].getFeedback());

    }
    @Test
    public void testExecutePastGuesses(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);


        interactor.execute(new EvaluateGuessInputData("67890"));
        interactor.execute(new EvaluateGuessInputData("12340"));
        assertNotNull(output[0]);
        assertEquals(2, output[0].getPastGuesses().size());
        assertEquals(Arrays.asList("6", "7", "8", "9", "0"), output[0].getPastGuesses().get(0));
        assertEquals(Arrays.asList("1", "2", "3", "4", "0"), output[0].getPastGuesses().get(1));

    }

    @Test
    public void testExecutePastFeedback(){
        CandleGameSession session = new CandleGameSession();
        CandleGame game = new CandleGame("12345", 10);
        session.setGame(game);

        EvaluateGuessOutputData[] output = new EvaluateGuessOutputData[1];
        EvaluateGuessOutputBoundary presenter = new EvaluateGuessOutputBoundary() {

            @Override
            public void prepareSuccessView(EvaluateGuessOutputData outputData) {
                output[0] = outputData;
            }

        };

        EvaluateGuessInteractor interactor = new EvaluateGuessInteractor(presenter, session);


        interactor.execute(new EvaluateGuessInputData("67890"));
        interactor.execute(new EvaluateGuessInputData("12340"));
        assertNotNull(output[0]);
        assertEquals(2, output[0].getPastGuesses().size());
        assertEquals(Arrays.asList("SNUFF", "SNUFF", "SNUFF", "SNUFF", "SNUFF"), output[0].getPastFeedback().get(0));
        assertEquals(Arrays.asList("BRIGHT", "BRIGHT", "BRIGHT", "BRIGHT", "SNUFF"), output[0].getPastFeedback().get(1));

    }


}
