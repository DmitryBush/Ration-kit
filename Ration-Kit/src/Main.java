import For_Products.Product.Product;
import Human.Human;

import java.sql.*;
import java.util.*;

public class Main
{
    static Scanner _scanner = new Scanner(System.in);
    public Human mainHuman;
    public static List<Product> Basic_Products = new ArrayList<Product>();
    public static List<Product> Garnish_Products = new ArrayList<Product>();
    public static List<Product> Adition_Products = new ArrayList<Product>();


    public static void main(String[] args)
    {
        Fill_Product_List();
        Perebor_List_Products();
        //Enter_Data_For_Person();

    }

    private static void Enter_Data_For_Person(){

        int age;
        float height, weight, activityCoeff;
        int Opredelitel_Mode_Life;


        while (true){
            System.out.println("Сколько тебе лет:");
            age = _scanner.nextInt();
            System.out.println("Введи рост");
            height = _scanner.nextFloat();
            System.out.println("Введи вес");
            weight = _scanner.nextFloat();

            System.out.println("Какой образ жизни ты ведёшь:\n" +
                    "1) Минимальная физическая нагрузка \n" +
                    "2) Тренировки средней тяжести 2-3 раза в неделю\n" +
                    "3) Интенсивные тренировки более 3 раз в неделю\n" +
                    "4) Ежедневная физическая нагрузка\n" +
                    "(Введите номер)\n");

            Opredelitel_Mode_Life = _scanner.nextInt();

            switch (Opredelitel_Mode_Life){

                case 1:
                    activityCoeff = 1.2f;
                    break;

                case 2:
                    activityCoeff = 1.4f;
                    break;
                case 3:
                    activityCoeff = 1.6f;
                    break;
                case 4:
                    activityCoeff = 1.8f;
                    break;

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
