//Class representing an AutoPark
//has an array of items that represent the products the auto park can sell

public class AutoPark{
    private final int MAX_ITEMS = 10;
    private static int totalItem;
    private String name;
    private double revenue;
    private Item[] items;

    public AutoPark(String name){
        this.name = name;
        revenue = 0;
        items = new Item[MAX_ITEMS];
        totalItem = 0;
    }

    public Item[] getItems(){ return items;}
    public int getTotalItem() { return totalItem;}

    //Adds an item and returns true if there is space in the array
    //Returns false otherwise
    public boolean addItem(Item newItem){
    if(totalItem < MAX_ITEMS){
     items[totalItem] = newItem;
      totalItem++;
            return true;
        }
        return false;
    }
    

    public static AutoPark createPark() {
        AutoPark park1 = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park1.addItem(s1);
        park1.addItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true,32000,10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false,21000,10);
        park1.addItem(suv1);
        park1.addItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods",true,28000,10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment",false,30000,10);
        park1.addItem(t1);
        park1.addItem(t2);
        Minivan v1 = new Minivan("Ford", "Transit", 2020, "goods",true,22000,10);
        Minivan v2 = new Minivan("Ram", "ProMaster", 2019, "equipment",false,19000,10);
        park1.addItem(v1);
        park1.addItem(v2);
        Tire tire1 = new Tire(10,30,true,390, 20);
        Tire tire2 = new Tire(12,35,false,320,20);
        park1.addItem(tire1);
        park1.addItem(tire2);
        return park1;
    }
}
