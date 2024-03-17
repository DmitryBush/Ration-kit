package For_Products;

import For_Products.Product.Product;
import Human.Human;

import java.util.*;

public class One_Meal implements Iterable<Product>{

    public float kilocalories, protein, fats, carbohydrates;
    public float max_protein, max_fats, max_carbohydrates, max_kilocalories;


    public Type_Of_Meal typeOfMeal;
    public List<Product> products = new ArrayList<>();

    public enum Type_Of_Meal {
        Breakfast,
        Lunch,
        Dinner;
    }


    public One_Meal(Type_Of_Meal type)
    {
        this.typeOfMeal = type;
    }

    public void Create_Meal(List<Product> _basic_product, List<Product> _garnish_product,
                            List<Product> _adition_product, Human _Human, List<One_Meal> meals_in_day){
        Random rand = new Random();
        int INT_Rand;
        Product _product = new Product();
        float count_gramm_product;

        switch (typeOfMeal){
            case Breakfast:
                max_fats = _Human.getFats() * 0.3f;
                max_carbohydrates = _Human.getCarbohydrates() * 0.3f;
                max_protein = _Human.getProtein()* 0.3f;
                break;
            case Lunch:
                max_fats = _Human.getFats() * 0.4f;
                max_carbohydrates = _Human.getCarbohydrates()* 0.4f;
                max_protein = _Human.getProtein()* 0.4f;
                break;
            case Dinner:
                max_fats = _Human.getFats()* 0.3f;
                max_carbohydrates = _Human.getCarbohydrates()* 0.3f;
                max_protein = _Human.getProtein()* 0.3f;
                break;
        }

        max_kilocalories = max_protein*4 + max_carbohydrates*4 + max_fats*9;

        AddProduct(Check_On_New_Product(_garnish_product,  meals_in_day));
        AddProduct(Check_On_New_Product(_basic_product,  meals_in_day));
        AddProduct(Check_On_New_Product(_adition_product,  meals_in_day));
        Balance_Products_In_Meal( _basic_product, _garnish_product, _adition_product, meals_in_day);
        Calculate_PFC(products);
    }



    void Calculate_PFC(List<Product> productList){
        protein =0;
        fats=0;
        carbohydrates=0;
        kilocalories = 0;
        for (int i=0; i<productList.size(); i++){
            protein += productList.get(i).protein/100  * productList.get(i).cur_count_gramm ;
            fats +=  productList.get(i).fats/100 * productList.get(i).cur_count_gramm ;
            carbohydrates +=  productList.get(i).carbohydrates/100 * productList.get(i).cur_count_gramm ;
        }

        kilocalories = protein*4 + carbohydrates*4+ fats*4;
    }

    void Balance_Products_In_Meal(List<Product> _basic_product, List<Product> _garnish_product,
                                  List<Product> _adition_product, List<One_Meal> meals_in_day){
        Random rand  = new Random();
        float product_gramm = 0;
        protein =0;
        fats=0;
        carbohydrates=0;
        kilocalories =0;

        if(!Check_on_Special_Diet(meals_in_day)){
            System.out.println("Standart");
            for (int i=0; i<products.size();i++){
                if (products.get(i).Type_product == Product.Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f, max_carbohydrates*0.8f)/(products.get(i).carbohydrates/100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC(products);
                }
                else if (products.get(i).Type_product == Product.Type_Product.Basic){
                    product_gramm = (max_protein - protein) / (products.get(i).protein/100);
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC(products);
                }
                else if (products.get(i).Type_product == Product.Type_Product.Adition){
                    product_gramm = (max_kilocalories-kilocalories) / ((products.get(2).protein *4 /100) +  (products.get(i).carbohydrates *4 /100) + (products.get(i).fats *9 /100));;
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC(products);
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
                    Calculate_PFC(products);
                }

                else if (products.get(i).Type_product == Product.Type_Product.Garnish){
                    product_gramm =(rand.nextFloat(max_carbohydrates*0.7f, max_carbohydrates*0.8f)/(products.get(i).carbohydrates/100));
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC(products);
                }

                else if (products.get(i).Type_product == Product.Type_Product.Adition){
                    product_gramm = (max_kilocalories-kilocalories) / ((products.get(2).protein *4 /100) +  (products.get(i).carbohydrates *4 /100) + (products.get(i).fats *9 /100));;
                    if(product_gramm > products.get(i).max_gramm){
                        product_gramm =  products.get(i).max_gramm;
                    }
                    products.get(i).cur_count_gramm = product_gramm;
                    Calculate_PFC(products);
                }
            }
        }


    }

    public void SetTypeMeal(Type_Of_Meal _type){
        typeOfMeal = _type;
    }
    public Type_Of_Meal GetTypeMeal(){
        return typeOfMeal;
    }
    public void AddProduct(Product product)
    {
        products.add(product);
    }

    public void RemoveProduct(Product product)
    {
        products.remove(product);
    }

    Product Check_On_New_Product(List<Product> list_product,  List<One_Meal> meals_in_day){
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

    Boolean Check_on_Special_Diet(List<One_Meal> mealList){
        for(int i=0; i< mealList.size(); i++){
            if(mealList.get(i).GetTypeMeal() == Type_Of_Meal.Breakfast){
                return false;
            }
        }
        return true;
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



}
