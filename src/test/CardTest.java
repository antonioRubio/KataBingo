package test;

import static org.junit.Assert.*;

import main.Card;
import main.Gap;
import main.GapException;

import org.junit.Before;
import org.junit.Test;

public class CardTest {
	
	private Card card;
	@Before
	public void setUp() {
		card = new Card();
	}
	
	@Test
	public void allOnesTest() {
		card.setGaps(generateAllOneGaps());
		assertFalse(card.isCorrect());
	}
	
	@Test
	public void correctGapsTest() {
		card.setGaps(generateCorrectGaps());
		assertTrue(card.isCorrect());
	}
	
	@Test
	public void hasLineAwardTest() {
		int[][] gapValues = {
			{2, 11, 0, 0, 41, 55, 63, 0, 0},
			{5, 13, 0, 37, 0, 51, 0, 73, 0},
			{0, 18, 0, 32, 0, 50, 66, 74, 0}};
		Gap[][] gaps = generateGapsFromValues(gapValues);
		card.setGaps(gaps);
		card.mark(2);
		card.mark(11);
		card.mark(41);
		card.mark(55);
		card.mark(63);
		assertTrue(card.hasLineAward());
	}
	
	@Test
	public void hasBingoAwardTest() {
		int[][] gapValues = {
				{2, 11, 0, 0, 41, 55, 63, 0, 0},
				{5, 13, 0, 37, 0, 51, 0, 73, 0},
				{0, 18, 0, 32, 0, 50, 66, 74, 0}};
		Gap[][] gaps = generateGapsFromValues(gapValues);
		card.setGaps(gaps);
		card.mark(2);
		card.mark(11);
		card.mark(41);
		card.mark(55);
		card.mark(63);
		card.mark(5);
		card.mark(13);
		card.mark(37);
		card.mark(51);
		card.mark(73);
		card.mark(18);
		card.mark(32);
		card.mark(50);
		card.mark(66);
		card.mark(74);
		assertTrue(card.hasBingoAward());
	}
	
	private Gap[][] generateAllOneGaps() {
		Gap[][] gaps = new Gap[Card.NUM_LINES][Card.NUM_COLUMNS];
		for (int i = 0; i < Card.NUM_LINES; i++) {
			for (int j = 0; j < Card.NUM_COLUMNS; j++) {
				gaps[i][j] = new Gap();
				try {
					gaps[i][j].setValue(1);
				} catch (GapException e) {
					e.printStackTrace();
				}
			}
		}
		return gaps;
	}
	
	private Gap[][] generateCorrectGaps() {
		Gap[][] gaps = new Gap[Card.NUM_LINES][Card.NUM_COLUMNS];
		for (int i = 0; i < Card.NUM_LINES; i++) {
			for (int j = 0; j < Card.NUM_COLUMNS; j++) {
				gaps[i][j] = new Gap();
				try {
					if (j % 2 != 0)
						gaps[i][j].setValue(Gap.BLANK);
					else {
						int value = (j * 10) + (i + 1);
						gaps[i][j].setValue(value);
					}
				} catch (GapException e) {
					e.printStackTrace();
				}
			}
		}
		return gaps;
	}
	
	private Gap[][] generateGapsFromValues(int[][] values) {
		Gap[][] gaps = new Gap[Card.NUM_LINES][Card.NUM_COLUMNS];
		for (int i = 0; i < Card.NUM_LINES; i++) {
			for (int j = 0; j < Card.NUM_COLUMNS; j++) {
				gaps[i][j] = new Gap();
				try {
					gaps[i][j].setValue(values[i][j]);
				} catch (GapException e) {
					e.printStackTrace();
				}
			}
		}
		return gaps;
	}

}
