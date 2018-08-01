package com.ubs.opsit.interviews;

/**
 * @author ketans
 * */

import java.util.Arrays;
import java.util.Collections;

public class TimeConverterImpl implements TimeConverter {

	private String convertedTime;

	private static final String NUMERIC_TIME = "Please provide numeric time";
	private static final String NO_TIME = "Please provide the appropriate time";
	private static final String INVALID_TIME = "Invalid time";

	private static final String NEW_LINE = System.getProperty("line.separator");

	// Implementation of time converter
	@Override
	public String convertTime(String aTime) {

		if (aTime == null)
			throw new IllegalArgumentException(NO_TIME);

		String[] times = aTime.split(":", 3);

		if (times.length != 3)
			throw new IllegalArgumentException(INVALID_TIME);

		int hours, minutes, seconds = 0;

		try {
			hours = Integer.parseInt(times[0]);
			minutes = Integer.parseInt(times[1]);
			seconds = Integer.parseInt(times[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(NUMERIC_TIME);
		}

		if (hours < 0 || hours > 24)
			throw new IllegalArgumentException("Invalid value of Hour");
		if (minutes < 0 || minutes > 59)
			throw new IllegalArgumentException("Invalid value of Minutes");
		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("Invalid value of Seconds");

		String line1 = (seconds % 2 == 0) ? "Y" : "O";
		String line2 = rowString(hours / 5, 4, "R").replace('0', 'O');
		String line3 = rowString(hours % 5, 4, "R").replace('0', 'O');
		String line4 = rowString(minutes / 5, 11, "Y").replaceAll("YYY", "YYR").replace('0', 'O');
		String line5 = rowString(minutes % 5, 4, "Y").replace('0', 'O');

		String cTime = String.join(NEW_LINE, Arrays.asList(line1, line2, line3, line4, line5));

		System.out.println(cTime);

		return cTime;
	}

	/**
	 * generate a string for each row of the clock
	 * 
	 * @param onLights
	 * @param lightsInRow
	 * @param lampType
	 * @return Single row of the clock
	 */

	private String rowString(int onLights, int lightsInRow, String lampType) {

		int offLights = lightsInRow - onLights;
		String on = String.join("", Collections.nCopies(onLights, lampType));
		String off = String.join("", Collections.nCopies(offLights, "0"));

		return on + off;
	}

	/**
	 * Berlin Clock time format
	 */
	public void printClock() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return this.convertedTime;
	}

}
