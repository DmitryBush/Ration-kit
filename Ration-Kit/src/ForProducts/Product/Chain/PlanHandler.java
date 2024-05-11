package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.One_Meal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.Type_of_Diet;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

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
    public void handle(Type_of_Diet type, List<One_Meal> dayMeals, Directory directory,
                       MealVisitor mealVisitor,List<Thread> threads)
    {
        if (this.type.ordinal() == type.ordinal())
            CreatePlan(dayMeals, directory,mealVisitor, threads);

        if (Objects.nonNull(nextChain))
            nextChain.handle(type, dayMeals, directory, mealVisitor,threads);
    }
    @Override
    public void Explain(Type_of_Diet type)
    {
        if (this.type.ordinal() == type.ordinal())
            Describe();

        if (Objects.nonNull(nextChain))
            nextChain.Explain(type);
    }
    protected abstract void CreatePlan(List<One_Meal> dayMeals, Directory directory,
                                       MealVisitor mealVisitor, List<Thread> threads);
    protected abstract void Describe();
}
