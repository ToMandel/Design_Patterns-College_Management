package Model;

import java.io.Serializable;

public class HoursException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	private int workTime;

	public HoursException(int workTime) {
		super("You can't work more than 14 hours");
		this.workTime = workTime;
	}

	public int getWorkTime() {
		return workTime;
	}
}
