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
            String[] testQuestions = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
            String[] testAnswers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            ArrayList<Card> exampleList1 = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Card c = new Card(testQuestions[i], testAnswers[i]);
                exampleList1.add(c);
            }
            Deck deck1 = new Deck("deck1", exampleList1);

            ArrayList<Card> exampleList2 = new ArrayList<>();
            for (int i = 4; i < 9; i++) {
                Card c = new Card(testQuestions[i], testAnswers[i]);
                exampleList1.add(c);
            }
            Deck deck2 = new Deck("deck2", exampleList2);
            if (deckID.equals("deck1")) {
                return deck1;
            } else if (deckID.equals("deck2")) {
                return deck2;
            }
            throw new IllegalStateException("Could not read file");
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
    // test for failure when you input a deck title and it's not in the DAO
    @Test
    public void notInDAO() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Flip", "X");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNotNull(recorder.failure);
        assertEquals(recorder.failure, "Invalid deck title");

    }
}
