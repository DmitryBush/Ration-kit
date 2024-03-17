package For_Products.Product;
import Database.Directory;
import For_Products.Meal.*;
import For_Products.Meal.Visitor.MealVisitorClass;
import Human.Human;

import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private List<One_Meal> Meals_in_day = new ArrayList<>();
    float day_protein, day_fats, day_carbonohydrates , day_kilocalories;
    Type_of_Diet _Type_Diet;

    public void Create_Day_Diet(Directory directory){
        _Type_Diet = Human.GetInstance().getTypeDiet();
        switch (_Type_Diet){
            case diet_regular, diet_16_8 ->
            {
                Meals_in_day.add(new Breakfast());
                Meals_in_day.add(new Lunch());
                Meals_in_day.add(new Dinner());
            }

            case diet_20_4 -> {
                Meals_in_day.add(new Lunch());
                Meals_in_day.add(new Dinner());
            }

            default ->{}
        }
        for(int i=0; i<Meals_in_day.size(); i++){
            Meals_in_day.get(i).Create_Meal(directory, Meals_in_day, new MealVisitorClass());
            day_protein +=Meals_in_day.get(i).getProtein();
            day_fats +=Meals_in_day.get(i).getFats();
            day_carbonohydrates += Meals_in_day.get(i).getCarbohydrates();
        }
        day_kilocalories = day_protein*4 + day_carbonohydrates*4 + day_fats*9;
        Explanations_of_intermittent_fasting(_Type_Diet);
    }


   public void Show_Ration_OnDay(){
       for (var meal : Meals_in_day) {
           System.out.println(meal.getClass().getSimpleName());

           for (var product: meal.getProducts())
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


    void Explanations_of_intermittent_fasting(Type_of_Diet type_of_diet){

        switch (type_of_diet){
            case diet_regular ->
            {
                System.out.println("Это стандартный тип питания, " +
                        "при котором вы можете есть в удобное для вас время, но не позже двух часов до сна!");
            }
            case diet_16_8 ->
            {
                System.out.println("Интервальное голодание, вы должны есть только на протяжении 8 часов, " +
                        "остальные 16 часов вы не должны есть. \n" +
                        "Уменьшите время между приёмами пищи" + "Вы должны пить как можно больше воды! \n" +
                        "Это поможет продержаться во время голодания. Вода помогает унять чувство голода.");
            }

            case diet_20_4 -> {
                System.out.println("Только для опытных! " +
                        "В этом виде голодания вы принимаете пищу на протяжении 4 часов,\n" +
                        " а на протяжении 20 часов вы не едите." +
                        "Вы должны пить как можно больше воды! " +
                        "Это поможет продержаться во время голодания.\n Вода помогает унять чувство голода.");
            }

            case diet_24_0 -> {
                System.out.println("Ультра-хардкор. Вы должны прожить без еды целые сутки. Можно пить только воду.\n" +
                        "Употребить не менее 10 стаканов воды за день");
            }

            default ->{
            }


        }

    }
}
