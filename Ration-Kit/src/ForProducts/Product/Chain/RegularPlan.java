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

public class RegularPlan extends PlanHandler
{
    public RegularPlan() {
        super(Type_of_Diet.diet_regular);
    }

    @Override
    protected void CreatePlan(List<One_Meal> dayMeals, Directory directory,
                              MealVisitor mealVisitor, List<Thread> threads)
    {
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
        System.out.println("Это стандартный тип питания, " +
                "при котором вы можете есть в удобное для вас время, но не позже двух часов до сна!");
    }
}
