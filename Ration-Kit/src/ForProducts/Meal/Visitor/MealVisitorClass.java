package ForProducts.Meal.Visitor;

import ForProducts.Meal.OneMeal;
import Human.Human;

public class MealVisitorClass implements MealVisitor
{

    @Override
    public void CalculateBreakfast(OneMeal meal) {
        var _Human = Human.GetInstance();
        float MaxProtein, MaxCarbohydrates, MaxFats;

        meal.setMaxprotein(MaxProtein = _Human.getProtein() * 0.3f);
        meal.setMax_fats(MaxFats = _Human.getFats() * 0.3f);
        meal.setMaxcarbohydrates(MaxCarbohydrates = _Human.getCarbohydrates() * 0.3f);

        meal.setMaxkilocalories(MaxProtein * 4 + MaxCarbohydrates * 4 + MaxFats * 9);
    }

    @Override
    public void CalculateLunch(OneMeal meal) {
        var _Human = Human.GetInstance();
        float max_protein, max_carbohydrates, max_fats;

        meal.setMaxprotein(max_protein = _Human.getProtein() * 0.4f);
        meal.setMax_fats(max_fats = _Human.getFats() * 0.4f);
        meal.setMaxcarbohydrates(max_carbohydrates = _Human.getCarbohydrates() * 0.4f);

        meal.setMaxkilocalories(max_protein * 4 + max_carbohydrates * 4 + max_fats * 9);
    }

    @Override
    public void CalculateDinner(OneMeal meal) {
        CalculateBreakfast(meal);
    }
}
