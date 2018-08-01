package com.ubs.opsit.interviews;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TimeConverterTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private TimeConverterImpl berlinClock = new TimeConverterImpl();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Test
	public void testMinValidBerlinClock() {
		berlinClock.convertTime("00:00:00");
	}

	@Test
	public void testMaxValidBerlinClock() {
		berlinClock.convertTime("23:59:59");
	}

	@Test
	public void testPrintClock() {
		// berlinClock.convertTime("12:00:00");

		String line1 = "Y";
		String line2 = "RROO";
		String line3 = "RROO";
		String line4 = "OOOOOOOOOOO";
		String line5 = "OOOO";

		final String NEW_LINE = System.getProperty("line.separator");

		String expected = String.join(NEW_LINE, Arrays.asList(line1, line2, line3, line4, line5));

		assertEquals(expected, berlinClock.convertTime("12:00:00").toString().trim());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidHours() {
		berlinClock.convertTime("25:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidMinutes() {
		berlinClock.convertTime("00:60:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidSeconds() {
		berlinClock.convertTime("00:00:60");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLowerInvalidHours() {
		berlinClock.convertTime("-01:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLowerInvalidMinutes() {
		berlinClock.convertTime("00:-01:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLowerInvalidSeconds() {
		berlinClock.convertTime("00:00:-01");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidString() {
		berlinClock.convertTime("00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullString() {
		berlinClock.convertTime(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyString() {
		berlinClock.convertTime("");
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

}
