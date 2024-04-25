package ForProducts.Product.Chain;

import ForProducts.Meal.One_Meal;
import ForProducts.Product.Type_of_Diet;

import java.util.List;

public interface Handler
{
    void setNext(Handler handler);
    void handle(Type_of_Diet type, List<One_Meal> dayMeals);
    void Explain(Type_of_Diet type);
}
