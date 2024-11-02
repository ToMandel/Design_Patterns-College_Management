package Model;

import java.io.Serializable;

public class ExternalLecturer extends Lecturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static int HAPPY_HOLIDAY = 350;
	private int workTime;

	// Builder Design Pattern
	public ExternalLecturer addWorkTime(int workTime) throws HoursException {
		this.workTime = workTime;
		if (workTime > 14)
			throw new HoursException(workTime);
		return this;
	}

	public int getWorkTime() {
		return workTime;
	}

	public static int getHappyHoliday() {
		return HAPPY_HOLIDAY;
	}

	@Override
	public String toString() {
		return "External Lecturer: \n" + super.toString() + String.format("Working time: %d", workTime);
	}

}