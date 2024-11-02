package Model;

import java.io.Serializable;

public class PermanentLecturer extends Lecturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static int HAPPY_HOLIDAY = 500;
	private int seniority;

	public enum committee {
		Exceptions, Teaching, Improvement, Other
	};

	private committee comm;

	// Builder Design Pattern
	public PermanentLecturer addSeniority(int seniority) {
		this.seniority = seniority;
		return this;
	}

	public PermanentLecturer addCommittee(committee comm) {
		this.comm = comm;
		return this;
	}

	public int getSeniority() {
		return seniority;
	}

	public committee getComm() {
		return comm;
	}

	public static int getHappyHoliday() {
		return HAPPY_HOLIDAY;
	}

	@Override
	public String toString() {
		return "Permanent Lecturer: \n" + super.toString()
				+ String.format("Seniority: %d\nCommitee: %s", seniority, comm);
	}
}