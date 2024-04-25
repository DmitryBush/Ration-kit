package ForProducts.Product.Chain;

import ForProducts.Meal.One_Meal;
import ForProducts.Product.Type_of_Diet;

import java.util.List;
import java.util.Objects;

public abstract class PlanHandler implements Handler
{
    private final Type_of_Diet type;
    private Handler nextChain;

    public PlanHandler(Type_of_Diet type)
    {
        this.type = type;
    }
    @Override
    public void setNext(Handler handler) {nextChain = handler;}
    @Override
    public void handle(Type_of_Diet type, List<One_Meal> dayMeals)
    {
        if (this.type.ordinal() == type.ordinal())
            CreatePlan(dayMeals);

        if (Objects.nonNull(nextChain))
            nextChain.handle(type, dayMeals);
    }
    @Override
    public void Explain(Type_of_Diet type)
    {
        if (this.type.ordinal() == type.ordinal())
            Describe();

        if (Objects.nonNull(nextChain))
            nextChain.Explain(type);
    }
    protected abstract void CreatePlan(List<One_Meal> dayMeals);
    protected abstract void Describe();
}
