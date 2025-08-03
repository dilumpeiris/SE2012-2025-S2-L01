import java.util.Scanner;

public class Welcome {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();
		
		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();

		System.out.println("Welcome to the Second Year " + firstName + " " + lastName);

		scanner.close();
	}
}
