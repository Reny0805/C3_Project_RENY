import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime=getCurrentTime();
        if(openingTime.isBefore(currentTime)&&closingTime.isAfter(currentTime))
             return true;
        else
            return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException {
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException("Item not found in Restaurant");
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }
    public String getName() {
        return name;
    }
    public int getOrderValue(List<String> itemNameList) throws itemNotFoundException {
        int totalCost=0;
        Item item;
        for (String itemName : itemNameList) {
            item = findItemByName(itemName);
            totalCost = totalCost + item.getPrice();
        }
        return totalCost;
    }

}
