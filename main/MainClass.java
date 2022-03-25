package in.sts.excelutility.main;

import java.util.HashSet;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

import in.sts.excelutility.dao.StudentDao;
import in.sts.excelutility.files.ReadExcelTable;
import in.sts.excelutility.files.ReadTextTable;
import in.sts.excelutility.files.ReadXMLFile;
import in.sts.excelutility.model.StudentModel;
import in.sts.excelutilty.reader.ReadFiles;

public class MainClass {
	public static void main(String[] args) {

		BasicConfigurator.configure();

		HashSet<StudentModel> uniqueSet = new HashSet<StudentModel>();
		StudentDao studentDao = new StudentDao();
		ReadTextTable readTextTable = new ReadTextTable();
		ReadExcelTable readExcelTable = new ReadExcelTable();
		ReadXMLFile readeXmlFile = new ReadXMLFile();

		Scanner selectcase = new Scanner(System.in);

		System.out.println("Select 1 to read the unique data from files");
		System.out.println("Select 2 to store the unique data into database");
		System.out.println("Select 3 to update the data ");
		System.out.println("Select 4 to search the data ");

		int select = selectcase.nextInt();
		switch (select) {

		case 1:
			ReadFiles readfiles = new ReadFiles();
			readfiles.readUniqueData();
			break;

		case 2:
			Scanner choicecase = new Scanner(System.in);
			System.out.println("Select A to store text file data");
			System.out.println("Select B to store excel file data");
			System.out.println("Select C to store xml file data");
			int choice = choicecase.nextInt();
			switch (choice) {
			case 1:
				uniqueSet = readTextTable.readTable();
				studentDao.insert(uniqueSet);
				break;

			case 2:
				uniqueSet = readExcelTable.readExcel();
				studentDao.insert(uniqueSet);
				break;

			case 3:
				uniqueSet = readeXmlFile.readXML();
				studentDao.insert(uniqueSet);
				break;
			}
			break;

		case 3:
			Scanner updatecase = new Scanner(System.in);
			System.out.println("Enter 1 to update text file data");
			System.out.println("Enter 2 to update excel file data");
			System.out.println("Enter 3 to update xml file data");

			int update = updatecase.nextInt();
			switch (update) {
			case 1:
				uniqueSet = readTextTable.readTable();
				studentDao.update(uniqueSet);
				break;

			case 2:
				uniqueSet = readExcelTable.readExcel();
				studentDao.update(uniqueSet);
				break;

			case 3:
				uniqueSet = readeXmlFile.readXML();
				studentDao.update(uniqueSet);
				break;
			}
			break;

		case 4:
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the subject name: ");
			String name = scanner.nextLine();
			studentDao.getHighestMarksInSubject(name);
			break;
		}
		selectcase.close();
	}
}
