package session7.entity;

import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Student student = Student.inputInfo();
            studentList.add(student);

            System.out.print("Do you want to add another student (y/n)? ");
            String another = scanner.next();
            if (!another.toLowerCase().equals("y")) {
                break;
            }
        }

        studentList.sort(Comparator.comparingInt(Student::getId));

        System.out.println("Student List (Sorted by ID in Ascending Order):");
        for (Student s : studentList) {
            System.out.println("ID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Address: " + s.getAddress());
            System.out.println("Phone Number: " + s.getPhoneNumber());
            System.out.println();
        }
    }
}
