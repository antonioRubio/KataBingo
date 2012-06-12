package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {
	public final static int MIN_NUMBER = 1;
	public final static int MAX_NUMBER = 89;
	private List<Integer> numbers;
	private int positionOfRandomList = 0;
	
	public NumberGenerator() {
		numbers = new ArrayList<Integer>();
		generateRandomNumbers();
	}

	private void generateRandomNumbers() {
		for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
	}

	public int getNextNumber() {
		return numbers.get(positionOfRandomList++);
	}

	public boolean hasNext() {
		return positionOfRandomList < MAX_NUMBER;
	}
	
	

}
