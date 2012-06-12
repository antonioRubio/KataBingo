package main;

public class Gap {

	public static final int BLANK = 0;
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = 89;
	
	private int value;
	private boolean mark;
	

	public Gap() { }

	public void setValue(int value) throws GapException {
		if ((value < MIN_VALUE || value > MAX_VALUE) && value != BLANK)
			throw new GapException(value);
		this.value = value;
	}

	public boolean isBlank() {
		return value == BLANK;
	}
	
	public int getValue() {
		return value;
	}

	public void mark() {
		mark = true;
	}

	public boolean isMarked() {
		return mark;
	}

	public String toString() {
		String stringValue = "";
		if (isMarked())
			stringValue += "x";
		stringValue += Integer.toString(value);
		return stringValue;
	}
}
