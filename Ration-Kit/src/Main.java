import Database.Directory;
import For_Products.One_Meal;
import For_Products.Product.Product;
import Human.Gender;
import Human.GenderException;
import Human.Human;
import Human.MealsNumber;
import java.sql.*;
import java.util.*;

public class Main
{
    static Scanner _scanner = new Scanner(System.in);
    public static Human mainHuman;
    public static Directory directory = new Directory();

    public static List<Product> Basic_Products = new ArrayList<Product>();
    public static List<Product> Garnish_Products = new ArrayList<Product>();
    public static List<Product> Adition_Products = new ArrayList<Product>();

    public static List<One_Meal> Meals_in_day = new ArrayList<>();

    static float day_protein, day_fats, day_carbonohydrates , day_kilocalories;

    public static void main(String[] args){

/////////////////////////////////////////  

        Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Breakfast));
        Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Lunch));
        Meals_in_day.add(new One_Meal(One_Meal.Type_Of_Meal.Dinner));

/////////////////////////////////////////////////////////
        Enter_Data_For_Person();

        for(int i=0; i<Meals_in_day.size(); i++){
            Meals_in_day.get(i).Create_Meal(directory.getBasic_Products(),
                    directory.getGarnish_Products(), directory.getAddition_Products(), Meals_in_day);
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
            day_protein +=Meals_in_day.get(i).protein;
            day_fats +=Meals_in_day.get(i).fats;
            day_carbonohydrates += Meals_in_day.get(i).carbohydrates;
            System.out.println("Общее количетсво белка за приём пищи: " + Meals_in_day.get(i).protein);
            System.out.println("Общее количетсво жиров за приём пищи: " + Meals_in_day.get(i).fats);
            System.out.println("Общее количетсво углеводов за приём пищи: " + Meals_in_day.get(i).carbohydrates);
            System.out.println("Общее количетсво ккал за приём пищи: " + Meals_in_day.get(i).kilocalories + "\n\n\n");

        }

        day_kilocalories = day_protein*4 + day_carbonohydrates*4 + day_fats*9;
        System.out.println("Общее количетсво белка за день: " + day_protein);
        System.out.println("Общее количетсво жиров за день: " + day_fats);
        System.out.println("Общее количетсво углеводов за день: " + day_carbonohydrates);
        System.out.println("Общее количетсво ккал за приём день: " + day_kilocalories+ "\n\n\n");
    }

    private static void Enter_Data_For_Person()
    {
        Integer age = 0, Opredelitel_Mode_Life = 0;
        Float height = 0.f, weight = 0.f;
        float activityCoefficient;

        Gender gender = Gender.Male;
        MealsNumber foodQuantity = MealsNumber.three;

        age = (Integer) EnterFromKeyboard("Сколько тебе лет:", age.getClass().getSimpleName());
        height = (Float) EnterFromKeyboard("Введи рост", height.getClass().getSimpleName());
        weight = (Float) EnterFromKeyboard("Введи вес", weight.getClass().getSimpleName());
        Opredelitel_Mode_Life = (Integer) EnterFromKeyboard("Какой образ жизни ты ведёшь:\n" +
                "1) Минимальная физическая нагрузка \n" +
                "2) Тренировки средней тяжести 2-3 раза в неделю\n" +
                "3) Интенсивные тренировки более 3 раз в неделю\n" +
                "4) Ежедневная физическая нагрузка\n" +
                "(Введите номер)\n", Opredelitel_Mode_Life.getClass().getSimpleName());
        gender = (Gender) EnterFromKeyboard("Определите свой пол:\n" +
                "1) Мужчина\n" +
                "2) Женщина", gender.getClass().getSimpleName());
        foodQuantity = (MealsNumber) EnterFromKeyboard("Выберите интервальную диету\n" +
                "1) Без интервальной диеты\n" +
                "2) 16/8 - Двухразовое питание\n" +
                "3) 24/0 - Одноразовое питание\n", foodQuantity.getClass().getSimpleName());


        switch (Opredelitel_Mode_Life)
        {
            default:
            case 1:
                activityCoefficient = 1.2f;
                break;
            case 2:
                activityCoefficient = 1.4f;
                break;
            case 3:
                activityCoefficient = 1.6f;
                break;
            case 4:
                activityCoefficient = 1.8f;
                break;
        }

        mainHuman = Human.Human(age,height,weight, activityCoefficient, gender, foodQuantity);
    }
    private static Object EnterFromKeyboard(String message, String datatype)
    {
        System.out.println(message);
        while (true)
        {
            switch (datatype)
            {
                case "Integer":
                {
                    if (_scanner.hasNextInt())
                        return _scanner.nextInt();
                    else
                        System.out.println(message);
                    break;
                }
                case "Float":
                {
                    if (_scanner.hasNextFloat())
                        return _scanner.nextFloat();
                    else
                        System.out.println(message);
                    break;
                }
                case "Gender":
                {
                    if (_scanner.hasNextInt())
                    {
                        try
                        {
                            switch (_scanner.nextInt())
                            {
                                case 1 ->
                                {
                                    return Gender.Male;
                                }
                                case 2 ->
                                {
                                    return Gender.Female;
                                }
                                default -> throw new GenderException("Неизвестный гендер\n" +
                                        "Попробуйте ввести еще раз");
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage() + "\n" + message);
                        }
                    }
                    else
                        System.out.println(message);
                    break;
                }
                case "MealsNumber":
                {
                    if (_scanner.hasNextInt())
                    {
                        try
                        {
                            switch (_scanner.nextInt())
                            {
                                case 1 ->
                                {
                                    return MealsNumber.three;
                                }
                                case 2 ->
                                {
                                    return MealsNumber.Two;
                                }
                                case 3 ->
                                {
                                    return MealsNumber.One;
                                }
                                default -> throw new RuntimeException("Неизвестная интервальная диета\n" +
                                        "Попробуйте ввести еще раз");
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage() + "\n" + message);
                        }
                    }
                    else
                        System.out.println(message);
                    break;
                }
            }
        }
    }

   static void Fill_Product_List(){

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "postgres");

            Connection connection = DriverManager.getConnection(url, authorization);
            if(connection!= null){
                System.out.println("Connect");
            }

            try (Statement _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                // Выполняем SQL-запрос и получаем результат в виде ResultSet
                String sql = "SELECT * FROM products";
                try (ResultSet resultSet = _statement.executeQuery(sql)) {
                    // Обрабатываем каждую строку результата
                    while (resultSet.next()) {
                        String Name = resultSet.getString("name_products");
                        float Protein  = resultSet.getFloat("protein");
                        float Fat  = resultSet.getFloat("fat");
                        float Carbonohydrates  = resultSet.getFloat("carbonohydrates");
                        int ID = resultSet.getInt("id");
                        boolean Vegetable = resultSet.getBoolean("vegetable");
                        boolean Garnish = resultSet.getBoolean("garnish");
                        boolean Adition = resultSet.getBoolean("adition");
                        boolean Basic = resultSet.getBoolean("basic");
                        int Limit = resultSet.getInt("max_count");
                        Product.Type_Product type_product = null;
                        Product.Original _original =null;

                        if(Vegetable==true){
                             _original = Product.Original.Vegetable;
                        }
                        else{
                            _original = Product.Original.Animal;
                        }

                        if (Garnish==true){
                            type_product = Product.Type_Product.Garnish;
                        }

                        else if (Adition==true) {
                            type_product = Product.Type_Product.Adition;
                        }

                        else if(Basic==true){
                            type_product = Product.Type_Product.Basic;
                        }

                        Product _product = new Product();
                        _product.Take_PFC_In_Base(Name, Protein, Fat , Carbonohydrates, _original, type_product, Limit);

                        if (type_product == Product.Type_Product.Garnish){
                            Garnish_Products.add(_product);
                        }

                        else if (type_product == Product.Type_Product.Adition) {
                            Adition_Products.add(_product);
                        }

                        else if(type_product == Product.Type_Product.Basic){
                            Basic_Products.add(_product);
                        }

                        /*
                        System.out.println("Продукт: " + Name + "\t");
                        System.out.println("БЖУ продукта: " + Protein + " "+ Fat + " " + Carbonohydrates + "\t");
                        System.out.println("Вегетарианский ли продукт: " + Vegetable+ "\t");
                        System.out.println("Тип продукта: " + type_product + "\t");
                        System.out.println("Лимит за приём пищи: " + type_product + "\n");
                        */
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
    }
   }

  static void  Perebor_List_Products(){
        for(int i=0; i< Basic_Products.size(); i++){
            System.out.println("Продукт: " + Basic_Products.get(i).Name + "\t");
            System.out.println("БЖУ продукта: " + Basic_Products.get(i).protein + " "+ Basic_Products.get(i).fats+ " " + Basic_Products.get(i).carbohydrates + "\t");
            System.out.println("Вегетарианский ли продукт: " + Basic_Products.get(i).original+ "\t");
            System.out.println("Тип продукта: " + Basic_Products.get(i).Type_product + "\t");
            System.out.println("Лимит за приём пищи: " + Basic_Products.get(i).max_gramm + "\n");

        }
       for(int i=0; i< Adition_Products.size(); i++){
           System.out.println("Продукт: " + Adition_Products.get(i).Name + "\t");
           System.out.println("БЖУ продукта: " + Adition_Products.get(i).protein + " "+ Adition_Products.get(i).fats+ " " +Adition_Products.get(i).carbohydrates + "\t");
           System.out.println("Вегетарианский ли продукт: " + Adition_Products.get(i).original+ "\t");
           System.out.println("Тип продукта: " + Adition_Products.get(i).Type_product + "\t");
           System.out.println("Лимит за приём пищи: " + Adition_Products.get(i).max_gramm + "\n");

       }

       for(int i=0; i< Garnish_Products.size(); i++){
           System.out.println("Продукт: " + Garnish_Products.get(i).Name + "\t");
           System.out.println("БЖУ продукта: " + Garnish_Products.get(i).protein + " "+ Garnish_Products.get(i).fats+ " " +Garnish_Products.get(i).carbohydrates + "\t");
           System.out.println("Вегетарианский ли продукт: " + Garnish_Products.get(i).original+ "\t");
           System.out.println("Тип продукта: " + Garnish_Products.get(i).Type_product + "\t");
           System.out.println("Лимит за приём пищи: " + Garnish_Products.get(i).max_gramm + "\n");

       }
   }
}
