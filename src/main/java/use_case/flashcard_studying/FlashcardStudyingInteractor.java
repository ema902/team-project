package use_case.flashcard_studying;
import data_access.FlashcardDataAccessInterface;
import entities.Deck;
import entities.Card;

public class FlashcardStudyingInteractor implements FlashcardStudyingInputBoundary {

    private final FlashcardStudyingOutputBoundary presenter;
    private final FlashcardDataAccessInterface dao;
    private Deck currentDeck;

    public FlashcardStudyingInteractor(FlashcardStudyingOutputBoundary presenter, FlashcardDataAccessInterface dao) {
        this.presenter = presenter;
        this.dao = dao;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public void execute(FlashcardStudyingInputData inputData) {
        if (inputData.getButtonClicked().equals("Start")) {
            // check if user didn't input a new deck
            if (inputData.getDeckTitle() == null) {
                presenter.prepareFailView("You need to input a deck title");
                return;
            } else {
                // check if in dao
                try {
                    currentDeck = dao.loadDeck(inputData.getDeckTitle());
                    Card currentCard = currentDeck.getCard();
                    currentCard.setQuestionFacingUp();
                    presenter.prepareSuccessView(new FlashcardStudyingOutputData(currentCard.getText()));
                    return;
                } catch (IllegalStateException e) {
                    presenter.prepareFailView("Invalid deck title");
                    return;
                }
            }
        } else if (currentDeck == null) {
            presenter.prepareFailView("You need to input a deck title");
            return;
            // current deck is not null
        } else if (inputData.getButtonClicked().equals("Flip")) {
            Card currentCard = currentDeck.getCurrentCard();
            currentCard.flip();
            presenter.prepareSuccessView(new FlashcardStudyingOutputData(currentCard.getText()));
        } else if (inputData.getButtonClicked().equals("Still learning")) {
            Card currentCard = currentDeck.getCard();
            currentCard.setQuestionFacingUp();
            presenter.prepareSuccessView(new FlashcardStudyingOutputData(currentCard.getQuestion()));
        } else if (inputData.getButtonClicked().equals("Knew it")) {
            currentDeck.removeCurrentCard();
            Card currentCard = currentDeck.getCard();
            currentCard.setQuestionFacingUp();
            presenter.prepareSuccessView(new FlashcardStudyingOutputData(currentCard.getQuestion()));
        }

    }


}
