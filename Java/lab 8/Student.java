package lab8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Student {
    private final int id;
    private String name;
    private String address;
    private String phoneNumber;
    private Date DoB;
    private Date entryDate;

    public Student(int id, String name, String address, String phoneNumber, Date DoB, Date entryDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.DoB = DoB;
        this.entryDate = entryDate;
    }

    public static Student inputInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter 7-Digit Phone Number: ");
        String phoneNumber = scanner.next();
        scanner.nextLine();
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

    public int getId() {
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

    private static final List<Student> studentList = new ArrayList<>();

    public static Student addStudent() {
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

        studentList.sort(Comparator.comparingInt(Student::getId));
        return studentList.get(studentList.size() - 1);
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void showStudent() {
        System.out.println("Student List (Sorted by ID in Ascending Order):");
        for (Student s : studentList) {
            System.out.println("ID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Address: " + s.getAddress());
            System.out.println("Phone Number: " + s.getPhoneNumber());
            System.out.println("Age: " + s.getAge());
            System.out.println("Level: " + s.getLevel() + "\n");

        }
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
            for (Student s : studentsWithSameAge) {
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Address: " + s.getAddress());
                System.out.println("Phone Number: " + s.getPhoneNumber());
                System.out.println("Age: " + s.getAge());
                System.out.println("Level: " + s.getLevel() + "\n");
            }
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
            for (Student s : studentsWithSameLevel) {
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Address: " + s.getAddress());
                System.out.println("Phone Number: " + s.getPhoneNumber());
                System.out.println("Age: " + s.getAge());
                System.out.println("Level: " + s.getLevel() + "\n");
            }
        }
    }
}
