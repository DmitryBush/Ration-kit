package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.Dinner;
import ForProducts.Meal.Lunch;
import ForProducts.Meal.OneMeal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.TypeofDiet;

import java.util.List;

public class Plan4 extends PlanHandler
{
    public Plan4() {
        super(TypeofDiet.diet_20_4);
    }
    @Override
    protected void CreatePlan(List<OneMeal> dayMeals, Directory directory,
                              MealVisitor mealVisitor, List<Thread> threads) {

        Lunch lunch = new Lunch(directory,dayMeals,mealVisitor);
        Dinner dinner = new Dinner(directory,dayMeals,mealVisitor);
        dayMeals.add(lunch);
        dayMeals.add(dinner);
        threads.add(new Thread(lunch));
        threads.add(new Thread(dinner));
    }



    @Override
    protected void Describe() {
        System.out.println("Только для опытных! " +
                "В этом виде голодания вы принимаете пищу на протяжении 4 часов,\n" +
                " а на протяжении 20 часов вы не едите." +
                "Вы должны пить как можно больше воды! " +
                "Это поможет продержаться во время голодания.\n Вода помогает унять чувство голода.");
    }
}
