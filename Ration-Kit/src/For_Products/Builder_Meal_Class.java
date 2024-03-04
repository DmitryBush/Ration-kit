package For_Products;

public class Builder_Meal_Class implements Builder_Meal {

    One_Meal meal = new One_Meal();

    
    @Override
    public Builder_Meal Set_Type_Of_Meal(One_Meal.Type_Of_Meal typeOfMeal) {
        meal.typeOfMeal = typeOfMeal;
        return this;
    }

    @Override
    public One_Meal Build_Meal() {
        return meal;
    }
}
