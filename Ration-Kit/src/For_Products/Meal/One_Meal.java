package For_Products.Meal;

import Database.Directory;
import For_Products.Meal.Visitor.MealVisitor;
import For_Products.Product.Product;
import For_Products.Product.Type_of_Diet;
import Human.Human;

import java.util.*;

public abstract class One_Meal implements Iterable<Product>
{
    private float kilocalories, protein, fats, carbohydrates;
    protected float max_protein, max_fats, max_carbohydrates, max_kilocalories;


    //protected Type_Of_Meal typeOfMeal;
    protected List<Product> products = new ArrayList<>();


//    public One_Meal(Type_Of_Meal type)
//    {
//        this.typeOfMeal = type;
//    }
    public abstract void Create_Meal(Directory directory, List<One_Meal> meals_in_day, MealVisitor mealVisitor);

    protected void CreatePlan(Directory directory, List<One_Meal> meals_in_day)
    {
        AddProduct(Check_On_New_Product(directory.getGarnish_Products(),  meals_in_day));
        AddProduct(Check_On_New_Product(directory.getBasic_Products(),  meals_in_day));
        AddProduct(Check_On_New_Product(directory.getAddition_Products(),  meals_in_day));
        Balance_Products_In_Meal();
        Calculate_PFC();
    }

    void Calculate_PFC(){
        protein =0;
        fats=0;
        carbohydrates=0;

        for (int i=0; i<products.size(); i++){
            protein += products.get(i).protein/100  * products.get(i).cur_count_gramm ;
            fats +=  products.get(i).fats/100 * products.get(i).cur_count_gramm ;
            carbohydrates +=  products.get(i).carbohydrates/100 * products.get(i).cur_count_gramm ;
        }

        kilocalories = protein*4 + carbohydrates*4+ fats*4;
    }

    void Balance_Products_In_Meal(){
        Random rand  = new Random();
        float product_gramm = 0;
        protein =0;
        fats=0;
        carbohydrates=0;
        kilocalories =0;

        if(!Check_on_Special_Diet(Human.GetInstance().getTypeDiet())){
            System.out.println("Standart");
            for (int i=0; i<products.size();i++){
                if (products.get(i).Type_product == Product.Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f,
                            max_carbohydrates*0.8f)/(products.get(i).carbohydrates/100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }
                else if (products.get(i).Type_product == Product.Type_Product.Basic){
                    product_gramm = (max_protein - protein) / (products.get(i).protein/100);
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }
                else if (products.get(i).Type_product == Product.Type_Product.Adition){
                    product_gramm = (max_kilocalories-kilocalories) / ((products.get(2).protein *4 /100) +  (products.get(i).carbohydrates *4 /100) + (products.get(i).fats *9 /100));;
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }
            }
        }
        else {
            for (int i=0; i<products.size();i++){
                if (products.get(i).Type_product == Product.Type_Product.Basic){
                    product_gramm =(rand.nextFloat(max_protein*0.9f, max_protein*0.95f)/(products.get(i).protein/100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }

                else if (products.get(i).Type_product == Product.Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f, max_carbohydrates*0.8f)/(products.get(i).carbohydrates/100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }

                else if (products.get(i).Type_product == Product.Type_Product.Adition){
                    product_gramm = (max_kilocalories-kilocalories) / ((products.get(2).protein *4 /100)
                            + (products.get(i).carbohydrates *4 /100) + (products.get(i).fats *9 /100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC();
                }
            }
        }
    }
    public void AddProduct(Product product)
    {
        products.add(product);
    }

    public void RemoveProduct(Product product)
    {
        products.remove(product);
    }

    Product Check_On_New_Product(List<Product> list_product, List<One_Meal> meals_in_day){
        boolean new_product = true;
        Random rand = new Random();
        Product product;
        int Rand;

        while (true){
            Rand = rand.nextInt(list_product.size());
            product = list_product.get(Rand);
           // System.out.println(product.Name);

            for(int i =0; i<meals_in_day.size(); i++){
                for (int j=0; j< meals_in_day.get(i).products.size(); j++){
                    if (product.Name == meals_in_day.get(i).products.get(j).Name){
                        new_product = false;
                        Rand = rand.nextInt(list_product.size());
                        product = list_product.get(Rand);
                        i = 0;
                        j = 0;
                    }
                    else{
                        new_product = true;
                    }
                }
            }
            if(new_product){
                break;
            }
        }
        return product;
    }

    Boolean Check_on_Special_Diet(Type_of_Diet diet)
    {
        return diet != Type_of_Diet.diet_regular && diet != Type_of_Diet.diet_16_8;
    }

    @Override
    public Iterator<Product> iterator()
    {
        return new Iterator<>()
        {
            private Iterator<Product> iter = products.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Product next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
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
//    public Type_Of_Meal getTypeOfMeal() {
//        return typeOfMeal;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setMax_protein(float max_protein) {
        this.max_protein = max_protein;
    }

    public void setMax_fats(float max_fats) {
        this.max_fats = max_fats;
    }

    public void setMax_carbohydrates(float max_carbohydrates) {
        this.max_carbohydrates = max_carbohydrates;
    }

    public void setMax_kilocalories(float max_kilocalories) {
        this.max_kilocalories = max_kilocalories;
    }

    //    private Type_Of_Meal typeOfMeal;
    //    public Breakfast(Type_Of_Meal type)
    //    {
    //        typeOfMeal = type;
    //    }
}
