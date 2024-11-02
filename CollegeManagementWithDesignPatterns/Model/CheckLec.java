package Model;

public class CheckLec {

	public Log log;

	public CheckLec(Log log) {
		this.log = log;
	}

	public String CompareOrg(Organization org1, Organization org2) {

		if (org1.getMembers().size() > org2.getMembers().size()) {
			return log.info("The Permanent Lecturer Organization is Bigger");
		} else if (org2.getMembers().size() > org1.getMembers().size()) {
			return log.info("The External Lecturer Organization is Stronger");
		} else {
			return log.info("Both Organizations are even");
		}
	}
}
