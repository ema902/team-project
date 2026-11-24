import entities.Deck;
import entities.Card;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

public class DeckTest {

    private final String[] testQuestions = {"Abc", "def", "ghi"};
    private final String[] testAnswers = {"123", "456", "789"};

    @Test
    public void testCurrentCardNull() {
        ArrayList<Card> exampleList = new ArrayList<>();
        for (int i = 0; i<3; i++) {
            exampleList.add(new Card(testQuestions[i], testAnswers[i]));
        }
        Deck d = new Deck("title", exampleList);
        assertNull(d.getCurrentCard());
    }

}