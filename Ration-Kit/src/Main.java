import Human.Human;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main
{
    static Scanner _scanner = new Scanner(System.in);
    public Human mainHuman;

    public static void main(String[] args)
    {
        Connect_To_DataBase();


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

   static void Connect_To_DataBase(){

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";


            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "postgres");

            Connection connection = DriverManager.getConnection(url, authorization);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery("SELECT * FROM public.products ");

            table.first();

            for (int j = 1; j <= table.getMetaData().getColumnCount(); j++){
                System.out.print(table.getMetaData().getColumnName(j)+"\t\t");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}