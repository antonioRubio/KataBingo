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
	

	public boolean isCorrect() {
		if (hasCorrectValues())
			return true;
		return false;
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
	
	private boolean hasCorrectValues() {
		Set<Integer> cardValues = new HashSet<Integer>();
		for (int i = 0; i < NUM_LINES; i ++) {
			if (!hasCorrectNumberOfBlanksInLine(gaps[i]) ||
					!hasCorrectNumberOfNumbersInLine(gaps[i]))
				return false;
			for (int j = 0; j < NUM_COLUMNS; j++) {
				if (!gaps[i][j].isBlank()) {
					int value = gaps[i][j].getValue();
					if (cardValues.contains(value))
						return false;
					else {
						cardValues.add(value);
						if (!possibleValue(value, j))
							return false;
					}
				}
			}
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
		for (int i = 0; i < NUM_LINES; i++) {
			res += "[";
			for (int j = 0; j < NUM_COLUMNS; j++) {
				res += gaps[i][j].toString() + ", ";
			}
			res += "]\n";
		}
		return res;
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

	public void mark(int number) {
		for (int i = 0; i < NUM_LINES; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				if (gaps[i][j].getValue() == number) {
					gaps[i][j].mark();
					return;
				}
			}
		}
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
