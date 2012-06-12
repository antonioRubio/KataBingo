package main;

public class GapException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public GapException(int value) {
		super(Integer.toString(value) + "#####");
	}
	
}
