import java.util.HashMap;
import java.util.Scanner;

public class StudentRecordSystem {

    // Static variables for student management
    static HashMap<Integer, Student> studentRecords = new HashMap<>();
    static int totalStudents = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> updateStudent(scanner);
                case 3 -> viewStudent(scanner);
                case 4 -> {
                    exit = true;
                    System.out.println("Exiting the system. Have a great day!");
                }
                default -> System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }

    // Display the menu options
    public static void displayMenu() {
        System.out.println("\n--- Student Record Management System ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Update Student Information");
        System.out.println("3. View Student Details");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new student
    public static void addStudent(Scanner scanner) {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        int id = getValidIntegerInput(scanner);

        if (studentRecords.containsKey(id)) {
            System.out.println("Student ID already exists. Please use a unique ID.");
            return;
        }

        scanner.nextLine();  // Clear input buffer
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = getValidIntegerInput(scanner);
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        studentRecords.put(id, new Student(id, name, age, grade));
        totalStudents++;
        System.out.println("Student added successfully!");
    }

    // Update existing student details
    public static void updateStudent(Scanner scanner) {
        System.out.println("\n--- Update Student Information ---");
        System.out.print("Enter Student ID to update: ");
        int id = getValidIntegerInput(scanner);

        if (studentRecords.containsKey(id)) {
            Student student = studentRecords.get(id);
            scanner.nextLine();  // Clear input buffer
            System.out.print("Enter New Name (Leave blank to retain current): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) student.setName(name);

            System.out.print("Enter New Age (0 to retain current): ");
            int age = getValidIntegerInput(scanner);
            if (age != 0) student.setAge(age);

            System.out.print("Enter New Grade (Leave blank to retain current): ");
            String grade = scanner.nextLine();
            if (!grade.isBlank()) student.setGrade(grade);

            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // View student details
    public static void viewStudent(Scanner scanner) {
        System.out.println("\n--- View Student Details ---");
        System.out.print("Enter Student ID: ");
        int id = getValidIntegerInput(scanner);

        if (studentRecords.containsKey(id)) {
            System.out.println(studentRecords.get(id));
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Helper method for integer input validation
    public static int getValidIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

// Student class with encapsulation
class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "\nStudent ID: " + id +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGrade: " + grade;
    }
}
