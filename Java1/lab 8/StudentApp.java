package lab8;

import java.util.*;

import static lab8.Student.saveStudentListToTextFile;

public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("Menu:");
            System.out.println("1. Add More Student");
            System.out.println("2. Show Student List");
            System.out.println("3. Add More Students");
            System.out.println("4. Remove a Student");
            System.out.println("5. Show Students with the Same Age");
            System.out.println("6. Show Students with the Same Level");
            System.out.println("7. Save Student List to a Text File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            Student studentInstance = Student.getInstance();

            switch (choice) {
                case 1:
                    // Add a student
                    Student newStudent = studentInstance.addStudent();
                    System.out.println("Student added: ID - " + newStudent.getId() + ", Name - " + newStudent.getName());
                    break;

                case 2:
                    // Show the student list
                    List<Student> studentList = studentInstance.getStudentList();
                    if (studentList.isEmpty()) {
                        System.out.println("No students existed.");
                    } else {
                        studentInstance.showStudent();
                        }
                    break;

                case 3:
                    //edit a student using ID
                    studentList = studentInstance.getStudentList();
                    if (studentList.isEmpty()) {
                        System.out.println("No students existed.");
                    } else {
                        System.out.print("Enter the ID of the student to edit: ");
                        int editStudentId = scanner.nextInt();
                        scanner.nextLine();
                        studentInstance.updateStudent(studentList, editStudentId, scanner);
                    }
                    break;

                case 4:
                    // Remove a student using ID
                    studentList = studentInstance.getStudentList();
                    if (studentList.isEmpty()) {
                        System.out.println("No students existed.");
                    } else {
                        System.out.print("Enter the ID of the student to remove: ");
                        int removeStudentId = scanner.nextInt();
                        scanner.nextLine();
                        studentInstance.removeStudent(studentList, removeStudentId, scanner);
                    }
                    break;

                case 5:
                    // Show students with the same age
                    studentList = studentInstance.getStudentList();
                    if (studentList.isEmpty()) {
                        System.out.println("No students existed.");
                    } else {
                        System.out.print("Enter the age to filter the student of that age: ");
                        int filterAge = scanner.nextInt();
                        scanner.nextLine();
                        studentInstance.showStudentWithSameAge(studentList, filterAge);
                    }
                    break;

                case 6:
                    // Show students with the same level
                    studentList = studentInstance.getStudentList();
                    if (studentList.isEmpty()) {
                        System.out.println("No students existed.");
                    } else {
                        System.out.print("Enter the level to filter the student of that level: ");
                        int filterLevel = scanner.nextInt();
                        scanner.nextLine();
                        studentInstance.showStudentWithLevel(studentList, filterLevel);
                    }
                    break;

                case 7:
                    // Save student list to a text file and exit
                    studentList = studentInstance.getStudentList();
                    saveStudentListToTextFile(studentList, "student_list.txt");
                    System.out.println("Student list saved to student_list.txt.");
                    System.out.println("Exiting the application.");
                    System.exit(0);

                case 8:
                    // Exit the application
                    System.out.println("Exiting the application.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}
