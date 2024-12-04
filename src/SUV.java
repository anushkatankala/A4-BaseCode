//Class representing a single type of SUV
public class SUV extends PersonalVehicle {
    private boolean allWheelDrive;

    public SUV(String make, String model, String color, int year,
                 boolean allWheelDrive, double price, int invQuantity){
        super(make,model,color,year,price,invQuantity);
        this.allWheelDrive = allWheelDrive;
    }

    public String toString(){
        String result = super.toString();
        if(allWheelDrive){ result = "AWD SUV: " + result; }
        else{ result = "SUV: " + result; }
        return result;
    }
}
