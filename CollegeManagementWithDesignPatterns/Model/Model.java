package Model;

import java.util.ArrayList;
import java.util.List;
import Model.Lecturer.academicDegree;
import Model.PermanentLecturer.committee;

public class Model {
	// Command Design Pattern
	public List<AddAndRemCommand> orgCommands = new ArrayList<AddAndRemCommand>() {
		private static final long serialVersionUID = 1L;
	};
	public ArrayList<Lecturer> lecArr;
	// Mediator Design Pattern
	public ChatRoom room = new ChatRoom();
	public Organization permanent;
	public Organization external;
	public ArrayList<College> collegeArr;

	public void NewCollege(ArrayList<College> collArr, String colName, String colCity, String colType)
			throws Exception {
		if (colName.isEmpty() || colName.contains(" ")) {
			throw new Exception("Wrong input please enter college Name!");
		}

		// Prototype Design Pattern
		if (collArr.size() == 0) {
			College c = new College(colName, colCity, colType);
			collArr.add(c);
		} else {
			College newColl = (College) collArr.get(0).clone();
			newColl.colName = colName;
			newColl.city = colCity;
			newColl.colType = colType;
			collArr.add(newColl);
		}
	}

	public String insertNewLecturer(ArrayList<Lecturer> lecArr, char lecType, String name, int id, int seniority,
			int workTime, academicDegree acDegree, String areaOfExpertise, committee comm, College coll)
			throws Exception {

		switch (lecType) {
		case 'P': {
			// Builder Design Pattern //Bridge Design Pattern //Observer Design Pattern
			PermanentLecturer p = (PermanentLecturer) new PermanentLecturer();
			p.addSeniority(seniority).addCommittee(comm).addName(name).addId(id).addSalary(p.permanentSalary(seniority))
					.addAreaOfExpertise(areaOfExpertise).addAcDegree(acDegree).addColl(coll)
					.addRank(permanent.getMembers().size());
			// Proxy Design Pattern
			if ((new CollegeProxy(p)).acceptance(lecType)) {
				lecArr.add(p);
				permanent.getMembers().add(p);
				if (permanent.getMembers().size() == 1) {
					permanent.setChairman(p);
				}
				return p.getName() + " Welcome to " + coll.getColName() + " college";
			} else {
				return "Sorry, we're looking for someone with at least 5 years of experience";
			}
		}

		case 'E': {
			// Builder Design Pattern //Bridge Design Pattern //Observer Design Pattern
			ExternalLecturer e = (ExternalLecturer) new ExternalLecturer();
			e.addWorkTime(workTime).addAcDegree(acDegree).addId(id).addName(name).addSalary(e.ExternalSalary(workTime))
					.addAreaOfExpertise(areaOfExpertise).addColl(coll).addRank(permanent.getMembers().size());
			// Proxy Design Pattern
			if ((new CollegeProxy(e)).acceptance(lecType)) {
				lecArr.add(e);
				external.getMembers().add(e);
				if (external.getMembers().size() == 1) {
					external.setChairman(e);
				}
				return e.getName() + " Welcome to " + coll.getColName() + " college";
			} else {
				return "Sorry, we're looking for someone with at least a Second Degree";
			}
		}
		}
		return "";
	}

	// Mediator Design Pattern
	public String happyHoliday(char lecType, College coll) {
		String str = "";
		switch (lecType) {
		case 'P': {
			str = "Happy Holiday, you got " + PermanentLecturer.getHappyHoliday() + " ILS for the Holiday from "
					+ coll.getColName() + " College";
			return room.broadcast("PermanentLecturer", lecArr, coll.getColName(), str);
		}
		case 'E': {
			str = "Happy Holiday, you got " + ExternalLecturer.getHappyHoliday() + " ILS for the Holiday from "
					+ coll.getColName() + " College";
			return room.broadcast("ExternalLecturer", lecArr, coll.getColName(), str);
		}
		}
		return str;
	}

	public String printList(College coll) {
		String print = "";
		for (int i = 0; i < lecArr.size(); i++) {
			if (coll.getColName().equals(lecArr.get(i).getColl().getColName())) {
				print += lecArr.get(i).toString() + "\n\n";
			}
		}
		if (print.isEmpty()) {
			return "There are no Lecturers in the college";
		}
		return print;
	}

