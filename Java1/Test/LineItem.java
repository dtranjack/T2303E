package Test;

public class LineItem{
    private static Product product;
    private static int quantity;

    public LineItem(Product product, int quantity) {
        super();
        LineItem.product = product;
        LineItem.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        LineItem.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                '}';
    }

    public double cost() {
        return product.getPrice() * quantity;
    }

}
