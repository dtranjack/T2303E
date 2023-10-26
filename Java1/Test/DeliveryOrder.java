package Test;

public class DeliveryOrder {
    private static String address;

    public DeliveryOrder() {
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        DeliveryOrder.address = address;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
