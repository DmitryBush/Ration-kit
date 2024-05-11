package ForProducts.Product;
import Database.Directory;
import ForProducts.Meal.*;
import ForProducts.Meal.Visitor.MealVisitorClass;
import ForProducts.Product.Chain.*;
import Human.Human;

import java.util.*;

public class DietPlan {
    private final List<OneMeal> MealsInday = new ArrayList<>();
    float dayprotein, dayfats, daycarbonohydrates, daykilocalories;
    TypeofDiet TypeDiet;

    public void Create_Day_Diet() throws InterruptedException {    // создание вариантов питания на день в зависимости от типа диеты

        List<Thread> threads = new LinkedList<>();
        CreatePlan(Directory.GetInstance(), threads);

        for(int i = 0; i< MealsInday.size(); i++)
            threads.get(i).start();

        for(var thr: threads)
            thr.join();

        for (var i: MealsInday)
        {
            dayprotein += i.getProtein();
            dayfats += i.getFats();
            daycarbonohydrates += i.getCarbohydrates();
        }

        daykilocalories = dayprotein *4 + daycarbonohydrates *4 + dayfats *9;
        Explanations_of_intermittent_fasting();
    }

    private void CreatePlan(Directory directory, List<Thread> threads)
    {
        TypeDiet = Human.GetInstance().getTypeDiet();
        Handler handler = new RegularPlan();
        Handler handler1 = new Plan8();
        Handler handler2 = new Plan4();

        handler.setNext(handler1);
        handler1.setNext(handler2);

        handler.handle(TypeDiet, Collections.synchronizedList(MealsInday), directory,
                new MealVisitorClass(), threads);
    }

   public void Show_Ration_OnDay(){     // показ всех продуктов используемых в дневном рационе
       for (var meal : MealsInday) {
           System.out.println(meal.getClass().getSimpleName());

           for (var product: meal)
           {
               System.out.println(product);
           }
           System.out.println(meal);
       }

        System.out.println("Общее количество белка за день: " + dayprotein);
        System.out.println("Общее количество жиров за день: " + dayfats);
        System.out.println("Общее количество углеводов за день: " + daycarbonohydrates);
        System.out.println("Общее количество ккал за приём день: " + daykilocalories + "\n\n\n");
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

        regular.Explain(TypeDiet);
    }
}
