import entities.Deck;
import entities.Card;


import org.junit.Test;

import java.util.List;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {

    private final String[] testQuestions = {"Abc", "def", "ghi"};
    private final String[] testAnswers = {"123", "456", "789"};
    ArrayList<Card> exampleList = new ArrayList<>();

    @Test
    public void testCurrentCardNull() {
        for (int i = 0; i<3; i++) {
            exampleList.add(new Card(testQuestions[i], testAnswers[i]));
        }
        Deck d = new Deck("title", exampleList);
        assertNull(d.getCurrentCard());
    }

    // tests whether getCard returns a card from the deck
    @Test
    public void testGetCard() {
        for (int i = 0; i<3; i++) {
            exampleList.add(new Card(testQuestions[i], testAnswers[i]));
        }
        Deck d = new Deck("title", exampleList);
        Card c = d.getCard();
        assertTrue(exampleList.contains(c));
    }

    // tests whether getCard doesn't return the same card twice in a row
    // repeats 15 times to check randomness
    @Test
    public void testGetCardRepeat() {
        for (int i = 0; i<3; i++) {
            exampleList.add(new Card(testQuestions[i], testAnswers[i]));
        }
        Deck d = new Deck("title", exampleList);
        Card c1 = d.getCard();
        Card c2 = d.getCard();
        assertNotEquals(c1.getQuestion(), c2.getQuestion());
    }

}