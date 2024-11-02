package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileSingleton implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final FileSingleton INSTANCE = new FileSingleton();
	private ArrayList<College> collegeArr;
	private ArrayList<Lecturer> lecArr;
	private Organization external;
	private Organization permanent;

	private FileSingleton() {

	}

	public ArrayList<College> getCollegeArr() {
		return collegeArr;
	}

	public void setCollegeArr(ArrayList<College> collegeArr) {
		this.collegeArr = collegeArr;
	}

	public ArrayList<Lecturer> getLecArr() {
		return lecArr;
	}

	public void setLecArr(ArrayList<Lecturer> lecArr) {
		this.lecArr = lecArr;
	}

	public Organization getExternal() {
		return external;
	}

	public void setExternal(Organization external) {
		this.external = external;
	}

	public Organization getPermanent() {
		return permanent;
	}

	public void setPermanent(Organization permanent) {
		this.permanent = permanent;
	}

	public void writeToFile(ArrayList<College> collegeArr, ArrayList<Lecturer> lecArr, Organization external,
			Organization permanent) throws Exception {
		ObjectOutputStream outCollegeFile = new ObjectOutputStream(new FileOutputStream("college.dat"));
		outCollegeFile.writeObject(collegeArr);
		outCollegeFile.close();
		ObjectOutputStream outExternalOrgFile = new ObjectOutputStream(new FileOutputStream("external.dat"));
		outExternalOrgFile.writeObject(external);
		outExternalOrgFile.close();
		ObjectOutputStream outPermOrgFile = new ObjectOutputStream(new FileOutputStream("permanent.dat"));
		outPermOrgFile.writeObject(permanent);
		outPermOrgFile.close();
		ObjectOutputStream outLecturersFile = new ObjectOutputStream(new FileOutputStream("lecturers.dat"));
		outLecturersFile.writeObject(lecArr);
		outLecturersFile.close();
	}

	public FileSingleton readFromFile() throws Exception {
		ObjectInputStream inCollegeFile = new ObjectInputStream(new FileInputStream("college.dat"));
		collegeArr = (ArrayList<College>) inCollegeFile.readObject();
		inCollegeFile.close();
		ObjectInputStream inExternalOrgFile = new ObjectInputStream(new FileInputStream("external.dat"));
		external = (Organization) inExternalOrgFile.readObject();
		inExternalOrgFile.close();
		ObjectInputStream inPermOrgFile = new ObjectInputStream(new FileInputStream("permanent.dat"));
		permanent = (Organization) inPermOrgFile.readObject();
		inPermOrgFile.close();
		ObjectInputStream inLecturersFile = new ObjectInputStream(new FileInputStream("lecturers.dat"));
		lecArr = (ArrayList<Lecturer>) inLecturersFile.readObject();
		inLecturersFile.close();
		return this;
	}

}
