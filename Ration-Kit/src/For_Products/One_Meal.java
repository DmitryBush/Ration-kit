package For_Products;

import java.util.ArrayList;
import java.util.List;

public class One_Meal {

    private float kilocalories, protein, fats, carbohydrates;
    Type_Of_Meal typeOfMeal;
    List <Product> products = new ArrayList<Product>();

    public enum Type_Of_Meal{
        Breakfast,
        Lunch,
        Dinner
    }


}
