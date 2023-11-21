package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Customer> customerMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCustomer Relationship Management");
            System.out.println("Select an action you want to perform");
            System.out.println("1. Add new customer");
            System.out.println("2. Find by name");
            System.out.println("3. Display all");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    //Add new customer
                    addCustomer();
                    break;
                case 2:
                    //Find by name
                    findByName();
                    break;
                case 3:
                    //Display all
                    displayAllCustomers();
                    break;
                case 4:
                    //Exit
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private static void addCustomer() {
        while (true) {
            System.out.println("\nAdding a new customer:");
            System.out.print("Input new customer's name: ");
            String name = scanner.nextLine();

            if (customerMap.containsKey(name)) {
                System.out.println("A customer with the name '" + name + "' already exists.");
                continue;
            }

            System.out.print("Input new customer's email: ");
            String email = scanner.nextLine();
            System.out.print("Enter their phone number: ");
            String phone = scanner.nextLine();

            Customer newCustomer = new Customer(name, email, phone);
            customerMap.put(name, newCustomer);
            System.out.println("Customer added successfully!");

            System.out.print("Do you want to add another customer (y/n)? ");
            String another = scanner.next();
            if (!another.equalsIgnoreCase("y")) {
                break;
            }
            scanner.nextLine();
        }
    }

    private static void findByName() {
        System.out.println("\nFinding customer by name:");
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        Customer customer = customerMap.get(name);
        if (customer != null) {
            if (customer.getPhone() != null && !customer.getPhone().isEmpty()) {
                System.out.println("Customer details: " + customer);
            } else {
                System.err.println("Customer found, but their phone number is Not found");
            }
        } else {
            System.err.println("Can't find the customer with the name: " + name);
        }
    }

    private static void displayAllCustomers() {
        System.out.println("\nDisplaying all customers:");
        if (customerMap.isEmpty()) {
            System.err.println("No customers found.");
        } else {
            customerMap.values().forEach(System.out::println);
        }
    }
}
