package Model;

import java.io.Serializable;
import java.util.ArrayList;

//Observer Design Pattern
public class Organization implements Serializable, Observer<Lecturer> {
	private static final long serialVersionUID = 1L;
	private String orgName;
	private Lecturer chairman;
	private String regulations;
	private ArrayList<Lecturer> members;

	public Organization(String orgName, Lecturer chairman, String regulations) {
		this.orgName = orgName;
		this.chairman = chairman;
		this.regulations = regulations;
		members = new ArrayList<>();
	}

	public ArrayList<Lecturer> getMembers() {
		return members;
	}

	public void setChairman(Lecturer chairman) {
		this.chairman = chairman;
	}

	public String getOrgName() {
		return orgName;
	}

	public Lecturer getChairman() {
		return chairman;
	}

	public String getRegulations() {
		return regulations;
	}

	//Observer Design Pattern
	@Override
	public String handle(PropertyChangedEventArgs<Lecturer> args) {
		return "\nYour current " + args.propertyName + " changed to " + args.newValue + " , a Chairman's rank";
	}
}
