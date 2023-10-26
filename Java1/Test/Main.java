package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialize products
        Product tomato = new Product("F523", "Tomato", "Food", 1.5, 589);
        Product zaraShirt = new Product("A763", "Zara shirt", "Appearance", 12.0, 90);
        Product kitchenTable = new Product("H320", "Kitchen table", "Household", 299.0, 13);
        Product iPhone = new Product("E092", "IPhone", "Electronic", 1000.0, 4);
        Product footBall = new Product("S108", "Football", "Sport", 19.9, 2);

        // Create an order for Mike Tyson
        Order order = new Order();
        order.setCustomerName("Mike Tyson");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date transactionTime = dateFormat.parse("2023-08-08 11:30:00");
            order.setTransactionTime(transactionTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add products to the order
        int tomatoQuantity = 5;
        int iPhoneQuantity = 2;
        int footballQuantity = 4;

        boolean addedTomato = order.addProduct(tomato, tomatoQuantity);
        boolean addediPhone = order.addProduct(iPhone, iPhoneQuantity);
        boolean addedFootball = order.addProduct(footBall, footballQuantity);

        // Display the result of adding products
        System.out.println("Added 5 Tomato to the order: " + (addedTomato ? "Success" : "Fail"));
        System.out.println("Added 2 IPhone to the order: " + (addediPhone ? "Success" : "Fail"));
        System.out.println("Added 4 Football to the order: " + (addedFootball ? "Success" : "Fail"));

        // Calculate and display the total cost of the order
        double totalCost = order.getTotalCost();
        System.out.println("Total Cost of the Order: $" + totalCost);

        // Print all the information of the order
        System.out.println("Order Information:");
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Transaction Time: " + dateFormat.format(order.getTransactionTime()));
        System.out.println("Delivery Address: " + DeliveryOrder.getAddress());
        System.out.print("\n");

        // Create a new DeliveryOrder for Chris Evans
        Order chrisOrder = new Order();
        chrisOrder.setCustomerName("Chris Evans");
        chrisOrder.setAddress("123 Cau Giay");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date transactionTime = dateFormat2.parse("2023-08-09 13:14:00");
            chrisOrder.setTransactionTime(transactionTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add products to the order for Chris Evans
        int zaraShirtQuantity = 3;
        int iPhoneQuantity2 = 3;

        boolean addedZaraShirt = chrisOrder.addProduct(zaraShirt, zaraShirtQuantity);
        boolean addediPhone2 = chrisOrder.addProduct(iPhone, iPhoneQuantity2);

        // Display the result of adding products
        System.out.println("Added 3 Zara shirts to the order: " + (addedZaraShirt ? "Success" : "Fail"));
        System.out.println("Added 3 iPhones to the order: " + (addediPhone2 ? "Success" : "Fail"));

        // Calculate and display the total cost of the order for Chris Evans
        double totalCostChris = chrisOrder.getTotalCost();
        System.out.println("Total Cost of the Order for Chris Evans: $" + totalCostChris);

        // Print all the information of the order for Chris Evans
        System.out.println("Order Information for Chris Evans:");
        System.out.println("Customer: " + chrisOrder.getCustomerName());
        System.out.println("Transaction Time: " + dateFormat.format(chrisOrder.getTransactionTime()));
        System.out.println("Delivery Address: " + chrisOrder.getAddress());
    }
}
