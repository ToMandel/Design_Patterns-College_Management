package Model;

import Model.Lecturer.academicDegree;

class CollegeProxy extends College {
	private static final long serialVersionUID = 1L;

	public CollegeProxy(PermanentLecturer permLec) {
		super(permLec);
	}

	public CollegeProxy(ExternalLecturer extLec) {
		super(extLec);
	}

	@Override
	public boolean acceptance(char lecType) {
		if (lecType == 'P') {
			if (permLec.getSeniority() >= 5) {
				return super.acceptance(lecType);
			} else {
				return false;
			}
		} else {
			if (extLec.getAcDegree() != academicDegree.First) {
				return super.acceptance(lecType);
			} else {
				return false;
			}
		}
	}
}
