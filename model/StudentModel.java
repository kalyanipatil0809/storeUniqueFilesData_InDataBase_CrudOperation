package in.sts.excelutility.model;

public class StudentModel {

	String firstName;
	String middleName;
	String lastName;
	String branch;
	MarksModel marksModel;

	public StudentModel() {
		super();
	}
	public StudentModel(String firstName, String middleName, String lastName, String branch) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.branch = branch;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public MarksModel getMarksModel() {
		return marksModel;
	}

	public void setMarksModel(MarksModel marksModel) {
		this.marksModel = marksModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentModel other = (StudentModel) obj;
		if (firstName.equals(other.firstName) && middleName.equals(other.middleName) && lastName.equals(other.lastName))
			return true;
		return true;
	}

	@Override
	public String toString() {
		return "StudentModel [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", branch=" + branch + "," + marksModel + "]";
	}
}
