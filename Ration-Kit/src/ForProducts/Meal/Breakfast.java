package ForProducts.Meal;

import Database.Directory;
import ForProducts.Meal.Visitor.MealVisitor;
import java.util.List;

public class Breakfast extends OneMeal implements Runnable {


    public Breakfast(Directory directory, List<OneMeal> MealsInDay, MealVisitor mealVisitor) {
        super(directory, MealsInDay, mealVisitor);
    }

    @Override
    public void Create_Meal(Directory directory, List<OneMeal> MealsInDay, MealVisitor mealVisitor)
    {
        mealVisitor.CalculateBreakfast(this);
        CreatePlan(directory, MealsInDay);
    }

    @Override
    public void run() {
        System.out.println("Начало создания приёма пищи: завтрак");
        Create_Meal(directory, mealsinday, mealVisitor);
    }
}
