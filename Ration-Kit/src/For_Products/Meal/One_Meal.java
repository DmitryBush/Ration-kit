package For_Products.Meal;

import Database.Directory;
import For_Products.Meal.Visitor.MealVisitor;
import For_Products.Product.Product;
import For_Products.Product.Type_Product;
import For_Products.Product.Type_of_Diet;
import Human.Human;

import java.util.*;

public abstract class One_Meal implements Iterable<Product>
{
    private float kilocalories, protein, fats, carbohydrates;
    private float max_protein, max_fats, max_carbohydrates, max_kilocalories;
    private List<Product> products = new ArrayList<>();

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
            protein += products.get(i).getProtein() /100  * products.get(i).getCur_count_gramm();
            fats +=  products.get(i).getFats() /100 * products.get(i).getCur_count_gramm();
            carbohydrates +=  products.get(i).getCarbohydrates() /100 * products.get(i).getCur_count_gramm();
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
            for (int i=0; i<products.size();i++){
                if (products.get(i).getType_product() == Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f,
                            max_carbohydrates*0.8f)/(products.get(i).getCarbohydrates() /100));
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
                    Calculate_PFC();
                }
                else if (products.get(i).getType_product() == Type_Product.Basic){
                    product_gramm = (max_protein - protein) / (products.get(i).getProtein() /100);
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
                    Calculate_PFC();
                }
                else if (products.get(i).getType_product() == Type_Product.Addition){
                    product_gramm = (max_kilocalories-kilocalories) /
                            ((products.get(2).getProtein() *4 /100)
                                    + (products.get(i).getCarbohydrates() *4 /100)
                                    + (products.get(i).getFats() *9 /100));
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
                    Calculate_PFC();
                }
            }
        }
        else {
            for (int i=0; i<products.size();i++){
                if (products.get(i).getType_product() == Type_Product.Basic){
                    product_gramm =(rand.nextFloat(max_protein*0.9f,
                            max_protein*0.95f)/(products.get(i).getProtein() /100));
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
                    Calculate_PFC();
                }

                else if (products.get(i).getType_product() == Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f, max_carbohydrates*0.8f)
                            /(products.get(i).getCarbohydrates() /100));
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
                    Calculate_PFC();
                }

                else if (products.get(i).getType_product() == Type_Product.Addition){
                    product_gramm = (max_kilocalories-kilocalories) / ((products.get(2).getProtein() *4 /100)
                            + (products.get(i).getCarbohydrates() *4 /100) + (products.get(i).getFats() *9 /100));
                    if(product_gramm > products.get(i).getMax_gramm()){
                        product_gramm = products.get(i).getMax_gramm();
                    }
                    products.get(i).setCur_count_gramm(product_gramm);
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

            for(int i =0; i<meals_in_day.size(); i++){
                for (int j=0; j< meals_in_day.get(i).products.size(); j++){
                    if (Objects.equals(product, meals_in_day.get(i).products.get(j))){
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

    @Override
    public String toString() {
        return  "Общее количество белка за приём пищи: " + protein + "\n" +
                "Общее количество Жиров за приём пищи: " + fats + "\n" +
                "Общее количество Углеводов за приём пищи: " + carbohydrates + "\n" +
                "Общее количество ккал за приём пищи: " + kilocalories + "\n\n\n";
    }
}
