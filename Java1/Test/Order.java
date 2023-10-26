package Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends DeliveryOrder {
    private String customerName;
    private Date transactionTime;
    private List<LineItem> lineItems = new ArrayList<>();
    private double totalCost = 0; // Initialize total cost

    public Order() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean addProduct(Product product, int quantityToPurchase) {
        int availableQuantity = product.getQuantity();

        if (quantityToPurchase <= availableQuantity) {
            // Create a new instance of LineItem and add it to the order's line item list
            LineItem lineItem = new LineItem(product, quantityToPurchase);
            lineItems.add(lineItem);

            // Update product availability
            product.setQuantity(availableQuantity - quantityToPurchase);

            // Calculate the cost of the new line item and add it to the total cost
            double lineItemCost = lineItem.cost();
            totalCost += lineItemCost;

            return true;
        } else {
            // Quantity to purchase is greater than available quantity, do nothing and return false
            return false;
        }
    }

    public double getTotalCost() {
        return totalCost;
    }
}
