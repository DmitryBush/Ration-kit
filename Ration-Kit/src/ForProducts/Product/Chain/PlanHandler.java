package ForProducts.Product.Chain;

import Database.Directory;
import ForProducts.Meal.OneMeal;
import ForProducts.Meal.Visitor.MealVisitor;
import ForProducts.Product.TypeofDiet;

import java.util.List;
import java.util.Objects;

public abstract class PlanHandler implements Handler
{
    private final TypeofDiet type;
    private Handler nextChain;

    public PlanHandler(TypeofDiet type)
    {
        this.type = type;
    }
    @Override
    public void setNext(Handler handler) {nextChain = handler;}
    @Override
    public void handle(TypeofDiet type, List<OneMeal> dayMeals, Directory directory,
                       MealVisitor mealVisitor, List<Thread> threads)
    {
        if (this.type.ordinal() == type.ordinal())
            CreatePlan(dayMeals, directory,mealVisitor, threads);

        if (Objects.nonNull(nextChain))
            nextChain.handle(type, dayMeals, directory, mealVisitor,threads);
    }
    @Override
    public void Explain(TypeofDiet type)
    {
        if (this.type.ordinal() == type.ordinal())
            Describe();

        if (Objects.nonNull(nextChain))
            nextChain.Explain(type);
    }
    protected abstract void CreatePlan(List<OneMeal> dayMeals, Directory directory,
                                       MealVisitor mealVisitor, List<Thread> threads);
    protected abstract void Describe();
}
