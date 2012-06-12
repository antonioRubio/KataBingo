package test;

import static org.junit.Assert.*;

import main.Card;
import main.CardGenerator;
import main.GapException;

import org.junit.Test;

public class CardGeneratorTest {
	
	@Test
	public void correctCardTest() throws GapException {
		Card card = CardGenerator.getCard();
		assertTrue(card.isCorrect());
	}

}
