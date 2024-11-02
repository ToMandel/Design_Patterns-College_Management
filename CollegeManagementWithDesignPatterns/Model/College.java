package Model;

import java.io.Serializable;

//Proxy Design Pattern	//Prototype Design Pattern
public class College implements ICollege, Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Prototype Design Pattern
	String colName;
	String city;
	String colType;

	// Proxy Design Pattern
	protected PermanentLecturer permLec;
	protected ExternalLecturer extLec;

	// Prototype Design Pattern
	public College(String colName, String city, String colType) {
		this.colName = colName;
		this.city = city;
		this.colType = colType;
	}

	// Proxy Design Pattern
	public College(PermanentLecturer permLec) {
		this.permLec = permLec;
	}

	public College(ExternalLecturer extLec) {
		this.extLec = extLec;
	}

	public String getColName() {
		return colName;
	}

	@Override
	public String toString() {
		return colName;
	}

	// Proxy Design Pattern
	@Override
	public boolean acceptance(char lecType) {
		return true;
	}

	// Prototype Design Pattern
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new College(colName, city, colType);
	}
}
