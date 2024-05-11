package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.OneMeal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.TypeofDiet;

import java.util.List;

public class Plan0 extends PlanHandler
{
    public Plan0() {
        super(TypeofDiet.diet_24_0);
    }

    @Override
    protected void CreatePlan(List<OneMeal> dayMeals, Directory directory,
                              MealVisitor mealVisitor, List<Thread> threads) {}

    @Override
    protected void Describe() {
        System.out.println("Ультра-хардкор. Вы должны прожить без еды целые сутки. Можно пить только воду.\n" +
                "Употребить не менее 10 стаканов воды за день");
    }
}
