package test;

import main.Bingo;
import main.GapException;

public class BingoTest {

	public void test() throws GapException {
		Bingo bingo = new Bingo(1000);
		bingo.play();
	}

}
