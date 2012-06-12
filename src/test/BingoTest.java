package test;

import static org.junit.Assert.*;

import main.Bingo;
import main.GapException;

import org.junit.Test;

public class BingoTest {

	@Test
	public void test() throws GapException {
		Bingo bingo = new Bingo(1000);
		bingo.play();
	}

}
