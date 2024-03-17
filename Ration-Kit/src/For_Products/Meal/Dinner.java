package For_Products.Meal;

import Database.Directory;
import For_Products.Meal.Visitor.MealVisitor;

import java.util.List;

public class Dinner extends One_Meal
{
    @Override
    public void Create_Meal(Directory directory, List<One_Meal> meals_in_day, MealVisitor mealVisitor)
    {
        mealVisitor.CalculateDinner(this);
        CreatePlan(directory, meals_in_day);
    }
}
