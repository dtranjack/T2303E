package lab8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Student {

    private static long id;
    private static String name;
    private static String address;
    private static String phoneNumber;
    private static Date DoB;
    private static Date entryDate;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dobStr = dateFormat.format(DoB);
        String entryDateStr = dateFormat.format(entryDate);

        return "Student ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Date of Birth: " + dobStr + "\n" +
                "Entry Date: " + entryDateStr + "\n" +
                "Age: " + getAge() + "\n" +
                "Level: " + getLevel() + "\n";
    }


    private static final Student instance = new Student();

    private Student() {
        // Private constructor
    }

    public static Student getInstance() {
        return instance;
    }

    public Student(long id, String name, String address, String phoneNumber, Date DoB, Date entryDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.DoB = DoB;
        this.entryDate = entryDate;
    }

    public static boolean setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 7 && phoneNumber.matches("[0-9]+")) {
            Student.phoneNumber = phoneNumber;
            return true;
        } else {
            System.out.println("The phone number must have 7 digits !");
            System.out.println("Try again :");
            return false;
        }
    }

    public static Student inputInfo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Student ID: ");
            try {
                long id = scanner.nextLong();
                scanner.nextLine(); // Consume the newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("ID need to be a number, try again: ");
                scanner.nextLine(); // Consume the newline
            }
        }
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter a 7-digit phone number: ");
        while (true) {
            if (setPhoneNumber(scanner.nextLine()))
                break;
        }
        System.out.print("Enter Date of Birth in the (yy-MM-dd) format: ");
        String DoBstr = scanner.nextLine();
        System.out.print("Enter Date of enrollment in the (yy-MM-dd) format: ");
        String entryDatestr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        Date entryDate = null;

        try {
            dob = dateFormat.parse(DoBstr);
            entryDate = dateFormat.parse(entryDatestr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return new Student(id, name, address, phoneNumber, dob, entryDate);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() == 7 && phoneNumber.matches("[0-9]+")) {
            this.phoneNumber = phoneNumber;
            return true;
        } else {
            System.err.println("The phone number must have 7 digits !");
            System.out.println("Try again :");
            return false;
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getAge() {
        if (this.DoB != null) {
            // Calculate age based on the DoB
            Instant dobDate = this.DoB.toInstant();
            Instant currentDate = Instant.now();
            Duration duration = Duration.between(dobDate, currentDate);
            long years = duration.toDays() / 365;
            return Math.toIntExact(years);
        }
        return null;
    }

    public Integer getLevel() {
        if (this.entryDate != null) {
            Instant entryDate = this.entryDate.toInstant();
            Instant currentDate = Instant.now();
            Duration duration = Duration.between(entryDate, currentDate);
            long years = duration.toDays() / 365;
            return Math.toIntExact(years);
        }
        return null;
    }

    private static List<Student> studentList = new ArrayList<>();

    public Student addStudent() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Student student = Student.inputInfo();
            studentList.add(student);

            System.out.print("Do you want to add another student (y/n)? ");
            String another = scanner.next();
            if (!another.equalsIgnoreCase("y")) {
                break;
            }
        }

        studentList.sort(Comparator.comparingLong(Student::getId));
        return studentList.get(studentList.size() - 1);
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void showStudent() {
        System.out.println("Student List (Sorted by ID in Ascending Order):");
        studentList.forEach(System.out::println);
    }

    public static void updateStudent(List<Student> studentList, int studentId, Scanner scanner) {
        // Find the student with the specified ID
        Student studentToEdit = null;
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                studentToEdit = student;
                break;
            }
        }

        if (studentToEdit == null) {
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            // Display the current student information
            System.out.println("Current Information for Student ID " + studentToEdit.getId() + ":");
            System.out.println("Name: " + studentToEdit.getName());
            System.out.println("Address: " + studentToEdit.getAddress());
            System.out.println("Phone Number: " + studentToEdit.getPhoneNumber());
            // Add more student details as needed

            // Now implement the logic to edit the student's information
            System.out.print("Enter the new name (press Enter to keep the current value): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                studentToEdit.name = newName;
            }

            System.out.print("Enter the new address (press Enter to keep the current value): ");
            String newAddress = scanner.nextLine();
            if (!newAddress.isEmpty()) {
                studentToEdit.address = newAddress;
            }

            System.out.print("Enter the new phone number (press Enter to keep the current value): ");
            String newPhoneNumber = scanner.nextLine();
            if (!newPhoneNumber.isEmpty()) {
                studentToEdit.phoneNumber = newPhoneNumber;
            }

            System.out.print("Enter the new Date of Birth in the (yy-MM-dd) format (press Enter to keep the current value): ");
            String newDoB = scanner.nextLine();
            if (!newDoB.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
                try {
                    Date newDoBDate = dateFormat.parse(newDoB);
                    studentToEdit.DoB = newDoBDate;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Date of Birth not updated.");
                }
            }

            System.out.print("Enter the new entry date (yy-MM-dd format) (press Enter to keep the current value): ");
            String newEntryDate = scanner.nextLine();
            if (!newEntryDate.isEmpty()) {
                SimpleDateFormat entryDateFormat = new SimpleDateFormat("yy-MM-dd");
                try {
                    studentToEdit.entryDate = entryDateFormat.parse(newEntryDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Entry date not updated.");
                }
            }

            System.out.println("Student information updated.");
        }
    }

    public static void removeStudent(List<Student> studentList, int studentId, Scanner scanner) {
        Student studentToRemove = null;
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove == null) {
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            studentList.remove(studentToRemove);
            System.out.println("Student removed.");
        }
    }

    public static void showStudentWithSameAge(List<Student> studentList, int filterAge) {
        List<Student> studentsWithSameAge = new ArrayList<>();
        for (Student student : studentList) {
            Integer age = student.getAge();
            if (age != null && age == filterAge) {
                studentsWithSameAge.add(student);
            }
        }

        if (studentsWithSameAge.isEmpty()) {
            System.out.println("No students with the specified age found.");
        } else {
            System.out.println("Students with age " + filterAge + ":");
            studentList.forEach(System.out::println);
        }
    }

    public static void showStudentWithLevel(List<Student> studentList, int filterLevel) {
        List<Student> studentsWithSameLevel = new ArrayList<>();
        for (Student student : studentList) {
            Integer level = student.getLevel();
            if (level != null && level == filterLevel) {
                studentsWithSameLevel.add(student);
            }
        }

        if (studentsWithSameLevel.isEmpty()) {
            System.out.println("No students with the specified level found.");
        } else {
            System.out.println("Students from the level " + filterLevel + ":");
            studentList.forEach(System.out::println);
        }
    }
    public static void saveStudentListToTextFile(List<Student> studentList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : studentList) {
                writer.write("ID: " + student.getId() + "\n");
                writer.write("Name: " + student.getName() + "\n");
                writer.write("Address: " + student.getAddress() + "\n");
                writer.write("Phone Number: " + student.getPhoneNumber() + "\n");
                writer.write("Age: " + student.getAge() + "\n");
                writer.write("Level: " + student.getLevel() + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving student list to the text file: " + e.getMessage());
        }
    }

}
