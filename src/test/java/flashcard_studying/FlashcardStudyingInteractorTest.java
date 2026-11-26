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

    public static String[] testQuestions = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    public static String[] testAnswers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
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

            ArrayList<Card> exampleList1 = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Card c = new Card(testQuestions[i], testAnswers[i]);
                exampleList1.add(c);
            }
            Deck deck1 = new Deck("deck1", exampleList1);

            ArrayList<Card> exampleList2 = new ArrayList<>();
            for (int i = 4; i < 6; i++) {
                Card c = new Card(testQuestions[i], testAnswers[i]);
                exampleList2.add(c);
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
    // test for failure on buttons other than start when user does not input a deck and there is no currentDeck
    @Test
    public void wrongButtonNoDeckTest() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Flip");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNull(interactor.getCurrentDeck());
        assertNotNull(recorder.failure);
        assertEquals(recorder.failure, "You need to input a deck title");

    }

    // test for failure when user presses start button and doesn't enter a deck
    @Test
    public void startButtonNoDeckTest() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Start");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNull(interactor.getCurrentDeck());
        assertNotNull(recorder.failure);
        assertEquals(recorder.failure, "You need to input a deck title");

    }
    // test for failure when you press start, input a deck title and it's not in the DAO
    @Test
    public void notInDAO() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Start", "X");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNotNull(recorder.failure);
        assertEquals(recorder.failure, "Invalid deck title");

    }
    // test that current deck is correctly assigned when deck title is valid
    @Test
    public void inDAO() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Start", "deck2");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        // should return a valid question (not an answer)
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions2(recorder.success.getText()));
        // should assign a currentDeck to interactor
        assertEquals(interactor.getCurrentDeck().getTitle(), "deck2");
        assertTrue(interactor.getCurrentDeck().getStudying());

    }

    // test that flip successfully flips to answer side
    // but when you call on the card again, it shows the answer side first
    @Test
    public void flipAndShowQuestionTest() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Start", "deck2");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions2(recorder.success.getText()));

        FlashcardStudyingInputData inputData2 = new FlashcardStudyingInputData("Flip");
        interactor.execute(inputData2);
        assertNotNull(recorder.success);
        assertTrue(inTestAnswers2(recorder.success.getText()));
        // get a new card
        FlashcardStudyingInputData inputData3 = new FlashcardStudyingInputData("Still learning");
        interactor.execute(inputData3);
        // should return to old card
        FlashcardStudyingInputData inputData4 = new FlashcardStudyingInputData("Still learning");
        interactor.execute(inputData4);
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions2(recorder.success.getText()));

    }

    // test that "knew it" successfully reduces the size of the deck until 0
    @Test
    public void knewItUntilEmpty() {
        FlashcardStudyingInputData inputData = new FlashcardStudyingInputData("Start", "deck1");
        FlashcardDataAccessInterface userRepository = new FakeDAO();
        OutputRecorder recorder = new OutputRecorder();

        FlashcardStudyingInteractor interactor = new FlashcardStudyingInteractor(recorder, userRepository);
        interactor.execute(inputData);
        assertNotNull(recorder.success);

        FlashcardStudyingInputData inputData2 = new FlashcardStudyingInputData("Knew it");
        interactor.execute(inputData2);
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions1(recorder.success.getText()));

        FlashcardStudyingInputData inputData3 = new FlashcardStudyingInputData("Knew it");
        interactor.execute(inputData3);
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions1(recorder.success.getText()));

        FlashcardStudyingInputData inputData4 = new FlashcardStudyingInputData("Knew it");
        interactor.execute(inputData4);
        assertNotNull(recorder.success);
        assertTrue(inTestQuestions1(recorder.success.getText()));
        // at the end of the deck, should return error message
        FlashcardStudyingInputData inputData5 = new FlashcardStudyingInputData("Knew it");
        interactor.execute(inputData5);
        assertEquals(recorder.failure, "All done!");
    }

    private boolean inTestQuestions2(String question) {
        ArrayList<String> example = new ArrayList<>();
        for (int i = 4; i < 6; i++) {
            example.add(testQuestions[i]);
        }
        return example.contains(question);
    }

    private boolean inTestAnswers2(String answer) {
        ArrayList<String> example = new ArrayList<>();
        for (int i = 4; i < 6; i++) {
            example.add(testAnswers[i]);
        }
        return example.contains(answer);
    }

    private boolean inTestQuestions1(String question) {
        ArrayList<String> example = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            example.add(testQuestions[i]);
        }
        return example.contains(question);
    }

    private boolean inTestAnswers1(String answer) {
        ArrayList<String> example = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            example.add(testAnswers[i]);
        }
        return example.contains(answer);
    }
}
