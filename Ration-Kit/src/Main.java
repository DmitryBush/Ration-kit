import Database.Directory;
import ForProducts.Product.DietPlan;

import ForProducts.Product.TypeofDiet;
import Human.Gender;
import Human.GenderException;
import Human.Human;

import java.util.*;

public class Main
{
    static Scanner Scanner = new Scanner(System.in);
    public static Human mainHuman;    // создаём параметры пользователя для которого нужна диета
    public static Directory directory = Directory.GetInstance();      //для получения списков продуктов из базы данных

    public static DietPlan dietPlan = new DietPlan();    // план питания на день


    public static void main(String[] args) throws InterruptedException {

        Enter_Data_For_Person();
        dietPlan.Create_Day_Diet();
        dietPlan.Show_Ration_OnDay();
    }

    private static void Enter_Data_For_Person()   // ввод всех необходимых данных о человеке
    {
        Integer age = 0, Opredelitel_Mode_Life = 0;
        Float height = 0.f, weight = 0.f;
        float activityCoefficient;


        Gender gender = Gender.Male;
        TypeofDiet dietplane = TypeofDiet.dietregular;

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


        dietplane = (TypeofDiet) EnterFromKeyboard("Определите нужный вам план диеты:\n" +
                "1) Обычный режим питания\n" + "2) Диета 15/9 \n" + "3) Диета 20/4\n" + "4) Диета 24/0\n"
                , dietplane.getClass().getSimpleName());

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
        mainHuman = Human.GetInstance(age,height,weight, activityCoefficient, gender, dietplane);
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
                    if (Scanner.hasNextInt())
                        return Scanner.nextInt();
                    else
                        System.out.println(message);
                    break;
                }
                case "Float":
                {
                    if (Scanner.hasNextFloat())
                        return Scanner.nextFloat();
                    else
                        System.out.println(message);
                    break;
                }

                case "TypeofDiet":
                {
                    if (Scanner.hasNextInt())
                    {
                        try
                        {
                            switch (Scanner.nextInt())
                            {
                                case 1 ->
                                {
                                    return TypeofDiet.dietregular;
                                }
                                case 2 ->
                                {
                                    return TypeofDiet.diet_16_8;
                                }

                                case 3 ->
                                {
                                    return TypeofDiet.diet_20_4;
                                }
                                case 4 ->
                                {
                                    return TypeofDiet.diet_24_0;
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

                case "Gender":
                {
                    if (Scanner.hasNextInt())
                    {
                        try
                        {
                            switch (Scanner.nextInt())
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
            }
        }
    }
}
