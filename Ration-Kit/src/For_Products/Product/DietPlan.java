package For_Products.Product;
import Human.Human;
import For_Products.One_Meal;

import java.util.ArrayList;
import java.util.List;

public class DietPlan {


    private  List<One_Meal> Meals_in_day = new ArrayList<>();
    float day_protein, day_fats, day_carbonohydrates , day_kilocalories;
    Type_of_Diet _Type_Diet;

    public enum Type_of_Diet{
        diet_regular,
        diet_16_8,
        diet_20_4,
        diet_24_0
    }


    public void Create_Day_Diet(Type_of_Diet type_of_diet, List<Product> _basic_product, List<Product> _garnish_product, List<Product> _adition_product, Human mainHuman){
        _Type_Diet = type_of_diet;
        switch (_Type_Diet){
            case diet_regular ->
            {
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Breakfast));
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Lunch));
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Dinner));
                break;
            }
            case diet_16_8 ->
            {
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Breakfast));
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Lunch));
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Dinner));
                break;
            }

            case diet_20_4 -> {
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Lunch));
                Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Dinner));
                break;
            }

            case diet_24_0 -> {
                break;
            }

            default ->{
                break;
            }
        }
        for(int i=0; i<Meals_in_day.size(); i++){
            Meals_in_day.get(i).Create_Meal(_basic_product,_garnish_product, _adition_product, mainHuman, Meals_in_day);
            day_protein +=Meals_in_day.get(i).protein;
            day_fats +=Meals_in_day.get(i).fats;
            day_carbonohydrates += Meals_in_day.get(i).carbohydrates;
        }
        day_kilocalories = day_protein*4 + day_carbonohydrates*4 + day_fats*9;
        Explanations_of_intermittent_fasting(type_of_diet);
    }


   public void Show_Racion_OnDay(){
        for(int i=0; i<Meals_in_day.size(); i++){
            System.out.println(Meals_in_day.get(i).typeOfMeal);
            for(int j=0; j< Meals_in_day.get(i).products.size(); j++){
                System.out.println("Продукт: " + Meals_in_day.get(i).products.get(j).Name + "\t");
                System.out.println("БЖУ продукта: " + Meals_in_day.get(i).products.get(j).protein
                        + " "+ Meals_in_day.get(i).products.get(j).fats
                        + " " + Meals_in_day.get(i).products.get(j).carbohydrates + "\t");
                System.out.println("Вегетарианский ли продукт: "
                        + Meals_in_day.get(i).products.get(j).original + "\t");
                System.out.println("Количество данного продукта: "
                        + Meals_in_day.get(i).products.get(j).cur_count_gramm + "\n");

            }
            System.out.println("Общее количетсво белка за приём пищи: " + Meals_in_day.get(i).protein);
            System.out.println("Общее количетсво жиров за приём пищи: " + Meals_in_day.get(i).fats);
            System.out.println("Общее количетсво углеводов за приём пищи: " + Meals_in_day.get(i).carbohydrates);
            System.out.println("Общее количетсво ккал за приём пищи: " + Meals_in_day.get(i).kilocalories + "\n\n\n");
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
