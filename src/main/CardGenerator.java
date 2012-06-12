package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGenerator {

	public static Card getCard() throws GapException {
		Card card = new Card();
		Gap[][] gaps = generateNonBlankGaps();
		gaps = fillBlanks(gaps);
		card.setGaps(gaps);
		return card;
	}
	
	private static Gap[][] generateNonBlankGaps() throws GapException {
		Gap[][] gaps = new Gap[Card.NUM_LINES][Card.NUM_COLUMNS];
		for (int column = 0; column < Card.NUM_COLUMNS; column++) {
			Gap[] gapsColumn = getColumnRandomized(column);
			for (int line = 0; line < Card.NUM_LINES; line++)
				gaps[line][column] = gapsColumn[line];
		}
		return gaps;
	}
	
	private static Gap[][] fillBlanks(Gap[][] gaps) throws GapException {
		for (int line = 0; line < Card.NUM_LINES; line++) {
			fillLineBlanks(line, gaps);
		}
		return gaps;
	}
	
	private static void fillLineBlanks(int line, Gap[][] gaps) throws GapException {
		int maxColumn = Card.NUM_COLUMNS - 1;
		Integer[] columnPositions = shuffleInterval(0, maxColumn);
		for (int randomPosition = 0; randomPosition < Card.NUM_BLANKS_PER_LINE; 
				randomPosition++) {
			int blankPosition = columnPositions[randomPosition];
			Gap blankGap = gaps[line][blankPosition];
			blankGap.setValue(Gap.BLANK);
		}
	}
	
	private static Integer[] shuffleInterval(int minValue, int maxValue) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int value = minValue; value <= maxValue; value++)
			positions.add(value);
		Collections.shuffle(positions);
		Integer[] resultPositions = new Integer[positions.size()];
		return positions.toArray(resultPositions);
	}

	private static Gap[] getColumnRandomized(int column) throws GapException {
		int dozenInColumn = column * 10;
		boolean isFirstColumn = (dozenInColumn == 0);
		int minPossibleValue = isFirstColumn ? 1 : dozenInColumn;
		int maxPossibleValue = dozenInColumn + 9;
		Integer[] columnValues = shuffleInterval(minPossibleValue, maxPossibleValue);
		return fillGapColumn(columnValues);
	}

	private static Gap[] fillGapColumn(Integer[] columnValues)
			throws GapException {
		Gap[] columnGaps = new Gap[Card.NUM_LINES];
		for (int i = 0; i < Card.NUM_LINES; i++) {
			Gap gap = new Gap();
			int value = columnValues[i];
			gap.setValue(value);
			columnGaps[i] = gap;
		}
		return columnGaps;
	}
}
