package For_Products.Meal.Visitor;

import For_Products.Meal.One_Meal;

public interface MealVisitor
{
    void CalculateBreakfast(One_Meal meal);
    void CalculateLunch(One_Meal meal);
    void CalculateDinner(One_Meal meal);
}
