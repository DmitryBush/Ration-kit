package Human;

public class Human
{
    // Static instance of a class
    private static Human instance = null;
    // Calculated ration values
    private float kilocalories, squirrels, fats, carbohydrates;

    private Human(int age, float height, float weight,
                  float activityCoeff, Gender gender) throws GenderException
    {
        kilocalories = CalculateKilocalories(age, height, weight, activityCoeff, gender);
        CalculateSFC();
    }
    public static Human Human(int age, float height, float weight,
                              float activityCoeff, Gender gender) throws GenderException
    {
        if (instance == null)
            instance = new Human(age, height, weight, activityCoeff, gender);
        return instance;
    }
    public static synchronized Human GetInstance(int age, float height, float weight,
                                                 float activityCoeff, Gender gender) throws GenderException
    {
        if (instance == null)
            instance = new Human(age, height, weight, activityCoeff, gender);
        return instance;
    }
    public static synchronized Human GetInstance()
    {
        return instance;
    }
    private float CalculateKilocalories(int age, float height, float weight,
                                        float activityCoeff, Gender gender) throws GenderException
    {
        switch (gender)
        {
            case Male ->
            {
                return (float) ((weight * 10 + height * 6.25 - age * 5 + 5) * activityCoeff);
            }
            case Female ->
            {
                return (float) ((weight * 10 + height * 6.25 - age * 5 - 161) * activityCoeff);
            }
            default ->
            {
                throw new GenderException("Unknown gender");
            }
        }
    }
    private void CalculateSFC()
    {
        squirrels = (kilocalories * 0.25f) / 4;
        fats = (kilocalories * 0.25f) / 9;
        carbohydrates = (kilocalories * 0.5f) / 4;
    }
}
