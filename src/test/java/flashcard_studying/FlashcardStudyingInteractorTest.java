package flashcard_studying;
import use_case.flashcard_studying.FlashcardStudyingInteractor;
import use_case.flashcard_studying.FlashcardStudyingInputBoundary;
import use_case.flashcard_studying.FlashcardStudyingOutputBoundary;
import use_case.flashcard_studying.FlashcardStudyingOutputData;
import use_case.flashcard_studying.FlashcardStudyingInputData;
import data_access.FlashcardDataAccessInterface;
import entities.Deck;
import entities.Card;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlashcardStudyingInteractorTest {

    private static class OutputRecorder implements FlashcardStudyingOutputBoundary {
        public FlashcardStudyingOutputData success;
        public String failure;

        @Override
        public void prepareSuccessView(FlashcardStudyingOutputData data) {
            success = data;
        }

        @Override
        public void prepareFailView(String error) {
            failure = error;
        }

    }

    private static class FakeDAO implements FlashcardDataAccessInterface {

        @Override
        public void saveDeck(Deck deck) {
            return;
        }

        @Override
        public Deck loadDeck(String deckID) {
            return new Deck("hi", new ArrayList<>());
        }

    }
    // test for failure when user does not input a deck and there is no currentDeck
    @Test
    public void emptyDeckTitleTest() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Flip");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNull(interactor.getCurrentDeck());
        assertNotNull(recorder.failure);
        assertEquals(recorder.failure, "You need to input a deck title");

    }
}
