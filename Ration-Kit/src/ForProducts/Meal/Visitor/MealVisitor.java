package ForProducts.Meal.Visitor;

import ForProducts.Meal.OneMeal;

public interface MealVisitor
{
    void CalculateBreakfast(OneMeal meal);
    void CalculateLunch(OneMeal meal);
    void CalculateDinner(OneMeal meal);
}
