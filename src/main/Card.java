package main;

import java.util.HashSet;
import java.util.Set;

public class Card {
	public static final int NUM_LINES = 3;
	public static final int NUM_COLUMNS = 9;
	public static final int NUM_BLANKS_PER_LINE = 4;
	private static final int NUM_NUMBERS_PER_LINE = 5;
	private Gap[][] gaps;
	
	public Card() { }
	
	public Card(Gap[][] gaps) {
		this.gaps = gaps;
	}
	
	public void setGaps(Gap[][] gaps) {
		this.gaps = gaps;
	}
	

	private boolean hasCorrectNumberOfBlanksInLine(Gap[] line) {
		int numBlanks = 0;
		for (Gap gap: line) {
			if (gap.isBlank())
				numBlanks++;
		}
		return numBlanks == NUM_BLANKS_PER_LINE;
	}
	
	private boolean hasCorrectNumberOfNumbersInLine(Gap[] line) {
		int numNumbers = 0;
		for (Gap gap: line) {
			if (!gap.isBlank())
				numNumbers++;
		}
		return numNumbers == NUM_NUMBERS_PER_LINE;
	}
	
	public boolean isCorrect() {
		Set<Integer> cardValues = new HashSet<Integer>();
		for (int line = 0; line < NUM_LINES; line++) {
			Gap[] lineGaps = gaps[line];
			if (!hasCorrectNumberOfBlanksInLine(lineGaps) ||
					!hasCorrectNumberOfNumbersInLine(lineGaps) ||
					!hasPossibleValue(lineGaps, cardValues))
				return false;
		}
		return true;
	}
	
	private boolean hasPossibleValue(Gap[] lineGaps, Set<Integer> cardValues) {
		for (int column = 0; column < NUM_COLUMNS; column++) {
			Gap gap = lineGaps[column];
			if (gap.isBlank()) return true;
			int value = gap.getValue();
			if (possibleValue(value, column) && !cardValues.contains(value))
				cardValues.add(value);
			else
				return false;
		}
		return true;
	}

	private boolean possibleValue(int value, int column) {
		int maxPossibleValue = (column * 10) + 9;
		int minPossibleValue = (column * 10);
		return value >= minPossibleValue && value <= maxPossibleValue;
	}
	
	public String toString() {
		String res = "";
		for (int line = 0; line < NUM_LINES; line++) {
			res += "[";
			for (int column = 0; column < NUM_COLUMNS; column++) {
				res += gaps[line][column].toString() + ", ";
			}
			res += "]\n";
		}
		return res;
	}
	
	public void mark(int number) {
		for (int line = 0; line < NUM_LINES; line++) {
			markGapInLine(number, line);
		}
	}
	
	private void markGapInLine(int number, int line) {
		for (int column = 0; column < NUM_COLUMNS; column++) {
			markGap(number, line, column);
		}
	}
	
	private void markGap(int number, int line, int column) {
		Gap gap = gaps[line][column];
		if (gap.getValue() == number) 
			gap.mark();
	}

	public boolean hasLineAward() {
		for (int i = 0; i < NUM_LINES; i++) {
			if (fullMarkedLine(i))
				return true;
		}
		return false;
	}
	
	public boolean hasBingoAward() {
		for (int i = 0; i < NUM_LINES; i++) {
			if (!fullMarkedLine(i))
				return false;
		}
		return true;
	}
	
	private boolean fullMarkedLine(int line) {
		int nums = NUM_COLUMNS - NUM_BLANKS_PER_LINE;
		for (int j = 0; j < NUM_COLUMNS; j++) {
			Gap gap = gaps[line][j];
			if (!gap.isBlank() && gap.isMarked())
				nums--;
		}
		return nums == 0;
	}
	
}