	public String addLecToOrg(int id) throws Exception {
		if (id < 99999999) {
			throw new Exception();
		}
		for (int i = 0; i < lecArr.size(); i++) {
			if (lecArr.get(i).getId() == id) {
				if (lecArr.get(i).getClass().getSimpleName().equals("PermanentLecturer")) {
					for (int j = 0; j < permanent.getMembers().size(); j++) {
						if (id == permanent.getMembers().get(j).getId()) {
							return "This lecturer is already in the Permanent Organization";
						}
					}
					// Observer Design Pattern
					permanent.getMembers().add(lecArr.get(i));
					permanent.getMembers().get(i).addRank(permanent.getMembers().size());
					return "The Lecturer was added successfully to the Permanent Lecturers Organization";

				} else {
					for (int j = 0; j < external.getMembers().size(); j++) {
						if (id == external.getMembers().get(j).getId()) {
							return "This lecturer is already in the External Organization";
						}
					}
					// Observer Design Pattern
					external.getMembers().add(lecArr.get(i));
					external.getMembers().get(i).addRank(external.getMembers().size());
					return "The Lecturer was added successfully to the External Lecturers Organization";
				}
			}
		}
		return "There is no such lecturer in any of the Organizations";
	}

	public String removeLecFromOrg(int id) throws Exception {
		if (id < 99999999) {
			throw new Exception();
		}
		for (int i = 0; i < lecArr.size(); i++) {
			if (lecArr.get(i).getId() == id) {
				if (lecArr.get(i).getClass().getSimpleName().equals("PermanentLecturer")) {
					for (int j = 0; j < permanent.getMembers().size(); j++) {
						if (id == permanent.getMembers().get(j).getId()) {
							// Observer Design Pattern
							permanent.getMembers().get(j).addRank(-1);
							permanent.getMembers().remove(lecArr.get(i));
							return "The Lecturer was removed successfully from the Permanent Lecturers Organization";
						}
					}
					return "The Lecturer was already removed from the Permanent Organization";

				} else {
					for (int j = 0; j < external.getMembers().size(); j++) {
						if (id == external.getMembers().get(j).getId()) {
							// Observer Design Pattern
							external.getMembers().get(j).addRank(-1);
							external.getMembers().remove(lecArr.get(i));
							return "The Lecturer was removed successfully from the External Lecturers Organization";
						}
					}
					return "The Lecturer was already removed from the External Organization";
				}
			}
		}
		return "There is no such lecturer in any of the Organizations";
	}

	// Observer Design Pattern
	public String setChairman(Organization org, int id) throws Exception {
		if (id < 99999999) {
			throw new Exception();
		}
		for (int i = 0; i < org.getMembers().size(); i++) {
			if (org.getMembers().get(i).getId() == id) {
				if (org.getChairman().getId() == id) {
					return org.getChairman().getName() + " is already a chairman";
				}
				org.setChairman(org.getMembers().get(i));
				org.getMembers().get(i).subscribe(org);
				org.getMembers().get(i).addRank(0);
				return "Congratulations " + org.getMembers().get(i).getName() + "!\n Your'e the new Chairman of the "
						+ org.getOrgName() + " Lecturer Organization " + org.getMembers().get(i).getStr();
			}
		}
		return "There is no such lecturer";
	}

	// Null-Object Design Pattern
	public String compareOrg() {
		NullLog nullLog = new NullLog();
		CheckLec checkLec = new CheckLec(nullLog);
		ConsoleLog consoleLog = new ConsoleLog();

		if (external.getMembers().size() == 0 && permanent.getMembers().size() == 0) {
			return checkLec.CompareOrg(permanent, external);
		} else {
			checkLec.log = consoleLog;
			return checkLec.CompareOrg(permanent, external);
		}
	}

	// Memento Design Pattern
	public String salaryPrint(String name, int year) {
		String salaryDetails = "";
		for (int i = 0; i < lecArr.size(); i++) {
			if (name.equals(lecArr.get(i).getName())) {
				SalarySim salarySim = new SalarySim(lecArr.get(i).getSalary());
				Memento memento1 = salarySim.updateSalary(salaryCalc.BASE_PERM / 3.5);
				Memento memento2 = salarySim.updateSalary(salaryCalc.BASE_PERM / 3.5);
				Memento memento3 = salarySim.updateSalary(salaryCalc.BASE_PERM / 3.5);
				Memento memento4 = salarySim.updateSalary(salaryCalc.BASE_PERM / 3.5);
				Memento memento5 = salarySim.updateSalary(salaryCalc.BASE_PERM / 3.5);
				switch (year - 2021) {
				case 0: {
					salarySim.restore(memento1);
					break;
				}
				case 1: {
					salarySim.restore(memento2);
					break;
				}
				case 2: {
					salarySim.restore(memento3);
					break;
				}
				case 3: {
					salarySim.restore(memento4);
					break;
				}
				case 4: {
					salarySim.restore(memento5);
					break;
				}
				}
				return "Here is " + lecArr.get(i).getName() + "'s simulation for " + year + "\nthe salary is:\t"
						+ (int) salarySim.getSalary() + " ILS\n\n";
			}
		}
		if (salaryDetails.isEmpty()) {
			return "There are no Lecturers in the college";
		}
		return salaryDetails;
	}
}
