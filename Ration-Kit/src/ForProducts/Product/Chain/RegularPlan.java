package ForProducts.Product.Chain;

import ForProducts.Meal.Breakfast;
import ForProducts.Meal.Dinner;
import ForProducts.Meal.Lunch;
import ForProducts.Meal.One_Meal;
import ForProducts.Product.Type_of_Diet;

import java.util.List;

public class RegularPlan extends PlanHandler
{
    public RegularPlan() {
        super(Type_of_Diet.diet_regular);
    }

    @Override
    protected void CreatePlan(List<One_Meal> dayMeals)
    {
        dayMeals.add(new Breakfast());
        dayMeals.add(new Lunch());
        dayMeals.add(new Dinner());
    }

    @Override
    protected void Describe() {
        System.out.println("Это стандартный тип питания, " +
                "при котором вы можете есть в удобное для вас время, но не позже двух часов до сна!");
    }
}
