package Model;

import java.io.Serializable;

//Observer Design Pattern
public abstract class Lecturer extends Observable<Lecturer> implements Serializable, salaryCalc {
	private static final long serialVersionUID = 1L;

	public enum academicDegree {
		First, Second, Doctor, Professor
	};

	private College coll;
	private String name;
	private int id;
	private String areaOfExpertise;
	academicDegree acDegree;
	private double salary;
	private int orgRank;
	private String str;
	public ChatRoom chatRoom;
	
	//Observer Design Pattern
	public int getRank() {
		return orgRank;
	}

	public String getStr() {
		return str;
	}

	// Builder Design Pattern
	public Lecturer addRank(int rank) {
		this.orgRank = rank;
		str = propertyChanged(this, "rank", rank);
		return this;
	}

	public Lecturer addColl(College coll) {
		this.coll = coll;
		return this;
	}

	public Lecturer addName(String name) {
		this.name = name;
		return this;
	}

	public Lecturer addId(int id) {
		this.id = id;
		return this;
	}

	public Lecturer addAreaOfExpertise(String areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
		return this;
	}

	public Lecturer addAcDegree(academicDegree acDegree) {
		this.acDegree = acDegree;
		return this;
	}

	public Lecturer addSalary(double salary) {
		this.salary = salary;
		return this;
	}

	// Bridge Design Pattern
	@Override
	public double ExternalSalary(int workTime) {
		double salary = workTime * BASE_EXT;
		return salary;
	}

	@Override
	public double permanentSalary(double seniority) {
		double salary = ((seniority / 3.5) * BASE_PERM);
		return salary;
	}

	public College getColl() {
		return coll;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public academicDegree getAcDegree() {
		return acDegree;
	}

	public static int getHappyHoliday() {
		return 0;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return String.format("Name: %s\nAcademic Degree: %s\nArea Of Expertise: %s\n", name, acDegree, areaOfExpertise);
	}

	public String receive(String sender, String message) {
		String s = sender + ": \t" + message;
		return "(" + name + "'s chat session) " + s;
	}
}
