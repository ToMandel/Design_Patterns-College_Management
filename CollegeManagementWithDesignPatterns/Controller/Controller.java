package Controller;

import Model.Lecturer.academicDegree;
import Model.Model;
import Model.Organization;
import Model.OrganizationCommand;
import Model.PermanentLecturer.committee;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import Model.College;
import Model.FileSingleton;
import Model.HoursException;
import View.View;

public class Controller {
	private Model model;
	private View view;
	private char lecType;
	private String chairman;
	FileSingleton fileSingleton = FileSingleton.INSTANCE;

	public Controller(Model model, View view) throws Exception {
		this.model = model;
		this.view = view;

		try {
			// Singleton Design Pattern
			fileSingleton.readFromFile();
			model.collegeArr = fileSingleton.getCollegeArr();
			model.external = fileSingleton.getExternal();
			model.permanent = fileSingleton.getPermanent();
			model.lecArr = fileSingleton.getLecArr();
			view.insertNewCol(model.collegeArr);

		} catch (FileNotFoundException e2) {
			model.collegeArr = new ArrayList<>();
			model.external = new Organization("External", null, null);
			model.permanent = new Organization("Permanent", null, null);
			model.lecArr = new ArrayList<>();
		} catch (IOException e2) {
		} catch (ClassNotFoundException e1) {
		}

		view.colInsertAction(e -> {
			view.setExFlag(false);
			try {
				model.NewCollege(model.collegeArr, view.getText1(), view.getText2(), view.getText3());
			} catch (Exception ex) {
				view.noCollege(ex.getMessage());
				view.setExFlag(true);
			}
			view.insertNewCol(model.collegeArr);
			view.clearFields();
		});

		view.lecInsertAction(e -> {
			try {
				view.setExFlag(false);
				if (getID() < 99999999)
					throw new Exception();
				if (getID() < 0 || getWorkTime() < 0 || getSeniority() < 0) {
					throw new IllegalArgumentException();
				} else {
					view.welcomeToCollege(
							model.insertNewLecturer(model.lecArr, getLecType(), getName(), getID(), getSeniority(),
									getWorkTime(), getAcDegree(), getAreaOfExpertise(), getComm(), getCollege()));

					// Memento Design Pattern
					if (getLecType() == 'P' && getSeniority() >= 5) {
						view.insertNewLec(getName());
					}
					view.clearFields();
				}

			} catch (HoursException ex) {
				view.hoursException(ex.getMessage());
				view.setExFlag(true);

			} catch (NumberFormatException ex) {
				view.invalidFormat();
				view.setExFlag(true);

			} catch (IllegalArgumentException ex) {
				view.negativeNumber();
				view.setExFlag(true);

			} catch (Exception ex) {
				view.invalidID();
				view.setExFlag(true);
			}
		});

		view.setChairAction(e -> {
			try {
				if (model.lecArr.size() != 0) {
					if (getLecType() == 'E') {
						chairman = model.setChairman(model.external, getID());
					} else {
						chairman = model.setChairman(model.permanent, getID());
					}
				} else {
					chairman = "The Lecturers Organizations are empty";
				}
				view.chairmanMessage(chairman);
				view.clearFields();
			} catch (Exception ex) {
				view.invalidID();
				view.setExFlag(true);
			}
		});

		// Command Design Pattern
		view.addToOrgAction(e -> {
			model.orgCommands.clear();
			try {
				if (model.lecArr.size() != 0) {
					model.orgCommands.add(new OrganizationCommand(OrganizationCommand.commandType.ADD, model, getID()));
				} else {
					view.addRmSuccess("The Lecturers Organizations are empty");
					view.clearFields();
				}
			} catch (Exception ex) {
				view.invalidID();
				view.setExFlag(true);
			}
		});
		view.removeFromOrgAction(e -> {
			model.orgCommands.clear();
			try {
				if (model.lecArr.size() != 0) {
					model.orgCommands
							.add(new OrganizationCommand(OrganizationCommand.commandType.REMOVE, model, getID()));
				} else {
					view.addRmSuccess("The Lecturers Organizations are empty");
					view.clearFields();
				}
			} catch (Exception ex) {
				view.invalidID();
			}
		});

		view.applyAction(e2 -> {
			model.orgCommands.forEach(OrganizationCommand -> {
				try {
					view.addRmSuccess(OrganizationCommand.apply());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
		});

		view.undoAction(e2 -> {
			Collections.reverse(model.orgCommands);
			model.orgCommands.forEach(OrganizationCommand -> {
				try {
					view.addRmSuccess(OrganizationCommand.undo());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		});

		view.compareAction(e -> {
			view.setPrint(getCompare());
			view.setText1(getPermOrg());
			view.setText2(getExtOrg());
		});

		view.sendMsgAction(e -> {
			view.sendEmail();
			view.clearFields();
		});

		view.sendGiftAction(e -> {
			try {
				view.sendGift(getGift());
			} catch (Exception ex) {
				view.chooseException();
			}
			view.clearFields();
		});

		view.printAction(e -> {
			view.setPrint(model.printList(getCollege()));
		});

		// Memento Design Pattern
		view.simAction(e -> {
			view.setPrint(model.salaryPrint(view.lecSimUpdate(), view.yearUpdate()));
		});

		// Singleton Design Pattern
		view.finishAction(e -> {
			try {
				fileSingleton.writeToFile(model.collegeArr, model.lecArr, model.external, model.permanent);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

	}

	public char getLecType() throws Exception {
		return view.lecUpdate();
	}

	public String getName() {
		return view.getText1();
	}

	public int getID() {
		return Integer.parseInt(view.getText2());
	}

	public int getSeniority() {
		return Integer.parseInt(view.getText3());
	}

	public int getWorkTime() {
		return Integer.parseInt(view.getText3());
	}

	public String getAreaOfExpertise() {
		return (view.getText4());
	}

	public academicDegree getAcDegree() {
		return view.degreeUpdate();
	}

	public committee getComm() {
		return view.commUpdate();
	}

	public void setLecType(char lecType) {
		this.lecType = lecType;
	}

	public String getCompare() {
		return model.compareOrg();
	}

	public String getGift() throws Exception {
		return model.happyHoliday(getLecType(), getCollege());
	}

	public String getPermOrg() {
		return String.valueOf(model.permanent.getMembers().size());
	}

	public String getExtOrg() {
		return String.valueOf(model.external.getMembers().size());
	}

	public College getCollege() {
		return view.collUpdate();
	}

}
