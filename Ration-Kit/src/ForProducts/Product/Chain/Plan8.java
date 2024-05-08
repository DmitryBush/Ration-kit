package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.Breakfast;
import ForProducts.Meal.Dinner;
import ForProducts.Meal.Lunch;
import ForProducts.Meal.One_Meal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.Type_of_Diet;

import java.util.List;
import java.util.Vector;

public class Plan8 extends PlanHandler
{
    public Plan8() {
        super(Type_of_Diet.diet_16_8);
    }
    @Override
    protected void CreatePlan(List<One_Meal> dayMeals, Directory directory,
                              MealVisitor mealVisitor, List<Thread> threads) {
        Breakfast breakfast = new Breakfast(directory,dayMeals,mealVisitor);
        Lunch lunch = new Lunch(directory,dayMeals,mealVisitor);
        Dinner dinner = new Dinner(directory,dayMeals,mealVisitor);
        dayMeals.add(lunch);
        dayMeals.add(dinner);
        dayMeals.add(breakfast);
        threads.add(new Thread(breakfast));
        threads.add(new Thread(lunch));
        threads.add(new Thread(dinner));
    }

    @Override
    protected void Describe() {
        System.out.println("Интервальное голодание, вы должны есть только на протяжении 8 часов, " +
                "остальные 16 часов вы не должны есть. \n" +
                "Уменьшите время между приёмами пищи" + "Вы должны пить как можно больше воды! \n" +
                "Это поможет продержаться во время голодания. Вода помогает унять чувство голода.");
    }
}
