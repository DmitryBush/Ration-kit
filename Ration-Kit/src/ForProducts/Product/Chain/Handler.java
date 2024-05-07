package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.One_Meal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.DietPlan;
import ForProducts.Product.Type_of_Diet;

import java.util.List;
import java.util.Vector;

public interface Handler
{
    void setNext(Handler handler);
    void handle(Type_of_Diet type, List<One_Meal> dayMeals, Directory directory, MealVisitor mealVisitor, Vector<Thread> threads);
    void Explain(Type_of_Diet type);
}
