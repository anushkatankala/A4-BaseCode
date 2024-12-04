//Class representing a single type of Minivan
public class Minivan extends CommercialVehicle {
    private boolean covered;

    public Minivan(String make, String model, int year,
                   String carries, boolean covered, double price, int invQuantity) {
        super(make, model, year, carries, price, invQuantity);
        this.covered = covered;
    }

    public boolean getCovered(){return covered;}

    public String toString(){
        String result = super.toString();
        if(covered){ result = "Covered Minivan: " + result; }
        else{ result = "Minivan: " + result; }
        return result;
    }


}
