package ForProducts.Product;
import Database.Directory;
import ForProducts.Meal.*;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Meal.Visitor.MealVisitorClass;
import ForProducts.Product.Chain.*;
import Human.Human;

import java.util.*;

public class DietPlan {
    private final List<One_Meal> Meals_in_day = new ArrayList<>();
    float day_protein, day_fats, day_carbonohydrates , day_kilocalories;
    Type_of_Diet _Type_Diet;

    public void Create_Day_Diet() {    // создание вариантов питания на день в зависимости от типа диеты
        List<Thread> threads = new LinkedList<>();
        CreatePlan(Directory.GetInstance(), threads);

        for(int i=0; i<Meals_in_day.size(); i++)
            threads.get(i).start();

        try
        {
            for(var thr: threads)
                thr.join();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Thread interrupt has occurred");
        }

        for (var i:Meals_in_day)
        {
            day_protein += i.getProtein();
            day_fats += i.getFats();
            day_carbonohydrates += i.getCarbohydrates();
        }

        day_kilocalories = day_protein*4 + day_carbonohydrates*4 + day_fats*9;
        Explanations_of_intermittent_fasting();
    }

    private void CreatePlan(Directory directory, List<Thread> threads)
    {
        _Type_Diet = Human.GetInstance().getTypeDiet();
        Handler handler = new RegularPlan();
        Handler handler1 = new Plan8();
        Handler handler2 = new Plan4();

        handler.setNext(handler1);
        handler1.setNext(handler2);

        handler.handle(_Type_Diet, Collections.synchronizedList(Meals_in_day), directory,
                new MealVisitorClass(), threads);
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
