public abstract class Person {
    private  String name;
    private  String phoneNumber;

    Person(String name , String phoneNumber){
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    protected Person() {
    }

    public void getInfo(){

        System.out.print( "Name: " + name + ", " + "Phone: " + phoneNumber + ", ");
    }


    public String getName(){
        return this.name;
    }
}

public class Customer extends Person{
    private final String customerId;
    private int loyaltyPoints;
    Customer(String name, String phoneNumber, String customerId, int loyaltyPoints) {
        super(name, phoneNumber);
        this.customerId=customerId;
        this.loyaltyPoints=loyaltyPoints;
    }
     @Override
    public void getInfo(){

         System.out.print("ID: " + this.customerId + ", ");
        super.getInfo();
         System.out.println("Loyalty Points: " + this.loyaltyPoints);

    }
    public int getLoyaltyPoints(){
        return this.loyaltyPoints;
    }

    public void addLoyaltyPoints(double totalPrice ){
        if (totalPrice>1000){
            this.loyaltyPoints += 2;
        } else if (totalPrice>500) {
            this.loyaltyPoints += 1;
        }
    }

    public double getDiscount(){
        double discount=1;
        if (loyaltyPoints>5){
           return  discount= 0.9;
        } else if (loyaltyPoints>3) {
            return  discount= 0.95;
        }else {
            return 1;
        }
    }
}


public class Employee extends Person{
    private final String employeeId;
    private final String position;
    private double salary;
    private double hoursWorked;
    Employee(String name, String phoneNumber, String employeeId, String position, double salary, double hoursWorked){
        super(name,phoneNumber);
        this.employeeId=employeeId;
        this.position=position;
        this.salary=salary;
        this.hoursWorked=hoursWorked;
    }


    @Override
    public void getInfo(){
        System.out.print("ID: " + this.employeeId + ", " );
        super.getInfo();
        System.out.print("Position: " + this.position + ", " );
        System.out.println("HoursWorked: " + this.hoursWorked);
    }

    public double addHoursWorked(double addedHours){
        return this.hoursWorked+=addedHours;

    }

   public double calculateSalary() {
       double baseSalary = this.salary;
       double overtime = this.hoursWorked - 160.00;
       if (this.hoursWorked < 160.00) {
           return this.salary = 0;
       } else {
           return this.salary = baseSalary + ((overtime/160.00)* (baseSalary * 1.5));
       }
   }
}


public abstract class MenuItem {
    private String itemId;
    private String name;
    private double price;
    private String category;

    MenuItem(String itemId, String name, double price, String category){
        this.itemId=itemId;
        this.name=name;
        this.price=price;
        this.category=category;
    }

    public void getDetails(){
        System.out.print("ID: " + itemId + ", " + "Name: " + name + ", " + "Price: " + price + ", " + "Category: " + category + ", " );

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}

public class Food extends MenuItem{
    String spiceLevel;
    int preparationTime;


    Food(String itemId, String name, double price, String category, String spiceLevel, int preparationTime) {

        super(itemId, name, price, category);
        this.spiceLevel=spiceLevel;
        this.preparationTime=preparationTime;
    }

    @Override
    public void getDetails(){
        super.getDetails();
        System.out.println("Spice: " + spiceLevel + ", " + "Preparation Time: " + preparationTime + "min");

    }


}

public class Beverage extends MenuItem{

    String Size;
    String temperature;

    Beverage(String itemId, String name, double price, String category, String Size, String temperature) {

        super(itemId, name, price, category);
        this.Size=Size;
        this.temperature=temperature;
    }

    @Override
    public void getDetails(){
        super.getDetails();
        System.out.println("Size: " + Size + ", " + "Temperature: " + temperature);
    }


}

public class Order {
    protected int OrderId;
    Customer customer;
    MenuItem[] items;
    double totalAmount;
    private static int OrderIdHistory=-1;
    private static boolean isFirstOrder=true;


    Order(int OrderId, Customer customer, MenuItem[] items){
        if (!isFirstOrder && OrderId!= OrderIdHistory + 1){
            throw new IllegalArgumentException(
                    "ORDER ID must be exactly " + (OrderIdHistory + 1) + " but was " + OrderId
            );
        }
        OrderIdHistory=OrderId;
        isFirstOrder=false;
        this.OrderId=OrderId;
        this.OrderId=OrderId + OrderIdHistory;

        this.customer=customer;
        this.items=items;

    }

    public String showItems(){
        StringBuilder itemsList= new StringBuilder();
        for (MenuItem item : items){
              itemsList.append(item).append(" - ");
        }
        itemsList.delete((4*(items.length)-3) , (4*(items.length)));
        return itemsList.toString();
    }

    public double calculateTotal(){
        double totalInitialPrice=0;
        double totalFinalPrice=0;
        for ( MenuItem item : items){
            totalInitialPrice+=item.getPrice();
        }
        customer.addLoyaltyPoints( totalInitialPrice);
       return   totalFinalPrice= totalInitialPrice*(customer.getDiscount());
    }

