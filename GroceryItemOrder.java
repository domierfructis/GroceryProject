public class GroceryItemOrder{
    String name;
    int quantity;
    double pricePerUnit;
    public GroceryItemOrder(String name, int quantity, double pricePerUnit){
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }
    public double getCost(){
        return quantity * pricePerUnit;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.pricePerUnit = price;
    }
    public String toString() {
            return this.quantity + " units of " + this.name;
    }
}