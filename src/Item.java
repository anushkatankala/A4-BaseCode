//Base class for all items the AutoPark sells
public abstract class Item {
    private double price;
    private int invQuantity;
    private int soldQuantity;

    public Item(double price, int invQuantity){
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount){
        if( amount > 0 && invQuantity >= amount){
            invQuantity -= amount;
            soldQuantity += amount;
            return amount * price;
        }
        return 0;
    }


    public abstract String toString();
    public double getPrice() { return price; }
    public int getSoldQuantity() { return soldQuantity;}
    public int getInvQuantity() { return invQuantity; }
}
