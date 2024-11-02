package Model;

//Bridge
public interface salaryCalc {
	final static int BASE_PERM = 5500;
	final static int BASE_EXT = 150;

	double permanentSalary(double seniority);

	double ExternalSalary(int workTime);
}
