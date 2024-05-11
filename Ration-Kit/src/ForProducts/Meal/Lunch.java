package ForProducts.Meal;

import Database.Directory;
import ForProducts.Meal.Visitor.MealVisitor;

import java.util.List;

public class Lunch extends OneMeal implements Runnable {

    public Lunch(Directory directory, List<OneMeal> MealsInDay, MealVisitor mealVisitor) {
        super(directory, MealsInDay, mealVisitor);
    }

    @Override
    public void Create_Meal(Directory directory, List<OneMeal> MealsInDay, MealVisitor mealVisitor)
    {
        mealVisitor.CalculateLunch(this);
        CreatePlan(directory, MealsInDay);
    }

    @Override
    public void run() {
        System.out.println("Начало создания приёма пищи: обед");
        Create_Meal(directory,mealsinday,mealVisitor);
    }
}
