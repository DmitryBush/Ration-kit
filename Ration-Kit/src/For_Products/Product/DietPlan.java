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
                break;
            }

            case diet_20_4 -> {
                Meals_in_day.add(new Lunch());
                Meals_in_day.add(new Dinner());
                break;
            }

            case diet_24_0 -> {}

            default ->{
                break;
            }
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
        for(int i=0; i<Meals_in_day.size(); i++){
            System.out.println(Meals_in_day.get(i).getClass().getSimpleName());
            for(int j=0; j< Meals_in_day.get(i).getProducts().size(); j++){
                System.out.println("Продукт: " + Meals_in_day.get(i).getProducts().get(j).Name + "\t");
                System.out.println("БЖУ продукта: " + Meals_in_day.get(i).getProducts().get(j).protein
                        + " "+ Meals_in_day.get(i).getProducts().get(j).fats
                        + " " + Meals_in_day.get(i).getProducts().get(j).carbohydrates + "\t");
                System.out.println("Вегетарианский ли продукт: "
                        + Meals_in_day.get(i).getProducts().get(j).original + "\t");
                System.out.println("Количество данного продукта: "
                        + Meals_in_day.get(i).getProducts().get(j).cur_count_gramm + "\n");

            }
            System.out.println("Общее количетсво белка за приём пищи: " + Meals_in_day.get(i).getProtein());
            System.out.println("Общее количетсво жиров за приём пищи: " + Meals_in_day.get(i).getFats());
            System.out.println("Общее количетсво углеводов за приём пищи: " + Meals_in_day.get(i).getCarbohydrates());
            System.out.println("Общее количетсво ккал за приём пищи: " + Meals_in_day.get(i).getKilocalories() + "\n\n\n");
        }

        System.out.println("Общее количетсво белка за день: " + day_protein);
        System.out.println("Общее количетсво жиров за день: " + day_fats);
        System.out.println("Общее количетсво углеводов за день: " + day_carbonohydrates);
        System.out.println("Общее количетсво ккал за приём день: " + day_kilocalories+ "\n\n\n");

    }


    void Explanations_of_intermittent_fasting(Type_of_Diet type_of_diet){

        switch (type_of_diet){
            case diet_regular ->
            {
                System.out.println("Это стандарный тип питания, при котором вы можете есть в удобное для вас время, но не позже двух часов до сна!");
                break;
            }
            case diet_16_8 ->
            {
                System.out.println("Интервальное голодание, вы должны есть только на протяжении 8 часов, остальные 16 часов вы не должны есть. \n" +
                        "Уменьшите время между приёмами пищи" +"Вы должны пить как можно больше воды! \nЭто поможет продержаться во время голодания. Вода помогает унять чувство голода.");
                break;
            }

            case diet_20_4 -> {
                System.out.println("Только для оытных! В этом виде голодания вы принимаете пищу на протяжении 4 часов,\n а на протяжении 20 часов вы не едите." +
                        "Вы должны пить как можно больше воды! Это поможет продержаться во время голодания.\n Вода помогает унять чувство голода.");
                break;
            }

            case diet_24_0 -> {
                System.out.println("Ульра-хардкор. Вы должны прожить без еды целые сутки. Можно пить только воду.\n" +
                        "Употребить не менее 10 стаканов воды за день");
                break;
            }

            default ->{
                break;
            }


        }

    }
}
