package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.OneMeal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.TypeofDiet;

import java.util.List;

public interface Handler
{
    void setNext(Handler handler);
    void handle(TypeofDiet type, List<OneMeal> dayMeals, Directory directory,
                MealVisitor mealVisitor, List<Thread> threads);
    void Explain(TypeofDiet type);
}
