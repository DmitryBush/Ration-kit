package Human;

public class Human
{
    // Static instance of a class
    private static Human instance = null;
    // Calculated ration values
    private float kilocalories, protein, fats, carbohydrates;
    private MealsNumber mealsNumber;


    private Human(int age, float height, float weight,
                 float activityCoefficient, Gender gender, MealsNumber quantity)
    {
        kilocalories = CalculateKilocalories(age, height, weight, activityCoefficient, gender);
        CalculateSFC();
    }

    public static Human Human(int age, float height, float weight,
                              float activityCoefficient, Gender gender, MealsNumber quantity)
    {
        if (instance == null)
            instance = new Human(age, height, weight, activityCoefficient, gender, quantity);
        return instance;
    }
    public static synchronized Human GetInstance(int age, float height, float weight,
                                                 float activityCoefficient, Gender gender, MealsNumber quantity)
    {
        if (instance == null)
            instance = new Human(age, height, weight, activityCoefficient, gender, quantity);
        return instance;
    }
    public static synchronized Human GetInstance()
    {
        return instance;
    }
    private float CalculateKilocalories(int age, float height, float weight,
                                        float activityCoefficient, Gender gender)
    {
        try
        {
            switch (gender)
            {
                case Male ->
                {

                    return (float) ((weight * 10 + height * 6.25 - age * 5 + 5) * activityCoefficient);

                }
                case Female ->
                {
                    return (float) ((weight * 10 + height * 6.25 - age * 5 - 161) * activityCoefficient);
                }
                default -> throw new GenderException("Unknown gender");
            }
        }
        catch (GenderException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    private void CalculateSFC()
    {
        protein = (kilocalories * 0.25f) / 4;
        fats = (kilocalories * 0.25f) / 9;
        carbohydrates = (kilocalories * 0.5f) / 4;
    }
    public float getKilocalories() {
        return kilocalories;
    }

    public float getProtein() {
        return protein;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public MealsNumber getMealsNumber() {
        return mealsNumber;
    }
}
