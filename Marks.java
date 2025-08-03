import java.util.Scanner;

public class Marks{
	// All the student information (their marks for each subject) is stored in a format of (studentID - 1) * 3 + (subjectID - 1).
	int[] studentMarks;
	// Total students amount.
	int n;

public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Marks mo = new Marks();

		System.out.print("Enter the number of students in the classroom: ");
		mo.n = scanner.nextInt();

		// To catch the empty line after nextInt().
		scanner.nextLine();

		mo.studentMarks = new int[mo.n * 3];

		System.out.println("1. Add student marks               : add [studentID]- student ID will be an integer ranging from 1 to n");
		System.out.println("2. Update student mark             : update [studentID] [subjectID] - subject ID will be an integer from 1 to 3");
		System.out.println("3. Get the average for a subject   : average_s [studentID]");
		System.out.println("4. Get the average for a student   : average [studentID]");
		System.out.println("5. Get the total mark of a student : total [studentID]");
		System.out.println("6. View the grades of a student    : grades [studentID]");
		System.out.println("7. Quit program                    : quit");
		System.out.println("----------------------------------------------------------------------------------------------------------------");

		while(true) {
			// To catch any missing arguments and wrong type inputs.
			try {
				System.out.print("Enter Command: ");
				String command = scanner.nextLine();

				String[] parts = command.split(" ");
				String action = parts[0];

				if(action.equals("add")) {
					int studentID = Integer.parseInt(parts[1]);

					addStudentMark(studentID, scanner, mo);
				}
				else if(action.equals("update")) {
					int studentID = Integer.parseInt(parts[1]);
					int subjectID = Integer.parseInt(parts[2]);

					updateMark(studentID, subjectID, scanner, mo);
				}
				else if(action.equals("average_s")) {
					int subjectID = Integer.parseInt(parts[1]);

					average_s(subjectID, mo);
				}
				else if(action.equals("average")) {
					int studentID = Integer.parseInt(parts[1]);

					average(studentID, mo);
				}
				else if(action.equals("total")) {
					int studentID = Integer.parseInt(parts[1]);

					total(studentID, mo);
				}
				else if(action.equals("grades")) {
					int studentID = Integer.parseInt(parts[1]);
					grades(studentID, mo);
				}
				else if(action.equals("quit")) {
					break;
				}
				else {
					System.out.println("!> Invalid Command");
				}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("!> Missing Arguments");
				} catch (NumberFormatException e) {
					System.out.println("!> Invalid Argument Type");
				}
		}
		scanner.close();
	}
// Add student marks in bulk.
public static void addStudentMark(int studentID, Scanner scanner, Marks mo){
		if(studentID > mo.n) {
			System.out.println("!> Invalid Index");

			return;
		}
		System.out.print("$ Math: ");
		mo.studentMarks[(studentID - 1) * 3 + 0]  = scanner.nextInt();

		// To catch the empty line after nextInt().
		scanner.nextLine();

		System.out.print("$ Chemistry: ");
		mo.studentMarks[(studentID - 1) * 3 + 1]  = scanner.nextInt();

		// To catch the empty line after nextInt().
		scanner.nextLine();

		System.out.print("$ Physics: ");
		mo.studentMarks[(studentID - 1) * 3 + 2]  = scanner.nextInt();

		// To catch the empty line after nextInt().
		scanner.nextLine();
	}
public static void updateMark(int studentID, int subjectID, Scanner scanner, Marks mo) {
		if(studentID > mo.n || subjectID > 3) {
			System.out.println("!> Invalid Index");

			return;
		}

		System.out.print("$ Mark: ");
		mo.studentMarks[(studentID - 1) * 3 + (subjectID - 1)]  = scanner.nextInt();

		// To catch the empty line after nextInt().
		scanner.nextLine();

	}
public static void average(int studentID, Marks mo) {
		if(studentID > mo.n) {
			System.out.println("!> Invalid Index");

			return;
		}

		int mark = 0;

		for(int i = (studentID - 1) * 3; i < ((studentID - 1) * 3 + 3); i++) {
			mark += mo.studentMarks[i];
		}

		System.out.println(">> Average Student Mark: " + (float)mark/3);
	}
public static void average_s(int subjectID, Marks mo) {
		if(subjectID > 3) {
			System.out.println("!> Invalid Index");

			return;
		}

		int mark = 0;

		// Stepping by 3 to access subjects.
		for(int i = 0; i < mo.n*3; i += 3) {
			// Subject = i + (subjectID - 1).
			mark += mo.studentMarks[i + (subjectID - 1)];
		}

		System.out.println(">> Average Subject Mark: " + (float)mark/mo.n);
	}
public static void total(int studentID, Marks mo) {
		if(studentID > mo.n) {
			System.out.println("!> Invalid Index");

			return;
		}

		int mark = 0;

		// from i = where the student's subjects starts in studentMarks to + 3 where they end.
		for(int i = (studentID - 1) * 3; i < ((studentID - 1) * 3 + 3); i++) {
			mark += mo.studentMarks[i];
		}

		System.out.println(">> Total Mark: " + mark);
	}
public static void grades(int studentID, Marks mo) {
		if(studentID > mo.n) {
			System.out.println("!> Invalid Index");

			return;
		}

		String[] grades = new String[3];

		// from i = where the student's subjects starts in studentMarks to + 3 where they end.
		for(int i = (studentID - 1) * 3; i < ((studentID - 1) * 3 + 3); i++) {
			// Have to reset j to 0 to use with grades.
			int j = i - (studentID - 1) * 3;

			int mark = mo.studentMarks[i];

			grades[j] = "Fail";
			if(mark >= 90) {grades[j] = "Grade A";}
			else if (mark >= 80) {grades[j] = "Grade B";}
			else if (mark >= 70) {grades[j] = "Grade C";}
			else if (mark >= 60) {grades[j] = "Grade D";}
		}
		System.out.println(">> Math: " + grades[0]);
		System.out.println(">> Chemistry: " + grades[1]);
		System.out.println(">> Physics: " + grades[2]);
	}
}
