package For_Products;

import java.util.List;

public interface Builder_Meal {


    public void Add_Products_In_Meal();
    public void Calculate_P_F_C(List<Product> _ProductList);

    public void Compilation_Best_List_Products();

    public Builder_Meal Set_Type_Of_Meal(One_Meal.Type_Of_Meal typeOfMeal);
    public One_Meal Build_Meal();

}
