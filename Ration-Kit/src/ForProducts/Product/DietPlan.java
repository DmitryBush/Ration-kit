package ForProducts.Product;
import Database.Directory;
import ForProducts.Meal.*;
import ForProducts.Meal.Visitor.MealVisitorClass;
import ForProducts.Product.Chain.*;
import Human.Human;

import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private final List<One_Meal> Meals_in_day = new ArrayList<>();
    float day_protein, day_fats, day_carbonohydrates , day_kilocalories;
    Type_of_Diet _Type_Diet;

    public void Create_Day_Diet(Directory directory){    // создание вариантов питания на день в зависимости от типа диеты
        CreatePlan();

        for(int i=0; i<Meals_in_day.size(); i++){
            Meals_in_day.get(i).Create_Meal(directory, Meals_in_day, new MealVisitorClass());
            day_protein +=Meals_in_day.get(i).getProtein();
            day_fats +=Meals_in_day.get(i).getFats();
            day_carbonohydrates += Meals_in_day.get(i).getCarbohydrates();
        }
        day_kilocalories = day_protein*4 + day_carbonohydrates*4 + day_fats*9;
        Explanations_of_intermittent_fasting();
    }

    private void CreatePlan()
    {
        _Type_Diet = Human.GetInstance().getTypeDiet();
        Handler handler = new RegularPlan();
        Handler handler1 = new Plan8();
        Handler handler2 = new Plan4();

        handler.setNext(handler1);
        handler1.setNext(handler2);

        handler.handle(_Type_Diet, Meals_in_day);
    }

   public void Show_Ration_OnDay(){     // показ всех продуктов используемых в дневном рационе
       for (var meal : Meals_in_day) {
           System.out.println(meal.getClass().getSimpleName());

           for (var product: meal)
           {
               System.out.println(product);
           }
           System.out.println(meal);
       }

        System.out.println("Общее количество белка за день: " + day_protein);
        System.out.println("Общее количество жиров за день: " + day_fats);
        System.out.println("Общее количество углеводов за день: " + day_carbonohydrates);
        System.out.println("Общее количество ккал за приём день: " + day_kilocalories+ "\n\n\n");
    }


    void Explanations_of_intermittent_fasting() // небольшой список советов
    {
        PlanHandler regular = new RegularPlan();
        PlanHandler plan0 = new Plan0();
        PlanHandler plan8 = new Plan8();
        PlanHandler plan4 = new Plan4();

        regular.setNext(plan8);
        plan8.setNext(plan4);
        plan4.setNext(plan0);

        regular.Explain(_Type_Diet);
    }
}
