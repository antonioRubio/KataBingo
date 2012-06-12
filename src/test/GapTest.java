package test;

import static org.junit.Assert.*;

import main.Gap;
import main.GapException;

import org.junit.Before;
import org.junit.Test;

public class GapTest {
	private Gap gap;
	
	@Before
	public void setUp() {
		gap = new Gap();
	}

	@Test
	public void blankTest() throws GapException {
		gap.setValue(Gap.BLANK);
		assertTrue(gap.isBlank());
	}
	
	@Test
	public void numberTest() throws GapException {
		gap.setValue(1);
		assertEquals(1, gap.getValue());
	}
	
	@Test
	public void isMarkedTrueTest() throws GapException {
		gap.setValue(1);
		gap.mark();
		assertTrue(gap.isMarked());
	}
	
	@Test
	public void isMarkedFalseTest() throws GapException {
		gap.setValue(1);
		assertFalse(gap.isMarked());
	}
	
	@Test(expected = GapException.class)
	public void maxNumberExceptionTest() throws GapException {
		gap.setValue(90);
	}
	
	@Test(expected = GapException.class)
	public void minNumberExceptionTest() throws GapException {
		gap.setValue(-1);
	}

}
