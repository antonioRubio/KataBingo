package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGenerator {

	public static Card getCard() throws GapException {
		Card card = new Card();
		Gap[][] gaps = generateGapArray();
		gaps = fillBlanks(gaps);
		card.setGaps(gaps);
		return card;
	}
	
	private static Gap[][] generateGapArray() throws GapException {
		Gap[][] gaps = new Gap[Card.NUM_LINES][Card.NUM_COLUMNS];
		for (int j = 0; j < Card.NUM_COLUMNS; j++) {
			Gap[] gapsColumn = getColumnRandomized(j);
			for (int i = 0; i < Card.NUM_LINES; i++)
				gaps[i][j] = gapsColumn[i];
		}
		return gaps;
	}
	
	private static Gap[][] fillBlanks(Gap[][] gaps) throws GapException {
		for (int i = 0; i < Card.NUM_LINES; i++) {
			List<Integer> columnPositions = new ArrayList<Integer>();
			for (int j = 0; j < Card.NUM_COLUMNS; j++)
				columnPositions.add(j);
			Collections.shuffle(columnPositions);
			for (int k = 0; k < Card.NUM_BLANKS_PER_LINE; k++) {
				int blankPosition = columnPositions.get(k);
				gaps[i][blankPosition].setValue(Gap.BLANK);
			}
		}
		return gaps;
		
	}
	
	private static Gap[] getColumnRandomized(int column) throws GapException {
		Gap[] columnGaps = new Gap[Card.NUM_LINES];
		List<Integer> columnValues = new ArrayList<Integer>();
		int maxPossibleValue = (column * 10) + 9;
		int minPossibleValue = (column * 10);
		for (int i = minPossibleValue; i <= maxPossibleValue; i++)
			if (i > 0) columnValues.add(i);
		Collections.shuffle(columnValues);
		for (int i = 0; i < Card.NUM_LINES; i++) {
			columnGaps[i] = new Gap();
			columnGaps[i].setValue(columnValues.get(i));
		}
		return columnGaps;
	}
	
}
