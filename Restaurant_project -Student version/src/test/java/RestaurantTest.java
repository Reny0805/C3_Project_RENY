import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {
    Restaurant restaurant;
    @BeforeEach
    void init(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //  LocalTime openTime = parse("09:00");
        //LocalTime closeTime = parse("21:00");
        //restaurant=new Restaurant("Pizza Hut","Coimbatore",openTime,closeTime);
        Restaurant mockRestaurant=Mockito.spy(restaurant);
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("13:01"));
        assertEquals(true,mockRestaurant.isRestaurantOpen());


    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant mockRestaurant=Mockito.spy(restaurant);
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:01"));
        assertEquals(false,mockRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //Method to test the ordercost
    //Method to implement getorderValue
    //arugments String<List> ItemName

    @Test
    public void  get_total_order_cost_when_items_are_chosen() throws itemNotFoundException {
        List<String> ItemName=List.of("Sweet corn soup","Vegetable lasagne");
        int Cost=restaurant.getOrderValue(ItemName);
        assertEquals(388,Cost);
    }

    @Test
    public void  get_total_order_cost_when_items_chosen_are_not_from_menu() throws itemNotFoundException {
        List<String> ItemName=List.of("Vegetable fried rice");
        assertThrows(itemNotFoundException.class,()->restaurant.getOrderValue(ItemName));
    }
}