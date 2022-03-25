package in.sts.excelutility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import in.sts.excelutility.model.MarksModel;
import in.sts.excelutility.model.StudentModel;
import in.sts.excelutility.mysqlconnection.DBConnection;

public class StudentDao {
	final int FIRST_NAME = 1;
	final int MIDDLE_NAME = 2;
	final int LAST_NAME = 3;
	final int BRANCH = 4;
	final int MATHS = 5;
	final int ENGLISH = 6;
	final int SCIENCE = 7;
	PreparedStatement preparedStatement = null;

	HashSet<StudentModel> uniqueSet = new HashSet<StudentModel>();

	public void insert(HashSet<StudentModel> uniqueSet) {
		try (Connection connection = DBConnection.connect()) {
			String insertQuery = "Insert into StudentDetails values(student_id,?,?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(insertQuery);

			if (preparedStatement != null && uniqueSet != null) {
				for (StudentModel studentModel : uniqueSet) {
					preparedStatement.setString(FIRST_NAME, studentModel.getFirstName());
					preparedStatement.setString(MIDDLE_NAME, studentModel.getMiddleName());
					preparedStatement.setString(LAST_NAME, studentModel.getLastName());
					preparedStatement.setString(BRANCH, studentModel.getBranch());
					preparedStatement.setInt(MATHS, studentModel.getMarksModel().getMaths());
					preparedStatement.setInt(ENGLISH, studentModel.getMarksModel().getEnglish());
					preparedStatement.setInt(SCIENCE, studentModel.getMarksModel().getScience());
					preparedStatement.executeUpdate();
				}
			}
			System.out.println("Data uploaded in database");
			connection.close();

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException sqlException) {
					// TODO Auto-generated catch block
					System.out.println("Message = " + sqlException.getMessage());
				}
			}
		}
	}
	public void update(HashSet<StudentModel> uniqueSet) {
		try (Connection connection = DBConnection.connect()) {
			for (StudentModel studentModel : uniqueSet) {
				MarksModel marks = studentModel.getMarksModel();
				String updateQuery = "update StudentDetails set branch=?, maths=? ,english=? ,science=?  where firstName=? and middleName=? and lastName=?";
				preparedStatement = connection.prepareStatement(updateQuery);

				preparedStatement.setString(1, studentModel.getBranch());
				preparedStatement.setInt(2, marks.getMaths());
				preparedStatement.setInt(3, marks.getEnglish());
				preparedStatement.setInt(4, marks.getScience());
				preparedStatement.setString(5, studentModel.getFirstName());
				preparedStatement.setString(6, studentModel.getMiddleName());
				preparedStatement.setString(7, studentModel.getLastName());

				preparedStatement.executeUpdate();
			}
			System.out.println("Data updated in database");

		} catch (SQLException sqlException) {
			System.out.println("Message = " + sqlException.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException sqlException) {
					System.out.println("Message = " + sqlException.getMessage());
				}
			}
		}
	}
	public void getHighestMarksInSubject(String subjectName) {

		try (Connection connection = DBConnection.connect()) {
		
			String fetchQuery=null;
			if(subjectName.toLowerCase().equals("maths"))
			{
				fetchQuery = "select firstName,branch,maths from studentdetails where maths in (select max(maths) from studentdetails)";
			}
			else if(subjectName.equalsIgnoreCase("science"))
			{
				fetchQuery = "select firstName,branch,science from studentdetails where science in (select max(science) from studentdetails)";
			}
			else if(subjectName.equalsIgnoreCase("english"))
			{
				fetchQuery = "select firstName,branch,english from studentdetails where english in (select max(english) from studentdetails)";
			}
			preparedStatement = connection.prepareStatement(fetchQuery);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("firstName") + "--- " + rs.getString("branch") + "---" +rs.getInt(subjectName));
			}
			System.out.println("Data searched successfully");

		} catch (SQLException sqlException) {
			System.out.println("Message = " + sqlException.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException sqlException) {
					System.out.println("Message = " + sqlException.getMessage());
				}
			}
		}
	}
}
