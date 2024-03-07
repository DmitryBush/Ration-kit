package For_Products;
import For_Products.Product.Product;

import java.util.LinkedList;
import java.util.List;

public class Builder_Meal_Class implements Builder_Meal {

    One_Meal meal = new One_Meal();
    List<Product> _ProductList = new LinkedList<Product>();

    @Override
    public void Add_Products_In_Meal() {   // вносим наилучший вариант продуктов в приём пищи, подобранных в Compilation_Best_List_Products


    }

    @Override
    public void Calculate_P_F_C(List<Product> _productlist) {   // подсчет текущего количества бжу и ккал

    }

    @Override
    public void Compilation_Best_List_Products() {     //перебираем рандомные наилучшие продукты для приёма пищи



    }

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
