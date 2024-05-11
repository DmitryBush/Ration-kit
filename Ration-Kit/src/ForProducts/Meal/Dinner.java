package ForProducts.Meal;

import Database.Directory;
import ForProducts.Meal.Visitor.MealVisitor;

import java.util.List;

public class Dinner extends One_Meal implements Runnable {
    public Dinner(Directory directory, List<One_Meal> meals_in_day, MealVisitor mealVisitor) {
        super(directory, meals_in_day, mealVisitor);
    }

    @Override
    public void Create_Meal(Directory directory, List<One_Meal> meals_in_day, MealVisitor mealVisitor)
    {
        mealVisitor.CalculateDinner(this);
        CreatePlan(directory, meals_in_day);
    }

    @Override
    public void run() {
        System.out.println("Начало создания приёма пищи: ужин");
        Create_Meal(directory,meals_in_day,mealVisitor);
    }
}
