package For_Products;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class One_Meal implements Iterable<Product>{

    private float kilocalories, protein, fats, carbohydrates;
    Type_Of_Meal typeOfMeal;
    List<Product> products = new LinkedList<>();

    public enum Type_Of_Meal {
        Breakfast,
        Lunch,
        Dinner;
    }

    public void AddProduct(Product product)
    {
        products.add(product);
    }
    public void RemoveProduct(Product product)
    {
        products.remove(product);
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
