package test;

import static org.junit.Assert.*;

import main.NumberGenerator;

import org.junit.Before;
import org.junit.Test;

public class NumberGeneratorTest {
	private NumberGenerator numberGenerator;
	
	@Before
	public void setUp() {
		numberGenerator = new NumberGenerator();
	}

	@Test
	public void generateNextNumberInRangeTest() {
		int number = numberGenerator.getNextNumber();
		boolean isInRange = number >= 1 && number <= 89;
		assertTrue(isInRange);
	}
	
	@Test
	public void nextNumberNotRepeatsTest() {
		boolean repeats = false;
		boolean numbers[] = new boolean[NumberGenerator.MAX_NUMBER];
		for (int i = NumberGenerator.MIN_NUMBER; i <= NumberGenerator.MAX_NUMBER; i++) {
			numbers[i - 1] = true;
		}
		int i = 1;
		while (i <= 89 && !repeats) {
			int nextNumber = numberGenerator.getNextNumber();
			if (numbers[nextNumber - 1])
				numbers[nextNumber - 1] = false;
			else
				repeats = true;
			i++;
		}
		assertFalse(repeats);
	}
	
	@Test
	public void hasNextNumberTest() {
		while (numberGenerator.hasNext()) {
			numberGenerator.getNextNumber();
		}
		assertFalse(numberGenerator.hasNext());
	}

}