    public String getOrderSummary(){
        return "Order ID: " + OrderId + ", " + "Customer: " + customer.getName() + "Total Amount: " + calculateTotal()
                + "Items: " + showItems();
    }
}

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main (String[] args){
        Employee chef= new Employee("greg", "09334565325", "E001", "chef", 20000.00, 7.00 );
        Employee accountant= new Employee("bob", "09954323634", "E002", "accountant", 15000.00, 5.00);
        Employee waiter= new Employee("alice", "09966754321", "E003", "waiter", 10000.00, 7.00);

        Customer customer1= new Customer("asghar", "09032445767", "C001", 2);
        Customer customer2 = new Customer("nader", "09806576654", "C002", 1);
        Customer customer3 = new Customer("michael", "09912345676", "C003", 3);
        Customer customer4 = new Customer("warren Buffet", "09908525300", "C000", 23 );
        Customer customer5 = new Customer("jimmy no hands", "09987654322", "C004", 0 );
        Customer[] customerList= {customer1, customer2, customer3, customer4, customer5};

        Food hamburger= new Food("HAMB", "hamburger", 5.99, "fastFood", "mild", 12);
        Food hotDog = new Food("hootOne", "hotDog", 3.00, "fastFood", "medium", 7);
        Food pizza = new Food("pizza(MMA MIA)", "pizza", 15.00, "fastFood", "spicy", 20);
        Beverage coke= new Beverage("cokeEnergi", "coca", 2.99, "coldBeverage", "medium", "cold");
        Beverage pepsiMnaFluids= new Beverage("pepsiMAN!", "pepsicoDrink", 2.99, "coldBeverage", "medium", "cold");
        Beverage stillWater= new Beverage("justWater", "stillWater", 0.99, "beverage", "medium", "cold");
        Beverage coffee= new Beverage("C", "coffee", 5.99, "hotBeverage", "small", "hot");
        Beverage tea= new Beverage("T", "tea", 5.99, "hotBeverage", "medium", "hot");
        Food spicyMeatBalls= new Food("SM", "spicyMeatBalls", 8.99, "nationalFoods", "spicy", 23);
        MenuItem[] itemsList={hamburger, hotDog, pizza, coke, pepsiMnaFluids, stillWater, coffee, tea, spicyMeatBalls};

        Order order1=new Order(1, customer1,new MenuItem[]{hamburger,coke} );
        Order order2=new Order(2,customer1, new  MenuItem[]{hotDog, stillWater});
        Order order3=new Order(3,customer1, new MenuItem[]{pizza, pepsiMnaFluids});
        Order order4=new Order(4,customer2, new MenuItem[]{spicyMeatBalls,coffee});
        Order order5=new Order(5,customer2, new MenuItem[]{pizza,coke});
        Order order6=new Order(6,customer2, new MenuItem[]{hotDog,hamburger,coke});
        Order order7=new Order(7,customer3, new MenuItem[]{hamburger,pepsiMnaFluids});
        Order order8=new Order(8,customer3, new MenuItem[]{pizza,coke});
        Order order9=new Order(9,customer3, new MenuItem[]{pizza,coffee});
        Order order10=new Order(10,customer4, new MenuItem[]{hamburger,coke});
        Order order11=new Order(11,customer4, new MenuItem[]{hamburger,coke});
        Order order12=new Order(12,customer4, new MenuItem[]{hamburger,coke});
        Order order13=new Order(13,customer5, new MenuItem[]{hotDog,pepsiMnaFluids});
        Order order14=new Order(14,customer5, new MenuItem[]{spicyMeatBalls,coffee});
        Order order15=new Order(15,customer5, new MenuItem[]{spicyMeatBalls,coke});


        chef.addHoursWorked(2.00);
        waiter.addHoursWorked(2.00);
        accountant.addHoursWorked(0.5);


        chef.calculateSalary();
        waiter.calculateSalary();
        accountant.calculateSalary();


        HashMap<String,Integer> loyaltyPoints = new HashMap<>();
        for (Customer customer : customerList){
                loyaltyPoints.put(customer.getName(), customer.getLoyaltyPoints());
        }
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : loyaltyPoints.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        System.out.println("Most Loyal Customer: " + maxKey);
        System.out.println("Loyalty Points of Customer " + maxKey + ": " + maxValue);

        customer1.getInfo();
        customer2.getInfo();
        customer3.getInfo();
        customer4.getInfo();
        customer5.getInfo();

        chef.getInfo();
        waiter.getInfo();
        accountant.getInfo();

        order1.showItems();
        order2.showItems();
        order3.showItems();
        order4.showItems();
        order5.showItems();
        order6.showItems();
        order7.showItems();
        order8.showItems();
        order9.showItems();
        order10.showItems();
        order11.showItems();
        order12.showItems();
        order13.showItems();
        order14.showItems();
        order15.showItems();
    }
}
