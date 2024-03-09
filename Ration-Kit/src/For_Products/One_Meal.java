package For_Products;

import For_Products.Product.Product;
import Human.Human;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class One_Meal implements Iterable<Product>{

    public float kilocalories, protein, fats, carbohydrates;
    public float max_protein, max_fats, max_carbohydrates;


    public Type_Of_Meal typeOfMeal;
    public List<Product> products = new LinkedList<>();

    public enum Type_Of_Meal {
        Breakfast,
        Lunch,
        Dinner;
    }

    public void Create_Meal(List<Product> _basic_product, List<Product> _garnish_product, List<Product> _adition_product, Human _Human){
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

        INT_Rand = rand.nextInt(_basic_product.size());
        _product = _basic_product.get(INT_Rand);
        count_gramm_product = (rand.nextFloat(max_protein * 0.85f) + max_protein*0.8f)/(_product.protein/100);
        _product.cur_count_gramm = count_gramm_product;
        AddProduct(_product);
        Calculate_PFC(products);

        INT_Rand = rand.nextInt(_garnish_product.size());
        _product = _garnish_product.get(INT_Rand);
        count_gramm_product = (rand.nextFloat(max_carbohydrates * 0.85f) + max_carbohydrates*0.8f)/(_product.carbohydrates/100);
        _product.cur_count_gramm = count_gramm_product;
        AddProduct(_product);
        Calculate_PFC(products);

        /*
        INT_Rand = rand.nextInt(_adition_product.size());
        _product = _adition_product.get(INT_Rand);
        count_gramm_product = (rand.nextFloat(max_protein * 0.85f) + max_protein*0.8f)/(_product.protein/100);
        protein +=  _product.protein * count_gramm_product;
        fats +=  _product.fats * count_gramm_product;
        carbohydrates +=  _product.carbohydrates * count_gramm_product;
        */






    }

    void Calculate_PFC(List<Product> productList){
        protein =0;
        fats=0;
        carbohydrates=0;
        kilocalories =0;
        for (int i=0; i<productList.size(); i++){
            protein += productList.get(i).protein* productList.get(i).cur_count_gramm ;
            fats +=  productList.get(i).fats* productList.get(i).cur_count_gramm ;
            carbohydrates +=  productList.get(i).carbohydrates* productList.get(i).cur_count_gramm ;
        }

        kilocalories = protein*4 + carbohydrates*4+ fats*4;
    }

    public void SetTypeMeal(Type_Of_Meal _type){
        typeOfMeal = _type;
    }


    public void AddProduct(Product product)
    {
        products.add(product);
    }
    public void RemoveProduct(Product product)
    {
        products.remove(product);
    }

    Product Copy_Product_From_List_To_Product(){
        Product _product = new Product();


        return _product;
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
