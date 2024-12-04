//Class representing a single type of Tire
public class Tire extends Item {
    private int wheelDiameter;
    private int sectionWidth;
    private boolean passengerTire;

    public Tire(int wheelDiameter, int sectionWidth, boolean passengerTire, double price, int invQuantity){
        super(price, invQuantity);
        this.wheelDiameter = wheelDiameter;
        this.sectionWidth = sectionWidth;
        this.passengerTire = passengerTire;
    }

    public String toString(){
        String result = "";
        if(passengerTire) result += "Passenger tire";
        else result = "Front Tire";
        return result + " with " + wheelDiameter + "\" wheel diameter " + sectionWidth +
                " mm. section width, price $" + String.format("%,.2f",getPrice()) + " each (" + getInvQuantity() +" in stock, " + getSoldQuantity()
                + " sold).";
    }
}
